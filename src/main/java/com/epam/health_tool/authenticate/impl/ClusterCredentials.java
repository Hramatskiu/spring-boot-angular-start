package com.epam.health_tool.authenticate.impl;

import com.epam.health_tool.authenticate.BaseCredentialsToken;
import com.epam.health_tool.model.Cluster;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClusterCredentials extends BaseCredentialsToken {
    @JsonProperty("cluster")
    Cluster cluster;
    //@JsonProperty("http")
    HttpCredentials http;
    //@JsonProperty("kerberos")
    Krb5Credentials krb5Credentials;
    //@JsonProperty("ssh")
    SshCredentials ssh;

    public ClusterCredentials(HttpCredentials http, Krb5Credentials krb5Credentials,
                              SshCredentials sshCredentials) {
        this.http = http;
        this.krb5Credentials = krb5Credentials;
        this.ssh = sshCredentials;
    }

    public HttpCredentials getHttp() {
        return http;
    }

    public void setHttp(HttpCredentials http) {
        this.http = http;
    }

    public Krb5Credentials getKrb5Credentials() {
        return krb5Credentials;
    }

    public void setKrb5Credentials(Krb5Credentials krb5Credentials) {
        this.krb5Credentials = krb5Credentials;
    }

    public SshCredentials getSsh() {
        return ssh;
    }

    public void setSsh(SshCredentials ssh) {
        this.ssh = ssh;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
}
