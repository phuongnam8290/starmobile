<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common/header.css">

<!-- Header -->
<header class="header px-5 pt-4 pb-1">
  <div class="container-fluid">

    <!--  Header-top  -->
    <div class="d-flex align-items-end mb-4 header-top">
      <div class="header-logo">
        <a href="${pageContext.request.contextPath}/home"><span>Star</span>mobile</a>
      </div>
      <form method="get" action="${pageContext.request.contextPath}/search"
            class="input-group header-search">
        <input type="text" name="query" class="form-control" placeholder="Type anything here" required>
        <div class="input-group-append">
          <button class="btn btn-static" type="submit">Search</button>
        </div>
      </form>

      <c:if test="${sessionScope.auth == null}">
        <div class="d-flex justify-content-end header-auth">
          <a href="#" class="login">Login</a>
          <span>/</span>
          <a href="#">Register</a>
        </div>
      </c:if>

      <c:if test="${sessionScope.auth != null}">
        <div class="d-flex align-items-baseline header-user">
          <div class="user-cart">
            <a href="${pageContext.request.contextPath}/cart">
              <i class="fa fa-shopping-cart" aria-hidden="true"></i>
            </a>
          </div>
          <div class="user-info">
            <c:if test="${sessionScope.auth.role.name=='user'}">
              <a href="#">Hello, ${sessionScope.auth.displayName}</a>
            </c:if>
            <c:if test="${sessionScope.auth.role.name=='admin'}">
              <a href="${pageContext.request.contextPath}/admin">
                Hello, ${sessionScope.auth.displayName}
              </a>
            </c:if>
          </div>
          <div class="user-logout">
            <a href="${pageContext.request.contextPath}/logout">
              <i class="fa fa-sign-out" aria-hidden="true"></i>
            </a>
          </div>
        </div>
      </c:if>
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

<jsp:include page="../form/sign-in-form.jsp" />