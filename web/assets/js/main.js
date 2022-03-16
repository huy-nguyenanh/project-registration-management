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
  if (!importFile) return;
  importFile.addEventListener("change", () => {
    formImportFile.submit();
  });
}
function editButton() {
  const editBtn = document.getElementById("edit-btn");
  const table = document.querySelector(".table-responsive");
  const tableEdit = document.querySelector(".table-responsive2");

  if (!editBtn) return;
  editBtn.addEventListener("click", (e) => {
    e.preventDefault();
    if (table.style.display !== "none") {
      table.style.display = "none";
      tableEdit.style.display = "block";
    } else {
      table.style.display = "block";
      tableEdit.style.display = "none";
    }
  });
}
function confirmSubmit() {
  // confirm submit button
  const submitBtn = document.querySelector(".confirm-submit");
  if (!submitBtn) return;
  submitBtn.addEventListener("click", () => {
    if (!confirm("Are you sure ?")) {
      return false;
    }
    this.form.submit();
  });
}
function showErrorModal() {
  // Error modal

  const a0 = document.getElementById("error-modal");
  const a1 = document.getElementById("myBtn");
  const a2 = document.getElementsByClassName("close")[0];
  const a3 = document.querySelector("span.error");

  if (a3.textContent !== "") {
    a2.onclick = function () {
      a0.style.display = "none";
      a3.textContent = "";
    };
    window.onclick = function (event) {
      if (event.target == a0) {
        a0.style.display = "none";
        a3.textContent = "";
      }
    };
  }
}
function updateProfile() {
  const btn = document.getElementById("update-profile-btn");
  // const submitBtn = document.getElementById("submit-profile-btn");
  const profileContent1 = document.getElementById("profile-1");
  const profileContent2 = document.getElementById("profile-2");
  // console.log(updateProfileBtn);
  if (!btn) return;

  btn.addEventListener("click", (e) => {
    // e.preventDefault();
    if (profileContent1.style.display !== "none") {
      profileContent1.style.display = "none";
      profileContent2.style.display = "flex";
      btn.textContent = "Back";
      // submitBtn.style.display = "inline-block";
    } else {
      profileContent1.style.display = "flex";
      profileContent2.style.display = "none";
      btn.textContent = "Change Password";
      // submitBtn.style.display = "none";
    }
  });
}
function editMode() {
  const viewBtn = document.getElementById("view-mode");
  const editBtn = document.getElementById("edit-mode");
  const table = document.querySelector(".table-responsive");
  const tableEdit = document.querySelector(".table-responsive2");

  if (!viewBtn || !editBtn) return;

  editBtn.addEventListener("click", (e) => {
    e.preventDefault();
    table.style.display = "none";
    tableEdit.style.display = "block";
  });
  viewBtn.addEventListener("click", (e) => {
    e.preventDefault();
    table.style.display = "block";
    tableEdit.style.display = "none";
  });
}

function validatePassword() {
  const password = document.getElementById("3");
  const confirm = document.getElementById("4");
  if (confirm.value === password.value) {
    confirm.setCustomValidity("");
  } else {
    confirm.setCustomValidity("Passwords do not match");
  }
}
(() => {
  dropdownProfile();
  submitImportOnchange();
  confirmSubmit();
  // editButton();
  // showErrorModal();
  updateProfile();
  editMode();
})();
