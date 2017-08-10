package com.appdynamics.api.appcheck;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class CheckApplications {
    private Config readConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Config config = mapper.readValue(new File("src/main/resources/config.yml"), Config.class);
        return config;
    }

    public static void main(String[] args)
    {
        CheckApplications check = new CheckApplications();
        try {
            check.readConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
