<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <meta name="viewport"
      content="
          height = [pixel_value | "device-height"] ,
          width = [pixel_value | "device-width"] ,
          initial-scale = float_value ,
          minimum-scale = float_value ,
          maximum-scale = float_value ,
          user-scalable = ["yes" | "no"]
          " />
    <title>Touristhan</title>

<link href="css/ionic.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<style>
.platform-ios .manual-ios-statusbar-padding{
        padding-top:20px;
      }
      .manual-remove-top-padding{
        padding-top:0px; 
      }
      .manual-remove-top-padding .scroll{
        padding-top:0px !important;
      }
      ion-list.manual-list-fullwidth div.list, .list.card.manual-card-fullwidth {
        margin-left:-10px;
        margin-right:-10px;
      }
      ion-list.manual-list-fullwidth div.list > .item, .list.card.manual-card-fullwidth > .item {
        border-radius:0px;
        border-left:0px;
        border-right: 0px;
      }
      .show-list-numbers-and-dots ul{
        list-style-type: disc;
        padding-left:40px;
      }
      .show-list-numbers-and-dots ol{
        list-style-type: decimal;
        padding-left:40px;
      }
/* The Modal (background) */
.place {
  display: block;
  padding-top: 30px;
  width: 100%;
  height: 100%;
  background-color: transparent;
  opacity: 0.95;
}
.hotel {
  display: block;
  padding-top: 30px;
  width: 100%;
  height: 100%;
  background-color: transparent;
  opacity: 0.95;
}
.guide {
  display: block;
  padding-top: 30px;
  width: 100%;
  height: 100%;
  background-color: transparent;
  opacity: 0.95;
}

