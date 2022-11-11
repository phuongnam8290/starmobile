package com.namdp.starmobile.entities;

import java.util.Date;
import java.util.List;

public class Order {
  private int id;
  private String mail;
  private OrderStatus status;
  private Date orderDate;
  private double discount;
  private String address;
  private double shipment;
  private List<OrderDetail> orderDetails;

  public Order(int id, String mail, OrderStatus status, Date orderDate, double discount, String address,
               double shipment, List<OrderDetail> orderDetails) {
    this.id = id;
    this.mail = mail;
    this.status = status;
    this.orderDate = orderDate;
    this.discount = discount;
    this.address = address;
    this.shipment = shipment;
    this.orderDetails = orderDetails;
  }

  public int getId() {
    return id;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public double getShipment() {
    return shipment;
  }

  public void setShipment(double shipment) {
    this.shipment = shipment;
  }

  public List<OrderDetail> getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(List<OrderDetail> orderDetails) {
    this.orderDetails = orderDetails;
  }
}
