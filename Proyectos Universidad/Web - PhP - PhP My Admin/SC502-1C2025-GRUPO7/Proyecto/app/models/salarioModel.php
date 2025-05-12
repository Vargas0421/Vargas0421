<?php

class salarioModel {
    private $pdo;

    public function __construct($pdo) {
        $this->pdo = $pdo;
    }

    public function historialSalario($idProfesor) {
        $stmt = $this->pdo->prepare('
            SELECT 
                h.salario AS cantidad,
                h.fecha_inicio AS fecha
            FROM Historial_Salarios h
            WHERE h.id_profesor = :id_profesor
        ');

        $stmt->execute(['id_profesor' => $idProfesor]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }     

    public function salarioActual($idProfesor) {
        $stmt = $this->pdo->prepare('
            SELECT 
                s.salario AS cantidad,
                s.fecha_actualizacion AS fecha
            FROM Salarios s
            WHERE s.id_profesor = :id_profesor
        ');

        $stmt->execute(['id_profesor' => $idProfesor]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }   

    //Admin

    public function actualizarSalario($idProfesor, $salarioNuevo) {
        $stmt = $this->pdo->prepare("
            INSERT INTO Historial_Salarios (id_profesor, salario, fecha_inicio)
            VALUES (:id_profesor, :salarioNuevo, NOW())");
        $stmt->execute([
            ':id_profesor' => $idProfesor,
            ':salarioNuevo' => $salarioNuevo]);
            // Revisar si existen columnas con el id del profesor
        $stmt = $this->pdo->prepare("SELECT COUNT(*) FROM Salarios WHERE id_profesor = :id_profesor");
        $stmt->execute([':id_profesor' => $idProfesor]);
        $existeSalario = $stmt->fetchColumn();
            // El profesor tiene un salario actual
        if ($existeSalario) {
            $stmt = $this->pdo->prepare("
                UPDATE Salarios SET salario = :salarioNuevo, fecha_actualizacion = NOW()
                WHERE id_profesor = :id_profesor");
            // El profesor aun no tiene un salario actual
        } else {
            $stmt = $this->pdo->prepare("
                INSERT INTO Salarios (id_profesor, salario, fecha_actualizacion)
                VALUES (:id_profesor, :salarioNuevo, NOW())");
        }
    
        $stmt->execute([
            ':salarioNuevo' => $salarioNuevo,
            ':id_profesor' => $idProfesor
        ]);
        return true;
    }
    
}

?>