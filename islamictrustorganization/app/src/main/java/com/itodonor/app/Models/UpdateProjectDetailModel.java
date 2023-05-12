package com.itodonor.app.Models;

public class UpdateProjectDetailModel {
    private String updateName;
    private String updateDate;
    private String updateDescription;
    private int updateID;

    public UpdateProjectDetailModel() {
    }

    public String getUpdateDescription() {
        return updateDescription;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
    }

    public UpdateProjectDetailModel(String updateName, String updateDate, String updateDescription, int updateID) {
        this.updateName = updateName;
        this.updateDate = updateDate;
        this.updateDescription = updateDescription;
        this.updateID = updateID;
    }

    public int getUpdateID() {
        return updateID;
    }

    public void setUpdateID(int updateID) {
        this.updateID = updateID;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
