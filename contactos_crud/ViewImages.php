<?php

require_once 'Database.php';
require_once 'Fotografias.php';

$database = new Database();
$db = $database->getConnection();

$items = new Fotografias($db);

$stmt = $items->getImages();
$itemCount = $stmt->rowCount();

if($itemCount < 0){
    echo 'No Hay Imagenes';
}
?>

<?php if($itemCount > 0){ ?>
    <div class="gallery">
        <?php while($row = $stmt->fetch(PDO::FETCH_ASSOC)){ ?>
            <img src="data:image/jpg;base64,<?php echo base64_encode($row['photo']); ?>"/>
        <?php } ?>
    </div>
    <?php } else { ?>
        <p class="status error"> Image(s) not found...</p>
    <?php } 
?>
