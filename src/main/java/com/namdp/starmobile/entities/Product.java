package com.namdp.starmobile.entities;

public class Product {
  private final int id;
  private final String name;
  private final String specs;
  private final double price;
  private final String img;
  private final double rating;
  private final Brand brand;

  public Product(int id, String name, String specs, double price, String img, double rating,
                 int brandId, String brandName, String brandImg) {
    this.id = id;
    this.name = name;
    this.specs = specs;
    this.price = price;
    this.img = img;
    this.rating = rating;
    this.brand = new Brand(brandId, brandName, brandImg);
  }

  public Product(int id, String name, String specs, double price, String img, double rating) {
    this(id, name, specs, price, img, rating, 0, "", "");
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSpecs() {
    return specs;
  }

  public double getPrice() {
    return price;
  }

  public String getImg() {
    return img;
  }

  public double getRating() {
    return rating;
  }

  public Brand getBrand() {
    return brand;
  }
}
