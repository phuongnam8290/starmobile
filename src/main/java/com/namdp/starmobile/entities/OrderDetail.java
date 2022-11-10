package com.namdp.starmobile.entities;

public class OrderDetail {
  private Product product;
  private Order order;
  private int quantity;
  private double price;

  public OrderDetail(Product product, Order order, int quantity, double price) {
    this.product = product;
    this.order = order;
    this.quantity = quantity;
    this.price = price;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
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
