<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/static/css/product-details.css">

<section class="container">
  <div class="section-title">
    <h4>Product Details</h4>
  </div>
  <div class="d-block d-lg-flex product-details">
    <div class="d-flex flex-column justify-content-between product-image">
      <img src="${pageContext.request.contextPath}/static/img/details/logo/${requestScope.product.brand.img}.png"
           class="product-logo">
      <img src="${pageContext.request.contextPath}/static/img/products/${requestScope.product.img}/large.png"
           class="product-picture">
      <div class="d-flex dots">
        <a href="#" class="active"></a>
        <a href="#"></a>
        <a href="#"></a>
        <a href="#"></a>
      </div>
    </div>

    <div class="product-description">
      <div>
        <h1 class="product-name">${requestScope.product.name}</h1>

        <div class="d-flex justify-content-between align-items-center">
          <span class="product-price">
            <fmt:setLocale value="vi_VN"/>
            <fmt:formatNumber value="${requestScope.product.price}" type="currency" />
          </span>
          <div class="rating">
            <custom:rating/>
          </div>
        </div>
      </div>
      <article class="mt-5 product-specifications">
        <h5 class="text-uppercase font-weight-bold">specifications</h5>
        <table class="table table-striped table-hover mt-3">
          <custom:spec />
        </table>
      </article>
      <div class="mt-5 d-flex product-options">
        <div class="product-color">
          <h5 class="text-uppercase font-weight-bold">color</h5>
          <ul class="d-flex mt-4">
            <li><button class="colors color-01 active"></button></li>
            <li><button class="colors color-02"></button></li>
            <li><button class="colors color-03"></button></li>
            <li><button class="colors color-04"></button></li>
            <li><button class="colors color-05"></button></li>
          </ul>
        </div>
        <div class="product-quantity">
          <h5 class="text-uppercase font-weight-bold">quantity</h5>
          <div class="d-flex mt-3">
            <button class="btn btn-outline-secondary remove disabled">
              <i class="fa fa-minus"></i>
            </button>
            <input type="text" name="quantity" id="quantity" value="1">
            <button class="btn btn-outline-secondary add">
              <i class="fa fa-plus"></i>
            </button>
          </div>
        </div>
      </div>
      <div class="mt-5 product-action">
        <a href="#" class="btn btn-lg btn-custom">
          <i class="fa fa-cart-plus mr-2"></i>
          Add to cart
        </a>
      </div>
    </div>
  </div>
</section>

<script src="${pageContext.request.contextPath}/static/js/product-details.js"></script>