package com.appdynamics.api.appcheck;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
    private String systemTenant;
    private String systemTenantUser;
    private String systemTenantPassword;
    private int applicationId;

    private String tenant;
    private String tenantUser;
    private String tenantPassword;

    private String severity;
    private String customeventType;
    private String eventType;

    private String appFile;
    private String controller;

    public String getSystemTenant() {
        return systemTenant;
    }

    public String getCustomeventType() {
        return customeventType;
    }

    public String getEventType() {
        return eventType;
    }

    public String getSeverity() {
        return severity;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public String getSystemTenantPassword() {
        return systemTenantPassword;
    }

    public String getSystemTenantUser() {
        return systemTenantUser;
    }

    public String getTenant() {
        return tenant;
    }

    public String getTenantPassword() {
        return tenantPassword;
    }

    public String getTenantUser() {
        return tenantUser;
    }

    public String getAppFile() {
        return appFile;
    }

    public String getController() {
        return controller;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public void setCustomeventType(String customeventType) {
        this.customeventType = customeventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setSystemTenant(String systemTenant) {
        this.systemTenant = systemTenant;
    }

    public void setSystemTenantPassword(String systemTenantPassword) {
        this.systemTenantPassword = systemTenantPassword;
    }

    public void setSystemTenantUser(String systemTenantUser) {
        this.systemTenantUser = systemTenantUser;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public void setTenantPassword(String tenantPassword) {
        this.tenantPassword = tenantPassword;
    }

    public void setTenantUser(String tenantUser) {
        this.tenantUser = tenantUser;
    }

    public void setAppFile(String appFile) {
        this.appFile = appFile;
    }

    public void setController(String controller) {
        this.controller = controller;
    }
}
