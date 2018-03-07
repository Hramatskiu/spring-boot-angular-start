package com.epam.health_tool.authenticate;

import com.epam.health_tool.authenticate.impl.ClusterCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by Vasilina_Terehova on 3/7/2018.
 */
@Component
public class Authenticator {

    @Autowired
    AuthenticationManager authenticationManager;

    public void authenticate(ClusterCredentials clusterCredentials) {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
        SecurityContextHolder.getContext()
                .setAuthentication(authenticationManager.authenticate(
                        clusterCredentials));
    }
}
