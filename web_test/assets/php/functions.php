<?php
//Session
session_start();
include './CONFIG.php';
//Connect to database
mysql_connect($hostname,$username,$password) or die();
mysql_select_db($database) or die();

//Login check function
function loggedin()
{
	if(isset($_SESSION['username']) || isset($_COOKIE['username'])) 
	{
		$loggedin = TRUE;
		return $loggedin;
	}
}
function getUsername()
{
	if(isset($_SESSION['username'])) {
		return $_SESSION['username'];
	}
	else if(isset($_COOKIE['username'])) {
		return $_COOKIE['username'];
	}

}
function getUserlevel() {
	if(loggedin()) {
		$username = getUsername();
		$level = mysql_query("SELECT * FROM mctrade_users WHERE username='$username'");
		$row = mysql_fetch_assoc($level);
		$level2 = $row['user_level'];
		return $level2;
	} else {
	return '1';
	}
}
function getActive()
{
	$username = getUsername();
	
	$login = mysql_query("SELECT * FROM mctrade_users WHERE username='$username'");
	
	$row = mysql_fetch_assoc($login);
	
	$active = $row['active'];
	return $active;
}

?>