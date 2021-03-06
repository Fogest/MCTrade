<?php
include 'php/functions.php';
	if(!loggedin()) {
	header("Location: login.php");
	exit();
	}
?>
<!DOCTYPE html>
<html lang="en"> 
<head>
<?php include_once 'header.php'; ?>
</head>
<body>

<?php 
$bodyTitle = "Member Area";
$bodySubTitle = "Cool people only!";
$bodyLocation = "Member Area";
$bodyTab = "member";
include_once 'body-head.php'?>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
										 CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<?php
$username = getUsername();
if($username) { ?>
<!-- start page-slogan -->
<div class="box-page-slogan">
	<div id="slogan-slider-box">
		<ul class="slogan-slider">
			<li>
				<div class="text-top">Welcome <?php echo "<mark class='dashed'>$username</mark>"; ?> to the members area!</div>
				<div class="text-bottom">If you look below you will find various member only features! For now you can only see your trades!</div>
			</li>
		</ul>
	</div>
</div>
<div id="info-section"></div>
<!-- end page-slogan -->
<div class="tab-styles"> 	
				<!-- tab block -->
				<div class="tab-navi">
					<ul>
						<li class="first-li"><a href="#Trades" class="active">Trades</a></li>
						<li class="last-li has-submenu"><a href="#Account">Account</a>
							<ul>
								<li><a href="#Verify">Verify Account</a></li>
							</ul>
						</li>
					</ul>
				</div>
				
				<div class="tab-content tab1-content">
					<div id="Trades" class="tab-content-item display-block">

				<!-- /tab block -->
				
			
<table class="def-table">
	<thead>
		<tr>
			<th class="first-th">Trade ID</th>
			<th>Minecraft Username</th>
			<th>Item</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Trading Notes</th>
			<th class="last-th">Trade Status(opened/closed)</th>
		</tr>
	</thead>
	
	<tbody>
	<?php 
	$username = getUsername();
	$query = mysql_query("SELECT * FROM mctrade_users WHERE username='$username'");

	$row = mysql_fetch_assoc($query);

	$userLevel = $row['user_level'];

	$res = mysql_query("SELECT MAX(id) FROM mctrade_trades");
	$row = mysql_fetch_row($res);
	$cur = $row[0];
	while($cur > 0) {
	$query = mysql_query("SELECT * FROM mctrade_trades WHERE AccountName='$username' AND id='$cur'");
	$row = mysql_fetch_assoc($query);
	
	$tradeID = $row['id'];
	$mcUsername = $row['Minecraft_Username'];
	$blockName = $row['Block_Name'];
	$quantity = $row['Quantity'];
	$Cost = $row['Cost'];
	$tradeNotes = $row['TradeNotes'];
	$tradeStatus = $row['Trade_Status'];
	if($mcUsername!="") {
	if($tradeStatus!=3) {
		echo "<tr>";
		echo "<td class='first-td'><a href='trades.php?id=$tradeID'>$tradeID</a></td>";
		echo "<td>$mcUsername</td>";
		echo "<td>$blockName</td>";
		echo "<td>$quantity</td>";
		echo "<td>$Cost</td>";
		echo "<td>$tradeNotes</td>";
		if($tradeStatus == 1) { 
		echo "<td class='last-td'><img src='images/assets/accept-icon.png' alt='Open' title='Open' width='16px' height='16px'/>";
		if($userLevel == 104) {echo "<a href='php/close.php?tradeID=$tradeID'>(Close)</a> </td>"; }
		}
		if($tradeStatus == 2) { echo "<td class='last-td'><img src='images/assets/cancel-icon.png' alt='Closed' title='Closed' width='16px' height='16px' /></td>"; }
		echo "</tr>";
		}
		}
		$cur = $cur - 1;
	} 
	?>
	</tbody>
</table>

<?php } ?>
					</div>	
					<div id="Verify" class="tab-content-item">
					<?php
					if (!$_POST['submit'] && getActive() == 1) {
					?>
					<form action="member.php#Verify" method="post" class="contact-form" id="SendContact" name="SendContact"/>
					
					<label class="required">Verification Code (Type /mctrade verify in-game to get this code)</label>
					<input type="text" name="mcVerify" value="" class="required" maxlength="10"/>
					
					<input type="submit" value="Send message" class="submit-btn" name="submit" />
				</form>
				<?php 
				} else if(getActive() == 2) {
				echo "Your account has already been verified with your minecraft account!";
				} else {
				$mcVerify = $_POST['mcVerify'];
				$mcVerify = mysql_real_escape_string($mcVerify);
				
				$username = getUsername();
				
				$login = mysql_query("SELECT * FROM mctrade_users WHERE username='$username'");
				
				$row = mysql_fetch_assoc($login);
				
				$mcCode = $row['mc_code'];
				$mcUsername = $row['minecraft_name'];
				if($mcVerify == $mcCode) {
				mysql_query("UPDATE mctrade_users SET active='2' WHERE username='$username'");
				
				} else {
					echo "Your verification code did not match the code generated for your account! The username you tried to verify is $mcUsername , if this is and error use the Contact form and we will change it for you!";
				}
				} 
				?>
					</div>
				</div>
		</div>
<?php include_once 'footer.php'; ?>
</body>
</html>