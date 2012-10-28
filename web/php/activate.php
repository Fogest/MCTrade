<?php
include 'php/functions.php';
include 'php/config.php';

$code = $_GET['code'];
$siteLinkUncleaned = $siteURL."member.php";
$siteLink = str_replace(' ','',$siteLinkUncleaned);

if(!code) {
echo "No code supplied";
}
else {
	$check = mysql_query("SELECT * FROM mctrade_users WHERE code='$code' AND active='1'");
	if(mysql_num_rows($check)==1) {
		echo "That is odd, you've already activated your account! If this is an error use the Contact page and send us an email";
	}
	else {
		$activate = mysql_query("UPDATE mctrade_users SET active='1' WHERE code='$code'");
		echo "Your account has been activated, you will now be redirected to the homepage! (Login at the top right!)";
		header("Refresh: 5; $siteLink");
	}
}
?>