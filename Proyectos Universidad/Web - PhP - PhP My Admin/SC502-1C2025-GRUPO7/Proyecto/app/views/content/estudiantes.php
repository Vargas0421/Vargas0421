<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Administración</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/estilos.css">
</head>

<body>
<?php 
session_start();
$titulo = "Manejo de estudiantes"; 
$_SESSION['vista_anterior'] = 'app/../../../index.php?action=home';

require_once('header/headerIndex.php'); 
require_once('../../config/config.php'); 
require_once('../../models/estudiantesModel.php'); 
require_once('../../controllers/VerificacionController.php'); 

$id_profesor = $_SESSION['email']['id_profesor'] ?? null;
$estudiantesModel = new EstudiantesModel($pdo);
$estudiantesPorCurso = $estudiantesModel->obtenerEstudiantesPorCursoProfesor($id_profesor);

// Agrupar estudiantes por curso
$cursosAgrupados = [];
foreach ($estudiantesPorCurso as $estudiante) {
    $idCurso = $estudiante['id_curso'];
    $cursosAgrupados[$idCurso]['nombre_curso'] = $estudiante['nombre_curso'];
    if ($estudiante['id_estudiante']) {
        $cursosAgrupados[$idCurso]['estudiantes'][] = $estudiante;
    }
}
?>

<div class="container">
    <div class="text-center mb-4">
        <p class="text-muted">Aquí puedes ver la información de los estudiantes asignados a tus cursos.</p>
    </div>

    <?php if (!empty($cursosAgrupados)): ?>
        <?php foreach ($cursosAgrupados as $curso): ?>
            <h4 class="mt-4 mb-2"><?= htmlspecialchars($curso['nombre_curso']) ?></h4>
            <div class="table-responsive table-container">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php if (!empty($curso['estudiantes'])): ?>
                            <?php foreach ($curso['estudiantes'] as $estudiante): ?>
                                <tr>
                                    <td><?= htmlspecialchars($estudiante['nombre_estudiante']) . ' ' . htmlspecialchars($estudiante['apellido']) ?></td>
                                    <td>
                                        <button 
                                            class="btn btn-info" 
                                            data-toggle="modal" 
                                            data-target="#modalEstudiante-<?= htmlspecialchars($estudiante['id_estudiante']) ?>"
                                        >
                                            Ver Detalles
                                        </button>
                                    </td>
                                </tr>
                                <!-- Modal -->
                                <div class="modal fade" id="modalEstudiante-<?= htmlspecialchars($estudiante['id_estudiante']) ?>" tabindex="-1" role="dialog" aria-labelledby="modalEstudianteLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="modalEstudianteLabel">Detalles del Estudiante</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button> 
                                            </div>
                                            <div class="modal-body">
                                                <p><strong>Nombre:</strong> <?= htmlspecialchars($estudiante['nombre_estudiante']) ?></p>
                                                <p><strong>Apellido:</strong> <?= htmlspecialchars($estudiante['apellido']) ?></p>
                                                <p><strong>Email:</strong> <?= htmlspecialchars($estudiante['email']) ?></p>
                                                <p><strong>Teléfono:</strong> <?= htmlspecialchars($estudiante['telefono']) ?></p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            <?php endforeach; ?>
                        <?php else: ?>
                            <tr>
                                <td colspan="2">
                                    <div class="alert alert-warning" role="alert">
                                        No hay estudiantes inscritos en este curso.
                                    </div>
                                </td>
                            </tr>
                        <?php endif; ?>
                    </tbody>
                </table>
            </div>
        <?php endforeach; ?>
    <?php else: ?>
        <div class="alert alert-warning text-center">
            No tienes cursos asignados o no hay estudiantes inscritos.
        </div>
    <?php endif; ?>
</div>

<?php require_once('footer/footer.php'); ?>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>

</html>