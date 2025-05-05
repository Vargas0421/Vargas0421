<?php
require_once('../../config/config.php');
require_once('../../models/profeModel.php');
require_once('../../controllers/VerificacionController.php');
    $verificacion = new VerificacionController();
    $verificacion->verificarAcceso();   
if (isset($_GET['id'])) {
    $id_profesor = $_GET['id'];
    $profeModel = new profeModel($pdo);
    $profesor = $profeModel->obtenerProfesorPorId($id_profesor);
    $direccion = $profeModel->obtenerDireccionId($id_profesor);

} else {
    echo "No se ha seleccionado un profesor.";
    exit();
}
?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Perfil del usuasrio</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        .card {
            max-width: 600px;
            margin: 0 auto;
        }

        .image-style {
            height: 200px;
            object-fit: cover;
        }
    </style>
</head>

<body>
    <?php
    $titulo = "Área de Gestión";
    $_SESSION['vista_anterior'] = 'adminProfesores.php';

    require_once('header/headerIndex.php');
    ?>



    <div class="container py-5">
        <h1 class="text-center mb-4">Perfil del Usuario</h1>

        <?php if ($profesor): ?>
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">
                        <?= htmlspecialchars($profesor['nombre']) . ' ' . htmlspecialchars($profesor['apellido']) ?>
                    </h5>
                    <p class="card-text"><strong>Puesto:</strong> <?= htmlspecialchars($profesor['puesto']) ?></p>
                    <p class="card-text"><strong>Email:</strong> <?= htmlspecialchars($profesor['email']) ?></p>
                    <p class="card-text"><strong>Dirección:</strong>
                        <?= htmlspecialchars($direccion['calle']) ?>,
                        <?= htmlspecialchars($direccion['ciudad']) ?>,
                        <?= htmlspecialchars($direccion['estado']) ?>,
                        CP <?= htmlspecialchars($direccion['codigo_postal']) ?>
                    </p>
                    </p>
                </div>
            </div>
            <section> 
                <a href="adminProfesores.php" class="btn btn-success    mt-4">Volver al listado</a>
                <?php if($profesor['rol_id'] != 1){?>
                    <form action="../../index.php?action=eliminarProfesor" method="POST"
                        onsubmit="return confirm('Seguro de que deseas eliminar este prfesor?')">
                        <input type="hidden" name="id_profesor" value="<?= htmlspecialchars($id_profesor    ) ?>">
                        <button type="submit" class="btn btn-danger mb-3">Eliminar Usuario</button>
                    </form>
                <?php } ?>
            </section>

        <?php else: ?>
            <p class="text-center text-danger">Profesor con el id encontrado.</p>
        <?php endif; ?>

    </div>

    <?php require_once('footer/footer.php'); ?>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>

</html>