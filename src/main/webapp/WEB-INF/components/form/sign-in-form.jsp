<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/form/sign-in.css">

<div class="d-none modal-overlay" id="sign-in-overlay">
  <div class="sign-in-modal">
    <div class="row">
      <div class="col-md-4 d-none d-md-block sign-in-img"></div>
      <form class="col-12 col-md-8 sign-in-form">
        <h1 class="sign-in-modal-header">Sign in</h1>
        <ul class="sign-in-socials">
          <li><a href="#"><i class="fa fa-facebook"></i></a></li>
          <li><a href="#"><i class="fa fa-twitter"></i></a></li>
          <li><a href="#"><i class="fa fa-instagram"></i></a></li>
          <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
        </ul>

        <div class="custom-input-group">
          <input type="text" name="email" id="email" placeholder="Email" class="form-control"
                 autocomplete="off">
          <p class="error-msg"></p>
        </div>
        <div class="custom-input-group">
          <input type="password" name="password" id="password" placeholder="Password" class="form-control">
          <p class="error-msg"></p>
        </div>
        <div class="mb-4">
          <input type="checkbox" name="remember" id="remember"/>
          <label for="remember" class="ml-1">Remember me?</label>
        </div>
        <input type="submit" value="Sign in" class="btn btn-custom">
      </form>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/static/js/sign-in.js"></script>