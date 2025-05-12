<?php
require_once __DIR__ . '/../models/cursosModel.php';
class gestionCursosController
{
    private $pdo;
    public function __construct($pdo)
    {
        $this->pdo = $pdo;
    }
    public function agregarCurso() {
        if (isset($_POST['nombre'], $_POST['descripcion'], $_POST['diaSemana'], $_POST['horaInicio'], $_POST['horaFin'], $_POST['id_profesor'],)) {
            $cursosModel = new cursosModel($this->pdo);
            $resultado = $cursosModel->agregarCurso($_POST['nombre'], $_POST['descripcion'], $_POST['diaSemana'], $_POST['horaInicio'], $_POST['horaFin'], $_POST['id_profesor'],);
            if ($resultado) {
                header('Location: views/content/adminCursos.php?exitoAgregarCurso');
            } else {
                header('Location: views/content/adminCursos.php?errorAgregarCurso');
            }
            exit();
        }
    }

    public function editarCurso()
    {
        if (isset($_POST['nombre'], $_POST['apellido'], $_POST['email'], $_POST['password'], $_POST['telefono'], $_POST['calle'], $_POST['ciudad'], $_POST['estado'], $_POST['codigo_postal'])) {
            $estudiantesModel = new estudiantesModel($this->pdo);
            $resultado = $estudiantesModel->agregarEstudiantes($_POST['nombre'], $_POST['apellido'], $_POST['email'], $_POST['password'], $_POST['telefono'], $_POST['calle'], $_POST['ciudad'], $_POST['estado'], $_POST['codigo_postal']);

            header('Location: views/content/adminEstudiantes.php');
            exit();
        }
    }



    public function eliminarCurso()
    {
        if (isset($_POST['id_curso'])) {
            $cursoModel = new cursosModel($this->pdo);
            $resultado = $cursoModel->eliminarCurso($_POST['id_curso']);
            if ($resultado) {
                header("Location: views/content/adminCursos.php?exitoEliminarCurso");
            } else {
                header("Location: views/content/adminCursos.php?errorEliminarCurso");
            }
            exit();
        }
    }


    public function actualizarInfoCurso()
    {
        if (
            isset(
            $_POST['id_profesor'],
            $_POST['id_curso'],
            $_POST['nombre'],
            $_POST['descripcion'],
            $_POST['horario'],
            $_POST['hora_inicio'],
            $_POST['hora_fin']
        )
        ) {
            $cursoModel = new cursosModel($this->pdo);

            $idProfesor = $_POST['id_profesor'];
            $idCurso = $_POST['id_curso'];
            $nombreCurso = $_POST['nombre'];
            $descripcion = $_POST['descripcion'];
            $diaSemana = $_POST['horario'];
            $horaInicio = $_POST['hora_inicio'];
            $horaFin = $_POST['hora_fin'];

            $resultado = $cursoModel->actualizarCursoProfe(
                $idProfesor,
                $idCurso,
                $nombreCurso,
                $descripcion,
                $diaSemana,
                $horaInicio,
                $horaFin
            );
            if ($resultado) {
                header("Location: views/content/adminVistacurso.php?id=" . $_POST['id_curso'] . "&exitoEditar");
            } else {
                header("Location: views/content/adminVistacurso.php?id=" . $_POST['id_curso'] . "&errorEditar");
            }
            exit();
        } else {
            // Manejo de error si faltan datos
            echo "Faltan datos en el formulario.";
        }
    }


}
?>