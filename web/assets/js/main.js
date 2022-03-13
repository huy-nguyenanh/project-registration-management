function dropdownProfile() {
  // profile dropdown
  const profile = document.querySelector(".profile");
  const imgProfile = profile.querySelector("img");
  const dropdown = profile.querySelector(".profile-link");

  imgProfile.addEventListener("click", () => {
    dropdown.classList.toggle("show");
  });

  window.addEventListener("click", function (e) {
    if (e.target !== imgProfile) {
      if (dropdown.classList.contains("show")) {
        dropdown.classList.remove("show");
      }
    }
  });
}
function submitImportOnchange() {
  // submit file on change
  const formImportFile = document.getElementById("import-form");
  const importFile = document.getElementById("import-file");
  importFile.addEventListener("change", () => {
    formImportFile.submit();
  });
}
function editButton() {
  const editBtn = document.querySelector(".edit-btn");
  const editSpan = document.querySelector(".edit-span");
  const editInput = document.getElementById("edit-input");
  const formUpdateId = document.getElementById("updateId-form");
  editBtn.addEventListener("click", (e) => {
    e.preventDefault();
    if (editSpan.style.display !== "none") {
      editSpan.style.display = "none";
      editInput.setAttribute("type", "text");
      editBtn.textContent = "Update";
    } else {
      editSpan.style.display = "block";
      editInput.setAttribute("type", "hidden");
      editBtn.textContent = "Edit this post";
      formUpdateId.submit();
    }
  });
}
function confirmSubmit() {
  // confirm submit button
  const submitBtn = document.querySelector(".confirm-submit");
  submitBtn.addEventListener("click", () => {
    if (!confirm("Are you sure ?")) {
      return false;
    }
    this.form.submit();
  });
}
function showErrorModal() {
  // Error modal
  (() => {
    const a0 = document.getElementById("error-modal");
    const a1 = document.getElementById("myBtn");
    const a2 = document.getElementsByClassName("close")[0];
    const a3 = document.querySelector(".error");
    if (a3.textContent !== "") {
      a0.style.display = "block";
      a3.textContent = a0.textContent;
      a2.onclick = function () {
        a0.style.display = "none";
        a3.textContent = "";
      };
      window.onclick = function (event) {
        if (event.target == modal) {
          a0.style.display = "none";
          a3.textContent = "";
        }
      };
    }
  })();
}
function showEditModal() {
  //////
  const modal = document.querySelector(".myModal");
  const btn = document.querySelector(".myBtn");
  // const span = modal.querySelector(".close");

  btn.addEventListener("click", (e) => {
    e.preventDefault();
    console.log(btn);
    modal.style.display = "block";
  });

  // span.onclick = function () {
  //   modal.style.display = "none";
  // };
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };
}
(() => {
  dropdownProfile();
  submitImportOnchange();
  confirmSubmit();
  showErrorModal();
  showEditModal();
  editButton();
})();
