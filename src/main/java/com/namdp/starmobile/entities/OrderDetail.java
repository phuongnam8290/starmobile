package com.namdp.starmobile.entities;

public class OrderDetail {
  private int id;
  private Product product;
  private int orderId;
  private int quantity;
  private double price;

  public OrderDetail(int id, Product product, int orderId, int quantity, double price) {
    this.id = id;
    this.product = product;
    this.orderId = orderId;
    this.quantity = quantity;
    this.price = price;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
