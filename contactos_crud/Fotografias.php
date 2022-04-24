<?php

class Fotografias{
        // Conexion
        private $conn;

        // Tabla
        private $db_table = "tblcontactos";

        // Columnas
        public $contactID;       
        public $photo;
    

        // Constructor de clae
        public function __construct($db){
            $this->conn = $db;
       }

    public function StoreImage(){
        $sqlQuery = "INSERT INTO ". $this->db_table ." SET photo = :photo";

        $stmt = $this->conn->prepare($sqlQuery);

        //binding data
        $stmt->bindParam(":photo", $this->photo);

        if($stmt->execute()){
            return true;
        }
        return false;
    }

    public function getImages(){
        $sqlQuery = "SELECT contactID, photo FROM ".$this->db_table;
        $stmt = $this->conn->prepare($sqlQuery);
        $stmt->execute();
        return $stmt;
    }

}
?>