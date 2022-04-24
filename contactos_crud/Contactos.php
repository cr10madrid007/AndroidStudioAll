<?php
    class Contactos{

        // Conexion
        private $conn;

        // Tabla
        private $db_table = "tblcontactos";

        // Columnas
        public $contactID;
        public $name;
        public $tel;
        public $latitud;
        public $longitud;
        //public $photo;
    

        // Constructor de clae
        public function __construct($db){
            $this->conn = $db;
        }

        // GET todos los empleados
        public function getContactos(){
            $sqlQuery = "SELECT contactID, name, tel, latitud, longitud FROM " . $this->db_table . "";
            $stmt = $this->conn->prepare($sqlQuery);
            $stmt->execute();
            return $stmt;
        }

        // Crear un empleados
        public function createContacto(){
            $sqlQuery = "INSERT INTO
                        ". $this->db_table ."
                    SET
                    name = :name, 
                    tel = :tel, 
                    latitud = :latitud,
                    longitud = :longitud";
        
            $stmt = $this->conn->prepare($sqlQuery);
        
            // sanitize
            $this->nombre=htmlspecialchars(strip_tags($this->name));
            $this->tel=htmlspecialchars(strip_tags($this->tel));
            $this->latitud=htmlspecialchars(strip_tags($this->latitud));
            $this->longitud=htmlspecialchars(strip_tags($this->longitud));           
          
        
            // bind data
            $stmt->bindParam(":name", $this->name);
            $stmt->bindParam(":tel", $this->tel);
            $stmt->bindParam(":latitud", $this->latitud);
            $stmt->bindParam(":longitud", $this->longitud);
            //$stmt->bindParam(":photo", $this->photo);           
           
        
            if($stmt->execute()){
               return true;
            }
            return false;
        }

        // READ single
        public function getSingleContacto(){
            $sqlQuery = "SELECT
                        contactID, 
                        name, 
                        tel, 
                        latitud, 
                        longitud
                      FROM
                        ". $this->db_table ."
                    WHERE 
                       id = ?
                    LIMIT 0,1";

            $stmt = $this->conn->prepare($sqlQuery);

            $stmt->bindParam(1, $this->id);

            $stmt->execute();

            $dataRow = $stmt->fetch(PDO::FETCH_ASSOC);
            
            $this->name = $dataRow['nombre'];
            $this->tel = $dataRow['tel'];
            $this->latitud = $dataRow['latitud'];
            $this->longitud = $dataRow['longitud'];
            //$this->photo = $dataRow['photo'];           
          
        }        

        // UPDATE
        public function updateContacto(){
            $sqlQuery = "UPDATE
                        ". $this->db_table ."
                    SET
                        name = :name, 
                        tel = :tel, 
                        latitud = :latitud,
                        longitud = :longitud
                    WHERE 
                        contactoID = :id";

            //echo $sqlQuery;
        
            $stmt = $this->conn->prepare($sqlQuery);           
           
            $this->contactID=htmlspecialchars(strip_tags($this->contactID));
            $this->tel=htmlspecialchars(strip_tags($this->tel));
            $this->latitud=htmlspecialchars(strip_tags($this->latitud));
            $this->longitud=htmlspecialchars(strip_tags($this->longitud)); 
            //$this->photo=htmlspecialchars(strip_tags($this->photo)); 
            
        
            // bind data
            $stmt->bindParam(":contactID", $this->contactID);
            $stmt->bindParam(":name", $this->name);
            $stmt->bindParam(":tel", $this->tel);
            $stmt->bindParam(":latitud", $this->latitud);
            $stmt->bindParam(":longitud", $this->longitud);
            //$stmt->bindParam(":photo", $this->photo);
         
        
            if($stmt->execute()){
               return true;
            }
            return false;
        }

        // DELETE
        function deleteContacto(){
            $sqlQuery = "DELETE FROM " . $this->db_table . " WHERE contactoID = ?";
            $stmt = $this->conn->prepare($sqlQuery);
        
            $this->id=htmlspecialchars(strip_tags($this->contactID));
        
            $stmt->bindParam(1, $this->contactID);
        
            if($stmt->execute()){
                return true;
            }
            return false;
        }

    }
?>