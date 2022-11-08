<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/header.css">

<!-- Header -->
<header class="header px-5 pt-4 pb-1">
  <div class="container-fluid">

    <!--  Header-top  -->
    <div class="d-flex align-items-end mb-4 header-top">
      <div class="header-logo">
        <a href="${pageContext.request.contextPath}/home"><span>Star</span>mobile</a>
      </div>
      <div class="input-group header-search">
        <input type="text" class="form-control" placeholder="Type anything here">
        <div class="input-group-append">
          <button class="btn btn-static" type="button">Search</button>
        </div>
      </div>

      <div class="d-flex justify-content-end header-auth">
        <c:if test="${sessionScope.auth == null}">
          <a href="#" class="login">Login</a>
          <span>/</span>
          <a href="#">Register</a>
        </c:if>

        <c:if test="${sessionScope.auth != null}">
          <a href="#">Hello, ${sessionScope.auth.username}</a>
        </c:if>

      </div>
    </div>
    <!--  End of header-top  -->

    <!--  Header-bottom  -->
    <nav class="header-menu">
      <ul class="d-flex">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Smartphone</a></li>
        <li><a href="#">Table</a></li>
        <li><a href="#">Laptop</a></li>
        <li><a href="#">Pages</a></li>
        <li><a href="#">Blog</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
    </nav>
    <!--  End of header-bottom  -->
  </div>
</header>
<!-- End of header -->

<jsp:include page="../components/sign-in-form.jsp" />