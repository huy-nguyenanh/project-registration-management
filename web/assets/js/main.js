// $(document).ready(function () {
//   $('a[data-toggle="tab"]').on("show.bs.tab", function (e) {
//     localStorage.setItem("activeTab", $(e.target).attr("href"));
//   });
//   var activeTab = localStorage.getItem("activeTab");
//   if (activeTab) {
//     $('#myTab a[href="' + activeTab + '"]').tab("show");
//   }
// });

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

// submit file on change
const formImportFile = document.getElementById("import-form");
const importFile = document.getElementById("import-file");
importFile.addEventListener("change", () => {
  formImportFile.submit();
});

//////
const modal = document.getElementById("myModal");
const btn = document.getElementById("myBtn");
const span = modal.querySelector(".close");

btn.onclick = function (e) {
  e.preventDefault();
  modal.style.display = "block";
};

span.onclick = function () {
  modal.style.display = "none";
};

window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
};

// show error
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

//
const submitBtn = document.querySelector(".confirm-submit");
submitBtn.addEventListener("click", () => {
  if (!confirm("Are you sure ?")) {
    return false;
  }
  this.form.submit();
});
