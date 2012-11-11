<?php
include 'functions.php';
	if(!loggedin()) {
	header("Location: \mctrade/login.html");
	exit();
	}
?>
<?php
$tradeID = $_GET['tradeID'];
if($tradeID) {
		$query = mysql_query("SELECT * FROM mctrade_trades WHERE id='$tradeID'");
		$row = mysql_fetch_assoc($query);
		$status = $row['Trade_Status'];
	$username = getUsername();
	if(mysql_query("UPDATE mctrade_trades SET Trade_Status='2' WHERE id='$tradeID' AND AccountName='$username' AND Trade_Status='1'")) {
		
		$query = mysql_query("SELECT * FROM mctrade_users WHERE username='$username'");
		$row = mysql_fetch_assoc($query);
		$userLevel = $row['user_level'];
		$curTrades = $row['Current_Open_Trades'];
		$curTrades = $curTrades - 1;
		mysql_query("UPDATE mctrade_users SET Current_Open_Trades='$curTrades' WHERE username='$username'");
		

		if($userLevel < 100 && $status == "1") {
			$userLevel = $userLevel + 1;
			mysql_query("UPDATE mctrade_users SET user_level='$userLevel' WHERE username='$username'") or die("It appears there was an error! Use the Contact page to report and error!");
			header("Location: \mctrade/member.html");
			exit();
		}
	}
	else {
		die("Your username did not correspond with the tradeID. If this is an error use the Contact form and send us an email!");
		exit();
	}
	

}
?>