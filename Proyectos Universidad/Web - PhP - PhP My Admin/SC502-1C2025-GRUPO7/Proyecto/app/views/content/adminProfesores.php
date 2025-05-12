<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Manejo de usuarios</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="views/css/profesores.css">
</head>

<body>
    <?php
    ini_set('display_errors', 1);
    ini_set('display_startup_errors', 1);
    error_reporting(E_ALL);

    $titulo = "Manejo de usuarios";
    $_SESSION['vista_anterior'] = 'app/../../../index.php?action=adminHome';

    require_once('header/headerIndex.php');
    require_once('../../config/config.php');
    require_once('../../models/profeModel.php');
    require_once('../../controllers/VerificacionController.php');
    $verificacion = new VerificacionController();
    $verificacion->verificarAcceso();   
    $profeModel = new profeModel($pdo);
    $profesores = $profeModel->obtenerProfesores();
    
    // Mensajes para la gestion de profesor
    $mensaje = "";
    $tipoAlerta = "";
    if (isset($_GET['exitoAgregarProfesor'])) {
        $mensaje = "El profesor fue agregado con exito";
        $tipoAlerta = "success";
    } elseif (isset($_GET['errorAgregarProfesor'])) {
        $mensaje = "Hubo problemas al agregar el profesor";
        $tipoAlerta = "warning";
    } elseif (isset($_GET['errorEmailIdentico'])) {
        $mensaje = "El email existe en el sistema";
        $tipoAlerta = "danger";
    } elseif (isset($_GET['exitoEliminarProfesor'])) {
        $mensaje = "El profesor fue eliminado con exito";
        $tipoAlerta = "success";
    } elseif (isset($_GET['errorEliminarProfesor'])) {
        $mensaje = "Hubo problemas al ingresar el profesor";
        $tipoAlerta = "danger";
    }
    ?>

    <div class="container py-5">
        <h1 class="text-center mb-4">Listado de usuarios</h1>
        
        <!-- Mensajes -->
        <?php if (!empty($mensaje)): ?>
            <div class="container mt-3">
                <div class="alert alert-<?= $tipoAlerta ?> alert-dismissible fade show" role="alert">
                    <?= $mensaje ?>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Cerrar">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        <?php endif; ?>

        <div class="row" id="profesores-container">
            <?php foreach ($profesores as $profesor): ?>
                <div class="col-md-4 d-flex align-items-stretch mb-4">
                    <div class="card shadow-sm w-100">
                        <div class="card-body">
                            <h5 class="card-title">
                                <?= htmlspecialchars($profesor['nombre']) . ' ' . htmlspecialchars($profesor['apellido']) ?>
                            </h5>
                            <p class="card-text">Puesto: <?= htmlspecialchars($profesor['puesto']) ?></p>
                            <p class="card-text">Email: <?= htmlspecialchars($profesor['email']) ?></p>
                            <a href="adminVistaProfesor.php?id=<?= $profesor['id_profesor'] ?>"
                                class="btn btn-outline-primary btn-sm mt-auto">Ver perfil</a>
                        </div>
                    </div>
                </div>
            <?php endforeach; ?>
        </div>


        <div class="mb-3 text-left">
            <button class="btn btn-primary" data-toggle="modal" data-target="#modalAgregarProfesor">
                Agregar un profesor
            </button>
        </div>

        <div class="modal fade" id="modalAgregarProfesor" tabindex="-1" role="dialog"
            aria-labelledby="modalAgregarLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <form action="../../index.php?action=agregarProfesor" method="POST" class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalAgregarLabel">Agregar Nuevo Profesor</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" name="nombre" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="apellido">Apellido</label>
                            <input type="text" name="apellido" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Correo</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="telefono">Contraseña</label>
                            <input type="password" name="password" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="telefono">Teléfono</label>
                            <input type="text" name="telefono" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="puesto">Puesto</label>
                            <input type="text" name="puesto" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="rol_id">Rol</label>
                            <select name="rol_id" class="form-control" required>
                                <option value="1">Admin</option>
                                <option value="2">Usuario Normal</option>
                            </select>
                        </div>
                        <h5>Dirección</h5>
                        <div class="form-group">
                            <label for="calle">Calle</label>
                            <input type="text" name="calle" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="ciudad">Ciudad</label>
                            <input type="text" name="ciudad" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="estado">Estado</label>
                            <input type="text" name="estado" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="codigo_postal">Código Postal</label>
                            <input type="text" name="codigo_postal" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Agregar profesor</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>

    <?php require_once('footer/footer.php'); ?>



    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    <script>
        document.getElementById('formAgregarProfesor').addEventListener('submit', function (e) {
            const pass1 = document.getElementById('password').value;
            const pass2 = document.getElementById('confirm_password').value;

            if (pass1 !== pass2) {
                e.preventDefault(); // Detiene el envío
                alert("Las contraseñas no coinciden.");
            }
        });
    </script>
</body>

</html>