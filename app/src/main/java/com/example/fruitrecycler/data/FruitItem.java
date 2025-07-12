package com.example.fruitrecycler.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity // This annotation indicates that this class is an entity in the Room database
public class FruitItem {
    @PrimaryKey(autoGenerate = true)
    // Added as primary key, Room will use this for database operations
    private int fruitId;

    @ColumnInfo(name = "image") // Optional: Column name in the database
    private int imageResource;
    private String name;
    private String description;
    private boolean like;

    public FruitItem(int imageResource, String name, String description) {
        this.imageResource = imageResource;
        this.name = name;
        this.description = description;
        this.like = false;
    }

    @Ignore
    public FruitItem(int imageResource, String name, String description, boolean like) {
        this.imageResource = imageResource;
        this.name = name;
        this.description = description;
        this.like = like;
    }

    public int getFruitId() {
        return fruitId;
    }

    public void setFruitId(int id) {
        this.fruitId = id;
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

    public boolean getLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
