<?php
    header("Access-Control-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");
    
    include_once 'Database.php';
    include_once 'Contactos.php';

    $database = new Database();
    $db = $database->getConnection();

    $items = new Contactos($db);

    $stmt = $items->getContactos();
    $itemCount = $stmt->rowCount();


    //echo json_encode($itemCount);

   
    if($itemCount > 0){
        
        $contactoArr = array();
        $contactoArr["contacto"] = array();
        //$employeeArr["itemCount"] = $itemCount;

        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
            extract($row);
            $e = array(
                "contactoID" => $contactID,
                "name" => $name,
                "tel" => $tel,
                "latitud" => $latitud,
                "longitud" => $longitud
            );

            array_push($contactoArr["contacto"], $e);
        }
        echo json_encode($contactoArr);
    }

    else{
        http_response_code(404);
        echo json_encode(
            array("message" => "No record found.")
        );
    }
    
?>