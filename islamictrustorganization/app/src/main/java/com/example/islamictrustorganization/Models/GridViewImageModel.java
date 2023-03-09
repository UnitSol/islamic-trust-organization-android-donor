package com.example.islamictrustorganization.Models;

public class GridViewImageModel {
    private String imgURL;
    private int imgID;

    public GridViewImageModel() {
    }
    public GridViewImageModel(String imgURL, int imgID) {
        this.imgURL = imgURL;
        this.imgID = imgID;
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
}
