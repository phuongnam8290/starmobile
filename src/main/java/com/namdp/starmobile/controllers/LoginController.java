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
    String username = getServletContext().getInitParameter("username");
    String password = getServletContext().getInitParameter("password");
    String responseJson = "";

    String emailRegex = "^[A-z0-9_a-z]+@[A-Z0-9.a-z]+\\.[A-Za-z]{2,6}$";
    String passwordRegex = "^[a-zA-Z0-9_!@#$%^&*]+$";

    // Check username & password not empty
    if(username == null || password == null || username.trim().length() == 0 ||
        password.trim().length() == 0) {
      responseJson = "{\"isValid\": false, \"message\": \"Username or password is empty.\"}";
    }
    // Check username & password match format requirement
    else if(!username.matches(emailRegex) || !password.matches(passwordRegex)) {
      responseJson = "{\"isValid\": false, \"message\": \"Invalid syntax.\"}";
    }
    // If username & password match parameter set in web.xml, create authentication for this session
    else if(request.getParameter("username").equals(username) &&
        request.getParameter("password").equals(password)) {
      HttpSession session = request.getSession();
      session.setAttribute("auth", new User(username, null));
      responseJson = "{\"isValid\": true}";
    } else {
      responseJson = "{\"isValid\": false, \"message\": \"Username or password does not match.\"}";
    }

    // Response as JSON
    PrintWriter writer = response.getWriter();
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    writer.print(responseJson);
    writer.flush();
  }
}
