<?php
$titulo = "Salario";
$_SESSION['vista_anterior'] = 'app/../../../index.php?action=home';

require_once('header/headerIndex.php');
require_once('../../config/config.php');
require_once('../../models/salarioModel.php');
require_once('../../controllers/VerificacionController.php');
$verificacion = new VerificacionController();
$verificacion->verificarAcceso();
$id_profesor = $_SESSION['email']['id_profesor'];
$salarioModel = new salarioModel($pdo);
$salarios = $salarioModel->historialSalario($id_profesor);
$salarioActual = $salarioModel->salarioActual($id_profesor);
$salarioActual = $salarioActual[0];
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Dashboard de salarios</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/dashboard.css">

</head>

<body>
  <div class="container-fluid mt-5 pt-3">
    <h2>Dashboard</h2>
    <div class="mb-4">
      <h4>Salario Actual</h4>
      <?php if ($salarios): ?>
        <p><strong>Cantidad:</strong> <?= number_format($salarioActual['cantidad']) ?></p>
        <p><strong>Última Actualización:</strong> <?= htmlspecialchars($salarioActual['fecha']) ?></p>
      <?php else: ?>
        <p>No hay información sobre el salario actual.</p>
      <?php endif; ?>
    </div>
    <canvas id="myChart" width="900" height="380"></canvas>
    <div class="table-responsive">
      <table class="table table-striped table-sm">
        <thead>
          <tr>
            <th>#</th>
            <th>Cantidad</th>
            <th>Fecha</th>
          </tr>
        </thead>
        <tbody>
          <?php
          $contador = 1;
          foreach ($salarios as $salario) {
            echo "<tr>";
            echo "<td>" . $contador++ . "</td>";
            echo "<td>" . number_format($salario['cantidad']) . "</td>";
            echo "<td>" . htmlspecialchars($salario['fecha']) . "</td>";
            echo "</tr>";
          }
          ?>
        </tbody>
      </table>
    </div>
  </div>
  <?php require_once('footer/footer.php'); ?>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
  <script>
    var ctx = document.getElementById("myChart").getContext("2d");
    var myChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: [<?php foreach ($salarios as $salario) {
          echo "'" . $salario['fecha'] . "',";
        } ?>],
        datasets: [{
          data: [<?php foreach ($salarios as $salario) {
            echo $salario['cantidad'] . ",";
          } ?>],
          lineTension: 0,
          backgroundColor: 'transparent',
          borderColor: '#007bff',
          borderWidth: 4,
          pointBackgroundColor: '#007bff'
        }]
      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: false
            }
          }]
        },
        legend: {
          display: false,
        }
      }
    });
  </script>
</body>

</html>