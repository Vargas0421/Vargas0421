<?php

class EstudiantesModel {
    private $pdo;

    public function __construct($pdo) {
        $this->pdo = $pdo;
    }

    public function obtenerEstudiantes() { 
        $stmt = $this->pdo->prepare('SELECT * FROM estudiantes');
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC); 
    }

    public function obtenerEstudiantesPorCursoProfesor($idProfesor) {
        $stmt = $this->pdo->prepare('
            SELECT 
                c.id_curso,
                c.nombre_curso,
                e.id_estudiante,
                e.nombre AS nombre_estudiante,
                e.apellido,
                e.email,
                e.telefono
            FROM cursos c
            INNER JOIN profesor_curso cp ON cp.id_curso = c.id_curso
            LEFT JOIN estudiante_curso ce ON ce.id_curso = c.id_curso
            LEFT JOIN estudiantes e ON e.id_estudiante = ce.id_estudiante
            WHERE cp.id_profesor = :idProfesor
            ORDER BY c.nombre_curso, e.apellido
        ');
        $stmt->execute(['idProfesor' => $idProfesor]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
    

    public function obtenerCursosEstudiante($idEstudiante) {
        $stmt = $this->pdo->prepare(
            'SELECT c.id_curso, c.nombre_curso 
            FROM estudiante_curso ec
            INNER JOIN cursos c ON ec.id_curso = c.id_curso
            WHERE ec.id_estudiante = :id_estudiante'
        );
        $stmt->execute(['id_estudiante' => $idEstudiante]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function agregarEstudiantes($nombre, $apellido, $email, $password, $telefono, $calle, $ciudad, $estado, $codigo_postal) {
        try {
            // 1. Verificar si la dirección ya existe
            $stmtBuscar = $this->pdo->prepare(
                'SELECT id_direccion FROM Direccion 
                 WHERE calle = :calle AND ciudad = :ciudad AND estado = :estado AND codigo_postal = :codigo_postal'
            );
            $stmtBuscar->execute([
                'calle' => $calle,
                'ciudad' => $ciudad,
                'estado' => $estado,
                'codigo_postal' => $codigo_postal
            ]);
        
            $direccion = $stmtBuscar->fetch(PDO::FETCH_ASSOC);
        
            // 2. Si la dirección ya existe, se usa el id_direccion encontrado
            if ($direccion) {
                $id_direccion = $direccion['id_direccion'];
            } else {
                // Si no existe, se inserta una nueva dirección
                $stmtInsertar = $this->pdo->prepare(
                    'INSERT INTO Direccion (calle, ciudad, estado, codigo_postal) 
                     VALUES (:calle, :ciudad, :estado, :codigo_postal)'
                );
                $stmtInsertar->execute([
                    'calle' => $calle,
                    'ciudad' => $ciudad,
                    'estado' => $estado,
                    'codigo_postal' => $codigo_postal
                ]);
                $id_direccion = $this->pdo->lastInsertId();
            }
    
            // 3. Insertar el estudiante con la dirección asociada
            $stmtEstudiante = $this->pdo->prepare(
                'INSERT INTO Estudiantes (nombre, apellido, email, password, telefono, id_direccion) 
                 VALUES (:nombre, :apellido, :email, :password, :telefono, :id_direccion)'
            );
    
            return $stmtEstudiante->execute([
                'nombre' => $nombre,
                'apellido' => $apellido,
                'email' => $email,
                'password' => $password,
                'telefono' => $telefono,
                'id_direccion' => $id_direccion
            ]);
        } catch (PDOException $e) {
            error_log('Error al agregar estudiante: ' . $e->getMessage());
            return false;
        }
    }
    
    
    public function obtenerCursos() {
        $stmt = $this->pdo->prepare('SELECT * FROM cursos');
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function CursosNoIngresados($idEstudiante) {
        $stmt = $this->pdo->prepare(
            'SELECT c.id_curso, c.nombre_curso FROM cursos c
            WHERE c.id_curso NOT IN (
                SELECT ec.id_curso 
                FROM estudiante_curso ec 
                WHERE ec.id_estudiante = :id_estudiante
            )'
        );
        $stmt->execute(['id_estudiante' => $idEstudiante]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC); 
    }

    public function agregarCursoEstudiante($idEstudiante, $idCurso) {
        $stmt = $this->pdo->prepare(
            'INSERT INTO estudiante_curso (id_estudiante, id_curso) 
             VALUES (:id_estudiante, :id_curso)'
        );
        return $stmt->execute([
            'id_estudiante' => $idEstudiante,
            'id_curso' => $idCurso
        ]);
    }

    public function eliminarEstudiante($idEstudiante) {
        $stmt = $this->pdo->prepare('DELETE FROM pagos WHERE id_estudiante = :id_estudiante');
        $stmt->execute(['id_estudiante' => $idEstudiante]);

        $stmt = $this->pdo->prepare('DELETE FROM inscripciones WHERE id_estudiante = :id_estudiante');
        $stmt->execute(['id_estudiante' => $idEstudiante]);

        $stmt = $this->pdo->prepare('DELETE FROM estudiante_curso WHERE id_estudiante = :id_estudiante');
        $stmt->execute(['id_estudiante' => $idEstudiante]);

        $stmt = $this->pdo->prepare('DELETE FROM estudiantes WHERE id_estudiante = :id_estudiante');
        return $stmt->execute(['id_estudiante' => $idEstudiante]);
    }

    public function desinscribirEstudiante($idEstudiante, $idCurso) {
        $stmt = $this->pdo->prepare('DELETE FROM estudiante_curso 
             WHERE id_estudiante = :id_estudiante AND id_curso = :id_curso'
        );
        return $stmt->execute([
            'id_estudiante' => $idEstudiante,
            'id_curso' => $idCurso
        ]);
    }
}
?>