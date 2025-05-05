function mostrarFormulario() {
    document.querySelector('.card-body').style.display = 'none'; // Oculta la vista actual
    document.getElementById('form-edicion').style.display = 'block'; // Muestra el formulario
    document.getElementById("botones-acciones").style.display = "none";

}

function cancelarEdicion() {
    document.getElementById('form-edicion').style.display = 'none'; // Oculta el formulario
    document.querySelector('.card-body').style.display = 'block'; // Vuelve a mostrar la vista
    document.getElementById("botones-acciones").style.display = "block";

}