const cartItems = document.querySelectorAll(".product-list-details");

// Prevent invalid input in quantity fields.
for (const cartItem of cartItems) {
  const quantityInput = cartItem.querySelector("input[type=text]");
  quantityInput.addEventListener("keypress", checkNumericKey);
}

function checkNumericKey(event) {
  let charCode = (event.which) ? event.which : event.keyCode;

  if ((charCode < 48 || charCode > 57)) {
    event.preventDefault();
  }
}

// Handle quantity input field
for (const cartItem of cartItems) {
  const quantityInput = cartItem.querySelector("input[type=text]");
  quantityInput.addEventListener("blur", async event => {

    // Make sure quantity input field has valid value when lose focus.
    if (Number.parseInt(event.target.value) === 0 || isNaN(Number.parseInt(event.target.value))) {
      event.target.value = 1;
    }

    const quantity = event.target.value;
    const productId = cartItem.id;

    await changeItemQuantity(productId, quantity, "none");
  })
}

// Handle add btn
for (const cartItem of cartItems) {
  const addBtn = cartItem.querySelector("button.add");
  addBtn.addEventListener("click", async () => {
    const productId = cartItem.id;
    const quantity = Number.parseInt(cartItem.querySelector("input[type=text]").value);
    await changeItemQuantity(productId, quantity, "add");
  })
}

// Handle remove btn
for (const cartItem of cartItems) {
  const addBtn = cartItem.querySelector("button.remove");
  addBtn.addEventListener("click", async () => {
    const productId = cartItem.id;
    const quantity = Number.parseInt(cartItem.querySelector("input[type=text]").value);
    await changeItemQuantity(productId, quantity, "remove");
  })
}

// Handle delete btn
for (const cartItem of cartItems) {
  const deleteBtn = cartItem.querySelector("button.delete");
  deleteBtn.addEventListener("click", async () => {
    const productId = cartItem.id;
    await deleteItem(productId);
  })
}

async function changeItemQuantity(productId, quantity, operation) {
  let modifiedQuantity;

  if (operation === "add") {
    modifiedQuantity = ++quantity;
  } else if (operation === "remove") {
    modifiedQuantity = --quantity;
  } else if (operation === "none") {
    modifiedQuantity = quantity;
  }

  const url = `cart/product?product_id=${productId}&quantity=${modifiedQuantity}`;
  const options = {
    method: "PUT",
  };

  let response = await fetch(url, options);
  updateItemInfo(await response.json());
  updatePaymentInfo();
}

async function deleteItem(productId) {
  const url = `cart/product?product_id=${productId}`;
  const options = {
    method: "DELETE",
  };

  let response = await fetch(url, options);
  updateItemInfo(await response.json());
  updatePaymentInfo();
}

const formatter = new Intl.NumberFormat("vi-VN", {
  style: "currency",
  currency: "VND"
})

// Update cart item info
function updateItemInfo(returnedJSON) {
  const status = returnedJSON.status;

  // Signify pending order has been deleted.
  if (status === "ORDER-DELETED") {
    location.reload();
    return;
  }

  const info = returnedJSON.message.split("-");
  const productId = info[0];

  const item = document.getElementById(`${productId}`);

  // If status = "UPDATED", update corresponding item info
  if (status === "UPDATED") {
    const quantity = info[1];
    const price = info[2];

    const qualityInput = item.querySelector("input[type='text']");
    const priceDiv = item.querySelector(".price div");
    const priceHiddenField = item.querySelector(".price input[type='hidden']");

    qualityInput.value = quantity;
    priceDiv.innerHTML = formatter.format(price);
    priceHiddenField.value = price;
  } else if (status === "DELETED") {
    item.remove();
  }
}

// Update cart payment info
function updatePaymentInfo() {
  let subtotal = 0;

  const cartItems = document.querySelectorAll(".product-list-details");

  for (const cartItem of cartItems) {
    const itemPrice = Number.parseInt(cartItem.querySelector(".price input[type='hidden']").value);
    subtotal += itemPrice;
  }
  let subtotalDiv = document.querySelector("#subtotal");
  subtotalDiv.innerHTML = formatter.format(subtotal);

  let shipmentHiddenField = document.querySelector("#shipment input[type='hidden']");
  let shipment = Number.parseInt(shipmentHiddenField.value);

  let discountHiddenField = document.querySelector("#discount input[type='hidden']");
  let discount = Number.parseInt(discountHiddenField.value);

  let total = subtotal + shipment - discount;
  let totalDiv = document.querySelector("#total");
  totalDiv.innerHTML = formatter.format(total);
}