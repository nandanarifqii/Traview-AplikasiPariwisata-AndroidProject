package com.example.finalprojectpapb4;

import android.net.Uri;

import java.util.Date;

public class ReviewModel {

    private String location;
    private Date date;
    private String userId;
    private String review;

    private Uri imageUri;

    public ReviewModel() {}
    public ReviewModel(String location, Date date, String userId, String review) {
        this.location = location;
        this.date = date;
        this.userId = userId;
        this.review = review;
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

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}