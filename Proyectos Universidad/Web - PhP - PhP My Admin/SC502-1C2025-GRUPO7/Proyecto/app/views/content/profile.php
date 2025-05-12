<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <title>Perfil</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
    <?php

    $titulo = "Bienvenido a tu perfil";

    // Requiere primero la información del profesor antes de la verificación
    require_once('../../config/config.php');
    require_once('../../models/UserModel.php');
    require_once('../../models/profeModel.php');
    require_once('../../controllers/VerificacionController.php');
    $verificacion = new VerificacionController();
    $verificacion->verificarAcceso();   
    $id = $_SESSION['email']['id_profesor'];
    $profeModel = new profeModel($pdo);
    $infoProfesor = $profeModel->obtenerProfesorPorId($id); 
    $direccion = $profeModel->obtenerDireccionId($id); 
    
    // Luego, verificar el rol de este profesor
    if ((int) htmlspecialchars($infoProfesor['rol_id']) === 1) {
        $_SESSION['vista_anterior'] = 'app/../../../index.php?action=adminHome';
    } else {
        $_SESSION['vista_anterior'] = 'app/../../../index.php?action=home';
    }
    require_once('header/headerIndex.php');

    // Mensajes de actualizar generañ
    $mensaje = "";
    $tipoAlerta = "";
    if (isset($_GET['exitoContraseña'])) {
        $mensaje = "Contraseña actualizada correctamente.";
        $tipoAlerta = "success";
    } elseif (isset($_GET['errorCoincidencia'])) {
        $mensaje = "Las contraseñas no coinciden.";
        $tipoAlerta = "warning";
    } elseif (isset($_GET['exitoProfesor'])) {
        $mensaje = "Datos personales actualizados correctamente.";
        $tipoAlerta = "success";
    } elseif (isset($_GET['errorProfesor'])) {
        $mensaje = "Ocurrió un error al actualizar los datos personales.";
        $tipoAlerta = "danger";
    } elseif (isset($_GET['exitoDireccion'])) {
        $mensaje = "Dirección actualizada correctamente.";
        $tipoAlerta = "success";
    } elseif (isset($_GET['errorDireccion'])) {
        $mensaje = "Ocurrió un error al actualizar la dirección.";
        $tipoAlerta = "danger";
    }

    ?>


    <!-- Contenido principal -->
    <div class="bg-light min-vh-100 d-flex align-items-center">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-12 col-md-10 col-lg-8 mb-4">
                    <div class="profile-header text-center mb-4">
                        <div class="position-relative d-inline-block">
                            <img src="../Images/6522516.png" class="rounded-circle profile-pic"
                                alt="Profile Picture" style="width: 170px; height: 170px;">
                        </div>
                        <?php
                        echo '<h3 class="mt-3 mb-1">' . htmlspecialchars($infoProfesor['nombre']) . ' ' . htmlspecialchars($infoProfesor['apellido']) . '</h3>';
                        echo ' <p class="text-white mb-3">' . htmlspecialchars($infoProfesor['puesto']) . '</p>';
                        ?>
                    </div>
                    
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

                    <!-- Main Content -->
                    <div class="card border-0 shadow-sm">
                        <div class="card-body p-0">
                            <div class="row g-0">
                                <div class="col-12">
                                    <div class="p-4">
                                        <form action="../../index.php?action=actualizarPerfil" method="POST"
                                            id="updateProfesor">
                                            <div class="mb-4">
                                                <h5 class="mb-4">Información Personal</h5>
                                                <div class="row g-3">
                                                    <div class="col-md-6">
                                                        <label class="form-label">Nombre</label>
                                                        <input type="text" class="form-control" name="nombre"
                                                            value="<?= htmlspecialchars($infoProfesor['nombre']) ?>">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label">Apellidos</label>
                                                        <input type="text" class="form-control" name="apellido"
                                                            value="<?= htmlspecialchars($infoProfesor['apellido']) ?>">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label">Telefono</label>
                                                        <input type="text" class="form-control" name="telefono"
                                                            value="<?= htmlspecialchars($infoProfesor['telefono']) ?>">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label">Puesto</label>
                                                        <input type="text" class="form-control" name="puesto"
                                                            value="<?= htmlspecialchars($infoProfesor['puesto']) ?>">
                                                    </div>
                                                    <input type="hidden" name="id_profesor"
                                                        value="<?= htmlspecialchars($_SESSION['email']['id_profesor']) ?>">
                                                    <!-- Campo con el id del profesor -->
                                                    <div class="mt-4">
                                                        <button type="submit" class="btn btn-primary">Guardar
                                                            Cambios</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <!-- Formulario para cambiar contraseña -->
                                        <form action="../../index.php?action=actualizarPassword" method="POST"
                                            id="cambiarPassword">
                                            <div class="mb-4">
                                                <h5 class="mb-4">Cambiar Contraseña</h5>
                                                <div class="row g-3">
                                                    <div class="col-md-6">
                                                        <label class="form-label">Nueva contraseña</label>
                                                        <input type="password" class="form-control" name="nuevaPassword"
                                                            required>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label">Confirmar contraseña</label>
                                                        <input type="password" class="form-control"
                                                            name="confirmarPassword" required>
                                                    </div>
                                                    <input type="hidden" name="id_profesor"
                                                        value="<?= htmlspecialchars($_SESSION['email']['id_profesor']) ?>">
                                                    <div class="mt-4 text-center">
                                                        <button type="submit" class="btn btn-primary">Actualizar
                                                            Contraseña</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>

                                        <!-- Calle, ciudad, estado, código postal -->
                                        <form action="../../index.php?action=actualizarDireccion" method="POST"
                                            id="updateDireccion">
                                            <div class="mb-4">
                                                <h5 class="mb-4">Dirección</h5>
                                                <div class="row g-3">
                                                    <div class="col-md-6">
                                                        <label class="form-label">Calle</label>
                                                        <input type="text" class="form-control" name="calle"
                                                            value="<?= htmlspecialchars($direccion['calle']) ?>">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label">Ciudad</label>
                                                        <input type="text" class="form-control" name="ciudad"
                                                            value="<?= htmlspecialchars($direccion['ciudad']) ?>">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label">Estado</label>
                                                        <input type="text" class="form-control" name="estado"
                                                            value="<?= htmlspecialchars($direccion['estado']) ?>">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="form-label">Código Postal</label>
                                                        <input type="text" class="form-control" name="codigo_postal"
                                                            value="<?= htmlspecialchars($direccion['codigo_postal']) ?>">
                                                    </div>
                                                    <input type="hidden" name="id_profesor"
                                                        value="<?= htmlspecialchars($_SESSION['email']['id_profesor']) ?>">
                                                    <!-- Campo con el id del profesor -->
                                                    <div class="mt-4 text-center">
                                                        <button type="submit" class="btn btn-primary">Guardar
                                                            Cambios</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <?php require_once('footer/footer.php'); ?>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>

</html>