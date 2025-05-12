<?php
// controllers/dashboardController.php
class dashboardController {
    public function index() {
        if (!isset($_SESSION['email'])) {
            header('Location: index.php');
            exit;
        }

        $user = $_SESSION['email'];
        require 'views/content/dashboard.php';
    }
}
?>
