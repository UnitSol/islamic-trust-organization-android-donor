package com.itodonor.app.Models;

public class ProjectTypeModel {
    private int projectTypeID;
    private String projectTypeName;

    public ProjectTypeModel() {

    }

    public ProjectTypeModel(int projectTypeID, String projectTypeName) {
        this.projectTypeID = projectTypeID;
        this.projectTypeName = projectTypeName;
    }

    public int getProjectTypeID() {
        return projectTypeID;
    }

    public void setProjectTypeID(int projectTypeID) {
        this.projectTypeID = projectTypeID;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }
}
