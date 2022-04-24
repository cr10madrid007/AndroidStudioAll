<?php
    header("Access-Control-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");
    header("Access-Control-Max-Age: 3600");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    
    include_once 'Database.php';
    include_once 'Contactos.php';
    
    $database = new Database();
    $db = $database->getConnection();
    
    $item = new Contactos($db);
    
    $data = json_decode(file_get_contents("php://input"));
    
    $item->id = $data->id;

    // Valores de la clase empleado
    $item->nombre = $data->nombre;
    $item->tel = $data->tel;
    $item->latitud = $data->latitud;
    $item->longitud = $data->longitud;    
    $item->photo = $data->photo;
  
    
    if($item->updateContacto()){
        echo json_encode("Contacto actualizado.");
    } else{
        echo json_encode("Contacto no actualizado");
    }
?>