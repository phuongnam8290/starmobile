package com.namdp.starmobile.entities;

public class Product {
  private final String name;
  private final String pictiure;
  private final int ratings;
  private final double price;

  public Product(String name, String pictiure, int ratings, double price) {
    this.name = name;
    this.pictiure = pictiure;
    this.ratings = ratings;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public String getPictiure() {
    return pictiure;
  }

  public int getRatings() {
    return ratings;
  }

  public double getPrice() {
    return price;
  }
}
