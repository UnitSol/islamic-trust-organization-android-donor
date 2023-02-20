package com.example.islamictrustorganization.Models;

public class ProjectDetailListModel {
    private String updateName;
    private String dateOfUpdate;
    private int updateID;

    public ProjectDetailListModel() {
    }
    public ProjectDetailListModel(String updateName, String dateOfUpdate, int updateID) {
        this.updateName = updateName;
        this.dateOfUpdate = dateOfUpdate;
        this.updateID = updateID;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfUpdate(String dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    public int getUpdateID() {
        return updateID;
    }

    public void setUpdateID(int updateID) {
        this.updateID = updateID;
    }
}
