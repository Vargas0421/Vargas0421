<?php
class adminDashboardController {
    public function index() {
        
        if (!isset($_SESSION['email'])) {
            header('Location: index.php');
            exit;
        }

        $user = $_SESSION['email'];
        require 'views/content/adminDashboard.php';
    }
}
?>
