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

signInForm.addEventListener("submit", event => {
  event.preventDefault();
  if (validateInput()) {
    //TODO: Call backend API
  }
})

username.addEventListener("keypress", () => {
  clearError(username);
})

password.addEventListener("keypress", () => {
  clearError(password);
})

function validateInput() {

  clearError(username);
  clearError(password);

  let isValid = true;

  let usernameValue = username.value.trim();
  let passwordValue = password.value.trim();

  if (usernameValue.length < 3) {
    setError(username, "Username must longer than 3 characters.");
    isValid = false;
  }

  if (passwordValue.length < 3) {
    setError(password, "Password must longer than 3 characters.");
    isValid = false;
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