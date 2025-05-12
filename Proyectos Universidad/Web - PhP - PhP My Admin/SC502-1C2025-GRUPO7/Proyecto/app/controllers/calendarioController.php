<?php
require_once __DIR__ . '/../models/calendarioModel.php';
class calendarioController {

    public function abrirCalendario() {
        header('Location: views/content/calendario.php');
    }
    
    public function __construct($pdo) {
        $this->pdo = $pdo;
    }

    public function agregaraCalendario() {
        if (isset($_POST['titulo'], $_POST['descripcion'], $_POST['fecha_inicial'], $_POST['hora'], $_POST['id'])) {
            $calendarioModel = new calendarioModel($this->pdo);
            $resultado = $calendarioModel->agregaraCalendario($_POST['titulo'], $_POST['descripcion'], $_POST['fecha_inicial'], $_POST['hora'], $_POST['id']);
            header('Location: views/content/calendario.php');
            exit();
        }
        echo "nunca pasa";
    }

    public function eliminarCalendario() {
        if (isset($_POST['id_calendario'])) {
            $calendarioModel = new calendarioModel($this->pdo);
            $resultado = $calendarioModel->eliminarCalendario($_POST['id_calendario']);
            header('Location: views/content/calendario.php');
            exit();
        }
        echo "nunca pasa";
    }
}