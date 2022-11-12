package com.namdp.starmobile.controllers;

import com.namdp.starmobile.dao.OrderDAO;
import com.namdp.starmobile.dao.OrderDetailDAO;
import com.namdp.starmobile.dao.ProductDAO;
import com.namdp.starmobile.entities.Order;
import com.namdp.starmobile.entities.OrderDetail;
import com.namdp.starmobile.entities.Product;
import com.namdp.starmobile.entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

// TODO: Add filter class to restrict access for non-login user

public class CartItemController extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    // Use when user add new product to order. Parameters of request:
    // {
    //  product_id: number,
    //  quantity: number
    // }

    // Check if quantity > 0. If not, send error json back.
    String quantityParameter = request.getParameter("quantity");
    int quantity;

    try {
      quantity = Integer.parseInt(quantityParameter);
    } catch (NullPointerException | NumberFormatException ex) {
      sendError(response);
      return;
    }

    // Fetch the pending order of the current user. If user does not have pending order, create new order.
    HttpSession session = request.getSession();
    User user = (User)(session.getAttribute("auth"));
    Order pendingOrder = OrderDAO.getPendingOrderByUser(user.getEmail());

    if(pendingOrder == null) {
      pendingOrder = OrderDAO.createPendingOrder(user);
    }

    // Check if product exist in db. If not, send error json back.
    String productIdParameter = request.getParameter("product_id");
    int productId;

    try {
      productId = Integer.parseInt(productIdParameter);
    } catch (NullPointerException | NumberFormatException ex) {
      sendError(response);
      return;
    }

    Product product = ProductDAO.getProductById(productId);
    if(product == null) {
      sendError(response);
      return;
    }

    // If the product already exist in the order, update the quantity. If not, create new one.
    for (OrderDetail detail : pendingOrder.getOrderDetails()) {
      if (product.getId() == detail.getProduct().getId()) {
        OrderDetailDAO.updateOrderDetail(detail.getId(), product, quantity);
      }
    }

    OrderDetailDAO.insertOrderDetail(pendingOrder, product, quantity);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // {
    //  product_id: number,
    //  quantity: number
    // }

    // Use when user increase / decrease quantity of the product in order.
    // Check if quantity >= 0. If not, send error json back.
    // Check if user has pending order. If not, send error json back.
    // Check if product exist in db. If not, send error json back.
    // Check if product exists in order. If not, send error json back.
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
    // Check if user has pending order. If not, send error json back.
    // Check if product exists in order. If not, send error json back.
    // Remove the product from order. After removal, check the order again. If the order is empty, delete order.
    // Synchronize with the order in session.
  }

  private void sendError(HttpServletResponse response) {
    return;
  }

  // Check if user has pending order. Only check in session, since session's order being synchronized after each request
  private boolean hasPendingOrder() {
    return false;
  }

  // Check if product exists in database. Necessary since user can send request to server without using provided ui
  private boolean isProductExistInDb(int productId) {
    return false;
  }

  // Check if product exists in order. Only check in session, since session's order being synchronized after each
  // request
  private boolean isProductExistInOrder(int productId) {
    return false;
  }

  // Synchronize order in session
  private void sychronizeOSession() {
    // Fetch pending order in db, replace session current order with its.
  }
}
