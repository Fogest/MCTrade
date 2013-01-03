<?php
include 'assets/php/functions.php';
?>
<!DOCTYPE html>
<html lang="en"> 
<head>
<?php include_once 'assets/php/header.php'; ?>
<link href="css/data_table.css" rel="stylesheet">
</head>
<body>

<?php 
$bodyTitle = "Home";
$bodySubTitle = "Your home!";
$bodyLocation = "Home";
$bodyTab = "help";
include_once 'assets/php/body-head.php'?>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<div class="container">
<h1>Help</h1>
<h3>Commands:</h3>
<p><strong>/mct create &ltpassword&gt</strong> - This creates an account for you to use on MCTrade which is needed for trading and accepting of trades.</p>
<p><strong>/mct accept &lttrade id&gt</strong> - This will accept a trade. You get a trade id from the in game trade creation message which states the trade id, or you search for it online! To find a trade online simply search for the item you want via the homepage. Once you have found a trade, use the trade id and this command to accept that trade.</p>
<p><strong>/mct &ltprice&gt</strong> - This will create a trade with the current item stack you are holding. Say you want to sell a stack of dirt. Simply hold that stack in your hand and then type /mct, followed by the price that you are selling the stack for.</p>
</div> 


<?php include_once 'assets/php/footer.php'; ?>
</body>

</html>

