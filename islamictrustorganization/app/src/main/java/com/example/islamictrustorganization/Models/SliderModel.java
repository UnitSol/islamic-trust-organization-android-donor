package com.example.islamictrustorganization.Models;

public class SliderModel {
    private String imgID;

    private String imgUrl;

    public SliderModel(String imgID) {
        this.imgID = imgID;
        this.imgUrl = imgUrl;
    }

    public SliderModel() {
    }
    public String getImgID() {
        return imgID;
    }

    public void setImgID(String imgID) {
        this.imgID = imgID;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
