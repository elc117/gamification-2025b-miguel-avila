package com.mubification.models;

public class Store {

    private int id;
    private String name;
    private String description;
    private int price;
    private String image;
    private String createdAt;

    public Store() {}

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public int getPrice() { return this.price; }
    public void setPrice(int price) { this.price = price; }

    public String getImage() { return this.image; }
    public void setImage(String image) { this.image = image; }

    public String getCreated() { return this.createdAt; }
    public void setCreated(String created) { this.createdAt = created; }


}
