<?php
include("conn.php");
$result = mysqli_query($con,'update guide set available=1,tourist_name = "'.$_POST['user'].'" where id = "'.$_POST['id'].'"');
?>