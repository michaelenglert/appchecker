package com.appdynamics.api.appcheck;

public class Config {
    private String systemTenant;
    private String systemTenantUser;
    private String systemTenantPassword;
    private int applicationId;

    private String tenant;
    private String tenantUser;
    private String tenantPassword;

    private String severity;
    private String customeventtype;
    private String eventtype;

    public String getSystemTenant() {
        return systemTenant;
    }

    public String getCustomeventtype() {
        return customeventtype;
    }

    public String getEventtype() {
        return eventtype;
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

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public void setCustomeventtype(String customeventtype) {
        this.customeventtype = customeventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
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
}
