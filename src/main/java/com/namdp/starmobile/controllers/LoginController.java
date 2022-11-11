package com.namdp.starmobile.controllers;

import com.namdp.starmobile.dao.OrderDAO;
import com.namdp.starmobile.dao.UserDAO;
import com.namdp.starmobile.db.DBUtils;
import com.namdp.starmobile.entities.Order;
import com.namdp.starmobile.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Get email & password from request
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String responseJson = "";

    String emailRegex = "^[A-z0-9_a-z]+@[A-Z0-9.a-z]+\\.[A-Za-z]{2,6}$";
    String passwordRegex = "^[a-zA-Z0-9_!@#$%^&*]+$";

    User user = null;

    // Check email & password not empty
    if(email == null || password == null || email.trim().length() == 0 ||
        password.trim().length() == 0) {
      responseJson = "{\"isValid\": false, \"message\": \"Email or password is empty.\"}";
    }
    // Check email & password match format requirement
    else if(!email.matches(emailRegex) || !password.matches(passwordRegex)) {
      responseJson = "{\"isValid\": false, \"message\": \"Invalid syntax.\"}";
    }
    // Check if user exists in db
    else if((user = UserDAO.getUserByAuth(email, password)) != null) {
      // Remove user password before add to session
      user.setPassword(null);

      HttpSession session = request.getSession();
      session.setAttribute("auth", user);
      responseJson = "{\"isValid\": true}";

      // IF remember me is checked, enable remember me. If not, disable it
      String remember = request.getParameter("remember");
      if(remember.equals("remember")) {
        enableRememberMe(email, response);
      } else {
        disableRememberMe(response);
      }
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

  private void enableRememberMe(String email, HttpServletResponse response) {
    Cookie cUsername = new Cookie("email", email);
    Cookie cRemember = new Cookie("remember", "remember");
    cUsername.setMaxAge(60 * 60 * 24 * 15); // 15 days
    cRemember.setMaxAge(60 * 60 * 24 * 15); // 15 days
    response.addCookie(cUsername);
    response.addCookie(cRemember);
  }

  private void disableRememberMe(HttpServletResponse response) {
    Cookie cUsername = new Cookie("email", null);
    Cookie cRemember = new Cookie("remember", null);
    cUsername.setMaxAge(0);
    cRemember.setMaxAge(0);
    response.addCookie(cUsername);
    response.addCookie(cRemember);
  }
}
