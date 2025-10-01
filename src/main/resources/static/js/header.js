document.addEventListener("DOMContentLoaded", function () {
    const btnOpcoes = document.getElementById("btnOpcoes");
    const sidebar = document.querySelector(".sidebar");

    if (!sidebar) return;

    // Recupera o estado salvo no localStorage
    const sidebarState = localStorage.getItem("sidebarState");
    if (sidebarState === "open") {
        sidebar.classList.remove("hidden");
    } else {
        sidebar.classList.add("hidden");
    }


    if (btnOpcoes) {
        btnOpcoes.addEventListener("click", function () {
            sidebar.classList.toggle("hidden");

            // Salva o estado
            if (sidebar.classList.contains("hidden")) {
                localStorage.setItem("sidebarState", "closed");
            } else {
                localStorage.setItem("sidebarState", "open");
            }
        });
    }
});
