<?php
include 'assets/php/functions.php';
?>
<!DOCTYPE html>
<html lang="en"> 
<head>
<?php include_once 'assets/php/header.php'; ?>
</head>
<body>

<?php 
$bodyTitle = "Login";
$bodySubTitle = "Login Page";
$bodyLocation = "Login";
$bodyTab = "login";
include_once 'assets/php/body-head.php'?>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
										 CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<div class="container">	
		<?php
		if(loggedin()) {
		echo "You are already logged in!";
		}
		else{
			$username = $_POST['username'];
			$username = mysql_real_escape_string($username);
			$password = $_POST['password'];
			$password = mysql_real_escape_string($password);
			$remPass = $_POST['remPass'];
			$remPass = mysql_real_escape_string($remPass);
			
			if($username&&$password){
				$login = mysql_query("SELECT * FROM mctrade_users WHERE username='$username'");
				if(mysql_num_rows($login)==0) {
					echo "No Such user";
				}
				else {
				while ($row = mysql_fetch_assoc($login))
				{
					$db_password = $row['password'];
					if(md5($password) == $db_password) {
						$loginok = TRUE;
					} else {
						$loginok = FALSE;
					}
					
					if($loginok == true) {
					$email = $row['email'];
						if($remPass=="on")
						{
							setcookie("username",$username);
						}
						else if($remPass=="")
						{
							$_SESSION['username'] = $username;
						}
						header("Location: member.php");
						exit();
					}
					else die("Incorrect username/password combination.");
				}
				}
				
			}
			else die("Please enter info into the two boxes, including your username and password!");
			}
		?>
		</div>
<?php include_once 'assets/php/footer.php'; ?>
</body>
</html>