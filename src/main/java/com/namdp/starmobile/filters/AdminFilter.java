package com.namdp.starmobile.filters;

import com.namdp.starmobile.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpSession session = request.getSession();

    User auth = (User) session.getAttribute("auth");
    if(auth.getRole().getName().equals("admin")) {
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      HttpServletResponse response = (HttpServletResponse) servletResponse;
      response.sendRedirect(request.getContextPath() + "/home");
    }
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void destroy() {

  }
}
