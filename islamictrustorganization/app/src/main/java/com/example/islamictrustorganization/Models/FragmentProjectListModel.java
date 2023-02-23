package com.example.islamictrustorganization.Models;

public class FragmentProjectListModel {

    private String imgProjectLogoURL;
    private String projectName;
    private String projectCast;
    private int projectID;

    public FragmentProjectListModel() {
    }

    public FragmentProjectListModel(String imgProjectLogoURL, String projectName, String projectCost, int projectID) {
        this.imgProjectLogoURL = imgProjectLogoURL;
        this.projectName = projectName;
        this.projectCast = projectCost;
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
        return projectCast;
    }

    public void setProjectCast(String projectCost) {
        this.projectCast = projectCost;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
