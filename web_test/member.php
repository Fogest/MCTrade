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
$_SESSION['bodyTitle'] = "Member";
$_SESSION['bodyTab'] = "member";
include_once 'assets/php/body-head.php'?>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<div class="container">
<h2>Your Trades</h2>
		<table class="table table-hover table-bordered table-striped" id="trades">
			<thead>
				<tr>
					<th class="first-th" width="10%">Trade ID</th>
					<th>Item</th>
					<th width="10%">Durability</th>
					<th width="10%">Quantity</th>
					<th width="15%">Price</th>
					<th width="15%"> Trade Status</th>
				</tr>
			</thead>
		</table>
</div> 


<?php include_once 'assets/php/footer.php'; ?>
</body>

</html>