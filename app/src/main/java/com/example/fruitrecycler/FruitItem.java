package com.example.fruitrecycler;

public class FruitItem {
    private int imageResource;
    private String name;
    private String description;
    private boolean like;

    public FruitItem(int imageResource, String name, String description, boolean like) {
        this.imageResource = imageResource;
        this.name = name;
        this.description = description;
        this.like = like;

    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLiked() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
