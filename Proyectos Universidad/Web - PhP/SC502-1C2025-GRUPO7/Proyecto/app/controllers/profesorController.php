<?php
require_once __DIR__ . '/../models/profeModel.php';
class ProfesorController {
    private $pdo;
    public function __construct($pdo) {
        $this->pdo = $pdo;
    }
    public function actualizarProfesor() {
        if (isset($_POST['id_profesor'], $_POST['nombre'], $_POST['apellido'], $_POST['telefono'], $_POST['puesto'])) {
            $profeModel = new profeModel($this->pdo);
            $resultado = $profeModel->updateProfesor($_POST['id_profesor'], $_POST['nombre'], $_POST['apellido'], $_POST['telefono'], $_POST['puesto']);
            
            if ($resultado) {
                header('Location: views/content/profile.php?exitoProfesor');
            } else {
                header('Location: views/content/profile.php?errorProfesor');
            }
            exit();
 
        }
    }
    public function actualizarDireccion() {
        if (isset($_POST['id_profesor'], $_POST['calle'], $_POST['ciudad'], $_POST['estado'], $_POST['codigo_postal'])) {
            $profeModel = new profeModel($this->pdo);
    
            $resultado = $profeModel->updateDireccion($_POST['id_profesor'], $_POST['calle'],$_POST['ciudad'], $_POST['estado'], $_POST['codigo_postal']);
            if ($resultado) {
                header('Location: views/content/profile.php?exitoDireccion');
            } else {
                header('Location: views/content/profile.php?errorDireccion');
            }
            exit();
        }
    }

    public function actualizarPassword() {
        if (isset($_POST['nuevaPassword'], $_POST['confirmarPassword'], $_POST['id_profesor'])) {
    
            if ($_POST['nuevaPassword'] === $_POST['confirmarPassword']) {

                $NuevaPassword = password_hash($_POST['nuevaPassword'], PASSWORD_DEFAULT);
                $profeModel = new profeModel($this->pdo);
                $resultado = $profeModel->updatePassword($_POST['id_profesor'], $NuevaPassword);
                if ($resultado) {
                    header('Location: views/content/profile.php?exitoContraseña');
                }
            } else {
                echo "No son iguales";
                header('Location: views/content/profile.php?errorCoincidencia');
            }
            exit();
        }
        echo "No acepta datos";
    }
    
    

    
}
?>