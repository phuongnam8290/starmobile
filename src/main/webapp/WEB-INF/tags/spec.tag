<%@ tag import="com.namdp.starmobile.entities.Product" %>
<%@ tag language="java" pageEncoding="UTF-8" %>

<%
  Product product = (Product)request.getAttribute("product");
  String[] specs = product.getSpecs().split(" \\| ");

  request.setAttribute("display", specs[0]);
  request.setAttribute("os", specs[1]);
  request.setAttribute("mainCamera", specs[2]);
  request.setAttribute("selfieCamera", specs[3]);
  request.setAttribute("chip", specs[4]);
  request.setAttribute("ram", specs[5]);
  request.setAttribute("storage", specs[6]);
%>

<tr>
  <th>Display</th>
  <td>${requestScope.display}</td>
</tr>
<tr>
  <th>OS</th>
  <td>${requestScope.os}</td>
</tr>
<tr>
  <th>Main camera</th>
  <td>${requestScope.mainCamera}</td>
</tr>
<tr>
  <th>Selfie camera</th>
  <td>${requestScope.selfieCamera}</td>
</tr>
<tr>
  <th>Chip</th>
  <td>${requestScope.chip}</td>
</tr>
<tr>
  <th>RAM</th>
  <td>${requestScope.ram}</td>
</tr>
<tr>
  <th>Storage</th>
  <td>${requestScope.storage}</td>
</tr>