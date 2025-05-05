<?php
// controllers/LoginController.php
require_once __DIR__ . '/../models/UserModel.php';

class LoginController
{
    private $userModel;

    public function __construct($pdo)
    {
        $this->userModel = new UserModel($pdo);
    }


    public function index() {
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $username = $_POST['email'];
            $password = $_POST['password'];
    
            // Busca el email del usuario unicamente
            $user = $this->userModel->login($username);
    
            if ($user) {
                // Verifica la contrase;a encriptada
                if (password_verify($password, $user['password'])) {
                    session_start();
                    $_SESSION['email'] = $user;
    
                    if ($user['rol_id'] == 1) {
                        header('Location: index.php?action=adminHome');
                    } elseif ($user['rol_id'] == 2) {
                        header('Location: index.php?action=home');
                    } else {
                        header('Location: index.php?action=home');
                    }
                    exit;
                } else {
                    $error = 'Contraseña incorrecta';
                    require 'views/content/login.php';
                }
            } else {
                $error = 'Usuario no encontrado';
                require 'views/content/login.php';
            }
        } else {
            require __DIR__ . '/../views/content/login.php';
        }
    }
    



    public function logout() {
        session_start();
        session_destroy();
        header('Location: index.php');
        exit;
    }
}
?>
