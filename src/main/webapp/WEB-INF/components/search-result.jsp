<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/search-result.css">

<section class="search-result">
  <div class="section-title">
    <h4>Search result</h4>
  </div>

  <div class="container">
    <p class="search-msg">
      Showing ${requestScope.numberOfProducts} result for <span>"${requestScope.query}"</span>
    </p>
  </div>
  <jsp:include page="../components/product-list.jsp" />
</section>