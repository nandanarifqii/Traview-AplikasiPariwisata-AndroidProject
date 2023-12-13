package com.example.finalprojectpapb4;

public class ReviewModel {

    private String location;
    private String date;
    private String name;
    private String review;

    public ReviewModel() {}
    public ReviewModel(String location, String date, String name, String review) {
        this.location = location;
        this.date = date;
        this.name = name;
        this.review = review;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }


}