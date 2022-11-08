package com.namdp.starmobile.controllers;

import com.namdp.starmobile.dao.ProductDAO;
import com.namdp.starmobile.entities.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class SearchController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String query = request.getParameter("query");
    if(query == null) {
      query = "";
    }

    List<Product> productList = ProductDAO.getProductsByName(query);
    request.setAttribute("query", query);
    request.setAttribute("numberOfProducts", productList.size());
    request.setAttribute("productList", productList);

    getServletContext().getRequestDispatcher("/WEB-INF/pages/search.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
