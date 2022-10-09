package com.namdp.starmobile.controllers;

import com.namdp.starmobile.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = getInitParameter("username");
    String password = getInitParameter("password");
    String responseJson = "";

    if(request.getParameter("username").equals(username) &&
        request.getParameter("password").equals(password)) {
      HttpSession session = request.getSession();
      session.setAttribute("auth", new User(username, null));
      responseJson = "{\"isValid\": true}";
    } else {
      responseJson = "{\"isValid\": false, \"message\": \"Username or password does not match.\"}";
    }

    PrintWriter writer = response.getWriter();
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    writer.print(responseJson);
    writer.flush();
  }
}
