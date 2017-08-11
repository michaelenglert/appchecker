package com.appdynamics.api.appcheck;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class ConfigReader {

    private Config config;

    public void getConfig() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            config = mapper.readValue(new File("src/main/resources/config.yml"), Config.class);
        } catch (IOException e) {
            e.getMessage();
        }
        checkConfig();
        printConfig();
    }

    private void checkConfig() {
        if(config.getCustomeventType() == null || config.getCustomeventType().isEmpty()){
            config.setCustomeventType(Globals.customeventType);
        }
        if(config.getSystemTenant() == null || config.getSystemTenant().isEmpty()){
            config.setSystemTenant(Globals.systemTenant);
        }
        if(config.getSeverity() == null || config.getSeverity().isEmpty()){
            config.setSeverity(Globals.severity);
        }
        if(config.getTenant() == null || config.getTenant().isEmpty()){
            config.setTenant(Globals.tenant);
        }
        if(config.getSystemTenantUser() == null || config.getSystemTenantUser().isEmpty()){
            config.setSystemTenantUser(Globals.systemTenantUser);
        }
        if(config.getEventType() == null || config.getEventType().isEmpty()){
            config.setEventType(Globals.eventType);
        }
        if(config.getApplicationId() == 0){
            config.setApplicationId(Globals.applicationId);
        }
    }

    private void printConfig() {
        System.out.println(config.getApplicationId());
        System.out.println(config.getCustomeventType());
        System.out.println(config.getEventType());
        System.out.println(config.getSeverity());
        System.out.println(config.getSystemTenant());
        System.out.println(config.getSystemTenantPassword());
        System.out.println(config.getSystemTenantUser());
        System.out.println(config.getTenant());
        System.out.println(config.getTenantUser());
        System.out.println(config.getTenantPassword());
    }
}
