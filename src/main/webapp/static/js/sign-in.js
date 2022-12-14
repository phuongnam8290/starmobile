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
Sign-in modal
---------------------------------------------------------------*/

let signInForm = document.querySelector(".sign-in-form");
let email = document.querySelector("#email");
let password = document.querySelector("#password");
let remember = document.querySelector("#remember");

// Populate username input field based on remember me checkbox
loginLink.addEventListener("click", () => {
  let rememberValue = getCookie("remember");
  if (rememberValue === 'remember') {
    email.value = getCookie("email")
    remember.checked = true;
  } else {
    email.value = "";
    remember.checked = false;
  }
});


// Submit form
signInForm.addEventListener("submit", async event => {
  event.preventDefault();
  // When username & password valid, simulate form submit to backend
  if (validateInput()) {
    let response = await fetch("login", {
      method: "post",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: `email=${email.value}&password=${password.value}&remember=${remember.checked ? 'remember' : 'none'}`,
    });
    let result = await response.json();

    // If user exists, reload the current page.
    if(result.isValid) {
      location.reload();
    } else {
      setError(email, result.message);
    }
  }
})

// Clear error when user press key.
email.addEventListener("keypress", () => {
  clearError(email);
});
password.addEventListener("keypress", () => {
  clearError(password);
});

// Use to validate username & password input field
function validateInput() {

  // Clear error from previous validation
  clearError(email);
  clearError(password);

  let isValid = true;

  let emailValue = email.value.trim();
  let passwordValue = password.value.trim();

  // Email valid if not empty & has valid email format.
  if (emailValue.length < 1) {
    setError(email, "Email cannot be empty.");
    isValid = false;
  } else {
    let emailRegex = /^[A-z0-9_a-z]+@[A-Z0-9.a-z]+\.[A-Za-z]{2,6}$/;
    if (!emailValue.match(emailRegex)) {
      setError(email, "Invalid email, the email must have format such as: example@gmail.com");
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

// Get cookie value
function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}