package com.appdynamics.api.appcheck;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

class ConfigReader {

    private Config config;

    public Config getConfig(String configFile) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            config = mapper.readValue(new File(configFile), Config.class);
        } catch (IOException e) {
            e.getMessage();
        }
        checkConfig();
        return config;
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
}
