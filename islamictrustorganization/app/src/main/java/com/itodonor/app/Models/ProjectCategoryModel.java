package com.itodonor.app.Models;

public class ProjectCategoryModel {
    private int projectCategoryID;
    private String projectCategoryName;

    public ProjectCategoryModel() {
    }
    public ProjectCategoryModel(int projectCategoryID, String projectCategoryName) {
        this.projectCategoryID = projectCategoryID;
        this.projectCategoryName = projectCategoryName;
    }

    public int getProjectCategoryID() {
        return projectCategoryID;
    }

    public void setProjectCategoryID(int projectCategoryID) {
        this.projectCategoryID = projectCategoryID;
    }

    public String getProjectCategoryName() {
        return projectCategoryName;
    }

    public void setProjectCategoryName(String projectCategoryName) {
        this.projectCategoryName = projectCategoryName;
    }
}
