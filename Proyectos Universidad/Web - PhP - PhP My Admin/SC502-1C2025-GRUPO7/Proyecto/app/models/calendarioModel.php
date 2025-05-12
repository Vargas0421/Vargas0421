  
<?php
class calendarioModel {
    private $pdo;

    public function __construct($pdo) {
        $this->pdo = $pdo;
    }
    public function agregaraCalendario($titulo, $descripcion, $fecha_inicial, $hora, $id) {
        $stmt = $this->pdo->prepare('INSERT INTO Calendario (titulo, descripcion, fecha_inicial, hora, id_profesor) 
        VALUES (:titulo, :descripcion, :fecha_inicial, :hora, :id_profesor)');
        return $stmt->execute([
            'titulo' => $titulo,
            'descripcion' => $descripcion,
            'fecha_inicial' => $fecha_inicial,
            'hora' => $hora,
            'id_profesor' => $id
        ]);
    }

    public function obtenerCalendarioPorId($id_profesor) {
        $stmt = $this->pdo->prepare("SELECT * FROM calendario WHERE id_profesor = ?");
        $stmt->execute([$id_profesor]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function eliminarCalendario($id_calendario) {
        $stmt = $this->pdo->prepare("DELETE FROM calendario WHERE id_calendario = ?");
        $stmt->execute([$id_calendario]); 
    }
    



}
?>
