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
$_SESSION['bodyTitle'] = "Home";
$_SESSION['bodyTab'] = "home";
include_once 'assets/php/body-head.php'?>

<!-- Content -->
<div class="container">
<h1>Trading</h1>
<?php 
$username = getUsername();
$query = mysql_query("SELECT * FROM mctrade_users WHERE username='$username'");

$row = mysql_fetch_assoc($query);

$userLevel = $row['user_level'];

$id = $_GET['id'];
if($id){

	$query = mysql_query("SELECT * FROM mctrade_trades WHERE id='$id'");

	$row = mysql_fetch_assoc($query);

	$tradeID = $row['id'];
	$mcUsername = $row['Minecraft_Username'];
	$blockName = $row['Block_Name'];
	$quantity = $row['Quantity'];
	$Cost = $row['Cost'];
	$tradeNotes = $row['TradeNotes'];
	$tradeStatus = $row['Trade_Status'];

	echo "<h2>Trade ID</h2>";
	echo "<a href='index.php?id=$tradeID'>$tradeID</a>";
	echo "<h2>Minecraft Username</h2>";
	echo "$mcUsername";
	echo "<h2>Item Name</h2>";
	echo "$blockName";
	echo "<h2>Quantity</h2>";
	echo "$quantity";
	echo "<h2>Cost Per Item</h2>";
	echo "$Cost";
	echo "<h2>Trade Notes</h2>";
	echo "$tradeNotes";
	echo "<h2>Trade Status</h2>";
	if($tradeStatus == 1) {echo "<img src='img/accept-icon.png' alt='Open' title='Open' width='16px' height='16px'/>"; }
	if($tradeStatus == 2) {echo "<img src='img/cancel-icon.png' alt='Open' title='Open' width='16px' height='16px'/>"; }

} else { 

	?>
	<table class="table table-hover table-bordered table-striped" id="trades">
	<thead>
	<tr>
		<th class="first-th" width="10%">Trade ID</th>
	<th width="20%">Minecraft Username</th>
	<th>Item</th>
	<th width="10%">Durability</th>
	<th width="10%">Quantity</th>
	<th width="15%">Price</th>
	</tr>
	</thead>
	<tbody>
	</tbody>
	</table>


	<?php } ?>
</div> <!-- /container -->


<?php include_once 'assets/php/footer.php'; ?>
</body>

</html>

