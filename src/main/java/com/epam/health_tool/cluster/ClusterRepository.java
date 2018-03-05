package com.epam.health_tool.cluster;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Repository
public class ClusterRepository {
    private ObjectMapper objectMapper;

    @PostConstruct
    public void postInit() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Cluster> findClustersList() {
        try {
            return objectMapper.readValue( getClass().getClassLoader().getResourceAsStream( "clusters.json" ), new TypeReference<List<Cluster>>(){});
        } catch (IOException e) {
            System.out.println( e.getMessage() );
            return null;
        }
    }
}
