<?php
//Session
session_start();
//Connect to database
mysql_connect("mysql11.000webhost.com","a6736476_fogest","123987justin") or die();
mysql_select_db("a6736476_mctrade") or die();

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
		return $COOKIE['username'];
	}

}

?>