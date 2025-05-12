<?php
// controllers/HomeController.php
class HomeController {
    public function index() {
        session_start();
        if (!isset($_SESSION['user'])) {
            header('Location: index.php');
            exit;
        }

        $user = $_SESSION['user'];
        require 'views/home.php';
    }
}
?>
