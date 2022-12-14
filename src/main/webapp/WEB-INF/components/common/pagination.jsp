<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common/pagination.css">

<!-- Pagination -->
<nav>
  <ul class="pagination justify-content-center">

    <c:choose>
      <c:when test="${requestScope.currentPage <= 1}">
        <li class="page-item disabled">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
      </c:when>
      <c:otherwise>
        <li class="page-item">
          <a class="page-link"
             href="${pageContext.request.contextPath}/home?page=${requestScope.currentPage - 1}"
             aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
      </c:otherwise>
    </c:choose>

    <c:forEach begin="1" end="${requestScope.numberOfPages}" var="page">
      <c:choose>
        <c:when test="${page==requestScope.currentPage}">
          <li class="page-item active">
            <a class="page-link" href="#">${page}</a>
          </li>
        </c:when>
        <c:otherwise>
          <li class="page-item">
            <a class="page-link"
               href="${pageContext.request.contextPath}/home?page=${page}">${page}</a>
          </li>
        </c:otherwise>
      </c:choose>
    </c:forEach>

    <c:choose>
      <c:when test="${requestScope.currentPage >= requestScope.numberOfPages}">
        <li class="page-item disabled">
          <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </c:when>
      <c:otherwise>
        <li class="page-item">
          <a class="page-link"
             href="${pageContext.request.contextPath}/home?page=${requestScope.currentPage + 1}"
             aria-label="Previous">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </c:otherwise>
    </c:choose>
  </ul>
</nav>
<!-- End of pagination -->