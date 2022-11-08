package com.namdp.starmobile.entities;

public class Brand {
  private final int id;
  private final String name;
  private final String img;

  public Brand(int id, String name, String img) {
    this.id = id;
    this.name = name;
    this.img = img;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getImg() {
    return img;
  }
}
