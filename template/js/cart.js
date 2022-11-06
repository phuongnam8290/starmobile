const removeBtns = document.querySelectorAll("button.remove");
const addBtns = document.querySelectorAll("button.add");

for(const removeBtn of removeBtns) {
  removeBtn.addEventListener("click", removeProduct);
}

for(const addBtn of addBtns) {
  addBtn.addEventListener("click", addProduct);
}

function removeProduct(event) {
  const removeBtn = event.target;
  const details = removeBtn.closest(".product-list-details");
  const id = details.getAttribute("id");
  const quality = details.querySelector("input").value;

  if(quality > 1) {
    console.log(id);
    console.log(quality);
    // TODO: use id & quantity to call update on backend api
  }
}

function addProduct(event) {
  const addBtn = event.target;
  const details = addBtn.closest(".product-list-details");
  const id = details.getAttribute("id");
  const quality = details.querySelector("input").value;

  console.log(id);
  console.log(quality);
  // TODO: use id & quantity to call update on backend api
}
