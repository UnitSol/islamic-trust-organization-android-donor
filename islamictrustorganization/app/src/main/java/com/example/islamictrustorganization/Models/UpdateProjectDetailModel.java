package com.example.islamictrustorganization.Models;

public class UpdateProjectDetailModel {
    private String updateName;
    private String updateDate;
    private int updateID;

    public UpdateProjectDetailModel() {
    }

    public UpdateProjectDetailModel(String updateName, String updateDate, int updateID) {
        this.updateName = updateName;
        this.updateDate = updateDate;
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
