package com.namdp.starmobile.controllers;

import com.namdp.starmobile.dao.ProductDAO;
import com.namdp.starmobile.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeController extends HttpServlet {
  private static final int PRODUCTS_PER_PAGE = 8;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String pageParameter = request.getParameter("page");
    int currentPage;

    try {
      currentPage = Integer.parseInt(pageParameter);
      if(currentPage <= 0) {
        currentPage = 1;
      }
    } catch (NumberFormatException ex) {
      currentPage = 1;
    }

    List<Product> productList = ProductDAO.getProducts(currentPage, PRODUCTS_PER_PAGE);

    if(productList.size() == 0) {
      response.sendRedirect(getServletContext().getContextPath() + "/home");
      return;
    }

    request.setAttribute("productList", productList);
    request.setAttribute("numberOfPages", (int)Math.ceil(ProductDAO.countProducts() * 1.0 / PRODUCTS_PER_PAGE));
    request.setAttribute("currentPage", currentPage);

    getServletContext().getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }
}
