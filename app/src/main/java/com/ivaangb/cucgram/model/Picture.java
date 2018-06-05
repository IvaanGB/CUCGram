package com.ivaangb.cucgram.model;

/**
 * Created by ivang on 4/6/2018.
 */

public class Picture {

    private String picture;
    private String userName;
    private String time;
    private String likeNumber = "0";

    public Picture(String picture, String userName, String time, String likeNumber) {
        this.picture = picture;
        this.userName = userName;
        this.time = time;
        this.likeNumber = likeNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }


}
