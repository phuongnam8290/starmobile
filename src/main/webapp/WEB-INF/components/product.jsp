<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/product.css">

<div class="col-lg-3 col-md-4 col-sm-6">
  <div class="spin-animate">
    <div class="product">
      <div class="product-pic">
        <img src="${pageContext.request.contextPath}/static/img/products/${product.img}/thumbnail.jpg"
             class="img-fluid">
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
          <a href="${pageContext.request.contextPath}/details?id=${product.id}">
            <c:out value="${product.name}"/>
          </a>
        </h6>
        <div class="rating">
          <custom:rating/>
        </div>
        <div class="product-price">
          <fmt:setLocale value = "vi_VN"/>
          <fmt:formatNumber value = "${product.price}" type = "currency"/>
        </div>
      </div>
    </div>
  </div>
</div>