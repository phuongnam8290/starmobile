package com.namdp.starmobile.controllers;

import com.namdp.starmobile.entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    getServletContext().getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
