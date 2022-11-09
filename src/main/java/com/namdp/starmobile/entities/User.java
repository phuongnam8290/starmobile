package com.namdp.starmobile.entities;

public class User {
  private String email;
  private String password;
  private Role role;
  private String name;
  private String address;
  private String phone;

  public User(String email, String password, Role role, String name, String address, String phone) {
    this(email, name, address, phone, role);
    this.password = password;
  }

  public User(String email, String name, String address, String phone, Role role) {
    this.email = email;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Role getRole() {
    return role;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getName() {
    return name;
  }

  public String getDisplayName() {
    String[] names = this.name.split(" ");
    return names[names.length - 1];
  }
}
