<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Starmobile</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
        integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/global.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css">
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-md navbar-light">
  <button class="navbar-toggler ml-auto mb-2 bg-light" type="button" data-toggle="collapse" data-target="#nav">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="nav">
    <div class="container-fluid">
      <div class="row flex-grow-1">
        <!-- Sidebar -->
        <div class="col-md-4 col-lg-3 col-xl-2 sidebar fixed-top">
          <div class="d-flex flex-column justify-content-center align-items-center mt-4 profile">
            <img src="${pageContext.request.contextPath}/static/img/admin/profile/01.jpg"
                 class="img-fluid rounded-circle">
            <p class="text-center mb-0 welcome-msg">Hello, ${sessionScope.auth.name}</p>
          </div>

          <ul class="navbar-nav flex-column mt-4">
            <li class="nav-item">
              <a class="nav-link p-3 mb-2 sidebar-link current" href="#">
                <i class="fa fa-home fa-lg mr-3"></i>
                Dashboard
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link p-3 mb-2 sidebar-link" href="#">
                <i class="fa fa-user fa-lg mr-3"></i>
                Profile
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link p-3 mb-2 sidebar-link" href="#">
                <i class="fa fa-envelope fa-lg mr-3"></i>
                Inbox
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link p-3 mb-2 sidebar-link" href="#">
                <i class="fa fa-shopping-cart fa-lg mr-3"></i>
                Sales
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link p-3 mb-2 sidebar-link" href="#">
                <i class="fa fa-line-chart fa-lg mr-3"></i>
                Analytics
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link p-3 mb-2 sidebar-link" href="#">
                <i class="fa fa-bar-chart fa-lg mr-3"></i>
                Charts
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link p-3 mb-2 sidebar-link" href="#">
                <i class="fa fa-table fa-lg mr-3"></i>
                Tables
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link p-3 mb-2 sidebar-link" href="#">
                <i class="fa fa-wrench fa-lg mr-3"></i>
                Settings
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link p-3 mb-2 sidebar-link" href="#">
                <i class="fa fa-file fa-lg mr-3"></i>
                Documentation
              </a>
            </li>
          </ul>
        </div>
        <!-- End of sidebar -->

        <!-- Top navbar -->
        <div class="col-md-8 col-lg-9 col-xl-10 ml-auto fixed-top py-3 top-navbar">
          <div class="d-flex align-items-center justify-content-between">
            <h4 class="mb-0 text-light text-uppercase">Dashboard</h4>
            <a class="btn sign-out-btn" href="${pageContext.request.contextPath}/logout">
              <span class="mr-2">Sign out</span>
              <i class="fa fa-sign-out fa-lg"></i>
            </a>
          </div>
        </div>
        <!-- End of top navbar -->
      </div>
    </div>
  </div>
</nav>
<!-- End of navbar -->

<!-- Card -->
<section class="container-fluid pt-0 py-md-2">
  <div class="row">
    <div class="col-md-8 col-lg-9 col-xl-10 ml-auto">
      <div class="row pt-md-5 mt-md-3 mb-5">
        <div class="col-sm-6 col-xl-3 p-2">
          <div class="card card-common">
            <div class="card-body d-flex justify-content-between align-items-center">
              <i class="fa fa-shopping-cart fa-4x text-warning"></i>
              <div class="text-secondary text-right">
                <h5>Sales</h5>
                <h3>$135.000</h3>
              </div>
            </div>
            <div class="card-footer text-secondary">
              <i class="fa fa-refresh mr-3"></i>
              <span>Updated Now</span>
            </div>
          </div>
        </div>
        <div class="col-sm-6 col-xl-3 p-2">
          <div class="card card-common">
            <div class="card-body d-flex justify-content-between align-items-center">
              <i class="fa fa-money fa-4x text-success"></i>
              <div class="text-secondary text-right">
                <h5>Expenses</h5>
                <h3>$39.000</h3>
              </div>
            </div>
            <div class="card-footer text-secondary">
              <i class="fa fa-refresh mr-3"></i>
              <span>Updated Now</span>
            </div>
          </div>
        </div>
        <div class="col-sm-6 col-xl-3 p-2">
          <div class="card card-common">
            <div class="card-body d-flex justify-content-between align-items-center">
              <i class="fa fa-user fa-4x text-info"></i>
              <div class="text-secondary text-right">
                <h5>Users</h5>
                <h3>15.000</h3>
              </div>
            </div>
            <div class="card-footer text-secondary">
              <i class="fa fa-refresh mr-3"></i>
              <span>Updated Now</span>
            </div>
          </div>
        </div>
        <div class="col-sm-6 col-xl-3 p-2">
          <div class="card card-common">
            <div class="card-body d-flex justify-content-between align-items-center">
              <i class="fa fa-line-chart fa-4x text-danger"></i>
              <div class="text-secondary text-right">
                <h5>Visitors</h5>
                <h3>45.000</h3>
              </div>
            </div>
            <div class="card-footer text-secondary">
              <i class="fa fa-refresh mr-3"></i>
              <span>Updated Now</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- End of card -->

