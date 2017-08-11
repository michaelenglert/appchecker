package com.appdynamics.api.appcheck;

public class Apps {
    public String description;
    public String id;
    public String name;

    public Apps() {
    }

    public Apps(String description, String id, String name) {
        this.description = description;
        this.id = id;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
