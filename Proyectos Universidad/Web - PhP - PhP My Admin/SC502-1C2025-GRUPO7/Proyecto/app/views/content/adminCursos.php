<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Manejo Cursos</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="views/css/profesores.css">
</head>

<body>
    <?php

    $titulo = "Manejo de cursos";
    $_SESSION['vista_anterior'] = 'app/../../../index.php?action=adminHome';

    require_once('header/headerIndex.php');
    require_once('../../models/cursosModel.php');
    require_once('../../models/profeModel.php');
    require_once('../../config/config.php');
    require_once('../../controllers/VerificacionController.php');
    $verificacion = new VerificacionController();
    $verificacion->verificarAcceso();   
    $cursoModel = new cursosModel($pdo);
    $cursos = $cursoModel->obtenerCursos();
    $profeModel = new profeModel($pdo);
    $profesores = $profeModel->obtenerProfesores();

    $mensaje = "";
    $tipoAlerta = "";
    if (isset($_GET['exitoAgregarCurso'])) {
        $mensaje = "El curso fue agregado con exito";
        $tipoAlerta = "success";
    } elseif (isset($_GET['errorAgregarCurso'])) {
        $mensaje = "Hubo problemas al agregar el curso";
        $tipoAlerta = "danger";
    } elseif (isset($_GET['exitoEliminarCurso'])) {
        $mensaje = "El curso fue eliminado con exito";
        $tipoAlerta = "success";
    } elseif (isset($_GET['errorEliminarCurso'])) {
        $mensaje = "Hubo problemas al eliminar el curso";
        $tipoAlerta = "danger";
    } 
    ?>

    <div class="container py-5">
        <h1 class="text-center mb-4">Listado de cursos</h1>

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
            <?php foreach ($cursos as $curso): ?>
                <div class="col-md-4 d-flex align-items-stretch mb-4">
                    <div class="card shadow-sm w-100">
                        <div class="card-body">
                            <h5 class="card-title">
                                <?= htmlspecialchars($curso['id_curso']) . '-' . htmlspecialchars($curso['nombre_curso']) ?>
                            </h5>
                            <p class="card-text">Puesto: <?= htmlspecialchars($curso['descripcion']) ?></p>
                            <a href="adminVistacurso.php?id=<?= $curso['id_curso'] ?>"
                                class="btn btn-outline-primary btn-sm mt-auto">Ver curso</a>
                        </div>
                    </div>
                </div>
            <?php endforeach; ?>
        </div>

        <div class="mb-3 text-left">
            <button class="btn btn-primary" data-toggle="modal" data-target="#modalAgregarCurso">
                Agregar un curso
            </button>
        </div>

        <div class="modal fade" id="modalAgregarCurso" tabindex="-1" role="dialog" aria-labelledby="modalAgregarLabel"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <form action="../../index.php?action=agregarCurso" method="POST" class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalAgregarLabel">Agregar un nuevo curso </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="nombre">Nombre del curso </label>
                            <input type="text" name="nombre" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="descripcion">Descripcion</label>
                            <input type="text" name="descripcion" class="form-control" required>
                        </div>
                        <h5>Horario</h5>
                        <div class="form-group"> 
                            <label for="diaSemana">Día de la semana</label>
                            <select name="diaSemana" class="form-control" required>
                                <option value="">Seleccione un día</option>
                                <option value="Lunes">Lunes</option>
                                <option value="Martes">Martes</option>
                                <option value="Miércoles">Miércoles</option>
                                <option value="Jueves">Jueves</option>
                                <option value="Viernes">Viernes</option>
                                <option value="Sábado">Sábado</option>
                                <option value="Domingo">Domingo</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="horaInicio">Hora de inicio</label>
                            <input type="time" name="horaInicio" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="horaFin">Hora de fin</label>
                            <input type="time" name="horaFin" class="form-control" required>
                        </div>
                        <div>
                            <label for="descripcion">Profe a cargo</label>
                            <select name="id_profesor" id="id_profesor" class="form-control" required>
                                <?php foreach ($profesores as $profesor): ?>
                                    <option value="<?= htmlspecialchars($profesor['id_profesor']) ?>">
                                        <?= htmlspecialchars($profesor['id_profesor']) ?>
                                        <?= htmlspecialchars($profesor['nombre']) ?>
                                        <?= htmlspecialchars($profesor['apellido']) ?>,
                                        @: <?= htmlspecialchars($profesor['email']) ?>,
                                        Puesto: <?= htmlspecialchars($profesor['puesto']) ?>.
                                    </option>
                                <?php endforeach; ?>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Agregar el curso </button>
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


</body>

</html>