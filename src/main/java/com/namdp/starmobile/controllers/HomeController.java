package com.namdp.starmobile.controllers;

import com.namdp.starmobile.dao.ProductDAO;
import com.namdp.starmobile.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.namdp.starmobile.db.DBUtils.PRODUCTS_PER_PAGE;

public class HomeController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String pageParameter = request.getParameter("page");
    int numberOfPages = (int) Math.ceil(ProductDAO.countProducts() * 1.0 / PRODUCTS_PER_PAGE);
    int currentPage;

    // Make sure that page parameter can convert to int and within range from 1 to total number of pages. If not, set
    // default to 1.
    try {
      currentPage = Integer.parseInt(pageParameter);
      if (currentPage <= 0 || currentPage > numberOfPages) {
        currentPage = 1;
      }
    } catch (NumberFormatException ex) {
      currentPage = 1;
    }

    List<Product> productList = ProductDAO.getProducts(currentPage, PRODUCTS_PER_PAGE);

    // Attribute to populate the product list
    request.setAttribute("productList", productList);

    // Attribute for pagination
    request.setAttribute("numberOfPages", numberOfPages);
    request.setAttribute("currentPage", currentPage);

    getServletContext().getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }
}
