package com.s.shopapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products_table")
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String imageUri;

    private String description;

    private double price;

    public Product(String name, String imageUri, double price, String description) {
        this.name = name;
        this.imageUri = imageUri;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
