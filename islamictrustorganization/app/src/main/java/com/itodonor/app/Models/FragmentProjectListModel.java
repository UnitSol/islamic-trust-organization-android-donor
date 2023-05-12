package com.itodonor.app.Models;

public class FragmentProjectListModel {

    private String imgProjectLogoURL;
    private String projectName;
    private String projectCost;
    private int projectID;

    public FragmentProjectListModel() {
    }

    public FragmentProjectListModel(String imgProjectLogoURL, String projectName, String projectCost, int projectID) {
        this.imgProjectLogoURL = imgProjectLogoURL;
        this.projectName = projectName;
        this.projectCost = projectCost;
        this.projectID = projectID;
    }

    public String getImgProjectLogoURL() {
        return imgProjectLogoURL;
    }

    public void setImgProjectLogoURL(String imgProjectLogoURL) {
        this.imgProjectLogoURL = imgProjectLogoURL;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCast() {
        return projectCost;
    }

    public void setProjectCast(String projectCost) {
        this.projectCost = projectCost;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
