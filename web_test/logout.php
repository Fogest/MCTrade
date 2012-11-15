<?php
	session_start();
	$_SESSION['loggedin'] = FALSE;
	//destroy session
	session_destroy();
	
	//unset cookies
	setcookie("loggedin",FALSE);
	
	header("Location: index.php");
?>