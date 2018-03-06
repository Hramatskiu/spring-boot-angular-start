package com.epam.health_tool.controller;

import com.epam.health_tool.authenticate.impl.BaseConfigLoadAuthentication;
import com.epam.health_tool.authenticate.impl.HttpCredentials;
import com.epam.health_tool.authenticate.impl.Krb5Credentials;
import com.epam.health_tool.authenticate.impl.SshCredentials;
import com.epam.health_tool.util.CommonUtilException;
import com.epam.health_tool.util.CommonUtilHolder;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by Vasilina_Terehova on 3/3/2018.
 */
@Controller
public class ClusterHealthCheckService {

    @Autowired
    CommonUtilHolder commonUtilHolder;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping("/getClusterStatus")
    public
    @ResponseBody
    ClusterHealthStatus greeting(@RequestParam(value = "clusterName", required = false, defaultValue = "cdh513") String clusterName, Model model) throws CommonUtilException, IOException, AuthenticationException {
        authenticate();
        HttpUriRequest httpUriRequest = CommonUtilHolder.httpCommonUtilInstance().createHttpUriRequest("http://svqxbdcn6cdh513n1.pentahoqa.com:7180/api/v18/clusters/CDH513Unsecure/services");
        System.out.println(EntityUtils.toString(CommonUtilHolder.httpCommonUtilInstance().createHttpClient()
                .execute(httpUriRequest).getEntity()));
        model.addAttribute("clusterName", clusterName);
        return new ClusterHealthStatus("cdh513", true);
        //return "greeting";
    }

    private void authenticate() {
        SecurityContextHolder.setStrategyName( SecurityContextHolder.MODE_GLOBAL );
        SecurityContextHolder.getContext()
                .setAuthentication( authenticationManager.authenticate(
                        new BaseConfigLoadAuthentication( new HttpCredentials("admin", "admin"),
                                new Krb5Credentials("devuser@PENTAHOQA.COM", "password"), new SshCredentials("devuser", "password")) ) );
    }

}