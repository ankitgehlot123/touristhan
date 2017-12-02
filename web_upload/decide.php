<?php
      if ($_SERVER["REQUEST_METHOD"] == "POST") {
        session_start();
        $_SESSION['amount'] = $_POST['amount'];
        $_SESSION['day'] = $_POST['day'];
        if ($_POST['tab'] == "Travel") {
          echo "<script>location.href = 'travel.php'</script>";
        }
        else{
          echo "<script>location.href = 'explore.php'</script>";
        }
      }
    ?>