package com.epam.health_tool.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClusterController {
    @Autowired
    private ClusterService clusterService;

    @GetMapping( "/clusters" )
    public ResponseEntity<List<Cluster>> getClustersList() {
        List<Cluster> clusters = clusterService.getClustersList();

        return clusters != null ? new ResponseEntity<List<Cluster>>( clusters, HttpStatus.OK ) : new ResponseEntity<List<Cluster>>(HttpStatus.NOT_FOUND );
    }
}
