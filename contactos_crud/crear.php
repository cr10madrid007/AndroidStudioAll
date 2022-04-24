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

    $item->nombre = $data->nombre;
    $item->tel = $data->tel;
    $item->latitud = $data->latitud;
    $item->longitud = $data->longitud;    
    $item->photo = $data->photo;
   
    
    if($item->createContacto()){
        echo json_encode(
            array("message" => "Creado"));
    } else{
        echo json_encode(
            array("message" => "No Creado"));
    }
?>