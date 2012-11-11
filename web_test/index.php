<?php
include 'assets/php/functions.php';
?>
<!DOCTYPE html>
<html lang="en"> 
<head>
<?php include_once 'assets/php/header.php'; ?>
</head>
<body>

<?php 
$bodyTitle = "Home";
$bodySubTitle = "Your home!";
$bodyLocation = "Home";
$bodyTab = "index";
include_once 'assets/php/body-head.php'?>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
										 CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
    <div class="container">
      <div class="hero-unit">
        <h1>Welcome!</h1>
        <p>Welcome to MCTrade. Click the button below for help on using MCTrade!</p>
        <p><a href="help.php" class="btn btn-primary btn-large">Help &raquo;</a></p>
      </div>
    </div> <!-- /container -->


<?php include_once 'assets/php/footer.php'; ?>
</body>
</html>