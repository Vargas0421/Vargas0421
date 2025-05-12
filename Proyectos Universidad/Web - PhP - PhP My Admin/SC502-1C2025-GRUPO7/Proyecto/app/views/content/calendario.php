
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calendario Interactivo con FullCalendar</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@3.2.0/dist/fullcalendar.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@3.2.0/dist/fullcalendar.print.css" rel="stylesheet" media="print">
</head>
<body>
<?php 
$titulo = "Calendario"; 
$_SESSION['vista_anterior'] = 'app/../../../index.php?action=home';
require_once('header/headerIndex.php'); 
require_once('../../config/config.php'); 
require_once('../../models/calendarioModel.php');
require_once('../../controllers/VerificacionController.php');
    $verificacion = new VerificacionController();
    $verificacion->verificarAcceso();   


$id_profesor = $_SESSION['email']['id_profesor'] ?? null;

$calendarioModel = new calendarioModel($pdo);
$eventos = $calendarioModel->obtenerCalendarioPorId($id_profesor);

// Pasa los eventos a un formato que FullCalendar pueda entender
$eventosFormateados = [];

foreach ($eventos as $evento) {
    $eventosFormateados[] = [
        'id' => $evento['id_calendario'],
        'title' => $evento['titulo'],
        'start' => $evento['fecha_inicial'] . 'T' . $evento['hora'],
        'description' => $evento['descripcion'],
        'className' => 'bg-primary text-white'
    ];
}
?>

<div class="container mt-5 ">
    <h2>Calendario Interactivo</h2>
    <button class="btn btn-primary mt-3 mb-3" data-toggle="modal" data-target="#modalCalendario">Agregar Evento</button>
    <div id="calendar"></div>
</div>


<!-- Model Agregar a calendarioS -->
<div class="modal fade" id="modalCalendario" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Nuevo Evento</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="form-calendario" action="../../index.php?action=agregaraCalendario" method="POST" >
                    <div class="form-group">
                        <label>Título</label>
                        <input type="text" class="form-control" name="titulo">
                    </div>
                    <div class="form-group">
                        <label>Descripción</label>
                        <input type="text" class="form-control" name="descripcion">
                    </div>
                    <div class="form-group">
                        <label>Fecha</label>
                        <input type="date" class="form-control" name="fecha_inicial">
                    </div>
                    <div class="form-group">
                        <label>Hora Inicio</label>
                        <input type="time" class="form-control" name="hora">
                    </div>
                    <input type="hidden" type="number" class="form-control" name="id" value="<?= htmlspecialchars($_SESSION['email']['id_profesor']) ?>">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Model que muestra la informacion del evento -->
<div class="modal fade" id="modalEvento" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalTitulo">Detalles del Evento</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <p><strong>Fecha:</strong> <span id="modalFecha"></span></p>
                <p><strong>Hora:</strong> <span id="modalHora"></span></p>
                <p><strong>Descripción:</strong> <span id="modalDescripcion"></span></p>
            </div>
            <div class="modal-footer">
                <form method="POST" action="../../index.php?action=eliminarCalendario" id="eliminarCalendario">
                <input type="hidden" id="idCalendario" name="id_calendario">
                    <button type="submit" class="btn btn-danger">Eliminar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/moment@2.29.1/moment.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@3.2.0/dist/fullcalendar.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
    $(document).ready(function() {
        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            defaultView: 'month',
            events: <?= json_encode($eventosFormateados) ?>,
            eventRender: function(event, element) {
                element.attr('title', event.description);
            },
            eventClick: function(event) {
                const fechaCompleta = moment(event.start).format('YYYY-MM-DD');
                const horaCompleta = moment(event.start).format('HH:mm');

                $('#modalTitulo').text(event.title);
                $('#modalFecha').text(fechaCompleta);
                $('#modalHora').text(horaCompleta);
                $('#modalDescripcion').text(event.description);

                // Id para el eliminar despues
                $('#idCalendario').val(event.id);

                $('#modalEvento').modal('show');
            }
        });
    });
</script>
<?php require_once('footer/footer.php'); ?>

</body>
</html>