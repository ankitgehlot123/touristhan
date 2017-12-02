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
.modal {
  display: none;
  position: fixed;
  z-index: 2;
  padding-top: 100px;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: white;
}

/* Modal Content */
.modal-content {
  position: relative;
  margin: auto;
  padding: 0;
  width: 90%;
  max-width: 1200px;
  background-color: transparent;
}

/* The Close Button */
.close {
  color: black;
  position: absolute;
  top: 10px;
  right: 25px;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #999;
  text-decoration: none;
  cursor: pointer;
}

</style>
   <body ng-app="app" animation="slide-left-right-ios7">
   <?php include("header.php"); session_start(); ?>
  
  <button id="tab1" style="margin-top: 10px;border-color: grey;" class="button" onclick="placeshow()">Place</button>
  <button id="tab2" class="button" onclick="hotelshow()">Hotel</button>
  <button id="tab3" class="button" onclick="guideshow()">Guide</button>
  
  <div id="place" class="place">
    <div class="place-content">
        
    <?php
    include("conn.php");
    $day =  3 * $_SESSION['day'];
    $result = mysqli_query($con,'select * from markers where address = "Udaipur" limit '.$day.'');
    //Fetch_Rows
    $i=0;
    if($result){
      while($row = $result->fetch_array(MYSQLI_NUM)){
        echo '
            <div onclick="wiki('.$i.')">
              <h5>'. $row['1'].'</h5>
              <span>TYPE : '. $row['5'].'</span>
              <hr style="width:94%;margin-right:6%;">
            </div>';
          $i++; 
      }
    }
    ?>
    
    <div id="myModal" class="modal">
      <span class="close cursor" onclick="closeModal()">&times;</span>
      <div id="modal-content"></div>
    </div>

  <div id="hotel" class="hotel">
    <div class="place-content">
    <?php
    include("conn.php");
    $result = mysqli_query($con,'select * from hotel where place = "Udaipur" and available = 0 and price <= "'.$_SESSION['amount'].'"');

    //Fetch_Rows
    if($result){
      while($row = $result->fetch_array(MYSQLI_NUM)){
        echo '
            <div>
              <h5>'. $row['1'].'</h5>
              <span>PRICE : '. $row['2'].'</span>
              <form id="form1" style="padding-top: 20px;" method="POST" action="book_hotel.php" id="login-form1" class="list">
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
    $result = mysqli_query($con,'select * from guide where city = "Udaipur" and available = 0 and fees <= "'.$_SESSION['amount'].'"');
    //Fetch_Rows
    if($result){
      while($row = $result->fetch_array(MYSQLI_NUM)){
        echo '
            <div>
              <h5>'. $row['1'].'</h5>
              <span>Phone : '. $row['2'].'</span><br>
              <span>Language : '. $row['5'].'</span><br>
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
    document.getElementById("hotel").style.display = "none";
    document.getElementById("place").style.display = "block";
    document.getElementById("guide").style.display = "none";
    document.getElementById("tab1").style.borderColor = "#6A6969";
    document.getElementById("tab2").style.borderColor = "#ffffff";
    document.getElementById("tab3").style.borderColor = "#ffffff";
    }
    function guideshow() {
    document.getElementById("hotel").style.display = "none";
    document.getElementById("place").style.display = "none";
    document.getElementById("guide").style.display = "block";
    document.getElementById("tab1").style.borderColor = "#ffffff";
    document.getElementById("tab2").style.borderColor = "#ffffff";
    document.getElementById("tab3").style.borderColor = "#6A6969";
    }
    function hotelshow() {
    document.getElementById("hotel").style.display = "block";
    document.getElementById("place").style.display = "none";
    document.getElementById("guide").style.display = "none";
    document.getElementById("tab1").style.borderColor = "#ffffff";
    document.getElementById("tab2").style.borderColor = "#6A6969";
    document.getElementById("tab3").style.borderColor = "#ffffff";
    }

    function closeModal() {
    document.getElementById("myModal").style.display = "none";
    document.getElementById("tab1").style.display = "block";
      document.getElementById("tab2").style.display = "block";
      document.getElementById("tab3").style.display = "block";
    }

    function wiki(n) {
      var wiki_content = document.getElementsByTagName("h5")[n];
      console.log(wiki_content.innerHTML); 
    
      document.getElementById("myModal").style.display = "block";
      document.getElementById("tab1").style.display = "none";
      document.getElementById("tab2").style.display = "none";
      document.getElementById("tab3").style.display = "none";
    
    $('#modal-content').html('');

    // See: https://en.wikipedia.org/w/api.php
    $.getJSON("http://en.wikipedia.org/w/api.php?callback=?", {
      srsearch: wiki_content.innerHTML,
      action: "query",
      list: "search",
      format: "json"
    }, function(data) {

      $.each(data.query.search, function(i, item) {
        $("#modal-content").append("<div><a href='http://en.wikipedia.org/wiki/" + item.title + "'>" + item.title + " </a>" + item.snippet + "</div><br>");
        console.log(item.title); 
      });

    })
  
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