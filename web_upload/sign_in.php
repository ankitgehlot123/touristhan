<html ng-app="crudApp">
<head>
<title>Touristhan</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
* {
  box-sizing: border-box;
}

header {
  box-shadow: 0px 5px 5px #999999;
  height: 50px;
  position: relative;
}  
</style>
</head>
<body>

<?php
    include("header.php");
    //open the database
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include("conn.php");
    $result = mysqli_query($con,'select * from guide where name = "'.$_POST['user'].'" and binary password = "'.$_POST['pwd'].'"');
    //Fetch_Rows
    $row = $result->fetch_array(MYSQLI_NUM);
    if($row > 0){
      echo '
            <div>
              <h1>'. $row['1'].'</h1>
              <span>Phone : '. $row['2'].'</span><br>
              <span>Language : '. $row['5'].'</span><br>
              <span>City : '. $row['4'].'</span><br>
              <span>Price : '. $row['3'].'</span><br>';
              if($row[6] == 0){
              echo '<span>Available : Yes<br>';
              }
              else{
                echo '<span>Available : No<br>';
                echo '<span>Tourist Name : '.$row[7];
              }
      echo '<hr style="width:94%;margin-right:6%;">
            </div>'; 
    }
    else
    {
         echo "<script type='text/javascript'>alert('Either Wrong ID or Password');</script>";
         echo "<script>location.href = 'guide_login.php'</script>";
    }
    }
?>
</body>
</html>