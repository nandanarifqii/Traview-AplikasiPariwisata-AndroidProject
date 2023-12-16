package com.example.finalprojectpapb4;

public class UserModel {
    private String username;
    private String name;
    private String imageProfileUri;

    public UserModel() {
    }

    public UserModel(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageProfileUri() {
        return imageProfileUri;
    }

    public void setImageProfileUri(String imageProfileUri) {
        this.imageProfileUri = imageProfileUri;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", imageProfileUri='" + imageProfileUri + '\'' +
                '}';
    }
}
