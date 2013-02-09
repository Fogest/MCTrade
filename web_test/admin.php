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
$_SESSION['bodyTitle'] = "Admin";
$_SESSION['bodyTab'] = "admin";
include_once 'assets/php/body-head.php'?>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<div class="container">
<h1>All Trades</h1>
	<table class="table table-hover table-bordered table-striped" id="trades">
	<thead>
	<tr>
	<th class="first-th" width="10%">Trade ID</th>
	<th width="20%">Minecraft Username</th>
	<th>Item ID</th>
	<th>Item</th>
	<th>Durability</th>
	<th>Quantity</th>
	<th>Price</th>
	<th>Enchantment</th>
	<th>Trade Status</th>
	</tr>
	</thead>
	<tbody>
	</tbody>
	</table>
</div> 


<?php include_once 'assets/php/footer.php'; ?>
</body>

</html>