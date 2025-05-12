<?php
require_once __DIR__ . '/../models/estudiantesModel.php';
class gestionEstudianteController {
    private $pdo;
    public function __construct($pdo) {
        $this->pdo = $pdo;
    }
    public function agregarEstudiante() {
        if (isset($_POST['nombre'], $_POST['apellido'], $_POST['email'], $_POST['password'], $_POST['telefono'], 
        $_POST['calle'], $_POST['ciudad'], $_POST['estado'], $_POST['codigo_postal'])) {

            $email = $_POST['email'];
            $estudiantesModel = new estudiantesModel($this->pdo);
            $estudiantes = $estudiantesModel->obtenerEstudiantes();
            // Verifica el email
            foreach ($estudiantes as $estudiante) {
                if ($estudiante['email'] === $email) {
                    header('Location: views/content/adminEstudiantes.php?errorEmailIdentico');
                    exit();
                }
            }
            $estudiantesModel = new estudiantesModel($this->pdo);
            $resultado = $estudiantesModel->agregarEstudiantes($_POST['nombre'], $_POST['apellido'], $_POST['email'], $_POST['password'], $_POST['telefono'], $_POST['calle'], $_POST['ciudad'], $_POST['estado'], $_POST['codigo_postal']);
            if ($resultado) {
                header('Location: views/content/adminEstudiantes.php?exitoAgregarEstudiante');
            } else {
                header('Location: views/content/adminEstudiantes.php?errorAgregarEstudiante');
            }
           
            exit();
        }
    }
    
    public function agregarCursoEstudiante() {
        if (isset($_POST['id_estudiante'], $_POST['id_curso'])) {
            $estudiantesModel = new estudiantesModel($this->pdo);
            $resultado = $estudiantesModel->agregarCursoEstudiante($_POST['id_estudiante'], $_POST['id_curso']);

            header("Location: views/content/gestionEstudiante.php?id=" . $_POST['id_estudiante']);
            exit();
        } 
    }
    
    public function eliminarEstudiante() {
        if (isset($_POST['id_estudiante'])) {
            $estudiantesModel = new estudiantesModel($this->pdo);
            $resultado = $estudiantesModel->eliminarEstudiante($_POST['id_estudiante']);

            if ($resultado) {
                header('Location: views/content/adminEstudiantes.php?exitoEliminarEstudiante');
            } else {
                header('Location: views/content/adminEstudiantes.php?errorEliminarEstudiante');
            }
            exit();
        }
    }

    public function desinscribirEstudiante() {
            if (isset($_POST['id_estudiante'], $_POST['id_curso'])) {
            $estudiantesModel = new estudiantesModel($this->pdo);
            $resultado = $estudiantesModel->desinscribirEstudiante($_POST['id_estudiante'], $_POST['id_curso']);
            header("Location: views/content/gestionEstudiante.php?id=" . $_POST['id_estudiante']);
            exit();
        }
    }
}
?>