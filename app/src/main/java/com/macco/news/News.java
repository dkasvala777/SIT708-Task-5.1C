package com.macco.news;


import java.io.Serializable;

public class News implements Serializable {
    private String title;
    private String description;
    private String imageUrl;
    private int imageResource;

    public News(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public News(String title, String description, int imageResource) {
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getImageResource() {
        return imageResource;
    }
}