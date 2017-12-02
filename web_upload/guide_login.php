<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
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

   <body ng-app="app" animation="slide-left-right-ios7">
   <?php
     include("header.php");
    ?>
    <div>
    <h3 style="padding-top: 20px;" class="text-center">Guide Login</h3>
  <div>
<ion-view title="Login" id="page3">
  <ion-content padding="true" class="has-header">
    <form style="padding-top: 20px;" method="POST" action="sign_in.php" id="login-form1" class="list">
      <ion-list id="login-list1">
        <label class="item item-input" id="login-input1">
          <span class="input-label">Username</span>
          <input type="text" name="user" placeholder ="" required>
        </label>
        <label class="item item-input" id="login-input2">
          <span class="input-label">Password</span>
          <input type="password" name="pwd" placeholder ="" required>
        </label>
      </ion-list>
      <div class="spacer" style="height: 40px;"></div>
      <button type="submit" value="login" id="login-button3" class="button button-stable button-block">Log in</button>
      </form>
  </ion-content>
</ion-view>
  </div>
</div>
  </body>
</html>