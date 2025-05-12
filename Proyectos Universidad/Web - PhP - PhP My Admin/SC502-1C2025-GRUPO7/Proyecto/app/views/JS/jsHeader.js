document.addEventListener("DOMContentLoaded", function () {
    const header = document.querySelector("header");
    const currentPage = window.location.pathname.split("/").pop();
    const urlParams = new URLSearchParams(window.location.search);
    const isDashboard = currentPage === "dashboard.php" || urlParams.get("action") === "home";

    if (isDashboard) {
        header.classList.add("header-dashboard");
    } else {
        header.classList.add("header-default");
    }
});
