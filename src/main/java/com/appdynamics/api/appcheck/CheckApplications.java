package com.appdynamics.api.appcheck;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.common.collect.SortedMapDifference;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

@SuppressWarnings("SpellCheckingInspection")
class CheckApplications {

    private static Config config;
    private static final FileOperations fileOps = new FileOperations();
    private static String appsCurrent;

    private static void getApplications () throws Exception {
        appsCurrent = Rest.doGet(config.getTenantUser(), config.getTenant(), config.getTenantPassword(),
                new URL(config.getController() + Globals.controllerGetApps));
    }

    private static TreeMap<Integer, String> getMap (String apps) {
        App[] appArr = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            appArr = objectMapper.readValue(apps, App[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TreeMap<Integer, String> appMap = new TreeMap<>();
        assert appArr != null;
        for (App app:appArr){
            appMap.put(app.getId(),app.getName());
        }
        return appMap;
    }

    private static void checkFiles() throws Exception {
        if(fileOps.fileExists(config.getAppFile())){
            doCompare();
            fileOps.writeFile(appsCurrent);
        }
        else {
            fileOps.writeFile(appsCurrent);
        }
    }

    private static void doCompare() throws Exception {
        TreeMap<Integer, String> appMapCurrent = getMap(appsCurrent);
        TreeMap<Integer, String> appMapBefore = getMap(fileOps.readFile());
        SortedMapDifference<Integer,String> diff = Maps.difference(appMapBefore,appMapCurrent);

        SortedMap changes = diff.entriesDiffering();
        SortedMap deleted = diff.entriesOnlyOnLeft();
        SortedMap added = diff.entriesOnlyOnRight();

        String message;

        URL url = new URL(config.getController() +
                "/controller/rest/applications/" + config.getApplicationId() +
                "/events?" +
                "severity=" + URLEncoder.encode(config.getSeverity(), Globals.urlEncoding) +
                "&eventtype=" + URLEncoder.encode(config.getEventType(), Globals.urlEncoding) +
                "&customeventtype=" + URLEncoder.encode(config.getCustomeventType(), Globals.urlEncoding));

        if (!changes.isEmpty()) {
            message = "Changed: " + changes.toString();
            Rest.doPost(config.getSystemTenantUser(), config.getSystemTenant(), config.getSystemTenantPassword(),
                    new URL(url + "&summary=" + URLEncoder.encode(message, Globals.urlEncoding)));
        }

        if (!deleted.isEmpty()) {
            message = "Deleted: " + deleted.toString();
            Rest.doPost(config.getSystemTenantUser(), config.getSystemTenant(), config.getSystemTenantPassword(),
                    new URL(url + "&summary=" + URLEncoder.encode(message, Globals.urlEncoding)));
        }

        if (!added.isEmpty()) {
            message = "Added: " + added.toString();
            Rest.doPost(config.getSystemTenantUser(), config.getSystemTenant(), config.getSystemTenantPassword(),
                    new URL(url + "&summary=" + URLEncoder.encode(message, Globals.urlEncoding)));
        }
    }

    public static void main(String[] args)
    {
        try {
            ConfigReader configReader = new ConfigReader();
            config = configReader.getConfig(args[0]);
            getApplications();
            checkFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
