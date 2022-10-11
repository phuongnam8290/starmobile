<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="col-lg-3 col-md-4 col-sm-6 product">
  <div class="product-pic">
    <img src="${pageContext.request.contextPath}/static/img/home/product/${param.picture}" class="img-fluid">
    <div class="label new">New</div>
    <ul class="product-hover">
      <li>
        <a href=""><i class="fa fa-arrows-alt"></i></a>
      </li>
      <li>
        <a href=""><i class="fa fa-heart"></i></a>
      </li>
      <li>
        <a href=""><i class="fa fa-shopping-bag"></i></a>
      </li>
    </ul>
  </div>
  <div class="product-text">
    <h6>
      <a href="#">
        <c:out value="${param.name}"/>
      </a>
    </h6>
    <div class="rating">
      <c:forEach begin="1" end="${param.ratings}">
        <i class="fa fa-star"></i>
      </c:forEach>
    </div>
    <div class="product-price">$ 59.0</div>
  </div>
</div>