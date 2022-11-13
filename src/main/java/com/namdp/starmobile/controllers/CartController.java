package com.namdp.starmobile.controllers;

import com.namdp.starmobile.dao.OrderDAO;
import com.namdp.starmobile.entities.Order;
import com.namdp.starmobile.entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.namdp.starmobile.utils.JSONSender.sendError;
import static com.namdp.starmobile.utils.JSONSender.sendSuccess;

// TODO: Add filter class to restrict access for non-login user

public class CartController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Use to show order details to user. Using order in session, since all order operation has been synchronized
    HttpSession session = request.getSession();
    User user = (User)session.getAttribute("auth");
    Order order = OrderDAO.getPendingOrderByUser(user.getEmail());

    request.setAttribute("cart", order);
    getServletContext().getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Use to check out order
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("auth");
    Order order = OrderDAO.getPendingOrderByUser(user.getEmail());

    if (order != null) {
      OrderDAO.checkoutOrder(order, user);
      sendSuccess(response, "CHECKOUTED", String.valueOf(order.getId()));
    } else {
      sendError(response, "Cannot checkout order");
    }
  }
}
