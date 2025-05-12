<?php

class profeModel {
    private $pdo;

    public function __construct($pdo) {
        $this->pdo = $pdo;
    }

    public function agregarProfesor($nombre, $apellido, $email, $password, $telefono, $puesto, $rol_id, $calle, $ciudad, $estado, $codigo_postal) {
        // 1. Verificar si la dirección ya existe
        $stmtBuscar = $this->pdo->prepare(
            'SELECT id_direccion FROM direccion 
             WHERE calle = :calle AND ciudad = :ciudad AND estado = :estado AND codigo_postal = :codigo_postal'
        );
    
        $stmtBuscar->execute([
            'calle' => $calle,
            'ciudad' => $ciudad,
            'estado' => $estado,
            'codigo_postal' => $codigo_postal
        ]);
    
        $direccion = $stmtBuscar->fetch(PDO::FETCH_ASSOC);
    
        // 2. Si no existe, insertar la dirección
        if ($direccion) {
            $idDireccion = $direccion['id_direccion'];
        } else {
            $stmtInsertar = $this->pdo->prepare(
                'INSERT INTO direccion (calle, ciudad, estado, codigo_postal) 
                 VALUES (:calle, :ciudad, :estado, :codigo_postal)'
            );
    
            $stmtInsertar->execute([
                'calle' => $calle,
                'ciudad' => $ciudad,
                'estado' => $estado,
                'codigo_postal' => $codigo_postal
            ]);
    
            $idDireccion = $this->pdo->lastInsertId();
        }
    
        // 3. Insertar al profesor
        $stmtProfesor = $this->pdo->prepare(
            'INSERT INTO profesores (nombre, apellido, email, password, telefono, puesto, rol_id, id_direccion) 
             VALUES (:nombre, :apellido, :email, :password, :telefono, :puesto, :rol_id, :id_direccion)'
        );
    
        return $stmtProfesor->execute([
            'nombre' => $nombre,
            'apellido' => $apellido,
            'email' => $email,
            'password' => $password,
            'telefono' => $telefono,
            'puesto' => $puesto,
            'rol_id' => $rol_id,
            'id_direccion' => $idDireccion
        ]);
    }
    
    

    public function eliminarProfesor($idProfesor) {
        $stmt = $this->pdo->prepare('DELETE FROM profesor_curso WHERE id_profesor = :id_profesor');
        $stmt->execute(['id_profesor' => $idProfesor]);

        $stmt = $this->pdo->prepare('DELETE FROM historial_salarios WHERE id_profesor = :id_profesor');
        $stmt->execute(['id_profesor' => $idProfesor]);

        $stmt = $this->pdo->prepare('DELETE FROM salarios WHERE id_profesor = :id_profesor');
        $stmt->execute(['id_profesor' => $idProfesor]);

        $stmt = $this->pdo->prepare('DELETE FROM profesores WHERE id_profesor = :id_profesor');
        return $stmt->execute(['id_profesor' => $idProfesor]);

        
    }

    public function updateProfesor($id, $nombre, $apellido, $telefono, $puesto) {
        $stmt = $this->pdo->prepare(
            'UPDATE profesores 
                SET nombre = :nombre, apellido = :apellido, telefono = :telefono, puesto = :puesto 
                WHERE id_profesor = :id_profesor'
        );
        $resultado = $stmt->execute([
            'nombre' => $nombre,
            'apellido' => $apellido,
            'telefono' => $telefono,
            'puesto' => $puesto,
            'id_profesor' => $id
        ]);
        
        return $resultado;
    }

    public function updatePassword($id, $password) {
        $stmt = $this->pdo->prepare('UPDATE profesores SET password = :password WHERE id_profesor = :id');
        return $stmt->execute([
            'password' => $password,
            'id' => $id
        ]);
    }

    public function updateDireccion($id, $calle, $ciudad, $estado, $codigo_postal) {
        $stmt = $this->pdo->prepare(
            "UPDATE Direccion 
            SET calle = :calle, ciudad = :ciudad, estado = :estado, codigo_postal = :codigo_postal
            WHERE id_direccion = (
                SELECT id_direccion FROM Profesores WHERE id_profesor = :id_profesor
            )"
        );
        $resultado = $stmt->execute([
            'calle' => $calle,
            'ciudad' => $ciudad,
            'estado' => $estado, 
            'codigo_postal' => $codigo_postal,
            'id_profesor' => $id
        ]);
        
        return $resultado;
    }

    public function obtenerDireccionId($idProfesor) {
        $stmt = $this->pdo->prepare('
            SELECT d.calle, d.ciudad, d.estado, d.codigo_postal FROM Direccion d
            INNER JOIN Profesores p ON d.id_direccion = p.id_direccion
            WHERE p.id_profesor = :id_profesor
        ');
        $stmt->execute(['id_profesor' => $idProfesor]);
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }
    public function obtenerProfesores() { 
        $stmt = $this->pdo->prepare("SELECT * FROM profesores WHERE rol_id = 2");
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
    public function obtenerProfesorPorId($id_profesor) {
        $stmt = $this->pdo->prepare("SELECT * FROM profesores WHERE id_profesor = :id_profesor");
        $stmt->bindParam(':id_profesor', $id_profesor, PDO::PARAM_INT);
        $stmt->execute();
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    public function obtenerTodosProfesores() { 
        $stmt = $this->pdo->prepare("SELECT * FROM profesores");
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
}

?>