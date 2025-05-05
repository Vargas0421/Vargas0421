<?php
require_once __DIR__ . '/../models/salarioModel.php';
class salarioController {
    private $pdo;
    public function __construct($pdo) {
        $this->pdo = $pdo;
    }

    public function actualizarSalario() {
        if (isset($_POST['id_profesor'], $_POST['salarioNuevo'])) {
            $salarioModel = new salarioModel($this->pdo);
            $resultado = $salarioModel->actualizarSalario($_POST['id_profesor'], $_POST['salarioNuevo']);
            if ($resultado) {
                header("Location: views/content/gestionSalario.php?id={$_POST['id_profesor']}" . "&exitoCambiarSalario");
            } else {
                header("Location: views/content/gestionSalario.php?id={$_POST['id_profesor']}" . "&errorCambiarSalario");
            }
            exit(); 
        }
    }

    
    
}
?>