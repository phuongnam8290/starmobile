<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/search/search-result.css">

<section class="search-result">
  <div class="section-title">
    <h4>Search result</h4>
  </div>

  <div class="container">
    <p class="search-msg">
      Showing ${requestScope.numberOfProducts} result for <span>"${requestScope.query}"</span>
    </p>
  </div>

  <div class="container">
    <div class="row product-list">
      <c:forEach var="product" items="${requestScope.productList}">
        <c:set var="product" value="${product}" scope="request"/>
        <jsp:include page="../common/product.jsp"/>
      </c:forEach>
    </div>
  </div>
</section>