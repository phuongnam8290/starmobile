package com.namdp.starmobile.controllers;

import com.namdp.starmobile.dao.OrderDAO;
import com.namdp.starmobile.dao.OrderDetailDAO;
import com.namdp.starmobile.entities.Order;
import com.namdp.starmobile.entities.OrderDetail;
import com.namdp.starmobile.entities.Product;
import com.namdp.starmobile.entities.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.namdp.starmobile.utils.JSONSender.sendError;
import static com.namdp.starmobile.utils.JSONSender.sendSuccess;

// TODO: Add filter class to restrict access for non-login user

public class CartItemController extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    // Use when user add new product to order. Parameters of request:
    // {
    //  product_id: number,
    //  quantity: number
    // }

    // Get pending order of current user. If not exist, create new one.
    Order pendingOrder = getPendingOrder(request, true);
    int quantity = (int) request.getAttribute("quantity");
    Product product = (Product) request.getAttribute("product");

    // If the product already exist in the order, update the quantity. If not, create new one.
    for (OrderDetail detail : pendingOrder.getOrderDetails()) {
      if (product.getId() == detail.getProduct().getId()) {
        OrderDetail orderDetail = OrderDetailDAO.updateOrderDetail(detail.getId(), product,
            detail.getQuantity() + quantity);
        sendSuccess(response, "UPDATED", orderDetail.getProduct().getId() + "-" + orderDetail.getQuantity());
        return;
      }
    }

    OrderDetail orderDetail = OrderDetailDAO.insertOrderDetail(pendingOrder, product, quantity);
    sendSuccess(response, "CREATED", orderDetail.getProduct().getId() + "-" + orderDetail.getQuantity());
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) {
    // Use when user increase / decrease quantity of the product in order.
    // {
    //  product_id: number,
    //  quantity: number
    // }

    // Check if user has pending order. If not, send error json back.
    Order pendingOrder = getPendingOrder(request, false);
    if (pendingOrder == null) {
      sendError(response, "No pending order exists");
      return;
    }

    // Check if product exists in order. If not, send error json back.
    Product product = (Product) request.getAttribute("product");
    OrderDetail orderDetail = null;

    for (OrderDetail detail : pendingOrder.getOrderDetails()) {
      if (detail.getProduct().getId() == product.getId()) {
        orderDetail = detail;
        break;
      }
    }

    if (orderDetail == null) {
      sendError(response, "Product not exist in pending order");
      return;
    }

    // Update order.
    // If quantity is 0. Remove the product from the order.
    int quantity = (int) request.getAttribute("quantity");
    if (quantity == 0) {
      deleteOrderDetail(request, response, orderDetail);
    } else {
      orderDetail = OrderDetailDAO.updateOrderDetail(orderDetail.getId(), product, quantity);
      sendSuccess(response, "UPDATED", orderDetail.getProduct().getId() + "-" + orderDetail.getQuantity() + "-" +
          String.format("%.0f", orderDetail.getPrice()));
    }
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
    // Use when user click trash icon in cart.
    // {
    //  product_id: number
    // }

    // Check if user has pending order. If not, send error json back.
    Order pendingOrder = getPendingOrder(request, false);
    if (pendingOrder == null) {
      sendError(response, "No pending order exists");
      return;
    }

    // Check if product exists in order. If not, send error json back.
    Product product = (Product) request.getAttribute("product");
    OrderDetail orderDetail = null;

    for (OrderDetail detail : pendingOrder.getOrderDetails()) {
      if (detail.getProduct().getId() == product.getId()) {
        orderDetail = detail;
        break;
      }
    }

    if (orderDetail == null) {
      sendError(response, "Product not exist in pending order");
      return;
    }
    // Remove the product from order.
    deleteOrderDetail(request, response, orderDetail);
  }

  // Get pending order from current user. Create new one if necessary.
  private Order getPendingOrder(HttpServletRequest request, boolean createdIfNotExist) {
    HttpSession session = request.getSession();

    User user = (User) (session.getAttribute("auth"));
    Order pendingOrder = OrderDAO.getPendingOrderByUser(user.getEmail());

    // If pending order not exist and need to create a new one.
    if (pendingOrder == null && createdIfNotExist) {
      pendingOrder = OrderDAO.createPendingOrder(user);
    }

    return pendingOrder;
  }

  // Delete order details from order
  private void deleteOrderDetail(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    Order pendingOrder;
    OrderDetailDAO.deleteOrderDetail(orderDetail);

    // After removal, check the order again. If the order is empty, delete order. Refresh to page
    pendingOrder = getPendingOrder(request, false);

    if (pendingOrder.getOrderDetails().size() == 0) {
      OrderDAO.deleteOrder(pendingOrder);

      // If pending order being delete, send {status: "ORDER-DELETED"} back to user
      sendSuccess(response, "ORDER-DELETED", "");
    } else {
      sendSuccess(response, "DELETED", String.valueOf(orderDetail.getProduct().getId()));
    }
  }
}
