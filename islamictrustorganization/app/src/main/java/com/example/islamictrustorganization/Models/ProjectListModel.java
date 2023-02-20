package com.example.islamictrustorganization.Models;

public class ProjectListModel {

    private String imgProjectLogoURL;
    private String projectName;
    private String projectDonation;
    private int projectID;

    public ProjectListModel() {
    }

    public ProjectListModel(String imgProjectLogoURL, String projectName, String projectCost, int projectID) {
        this.imgProjectLogoURL = imgProjectLogoURL;
        this.projectName = projectName;
        this.projectDonation = projectCost;
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

    public String getProjectDonation() {
        return projectDonation;
    }

    public void setProjectDonation(String projectCost) {
        this.projectDonation = projectCost;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
