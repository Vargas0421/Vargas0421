<?php
// index.php
session_start(); // Es necesario para acceder a $_SESSION

require_once 'config/config.php';
require_once 'controllers/loginController.php';
require_once 'controllers/clasesController.php';
require_once 'controllers/profileController.php';
require_once 'controllers/dashboardController.php';
require_once 'controllers/adminDashboardController.php';
require_once 'controllers/UserController.php';
require_once 'controllers/ProfesorController.php';
require_once 'controllers/gestionEstudianteController.php';
require_once 'controllers/gestionProfesorController.php';
require_once 'controllers/salarioController.php';
require_once 'controllers/gestionCursosController.php';
require_once 'controllers/calendarioController.php';

$action = $_GET['action'] ?? 'login';


switch ($action) {
    case 'login':
        $loginController = new LoginController($pdo);
        $loginController->index();
        break;
    case 'home':
        $dashboardController = new DashboardController();
        $dashboardController->index();
        break;
    case 'adminHome':
        $adminDashboardController = new AdminDashboardController();
        $adminDashboardController->index();
        break;
    case 'logout':
        $loginController = new LoginController($pdo);
        $loginController->logout();
        break;
    case 'actualizarUser':
        $controller = new UserController();
        $controller->actualizarUser();
        break;
    case 'actualizarPerfil':
        $controller = new ProfesorController($pdo);
        $controller->actualizarProfesor();
        break;
    case 'actualizarPassword':
        $controller = new ProfesorController($pdo);
        $controller->actualizarPassword();
        break;
    case 'actualizarDireccion':
        $controller = new ProfesorController($pdo);
        $controller->actualizarDireccion();
        break;
    case 'verClases':
        $clasesController = new ClasesController($pdo);
        $clasesController->abrirClases();
        break;
    case 'verPerfile':
        $profileController = new ProfileController($pdo);
        $profileController->abrirProfile();
        break;
    case 'agregarProfesor':
        $controller = new gestionProfesorController($pdo);
        $controller->agregarProfesor();
        break;
    case 'agregarEstudiante':
        $controller = new gestionEstudianteController($pdo);
        $controller->agregarEstudiante();
        break;
    case 'agregarCursoEstudiante':
        $controller = new gestionEstudianteController($pdo);
        $controller->agregarCursoEstudiante();
        break;
    case 'actualizarInfoCurso':
        $controller = new gestionCursosController($pdo);
        $controller->actualizarInfoCurso();
        break;
    case 'eliminarCurso':
        $controller = new gestionCursosController($pdo);
        $controller->eliminarCurso();
        break;
    case 'agregarCurso':
        $controller = new gestionCursosController($pdo);
        $controller->agregarCurso();
        break;
    case 'eliminarEstudiante':
        $controller = new gestionEstudianteController($pdo);
        $controller->eliminarEstudiante();
        break;
    case 'eliminarProfesor':
        $controller = new gestionProfesorController($pdo);
        $controller->eliminarProfesor();
        break;
    case 'desinscribirEstudiante':
        $controller = new gestionEstudianteController($pdo);
        $controller->desinscribirEstudiante();
        break;
    case 'editarSalarioProfesor':
        $controller = new salarioController($pdo);
        $controller->actualizarSalario();
        break;
    case 'verCalendario':
        $controller = new calendarioController($pdo);
        $controller->abrirCalendario();
        break;
    case 'agregaraCalendario':
        $controller = new calendarioController($pdo);
        $controller->agregaraCalendario();
        break;
    case 'eliminarCalendario':
        $controller = new calendarioController($pdo);
        $controller->eliminarCalendario();
        break;
    default:
        $loginController = new LoginController($pdo);
        $loginController->index();
        break;
}


