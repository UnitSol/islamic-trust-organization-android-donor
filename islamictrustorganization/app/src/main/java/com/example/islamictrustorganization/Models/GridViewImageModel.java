package com.example.islamictrustorganization.Models;

public class GridViewImageModel {
    private String imgURL;
    private int imgID;

    private boolean flagIsVideo;

    public GridViewImageModel() {
    }

    public GridViewImageModel(String imgURL, int imgID, boolean flagIsVideo) {
        this.imgURL = imgURL;
        this.imgID = imgID;
        this.flagIsVideo = flagIsVideo;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public boolean isFlagIsVideo() {
        return flagIsVideo;
    }

    public void setFlagIsVideo(boolean flagIsVideo) {
        this.flagIsVideo = flagIsVideo;
    }
}
