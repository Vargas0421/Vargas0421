<?php
class UserModel {
    private $pdo;

    public function __construct($pdo) {
        $this->pdo = $pdo;
    }

    public function login($email) {
        $stmt = $this->pdo->prepare('SELECT * FROM profesores WHERE email = :email');
        $stmt->execute(['email' => $email]);
        $user = $stmt->fetch(PDO::FETCH_ASSOC);

        if ($user) {
            return $user;
        }
        return false;
    }

    public function updateProfesor($id_profesor, $nombre, $apellido, $puesto) {
        $stmt = $this->db->prepare("UPDATE profesores SET nombre = ?, apellido = ?, puesto = ? WHERE id_profesor = ?");
        $stmt->bind_param('sssi', $nombre, $apellido, $puesto, $id_profesor);
        $stmt->execute();
    }
    
    

}
?>