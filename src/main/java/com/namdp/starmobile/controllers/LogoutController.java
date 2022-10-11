package com.namdp.starmobile.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.invalidate();

    response.sendRedirect(getServletContext().getContextPath() + "/home");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
