<?php
include("conn.php");
$result = mysqli_query($con,'update hotel set available=1,username = "'.$_POST['user'].'" where id = "'.$_POST['id'].'"');
?>