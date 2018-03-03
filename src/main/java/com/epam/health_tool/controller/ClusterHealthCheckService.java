package com.epam.health_tool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Vasilina_Terehova on 3/3/2018.
 */
@Controller
public class ClusterHealthCheckService {

    @RequestMapping("/getClusterStatus")
    public
    @ResponseBody
    ClusterHealthStatus greeting(@RequestParam(value = "clusterName", required = false, defaultValue = "cdh513") String clusterName, Model model) {
        model.addAttribute("clusterName", clusterName);
        return new ClusterHealthStatus("cdh513", true);
        //return "greeting";
    }

}