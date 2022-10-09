<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.namdp.starmobile.entities.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<section class="products container">
  <div class="section-title">
    <h4>New Products</h4>
  </div>

  <%
    List<Product> products = new ArrayList<>();
    products.add(new Product("Samsung Galaxy Z Flip4 5G", "01.jpg", 5, 1500.0));
    products.add(new Product("iPhone 11", "02.jpg", 5, 700.0));
    products.add(new Product("Xiaomi Redmi Note 11", "03.jpg", 4, 500.0));
    products.add(new Product("Samsung Galaxy S22 Ultra 5G", "04.jpg", 5, 670.0));
    products.add(new Product("Xiaomi Redmi Note 11S series", "05.jpg", 5, 450.0));
    products.add(new Product("iPhone 13 Pro Max", "06.jpg", 5, 1000.0));
    products.add(new Product("Samsung Galaxy A23", "07.jpg", 3, 250.0));
    products.add(new Product("Vivo Y15 series", "08.jpg", 5, 300.0));
    products.add(new Product("Oppo Reno8 series", "09.jpg", 5, 899.0));

    request.setAttribute("products", products);
  %>

  <div class="row product-list">
    <c:forEach var="product" items="${products}">
      <jsp:include page="product.jsp">
        <jsp:param name="name" value="${product.name}"/>
        <jsp:param name="picture" value="${product.pictiure}"/>
        <jsp:param name="ratings" value="${product.ratings}"/>
        <jsp:param name="price" value="${product.price}"/>
      </jsp:include>
    </c:forEach>

  </div>
</section>