/* Modal Content */
.place-content {
  position: relative;
  margin: auto;
  padding: 0;
  width: 90%;
  height: 100%;
  overflow:scroll;
  background-color: transparent;
  margin-bottom: 30px;
}
#tab1, #tab2, #tab3 {
  width: 33%; 
  float: left;
  z-index: 1;
  border-color: none;
}
</style>
   <body ng-app="app" animation="slide-left-right-ios7">
   <?php include("header.php"); session_start(); ?>
  
  <button id="tab1" style="margin-top: 10px;border-color: grey;" class="button" onclick="placeshow()">Place</button>
  <button id="tab2" class="button" onclick="campshow()">Camp</button>
  <button id="tab3" class="button" onclick="guideshow()">Guide</button>
  
  <div id="place" class="place">
    <div class="place-content">
    <?php
    include("conn.php");
    $day =  $_SESSION['day'];
    if($day <= 2 ){
      $day *= 3;
      $result = mysqli_query($con,'select * from explore where city = "Udaipur" limit '.$day.'');
    }
    else{
      $result = mysqli_query($con,'select * from explore where city = "Udaipur" limit 4');
      while($row = $result->fetch_array(MYSQLI_NUM)){
        echo '
            <div>
              <h5>'. $row['2'].'</h5>
              <span>City : '. $row['1'].'</span>
              <hr style="width:94%;margin-right:6%;">
            </div>'; 
      }
      $result = mysqli_query($con,'select * from explore where city = "Jodhpur" limit 4');
      while($row = $result->fetch_array(MYSQLI_NUM)){
        echo '
            <div>
              <h5>'. $row['2'].'</h5>
              <span>City : '. $row['1'].'</span>
              <hr style="width:94%;margin-right:6%;">
            </div>'; 
      }
      $result = mysqli_query($con,'select * from explore where city = "Jaipur" limit 4');
    }
    //Fetch_Rows
    if($result){
      while($row = $result->fetch_array(MYSQLI_NUM)){
        echo '
            <div>
              <h5>'. $row['2'].'</h5>
              <span>City : '. $row['1'].'</span>
              <hr style="width:94%;margin-right:6%;">
            </div>'; 
      }
    }
    ?>
    </div>
  </div>

  <div id="camp" class="camp">
    <div class="place-content">
    <?php
    include("conn.php");
    $result = mysqli_query($con,'select * from camp where place = "Udaipur" and available = 0 and price <= "'.$_SESSION['amount'].'"');

    //Fetch_Rows
    if($result){
      while($row = $result->fetch_array(MYSQLI_NUM)){
        echo '
            <div>
              <h5>'. $row['1'].'</h5>
              <span>PRICE : '. $row['2'].'</span><br>
              <span>PLACE : '. $row['3'].'</span>
              <form id="form1" style="padding-top: 20px;" method="POST" action="book_camp.php" id="login-form1" class="list">
              <input style="display:none;" type="text" name="id" value="'.$row['0'].'">
              <input style="display:none;" type="text" name="user" value='.$_SESSION['user'].'>
              <button type="submit" name="tab" class="button button-stable button-block">Book Now</button>
              </form>
              <hr style="width:94%;margin-right:6%;">
            </div>'; 
      }
    }
    ?>
    </div>
  </div>

  <div id="guide" class="guide">
    <div class="place-content">
    <?php
    include("conn.php");
    $result = mysqli_query($con,'select * from guide where available = 0 and fees <= "'.$_SESSION['amount'].'"');
    //Fetch_Rows
    if($result){
      while($row = $result->fetch_array(MYSQLI_NUM)){
        echo '
            <div>
              <h5>'. $row['1'].'</h5>
              <span>City : '. $row['4'].'</span><br>
              <span>Language : '. $row['5'].'</span><br>
              <span>Phone : '. $row['2'].'</span><br>
              <span>Price : '. $row['3'].'</span>
              <form id="form2" style="padding-top: 20px;" method="POST" action="book_guide.php" id="login-form1" class="list">
              <input style="display:none;" type="text" name="id" value="'.$row['0'].'">
              <input style="display:none;" type="text" name="user" value='.$_SESSION['user'].'>
              <button type="submit" name="tab" class="button button-stable button-block">Book Now</button>
              </form>
              <hr style="width:94%;margin-right:6%;">
            </div>'; 
      }
    }
    ?>
    </div>
  </div>

  <script type="text/javascript">
  function placeshow() {
    document.getElementById("camp").style.display = "none";
    document.getElementById("place").style.display = "block";
    document.getElementById("guide").style.display = "none";
    document.getElementById("tab1").style.borderColor = "#6A6969";
    document.getElementById("tab2").style.borderColor = "#ffffff";
    document.getElementById("tab3").style.borderColor = "#ffffff";
    }
    function guideshow() {
    document.getElementById("camp").style.display = "none";
    document.getElementById("place").style.display = "none";
    document.getElementById("guide").style.display = "block";
    document.getElementById("tab1").style.borderColor = "#ffffff";
    document.getElementById("tab2").style.borderColor = "#ffffff";
    document.getElementById("tab3").style.borderColor = "#6A6969";
    }
    function campshow() {
    document.getElementById("camp").style.display = "block";
    document.getElementById("place").style.display = "none";
    document.getElementById("guide").style.display = "none";
    document.getElementById("tab1").style.borderColor = "#ffffff";
    document.getElementById("tab2").style.borderColor = "#6A6969";
    document.getElementById("tab3").style.borderColor = "#ffffff";
    }


$(document).ready(function() {
  
  $('#form1').submit(function(e) {
    e.preventDefault();
    var url = $(this).attr('action');
    var data = $(this).serialize();
    
    $.post(url, data)
      .done( function(response) {
        $('#message1').val("");
        $('#response').html(response);
        alert("SUCCESSFULLY BOOKED");
      })
      .fail( function() {
        alert("ERROR OCCURED");
      });
  });

  $('#form2').submit(function(e) {
    e.preventDefault();
    var url = $(this).attr('action');
    var data = $(this).serialize();
    
    $.post(url, data)
      .done( function(response) {
        $('#message2').val("");
        $('#response').html(response);
        alert("SUCCESSFULLY BOOKED");
      })
      .fail( function() {
        alert("ERROR OCCURED");
      });
  });
});
  </script>
  </body>
</html>