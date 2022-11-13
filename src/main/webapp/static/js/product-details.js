// Set background gradient
const colorThief = new ColorThief();
const background = document.querySelector(".product-details .product-image");
const img = document.querySelector(".product-details .product-picture");

if (img.complete) {
  setBackgroudGradient();
  setDotsColor();
} else {
  img.addEventListener("load", () => {
    setBackgroudGradient();
    setDotsColor();
  });
}

function setBackgroudGradient() {
  const colorTop = colorThief.getPalette(img)[0];
  const colorBottom = colorThief.getPalette(img)[1];
  background.style.setProperty("--gradient-color-top",
      `rgb(${colorTop[0]}, ${colorTop[1]}, ${colorTop[2]})`);
  background.style.setProperty("--gradient-color-bottom",
      `rgb(${colorBottom[0]}, ${colorBottom[1]}, ${colorBottom[2]})`);
}

// Set dots color
function setDotsColor() {
  const dots = document.querySelectorAll(".product-details .dots a");
  const color= colorThief.getPalette(img)[0];
  for(const dot of dots) {
    dot.style.setProperty("--dots-color", `rgb(${color[0]}, ${color[1]}, ${color[2]})`);
  }
}

// Handle quantity field, make sure that input field always has valid value (number and larger than 0)
const quantityInput = document.querySelector(".product-details .product-quantity input");
quantityInput.addEventListener("keypress", checkNumericKey);
quantityInput.addEventListener("blur", event => {
  if(Number.parseInt(event.target.value) === 0 || isNaN(Number.parseInt(event.target.value))) {
    event.target.value = 1;
  }
})

function checkNumericKey(event) {
  let charCode = (event.which) ? event.which : event.keyCode;

  if ((charCode < 48 || charCode > 57)) {
    event.preventDefault();
  }
}

// Handle add and remove button
const addButton = document.querySelector(".product-details .product-quantity .add");
const removeButton = document.querySelector(".product-details .product-quantity .remove");

addButton.addEventListener("click", () => {
  quantityInput.value++;
  // Simulate change event since programmatically change input's value don't trigger it
  quantityInput.dispatchEvent(new Event("change"));
})

removeButton.addEventListener("click", () => {
  if(quantityInput.value > 1) {
    quantityInput.value--;
  }

  // Simulate change event since programmatically change input's value don't trigger it
  quantityInput.dispatchEvent(new Event("change"));
})

quantityInput.addEventListener("change", event => {

  // Disable remove button when quantity <= 1
  if(Number.parseInt(event.target.value) <= 1) {
    removeButton.classList.add("disabled");
  } else {
    removeButton.classList.remove("disabled");
  }
})

// Handle add to cart button
const addItemBtm = document.querySelector("#add-item");
addItemBtm.addEventListener("click", async () => {
  const productId = document.querySelector(".product-details").id;
  const quantity = document.querySelector("#quantity").value;

  const params = `product_id=${productId}&quantity=${quantity}`;

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body: params
  };

  let response = await fetch("cart/product", options);
  let result = await response.json();

  console.log(result);

  if(result.status === "CREATED" || result.status === "UPDATED") {
    let quantity = result.message.split("-")[1];
    alert(`Add ${quantity} product to cart.`);
  } else {
    alert("Error adding product to cart.");
  }
})