<!-- Tables -->
<section class="container-fluid pt-0">
  <div class="row mb-5">
    <div class="col-md-8 col-lg-9 col-xl-10 ml-auto">
      <div class="row align-items-center">
        <div class="col-12 col-xl-6 mb-4 mb-xl-0">
          <h3 class="text-muted text-center mb-3">Staff Salary</h3>
          <table class="table table-striped bg-light text-center">
            <thead>
            <tr class="text-muted">
              <th>#</th>
              <th>Name</th>
              <th>Salary</th>
              <th>Date</th>
              <th>Contact</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <th>1</th>
              <td>John</td>
              <td>$2000</td>
              <td>25/5/2018</td>
              <td>
                <button class="btn btn-info btn-sm" type="button">Message</button>
              </td>
            </tr>
            <tr>
              <th>2</th>
              <td>Ann</td>
              <td>$2000</td>
              <td>25/5/2018</td>
              <td>
                <button class="btn btn-info btn-sm" type="button">Message</button>
              </td>
            </tr>
            <tr>
              <th>3</th>
              <td>Mark</td>
              <td>$2000</td>
              <td>25/5/2018</td>
              <td>
                <button class="btn btn-info btn-sm" type="button">Message</button>
              </td>
            </tr>
            <tr>
              <th>4</th>
              <td>Mary</td>
              <td>$2000</td>
              <td>25/5/2018</td>
              <td>
                <button class="btn btn-info btn-sm" type="button">Message</button>
              </td>
            </tr>
            <tr>
              <th>5</th>
              <td>Bob</td>
              <td>$2000</td>
              <td>25/5/2018</td>
              <td>
                <button class="btn btn-info btn-sm" type="button">Message</button>
              </td>
            </tr>
            </tbody>
          </table>

          <!-- Pagination -->
          <nav>
            <ul class="pagination justify-content-center">
              <li class="page-item disabled">
                <a href="#" class="page-link py-2 px-3">
                  <span>&laquo;</span>
                </a>
              </li>
              <li class="page-item active">
                <a href="#" class="page-link py-2 px-3">
                  <span>1</span>
                </a>
              </li>
              <li class="page-item">
                <a href="#" class="page-link py-2 px-3">
                  <span>2</span>
                </a>
              </li>
              <li class="page-item">
                <a href="#" class="page-link py-2 px-3">
                  <span>3</span>
                </a>
              </li>
              <li class="page-item">
                <a href="#" class="page-link py-2 px-3">
                  <span>&raquo;</span>
                </a>
              </li>
            </ul>
          </nav>
          <!-- End of pagination -->
        </div>
        <div class="col-12 col-xl-6">
          <h3 class="text-muted text-center mb-3">Recent Payment</h3>
          <table class="table table-dark table-hover text-center">
            <thead>
            <tr class="text-muted">
              <th>#</th>
              <th>Name</th>
              <th>Salary</th>
              <th>Date</th>
              <th>Contact</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <th>1</th>
              <td>Monica</td>
              <td>$2000</td>
              <td>25/5/2018</td>
              <td>
                <span class="badge badge-success w-75 py-2">Approve</span>
              </td>
            </tr>
            <tr>
              <th>2</th>
              <td>Nick</td>
              <td>$2000</td>
              <td>25/5/2018</td>
              <td>
                <span class="badge badge-success w-75 py-2">Approve</span>
              </td>
            </tr>
            <tr>
              <th>3</th>
              <td>Alex</td>
              <td>$2000</td>
              <td>25/5/2018</td>
              <td>
                <span class="badge badge-danger w-75 py-2">Pending</span>
              </td>
            </tr>
            <tr>
              <th>4</th>
              <td>Jane</td>
              <td>$2000</td>
              <td>25/5/2018</td>
              <td>
                <span class="badge badge-danger w-75 py-2">Pending</span>
              </td>
            </tr>
            <tr>
              <th>5</th>
              <td>Michael</td>
              <td>$2000</td>
              <td>25/5/2018</td>
              <td>
                <span class="badge badge-success w-75 py-2">Approve</span>
              </td>
            </tr>
            <tr>
              <th>6</th>
              <td>Kate</td>
              <td>$2000</td>
              <td>25/5/2018</td>
              <td>
                <span class="badge badge-danger w-75 py-2">Pending</span>
              </td>
            </tr>
            </tbody>
          </table>

          <!-- Pagination -->
          <nav>
            <ul class="pagination justify-content-center">
              <li class="page-item disabled">
                <a href="#" class="page-link py-2 px-3">
                  <span>Previous</span>
                </a>
              </li>
              <li class="page-item active">
                <a href="#" class="page-link py-2 px-3">
                  <span>1</span>
                </a>
              </li>
              <li class="page-item">
                <a href="#" class="page-link py-2 px-3">
                  <span>2</span>
                </a>
              </li>
              <li class="page-item">
                <a href="#" class="page-link py-2 px-3">
                  <span>3</span>
                </a>
              </li>
              <li class="page-item">
                <a href="#" class="page-link py-2 px-3">
                  <span>Next</span>
                </a>
              </li>
            </ul>
          </nav>
          <!-- End of pagination -->
        </div>
      </div>
    </div>
  </div>
</section>
<!-- End of tables -->

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>