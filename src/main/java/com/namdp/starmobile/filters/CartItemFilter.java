package com.namdp.starmobile.filters;

import com.namdp.starmobile.dao.ProductDAO;
import com.namdp.starmobile.entities.Product;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.namdp.starmobile.utils.JSONSender.sendError;

// Sanitize request parameter before process to endpoints

public class CartItemFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    // If product parameter not valid, send error back to user as JSON.
    if (!isProductParameterValid(request, response)) {
      sendError(response, "Invalid product parameter");
      return;
    }

    // If quantity parameter not valid, send error back to user as JSON.
    if (!isQuantityParameterValid(request, response)) {
      sendError(response, "Invalid quantity parameter");
      return;
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void destroy() {
  }

  // Check if quantity parameter valid or not. IF valid, add to request.
  private boolean isQuantityParameterValid(HttpServletRequest request, HttpServletResponse response) {
    String HTTPMethod = request.getMethod();

    // If the user delete product (using DELETE method), then we do not need to check the quantity.
    if(HTTPMethod.equals("DELETE")) {
      return true;
    }

    String quantityParameter = request.getParameter("quantity");
    int quantity;

    // Check if quantity parameter is a number.
    try {
      quantity = Integer.parseInt(quantityParameter);
    } catch (NullPointerException | NumberFormatException ex) {
      return false;
    }

    // If user add new product to cart (using POST method), the quantity must > 0. Otherwise, if user change the
    // quantity in cart (using PUT method), the quantity must >= 0.
    if ((HTTPMethod.equals("POST") && quantity <= 0) ||
        (HTTPMethod.equals("PUT") && quantity < 0)) {
      return false;
    } else {
      request.setAttribute("quantity", quantity);
      return true;
    }
  }

  // Check if product parameter valid or not. IF valid, add to request.
  private boolean isProductParameterValid(HttpServletRequest request, HttpServletResponse response) {
    String productIdParameter = request.getParameter("product_id");
    int productId;

    // Check if product id parameter is a number.
    try {
      productId = Integer.parseInt(productIdParameter);
    } catch (NullPointerException | NumberFormatException ex) {
      return false;
    }

    // Check if product exist in db.
    Product product = ProductDAO.getProductById(productId);
    if (product == null) {
      return false;
    }

    request.setAttribute("product", product);
    return true;
  }
}
