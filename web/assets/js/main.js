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
