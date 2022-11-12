package com.namdp.starmobile.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JSONSender {
  // Send error back to user as a JSON object.
  public static void sendError(HttpServletResponse response, String msg) {
    String responseJSON = String.format("{\"isValid\": %s, \"message\": \"%s\"}", false, msg);
    sendJSON(response, responseJSON);
  }

  // Send success msg back to user as a JSON object.
  public static void sendSuccess(HttpServletResponse response, String status, String msg) {
    String responseJSON = String.format("{\"status\": %s, \"message\": \"%s\"}", status, msg);
    sendJSON(response, responseJSON);
  }

  // Send response back as JSON.
  private static void sendJSON(HttpServletResponse response, String content) {
    try {
      PrintWriter writer = response.getWriter();
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      writer.print(content);
      writer.flush();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
