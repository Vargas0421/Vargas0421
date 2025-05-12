<?php
require_once('../../config/config.php');
require_once('../../models/cursosModel.php');
require_once('../../models/profeModel.php');
require_once('../../controllers/VerificacionController.php');
    $verificacion = new VerificacionController();
    $verificacion->verificarAcceso();   
if (isset($_GET['id'])) {
    $id_curso = $_GET['id']; 
    $profes = new profeModel($pdo);
    $profesores = $profes -> obtenerProfesores();
    $cursoModel = new cursosModel($pdo);
    $curso = $cursoModel->obtenerCursoFullPorId($id_curso);

    if (!$curso) {
        echo '<pre>';
        var_dump($curso);
        echo '<pre>';

        echo "El curso no existe o no se encontró.";
        exit();
    }
    } else {
        echo "No se ha seleccionado un curso.";
        exit();
    }

    $mensaje = "";
    $tipoAlerta = "";
    if (isset($_GET['exitoEditar'])) {
        $mensaje = "El curso fue editado con exito";
        $tipoAlerta = "success";
    } elseif (isset($_GET['exitoEditar'])) {
        $mensaje = "Hubo problemas al editar el curso";
        $tipoAlerta = "danger";
    }  

?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Vista de curso</title>
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
    $titulo = "Área de gestion un curso";
    $_SESSION['vista_anterior'] = 'adminCursos.php';
    require_once('header/headerIndex.php');
    ?>



    <div class="container py-5">
        <h1 class="text-center mb-4">Curso</h1>

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


        <?php if ($curso): ?>
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">
                        <?= htmlspecialchars($curso['nombre']) ?>
                    </h5>
                    <p class="card-text">
                        <strong>Descripción:</strong> <?= htmlspecialchars($curso['descripcion']) ?>
                    </p>
                    <p class="card-text">
                        <strong>Profesor asignad@:</strong> <?= htmlspecialchars($curso['profesor']) ?>
                    </p>
                    <p class="card-text">
                        <strong>Teléfono:</strong> <?= htmlspecialchars($curso['telefono']) ?>
                    </p>
                    <p class="card-text">
                        <strong>Email:</strong> <?= htmlspecialchars($curso['email']) ?>
                    </p>
                    <p class="card-text">
                        <strong>Día:</strong> <?= htmlspecialchars($curso['horario']) ?>
                    </p>
                    <p class="card-text">
                        <strong>Hora de inicio:</strong> <?= htmlspecialchars($curso['hora_inicio']) ?>
                    </p>
                    <p class="card-text">
                        <strong>Hora de fin:</strong> <?= htmlspecialchars($curso['hora_fin']) ?>
                    </p>
                </div>
            </div>
            <section id="botones-acciones">
                <a href="adminCursos.php" class="btn btn-success mt-4">Volver al listado de cursos</a>
                <button class="btn btn-info mt-4" onclick="mostrarFormulario()">Editar información</button>
                <form action="../../index.php?action=eliminarCurso" method="POST" 
                    onsubmit = "return confirm('Seguro de que deseas eliminar este curso? Al hacerlo eliminara tambien la información relacionada al curso, como horarios, profesores y estudiantes asignados al curso ')">
                    <input type="hidden" name="id_curso" value="<?= htmlspecialchars($id_curso) ?>">
                    <button type="submit" class=    "btn btn-danger mb-3">Eliminar curso</button>
                </form>

            </section>
                <form action="../../index.php?action=actualizarInfoCurso" method="post" class="card shadow-sm p-4 mt-4" id="form-edicion"
                    style="display: none;">
                    <input type="hidden" name="id_curso" value="<?= htmlspecialchars($id_curso) ?>">
                    <div class="form-group">
                        <label for="nombre">Nombre del curso</label>
                        <input type="text" class="form-control" id="nombre" name="nombre"
                            value="<?= htmlspecialchars($curso['nombre']) ?>" required>
                    </div>
                    <div class="form-group">
                        <label for="descripcion">Descripción</label>
                        <textarea class="form-control" id="descripcion" name="descripcion" rows="3"
                            required><?= htmlspecialchars($curso['descripcion']) ?></textarea>
                    </div>
                    <div>
                    <label for="descripcion">Profesor a cargo</label>
                    <select name="id_profesor" id="id_profesor" class="form-control" required>
                        <?php foreach ($profesores as $profesor): ?>
                            <option value="<?= htmlspecialchars($profesor['id_profesor']) ?>"
                                <?php if ($profesor['email'] === $curso['email']) echo 'selected'; ?>>
                                <?= htmlspecialchars($profesor['id_profesor']) ?>
                                <?= htmlspecialchars($profesor['nombre']) ?>
                                <?= htmlspecialchars($profesor['apellido']) ?>,
                                @: <?= htmlspecialchars($profesor['email']) ?>,
                                Puesto: <?= htmlspecialchars($profesor['puesto']) ?>.
                            </option>
                        <?php endforeach; ?>
                    </select>

                    </div>
                    <div class="form-group">
                        <label for="horario">Día de la semana</label>
                        <select class="form-control" id="horario" name="horario" required>
                            <?php
                            $dias = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'];
                            foreach ($dias as $dia):
                            ?>
                                <option value="<?= $dia ?>" <?= ($curso['horario'] === $dia) ? 'selected' : '' ?>>
                                    <?= $dia ?>
                                </option>
                            <?php endforeach; ?>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="hora_inicio">Hora de inicio</label>
                        <input type="time" class="form-control" id="hora_inicio" name="hora_inicio"
                            value="<?= htmlspecialchars($curso['hora_inicio']) ?>">
                    </div>

                    <div class="form-group">
                        <label for="hora_fin">Hora de fin</label>
                        <input type="time" class="form-control" id="hora_fin" name="hora_fin"
                            value="<?= htmlspecialchars($curso['hora_fin']) ?>">
                    </div>
                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                    <button type="button" class="btn btn-secondary ml-2" onclick="cancelarEdicion()">Cancelar</button>
                </form>



        <?php endif; ?>

    </div>

    <?php require_once('footer/footer.php'); ?>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../JS/jsCursos.js" defer></script>

</body>

</html>