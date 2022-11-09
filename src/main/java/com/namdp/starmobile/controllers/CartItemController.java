package com.namdp.starmobile.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

// TODO: Add filter class to restrict access for non-login user

public class CartItemController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // {
    //  product_id: number,
    //  quantity: number
    // }

    // Use when user add new product to order
    // If the current user does not have pending order, create new order. Synchronize with the order in session
    // If the product already exist in the order, update the quantity. Synchronize with the order in session
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // {
    //  product_id: number,
    //  quantity: number
    // }

    // Use when user increase / decrease quantity of the product in order.
    // Check if user has pending order. If not, do nothing.
    // Check if product exists in order. If not, do nothing.
    // Update order. Synchronize with the order in session
    // If quantity is 0. Remove the product from the order. After removal, check the order again. If the
    // order is empty, delete order. Synchronize with the order in session.
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // {
    //  product_id: number
    // }

    // Use when user click trash icon in cart.
    // Check if user has pending order. Of not, do nothing.
    // Check if product exists in order. If not, do nothing.
    // Remove the product from order. After removal, check the order again. If the order is empty, delete order.
    // Synchronize with the order in session.
  }

  // Check if user has pending order. Only check in session, since all order operation has been synchronized in
  // session.
  private boolean hasPendingOrder() {
    return false;
  }

  // Check if product exists in order. Only check in session, since all order operation has been synchronized in
  // session.
  private boolean isProductExist(int productId) {
    return false;
  }

  // Synchronize order in session
  private void sychronizeOSession() {
    // Fetch pending order in db, replace session current order with its.
  }
}
