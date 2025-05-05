<?php
class cursosModel
{
    private $pdo;

    public function __construct($pdo)
    {
        $this->pdo = $pdo;
    }

    public function obtenerCursos()
    {
        $stmt = $this->pdo->prepare('SELECT * FROM cursos');
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function obtenerCursoPorId($id_curso)
    {
        $stmt = $this->pdo->prepare('SELECT * FROM cursos WHERE id_curso = :id_curso');
        $stmt->bindParam(':id_curso', $id_curso, PDO::PARAM_INT);
        $stmt->execute();
        return $stmt->fetch(PDO::FETCH_ASSOC); 
    }


    public function info_cursos($idProfesor)
    {
        $stmt = $this->pdo->prepare('
            SELECT 
                c.id_curso,
                c.nombre_curso AS nombre,
                c.descripcion,
                CONCAT(p.nombre, " ", p.apellido) AS profesor
            FROM Profesor_Curso pc
            INNER JOIN Cursos c ON pc.id_curso = c.id_curso
            INNER JOIN Profesores p ON pc.id_profesor = p.id_profesor
            WHERE pc.id_profesor = :idProfesor
        ');

        $stmt->execute(['idProfesor' => $idProfesor]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
    public function actualizarCursoProfe($idProfe, $idCurso, $nombreCurso, $descripcion, $dia_semana, $hora_inicio, $hora_fin) {
        // Actualizar profesor
        $stmt1 = $this->pdo->prepare(
            'UPDATE profesor_curso 
             SET id_profesor = :id_profesor
             WHERE id_curso = :id_curso'
        );
        $res1 = $stmt1->execute([
            'id_profesor' => $idProfe,
            'id_curso' => $idCurso
        ]);
    
        // Actualizar curso
        $stmt2 = $this->pdo->prepare(
            'UPDATE cursos 
             SET nombre_curso = :nombre_curso, descripcion = :descripcion
             WHERE id_curso = :id_curso'
        );
        $res2 = $stmt2->execute([
            'nombre_curso' => $nombreCurso,
            'descripcion' => $descripcion,
            'id_curso' => $idCurso
        ]);
    
        // Actualizar horario
        $stmt3 = $this->pdo->prepare(
            'UPDATE horarios 
             SET dia_semana = :dia_semana, hora_inicio = :hora_inicio, hora_fin = :hora_fin
             WHERE id_curso = :id_curso'
        );
        $res3 = $stmt3->execute([
            'dia_semana' => $dia_semana,
            'hora_inicio' => $hora_inicio,
            'hora_fin' => $hora_fin,
            'id_curso' => $idCurso
        ]);
    
        // Devolver true solo si las 3 operaciones fueron exitosas
        return $res1 && $res2 && $res3;
    }   
    
    
    

    public function obtenerCursoFullPorId($idCurso)
    {
        $stmt = $this->pdo->prepare('
            SELECT 
                c.nombre_curso AS nombre,
                c.descripcion,
                CONCAT(p.nombre, " ", p.apellido) AS profesor,
                p.telefono AS telefono,
                p.email AS email,   
                h.dia_semana AS horario,
                h.hora_inicio,
                h.hora_fin
            FROM Cursos c
            LEFT JOIN profesor_curso pc ON pc.id_curso = c.id_curso
            LEFT JOIN Profesores p ON pc.id_profesor = p.id_profesor
            LEFT JOIN Direccion d ON p.id_direccion = d.id_direccion
            LEFT JOIN Horarios h ON h.id_curso = c.id_curso
            WHERE c.id_curso = :idCurso
        ');
        $stmt->execute(['idCurso' => $idCurso]);
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }public function eliminarCurso($idCurso) {
        
            // Primero eliminar relaciones
            $stmt = $this->pdo->prepare('DELETE FROM Estudiante_curso WHERE id_curso = :idCurso');
            $stmt->execute([':idCurso' => $idCurso]);
    
            $stmt = $this->pdo->prepare('DELETE FROM profesor_curso WHERE id_curso = :idCurso');
            $stmt->execute([':idCurso' => $idCurso]);
    
            $stmt = $this->pdo->prepare('DELETE FROM Horarios WHERE id_curso = :idCurso');
            $stmt->execute([':idCurso' => $idCurso]);
    
            $stmt = $this->pdo->prepare('DELETE FROM Inscripciones WHERE id_curso = :idCurso');
            $stmt->execute([':idCurso' => $idCurso]);

            // Finalmente eliminar el curso
            $stmt = $this->pdo->prepare('DELETE FROM Cursos WHERE id_curso = :idCurso');
            $stmt->execute([':idCurso' => $idCurso]);
    
            return true;
            
    }
    
    
    
    

    public function obtenerEstudiantesPorCurso($idCurso) {
        $stmt = $this->pdo->prepare("
            SELECT e.nombre, e.apellido, e.email
            FROM Estudiantes e
            INNER JOIN Estudiante_Curso ec ON e.id_estudiante = ec.id_estudiante
            WHERE ec.id_curso = :idCurso
        ");
        $stmt->execute([':idCurso' => $idCurso]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
    


    // Admin
    
    public function agregarCurso($nombre, $descripcion, $diaSemana, $horaInicio, $horaFin, $id_profesor) {
        
    
            // Insertar el curso
            $stmt = $this->pdo->prepare('INSERT INTO Cursos (nombre_curso, descripcion) VALUES (:nombre, :descripcion)');
            $stmt->execute([
                ':nombre' => $nombre,
                ':descripcion' => $descripcion
            ]);
    
            $idCurso = $this->pdo->lastInsertId();
    
            // Insertar horario
            $stmtHorario = $this->pdo->prepare(
                'INSERT INTO Horarios (id_curso, dia_semana, hora_inicio, hora_fin) 
                 VALUES (:idCurso, :diaSemana, :horaInicio, :horaFin)'
            );
            $stmtHorario->execute([
                ':idCurso' => $idCurso,
                ':diaSemana' => $diaSemana,
                ':horaInicio' => $horaInicio,
                ':horaFin' => $horaFin
            ]);
    
            // Insertar relación con profesor
            $stmtProfesor = $this->pdo->prepare(
                'INSERT INTO profesor_curso (id_curso, id_profesor) 
                 VALUES (:idCurso, :id_profesor)'
            );
            $stmtProfesor->execute([
                ':idCurso' => $idCurso,
                ':id_profesor' => $id_profesor
            ]);
    
            return true;
    
       
    }
    
    

}
?>