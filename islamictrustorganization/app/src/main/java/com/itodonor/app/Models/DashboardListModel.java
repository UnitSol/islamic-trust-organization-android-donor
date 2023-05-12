package com.itodonor.app.Models;

public class DashboardListModel {
    private String imageUrl;
    private String totalFund;
    private String getTotalFund;
    private int itemID;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public DashboardListModel() {
    }

    public DashboardListModel(String imageUrl, String totalFund, String getTotalFund, int itemID) {
        this.imageUrl = imageUrl;
        this.totalFund = totalFund;
        this.getTotalFund = getTotalFund;
        this.itemID = itemID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(String totalFund) {
        this.totalFund = totalFund;
    }

    public String getGetTotalFund() {
        return getTotalFund;
    }

    public void setGetTotalFund(String getTotalFund) {
        this.getTotalFund = getTotalFund;
    }
}
