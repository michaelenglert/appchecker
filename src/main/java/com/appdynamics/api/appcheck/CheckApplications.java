package com.appdynamics.api.appcheck;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

import java.io.IOException;
import java.util.List;

public class CheckApplications {

    private static ConfigReader configReader = new ConfigReader();
    private static Config config = configReader.getConfig();
    private static FileOperations fileOps = new FileOperations();
    private static String apps;

    private static void getApplications () throws Exception {
        ClientResponse response;

        response = Rest.doGet(config.getTenantUser(), config.getTenant(), config.getTenantPassword(),
                config.getController() + "/controller/rest/applications?output=JSON");

        apps = response.getEntity(String.class);
    }

    private static void doCompare() throws IOException {
        if(fileOps.fileExists(config.getAppFile())){
            if(apps.equals(fileOps.readFile())) {
                System.out.println("Same same");
            }
            else {
                List<Apps> appListOld;
                List<Apps> appListNew;
                ObjectMapper objectMapper = new ObjectMapper();
                TypeReference<List<Apps>> mapType = new TypeReference<List<Apps>>() {};
                appListNew = objectMapper.readValue(apps, mapType);
                appListOld = objectMapper.readValue(fileOps.readFile(), mapType);
            }
        }
        else {
            fileOps.writeFile(apps);
        }
    }

    public static void main(String[] args)
    {
        try {
            getApplications();
            doCompare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
