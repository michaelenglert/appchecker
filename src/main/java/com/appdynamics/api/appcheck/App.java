package com.appdynamics.api.appcheck;

@SuppressWarnings("unused")
class App {
    private String name;
    private String description;
    private Integer id;
    public App(){}

    Integer getId() { return id; }

    public String getDescription() {
        return description;
    }

    String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
