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

    private static void checkFiles() throws IOException {
        if(fileOps.fileExists(config.getAppFile())){
            doSimpleCompare();
        }
        else {
            fileOps.writeFile(apps);
        }
    }

    private static void doSimpleCompare() throws IOException {
        if(apps.equals(fileOps.readFile())) {
            System.out.println("Same same");
        }
        else {
            doCompare();
        }
    }

    private static void doCompare() throws IOException {
        List<Apps> appListRest;
        List<Apps> appListFile;
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Apps>> mapType = new TypeReference<List<Apps>>() {};
        appListRest = objectMapper.readValue(apps, mapType);
        appListFile = objectMapper.readValue(fileOps.readFile(), mapType);

        if(appListRest.size() >= appListFile.size()){
            System.out.println("Stuff added");
            int i = 0;
            while (i < appListRest.size()) {
                if (appListFile.size() <= i){
                    System.out.println("Add: " + appListRest.get(i).getName());
                }
                else if(appListFile.get(i).getId() == appListRest.get(i).getId() ||
                        !appListFile.get(i).getName().equals(appListRest.get(i).getName())){
                    System.out.println("Changed: " + appListRest.get(i).getName());
                }
                i++;
            }
        }
        else {
            System.out.println("Stuff removed");
        }
    }

    public static void main(String[] args)
    {
        try {
            getApplications();
            checkFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
