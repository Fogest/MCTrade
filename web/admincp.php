<?php
include 'php/functions.php';
	if(!loggedin()) {
	header("Location: login.html");
	exit();
	}
	if(!(getUsername() == "Fogest")) {
	header("Location: login.html");
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
$bodyTitle = "Admin CP";
$bodySubTitle = "Secret, unfinished admin panel";
$bodyLocation = "Admin CP";
$bodyTab = "admincp";
include_once 'body-head.php'?>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
										 CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
ONLY THE REALLY SPECIAL ONES CAN SEE THIS.




<?php include_once 'footer.php'; ?>
</body>
</html>