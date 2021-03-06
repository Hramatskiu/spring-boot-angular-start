package com.epam.health_tool.delegating.ssh;

import com.epam.health_tool.util.ByteCopierUtil;
import com.jcraft.jsch.*;
import org.apache.commons.lang.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class DelegatingSshSession implements Closeable {
  private Session session;

  public DelegatingSshSession( String user, String host, int port, String password, String identityPath )
    throws IOException {
    session = createSession( user, host, port, password, identityPath );
  }

  public String downloadFile( String sourcePath ) {
    StringBuilder commandResult = new StringBuilder( StringUtils.EMPTY );
    Channel channel = null;

    try {
      channel = session.openChannel( "sftp" );
      channel.connect();

      SftpATTRS sftpATTRS = ( (ChannelSftp) channel ).stat( sourcePath );
      InputStream in = ( (ChannelSftp) channel ).get( sourcePath );
      int size = sftpATTRS.getSize() > 1024 ? 256 : (int) sftpATTRS.getSize();
      byte[] tmp = new byte[ size ];
      int i;
      while ( ( i = in.read( tmp, 0, size ) ) > 0 ) {
        commandResult.append( new String( tmp, 0, i ) );
      }
    } catch ( JSchException | IOException | SftpException ex ) {
      ex.printStackTrace();
    } finally {
      if ( channel != null ) {
        channel.disconnect();
      }
    }

    return commandResult.toString();
  }

  public byte[] downloadFileAsByteArray( String sourcePath ) {
    byte[] commandResult = null;
    Channel channel = null;

    try {
      channel = session.openChannel( "sftp" );
      channel.connect();

      SftpATTRS sftpATTRS = ( (ChannelSftp) channel ).stat( sourcePath );
      InputStream in = ( (ChannelSftp) channel ).get( sourcePath );
      int size = sftpATTRS.getSize() > 1024 ? 256 : (int) sftpATTRS.getSize();
      byte[] tmp = new byte[ size ];
      int i;
      while ( ( i = in.read( tmp, 0, size ) ) > 0 ) {
        commandResult = ByteCopierUtil.addBytesToArray( commandResult, tmp, i );
      }
    } catch ( JSchException | IOException | SftpException ex ) {
      ex.printStackTrace();
    } finally {
      if ( channel != null ) {
        channel.disconnect();
      }
    }

    return commandResult;
  }

  public String executeCommand( String command ) {
    StringBuilder commandResult = new StringBuilder( StringUtils.EMPTY );
    Channel channel = null;

    try {
      channel = session.openChannel( "exec" );
      ( (ChannelExec) channel ).setCommand( command.trim() );

      channel.setInputStream( null );
      ( (ChannelExec) channel ).setErrStream( System.err );
      InputStream in = channel.getInputStream();
      channel.connect();
      byte[] tmp = new byte[ 10024 ];
      while ( true ) {
        while ( in.available() > 0 ) {
          int i = in.read( tmp, 0, 5024 );
          if ( i < 0 ) {
            break;
          }
          commandResult.append( new String( tmp, 0, i ) );
        }
        if ( channel.isClosed() ) {
          if ( in.available() > 0 ) {
            continue;
          }
          break;
        }
        try {
          Thread.sleep( 100 );
        } catch ( Exception ex ) {
          System.out.println( ex.getMessage() );
        }
      }
    } catch ( JSchException | IOException ex ) {
      ex.printStackTrace();
    } finally {
      if ( channel != null ) {
        channel.disconnect();
      }
    }

    return commandResult.toString();
  }

  @Override
  public void close() throws IOException {
    session.disconnect();
  }

  private Session createSession( String user, String host, int port, String password, String identityPath )
    throws IOException {
    try {
      JSch jsch = new JSch();
      if ( identityPath != null && !identityPath.isEmpty() ) {
        jsch.addIdentity( identityPath );
      }

      Session session = jsch.getSession( user, host, 22 );
      session.setConfig( "StrictHostKeyChecking", "no" );
      session.setConfig( "GSSAPIAuthentication", "yes" );
      session.setConfig( "GSSAPIDelegateCredentials", "no" );
      session.setConfig( "UseDNS", "no" );
      if ( identityPath == null || identityPath.isEmpty() ) {
        session.setPassword( password );
      }

      session.connect();

      return session;
    } catch ( JSchException ex ) {
      throw new IOException( ex );
    }
  }
}
