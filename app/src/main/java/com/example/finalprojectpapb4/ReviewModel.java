package com.example.finalprojectpapb4;

import java.util.Date;

public class ReviewModel {

    private String location;
    private Date date;
    private String userId;
    private String review;

    private String imageUri;

    public ReviewModel() {}
    public ReviewModel(String location, Date date, String userId, String review, String imageUri) {
        this.location = location;
        this.date = date;
        this.userId = userId;
        this.review = review;
        this.imageUri = imageUri;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}