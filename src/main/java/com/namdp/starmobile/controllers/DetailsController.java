package com.namdp.starmobile.controllers;

import com.namdp.starmobile.dao.ProductDAO;
import com.namdp.starmobile.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailsController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String idParameter = request.getParameter("id");
    Product product = null;

    // If product id does not exist, redirect back to home page.
    if (idParameter == null) {
      response.sendRedirect(getServletContext().getContextPath() + "/home");
      return;
    }

    // If product id is not an integer, redirect back to home page.
    try {
      int id = Integer.parseInt(idParameter);
      product = ProductDAO.getProductById(id);

    } catch (NumberFormatException ex) {
      response.sendRedirect(getServletContext().getContextPath() + "/home");
      return;
    }

    // Uf product with the given id does not exist, redirect back to home page.
    if (product == null) {
      response.sendRedirect(getServletContext().getContextPath() + "/home");
    } else {
      request.setAttribute("product", product);
      getServletContext().getRequestDispatcher("/WEB-INF/pages/details.jsp").forward(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
