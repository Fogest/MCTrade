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
$bodyTab = "home";
include_once 'assets/php/body-head.php'?>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<div class="container">
<h1>Trading</h1>
<?php 
$username = getUsername();
$query = mysql_query("SELECT * FROM mctrade_users WHERE username='$username'");

$row = mysql_fetch_assoc($query);

$userLevel = $row['user_level'];

if($userLevel == 104) {
	echo "<a href='admincp.php?location=edit-trades'>Edit Trades</a>";
}

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
	<th class="first-th">Trade ID</th>
	<th>Minecraft Username</th>
	<th>Item</th>
	<th>Quantity</th>
	<th>Price</th>
	<th class="last-th center-th">Trade Status(opened/closed)</th>
	</tr>
	</thead>
	
	<tbody>
	<?php 
	$res = mysql_query("SELECT MAX(id) FROM mctrade_trades");
	$row = mysql_fetch_row($res);
	$cur = $row[0];
	while($cur > 0) {
		$query = mysql_query("SELECT * FROM mctrade_trades WHERE id='$cur'");

		$row = mysql_fetch_assoc($query);
		
		$tradeID = $row['id'];
		$mcUsername = $row['Minecraft_Username'];
		$blockName = $row['Block_Name'];
		$quantity = $row['Quantity'];
		$Cost = $row['Cost'];
		$tradeStatus = $row['Trade_Status'];
		
		if($tradeStatus!=3) {
			echo "<tr>";
			echo "<td class='first-td'>$tradeID</td>";
			echo "<td>$mcUsername</td>";
			echo "<td>$blockName</td>";
			echo "<td>$quantity</td>";
			echo "<td>$Cost</td>";
			if($tradeStatus == 1) { echo "<td class='last-td center-td'><img src='img/accept-icon.png' alt='Open' title='Open' width='16px' height='16px'/></td>"; }
			if($tradeStatus == 2) { echo "<td class='last-td center-td'><img src='img/cancel-icon.png' alt='Closed' title='Closed' width='16px' height='16px' /></td>"; }
			echo "</tr>";
		}
		$cur = $cur - 1;
	} 
	?>
	</tbody>
	</table>


	<?php } ?>
</div> <!-- /container -->


<?php include_once 'assets/php/footer.php'; ?>
</body>

</html>

