package com.espressif.ui.models;

public class Project {
    private String GAAProjectRef;
    private String GAAProjectName;

    public String getGAAProjectRef() {
        return GAAProjectRef;
    }

    public void setGAAProjectRef(String GAAProjectRef) {
        this.GAAProjectRef = GAAProjectRef;
    }

    public String getGAAProjectName() {
        return GAAProjectName;
    }

    public void setGAAProjectName(String GAAProjectName) {
        this.GAAProjectName = GAAProjectName;
    }

    public Project(String GAAProjectRef, String GAAProjectName) {
        this.GAAProjectRef = GAAProjectRef;
        this.GAAProjectName = GAAProjectName;
    }
}
