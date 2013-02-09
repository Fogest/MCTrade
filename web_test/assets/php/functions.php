<?php
//Session
session_start();
include 'CONFIG.php';

function fatal_error ( $sErrorMessage = '' )
{
	header( $_SERVER['SERVER_PROTOCOL'] .' 500 Internal Server Error' );
	die( $sErrorMessage );
}
/*
 * MySQL connection
 */
if ( ! $gaSql['link'] = mysql_pconnect( $hostname, $username, $password  ) )
{
	fatal_error( 'Could not open connection to server' );
}

if ( ! mysql_select_db( $database, $gaSql['link'] ) )
{
	fatal_error( 'Could not select database ' );
}

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