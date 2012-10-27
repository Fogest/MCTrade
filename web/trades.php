<?php
include 'php/functions.php';
?>
<!DOCTYPE html>
<html lang="en"> 
<head>
<?php include_once 'header.php'; ?>
</head>
<body>

<?php 
$bodyTitle = "Trades";
$bodySubTitle = "View all the current trades!";
$bodyLocation = "Trades";
$bodyTab = "trades";
include_once 'body-head.php'?>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
										 CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
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

echo "<h1>Trade ID</h1>";
echo "<a href='trades.php?id=$tradeID'>$tradeID</a>";
echo "<h1>Minecraft Username</h1>";
echo "$mcUsername";
echo "<h1>Item Name</h1>";
echo "$blockName";
echo "<h1>Quantity</h1>";
echo "$quantity";
echo "<h1>Cost Per Item</h1>";
echo "$Cost";
echo "<h1>Trade Notes</h1>";
echo "$tradeNotes";
echo "<h1>Trade Status</h1>";
if($tradeStatus == 1) {echo "<img src='images/assets/accept-icon.png' alt='Open' title='Open' width='16px' height='16px'/>"; }
if($tradeStatus == 2) {echo "<img src='images/assets/cancel-icon.png' alt='Open' title='Open' width='16px' height='16px'/>"; }

} else { 

?>
<table class="def-table">
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
		echo "<td class='first-td'><a href='trades.php?id=$tradeID'>$tradeID</a></td>";
		echo "<td>$mcUsername</td>";
		echo "<td>$blockName</td>";
		echo "<td>$quantity</td>";
		echo "<td>$Cost</td>";
		if($tradeStatus == 1) { echo "<td class='last-td center-td'><img src='images/assets/accept-icon.png' alt='Open' title='Open' width='16px' height='16px'/></td>"; }
		if($tradeStatus == 2) { echo "<td class='last-td center-td'><img src='images/assets/cancel-icon.png' alt='Closed' title='Closed' width='16px' height='16px' /></td>"; }
		echo "</tr>";
		}
		$cur = $cur - 1;
	} 
	?>
	</tbody>
</table>


<?php } ?>




<?php include_once 'footer.php'; ?>
</body>
</html>