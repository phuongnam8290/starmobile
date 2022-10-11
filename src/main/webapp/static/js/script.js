/*-------------------------------------------------------------
Show & hide modals
---------------------------------------------------------------*/

let loginLink = document.querySelector(".header-auth .login");
loginLink.addEventListener("click", toggleSignInModal);

let signInMOverlay = document.querySelector("#sign-in-overlay");
signInMOverlay.addEventListener("click", event => {
  // Close modal if user click outside its.
  if (event.target === signInMOverlay) {
    toggleSignInModal();
  }
})

function toggleSignInModal() {
  let signInOverlay = document.querySelector("#sign-in-overlay");
  signInOverlay.classList.toggle("d-none");

  let body = document.querySelector("body");
  body.classList.toggle("custom-modal-open");
}


/*-------------------------------------------------------------
Validate Sign In modal
---------------------------------------------------------------*/

let signInForm = document.querySelector(".sign-in-form");
let username = document.querySelector("#username");
let password = document.querySelector("#password");

signInForm.addEventListener("submit", async event => {
  event.preventDefault();
  // When username & password valid, simulate form submit to backend
  if (validateInput()) {
    let response = await fetch("login", {
      method: "post",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: `username=${username.value}&password=${password.value}`,
    });
    let result = await response.json();

    // If user exists, redirect to admin page. If not, show error.
    if(result.isValid) {
      window.location.replace("admin");
    } else {
      setError(username, result.message);
    }
  }
})

// Clear error when user press key.
username.addEventListener("keypress", () => {
  clearError(username);
});
password.addEventListener("keypress", () => {
  clearError(password);
});

// Use to validate username & password input field
function validateInput() {

  // Clear error from previous validation
  clearError(username);
  clearError(password);

  let isValid = true;

  let usernameValue = username.value.trim();
  let passwordValue = password.value.trim();

  // Username valid if not empty & has valid email format.
  if (usernameValue.length < 1) {
    setError(username, "Username cannot be empty.");
    isValid = false;
  } else {
    let emailRegex = /^[A-z0-9_a-z]+@[A-Z0-9\.a-z]+\.[A-Za-z]{2,6}$/;
    if (!usernameValue.match(emailRegex)) {
      setError(username, "Invalid email, the email must have format such as: example@gmail.com");
      isValid = false;
    }
  }

  // Password valid if not empty and has only character, number or _!@#$%^&*
  if (passwordValue.length < 1) {
    setError(password, "Password cannot be empty.");
    isValid = false;
  } else {
    let passwordRegex = /^[a-zA-Z0-9_!@#$%^&*]+$/
    if (!passwordValue.match(passwordRegex)) {
      setError(password, "Invalid character(s). Password can contain only number, character and _!@#$%^&*");
      isValid = false;
    }
  }

  return isValid;
}

function setError(input, message) {
  let parent = input.parentElement;
  let p = parent.querySelector(".error-msg");

  parent.classList.add("error");
  p.innerText = message;
}

function clearError(input) {
  let parent = input.parentElement;
  let p = parent.querySelector(".error-msg");

  parent.classList.remove("error");
  p.innerText = "";
}