<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/cart/cart-items.css">

<!-- Shopping cart -->
<section class="container shopping-cart">
  <div class="section-title">
    <h4>My shopping cart</h4>
  </div>

  <div class="row">
    <!--  Product list-->
    <div class="col-sm-12 col-md-6 col-lg-8">

      <!-- Product list header -->
      <div class="product-list-header">
        <div class="product">Product</div>
        <div class="price">Price</div>
        <div class="quantity">Quantity</div>
        <div></div>
      </div>
      <!-- End of product list header -->

      <c:forEach var="orderDetail" items="${requestScope.cart.orderDetails}">
        <!-- Product details -->
        <div class="product-list-details" id="${orderDetail.product.id}">
          <div class="image">
            <img src="${pageContext.request.contextPath}/static/img/products/${orderDetail.product.img}/thumbnail.jpg">
          </div>
          <div class="name">
            <a href="${pageContext.request.contextPath}/details?id=${orderDetail.product.id}">${orderDetail.product.name}</a>
          </div>
          <div class="price">
            <div>
              <fmt:setLocale value="vi_VN"/>
              <fmt:formatNumber value="${orderDetail.price}" type="currency"/>
            </div>
            <input type="hidden"
                   value="
                      <fmt:formatNumber type="number" value='${orderDetail.price}' groupingUsed='false'
                      maxFractionDigits='0'/>
                   "/>
          </div>
          <div class="d-flex quantity">
            <button class="btn btn-outline-secondary remove">
              <i class="fa fa-minus"></i>
            </button>
            <input type="text" name="quantity" value="${orderDetail.quantity}"/>
            <button class="btn btn-outline-secondary add">
              <i class="fa fa-plus"></i>
            </button>
          </div>
          <div>
            <button class="delete"><i class="fa fa-trash"></i></button>
          </div>
        </div>
        <!-- End of product details -->
      </c:forEach>
    </div>
    <!--  End of product list-->

    <!--  Payment info  -->
    <div class="col-sm-12 col-md-6 col-lg-4">
      <div class="shipment-info">
        <h5>Shipment info</h5>
        <div class="mt-2 name">${sessionScope.auth.name} - ${sessionScope.auth.phone}</div>
        <div class="mt-1 address">
          <span>home</span>
          ${sessionScope.auth.address}
        </div>
      </div>

      <div class="payment-info">
        <div class="price-calculation">
          <div>Subtotal</div>
          <div id="subtotal">
            <c:set var="subtotal" value="0"/>
            <c:forEach var="orderDetail" items="${requestScope.cart.orderDetails}">
              <c:set var="subtotal" value="${subtotal + orderDetail.price}"/>
            </c:forEach>
            <fmt:formatNumber value="${subtotal}" type="currency"/>
          </div>
          <div>Shipment</div>
          <div id="shipment">
            <fmt:formatNumber value="${requestScope.cart.shipment}" type="currency"/>
            <input type="hidden" value="${requestScope.cart.shipment}"/>
          </div>
          <div>Discount</div>
          <div id="discount">
            -<fmt:formatNumber value="${requestScope.cart.discount}" type="currency"/>
            <input type="hidden" value="${requestScope.cart.discount}"/>
          </div>
        </div>

        <div class="line"></div>

        <div class="total">
          <div>Total</div>
          <div id="total">
            <c:set var="total" value="${subtotal - requestScope.cart.discount + requestScope.cart.shipment}"/>
            <fmt:formatNumber value="${total}" type="currency"/>
          </div>
        </div>

        <button class="mt-3 btn btn-custom checkout" id="checkout">checkout</button>
      </div>
    </div>
    <!--  End of payment info  -->
  </div>
</section>
<!-- End of shopping cart-->

<script src="${pageContext.request.contextPath}/static/js/cart-items.js"></script>
