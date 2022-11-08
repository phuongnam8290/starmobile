<%@ tag import="com.namdp.starmobile.entities.Product" %>
<%@ tag language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
  double rating = ((Product)request.getAttribute("product")).getRating();
  int fullStars = (int)Math.floor(rating);
  int emptyStars = 5 - (int)Math.ceil(rating);
  int halfStar = 5 - (fullStars + emptyStars);

  request.setAttribute("fullStars", fullStars);
  request.setAttribute("emptyStars", emptyStars);
  request.setAttribute("halfStar", halfStar);
%>

<c:forEach begin="1" end="${requestScope.fullStars}">
  <i class="fa fa-star"></i>
</c:forEach>
<c:forEach begin="1" end="${requestScope.halfStar}">
  <i class="fa fa-star-half-o"></i>
</c:forEach>
<c:forEach begin="1" end="${requestScope.emptyStars}">
  <i class="fa fa-star-o"></i>
</c:forEach>