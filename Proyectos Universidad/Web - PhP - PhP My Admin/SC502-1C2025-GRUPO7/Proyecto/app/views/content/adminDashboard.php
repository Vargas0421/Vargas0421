<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Administracion</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href='views/css/dashboard.css'>
</head>

<body>
    <?php
    $titulo = "Área Administrativa";
    require_once('header/headerIndex.php');
    require_once('controllers/VerificacionController.php');
    $verificacion = new VerificacionController();
    $verificacion->verificarAcceso();
    ?>

    <main role="main">
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Bienvenido administrador <?= htmlspecialchars($user['nombre']) ?>
                    <?= htmlspecialchars($user['apellido']) ?> </h1>
            </div>
        </section>
        <div class="album py-5 bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 d-flex align-items-stretch">
                        <div class="card mb-4 shadow-sm">
                            <img class="card-img-top image-style" src="views/Images/imagenProfesores.png"
                                alt="Profesores">
                            <div class="card-body">
                                <h3>Manejo de profesores</h3>
                                <p class="card-text">Desde aca puesdes administrar los roles y cursos de los profesores
                                </p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a href="views/content/adminProfesores.php"
                                            class="btn btn-sm btn-outline-primary">Abrir manejo de profesores</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 d-flex align-items-stretch">
                        <div class="card mb-4 box-shadow">
                            <img class="card-img-top image-style" src="views/Images/imagenEstudiantesAdmin.png"
                                alt="Estudiantes">
                            <div class="card-body">
                                <h3>Manejo de estudiantes</h3>
                                <p class="card-text">En este elemento podrás administrar los estudiantes y los cursos a
                                    los que estaran asiganados</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a href="views/content/adminEstudiantes.php"
                                            class="btn btn-sm btn-outline-primary">Manejar estudiantes</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 d-flex align-items-stretch">
                        <div class="card mb-4 box-shadow">
                            <img class="card-img-top image-style" src="views/Images/imagenSalarioAdmin.png"
                                alt="Salarios">
                            <div class="card-body">
                                <h3>Admistrar pagos</h3>
                                <p class="card-text">Aca podras administar los pagos de los profesores</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a href="views/content/adminSalario.php"
                                            class="btn btn-sm btn-outline-primary">Administrar pagos</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 d-flex align-items-stretch">
                        <div class="card mb-4 box-shadow">
                            <img class="card-img-top image-style" src="views/Images/imagenCurosAdmin.png" alt="Clases">
                            <div class="card-body">
                                <h3>Manejo de Cursos</h3>
                                <p class="card-text">Aca puesdes administar los cursos, tanto estudiantes como
                                    profesores.</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a href="views/content/adminCursos.php"
                                            class="btn btn-sm btn-outline-primary">Administar cursos</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <?php require_once('footer/footer.php'); ?>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>

</html>