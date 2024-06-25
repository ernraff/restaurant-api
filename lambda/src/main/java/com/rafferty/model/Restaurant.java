package com.rafferty.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBTable(tableName = "restaurant-data")
public class Restaurant {
    private String business;

    private String id;
    private String name;
    private int reviewCount;
    private double rating;
    private String price;
    private Location location;

    private String cuisine;

    public Restaurant() {
//        this.business = "restaurant";
    }

    public Restaurant(String id, String name, int reviewCount, double rating, String price, Location location) {
        this.id = id;
        this.name = name;
        this.reviewCount = reviewCount;
        this.rating = rating;
        this.price = price;
        this.location = location;
        this.business = "restaurant";
    }

    @DynamoDBHashKey(attributeName = "business")
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    @DynamoDBRangeKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "review_count")
    @JsonProperty("review_count")
    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    @DynamoDBAttribute(attributeName = "rating")
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "cuisineIndex")
    @DynamoDBAttribute(attributeName = "cuisine")
    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @DynamoDBAttribute(attributeName = "price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @DynamoDBTypeConverted(converter = LocationConverter.class)
    @DynamoDBAttribute(attributeName = "location")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
