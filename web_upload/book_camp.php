<?php
include("conn.php");
$result = mysqli_query($con,'update camp set available=1,tourist = "'.$_POST['user'].'" where id = "'.$_POST['id'].'"');
?>