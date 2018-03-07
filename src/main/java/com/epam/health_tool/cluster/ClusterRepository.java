package com.epam.health_tool.cluster;

import com.epam.health_tool.authenticate.impl.ClusterCredentials;
import com.epam.health_tool.model.Cluster;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClusterRepository {
    private ObjectMapper objectMapper;

    @PostConstruct
    public void postInit() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Cluster> findClustersList() {
        try {
            //return objectMapper.readValue( getClass().getClassLoader().getResourceAsStream( "clusters.json" ), new TypeReference<List<Cluster>>(){});
            String fileName = Paths.get(getClass().getClassLoader().getResource("clusters.json").toURI()).toString();

            JsonReader reader = new JsonReader(new FileReader(fileName));
            Type listType = new TypeToken<ArrayList<ClusterCredentials>>(){}.getType();
            ArrayList<ClusterCredentials> clusterListObject = new Gson().fromJson(reader, listType);
            return clusterListObject.stream().map(ClusterCredentials::getCluster).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println( e.getMessage() );
            return null;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
