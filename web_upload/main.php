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

    <style type="text/css">
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
    </style>


    <!-- Only required for Tab projects w/ pages in multiple tabs 
    <script src="lib/ionicuirouter/ionicUIRouter.js"></script>
    -->
   <body ng-app="app" animation="slide-left-right-ios7">
    <?php
     include("header.php");
     session_start();
     $_SESSION['user'] = $_GET['name'];
    ?>
    
  <div>
    <h1 style="padding-top: 20px;" class="text-center">Trip Advisor</h1>
  <div>
<ion-view title="Login" id="page3">
  <ion-content padding="true" class="has-header">
    <form style="padding-top: 20px;" method="POST" action="decide.php" id="login-form1" class="list">
      <ion-list id="login-list1">
        <label class="item item-input" id="login-input1">
          <span class="input-label">No. of Days</span>
          <input type="text" name="day" placeholder ="" required>
        </label>
        <label class="item item-input" id="login-input2">
          <span class="input-label">Amount</span>
          <input type="text" name="amount" placeholder ="" required>
        </label>
      </ion-list>
      <div class="spacer" style="height: 40px;"></div>
      <button type="submit" value="Travel" id="login-button3" name="tab" class="button button-stable button-block">Travel</button>
      <button type="submit" value="Explore" id="login-button3" name="tab" class="button button-stable button-block">Explore</button>
      </form>
  </ion-content>
</ion-view>
  </div>
</div>
  </body>
</html>