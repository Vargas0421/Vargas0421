<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cursos Universitarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <?php
    $titulo = "Maneja tus cursos";
    $_SESSION['vista_anterior'] = 'app/../../../index.php?action=home';
    require_once('header/headerIndex.php');
    require_once('../../config/config.php');
    require_once('../../models/UserModel.php');
    require_once('../../models/cursosModel.php'); 
    require_once('../../controllers/VerificacionController.php');
    $verificacion = new VerificacionController();
    $verificacion->verificarAcceso();

    $id_profesor = $_SESSION['email']['id_profesor'];
    $cursosModel = new cursosModel($pdo);
    $cursos = $cursosModel->info_cursos($id_profesor);

    ?>

    <main role="main">
        <div class="container marketing mt-5 pt-5">
            <h2 class="text-center mb-4">Listado de cursos impartidos</h2>
            <div class="row">
                <?php if (!empty($cursos)): ?>
                    <?php foreach ($cursos as $curso): ?>
                        <div class="col-lg-4 mb-4">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title"><?= htmlspecialchars($curso['nombre']) ?></h5>
                                    <p class="card-text">
                                        <strong>Profesor:</strong> <?= htmlspecialchars($curso['profesor']) ?><br>
                                        <strong>Descripción:</strong> <?= htmlspecialchars($curso['descripcion']) ?><br>
                                    </p>
                                    <a href="informacionCurso.php?id=<?= urlencode($curso['id_curso']) ?>"
                                        class="btn btn-primary">Más información</a>
                                </div>
                            </div>
                        </div>
                    <?php endforeach; ?>
                <?php else: ?>
                    <div class="col-12">
                        <div class="alert alert-warning text-center">
                            No hay cursos disponibles para usted en este momento
                        </div>
                    </div>
                <?php endif; ?>
            </div>
        </div>
    </main>

    <?php require_once('footer/footer.php'); ?>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>

</html>