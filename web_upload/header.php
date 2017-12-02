<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>Touristhan</title>

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
<header>
  <div><img onclick="goBack()" src="images/back.png" style="max-height: 30px;max-width: 30px;margin: 10px 10px;float: left;"><h1 style="float: right; padding-right: 20px; top: -7px;position: relative; font-size: 25px; ">Touristhan</h1></div>
</header>
<script>
function goBack() {
    window.history.back();
}
</script>
</body>