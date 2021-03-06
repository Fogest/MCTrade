<?php
include 'php/functions.php';
include 'php/config.php';
?>
<!DOCTYPE html>
<html lang="en"> 
<head>
<?php include_once 'header.php'; ?>
</head>
<body>

<?php 
$bodyTitle = "Register";
$bodySubTitle = "Registration Form";
$bodyLocation = "Registration";
$bodyTab = "register";
include_once 'body-head.php'?>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
										 CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
			<?php
			if (!$_POST['submit'] ) {
			?>
				<form action="registration.php" method="post" class="contact-form" id="SendContact" name="SendContact"/>

					<label class="required">Username</label>
					<input type="text" name="username" value="" class="required" maxlength="32" />
					
					<label class="required">Password</label>
					<input type="password" name="password" value="" class="required" maxlength="150"/>
					
					<label class="required">Password (Confirm)</label>
					<input type="password" name="passwordConfirm" value="" class="required" maxlength="150"/>
					
					<label class="required">Minecraft Username (Use real one, as it must be verified later)</label>
					<input type="text" name="mcUsername" value="" class="required" maxlength="17"/>
					
					<label class="required">Email</label>
					<input type="text" name="email" value="" class="required email" maxlength="150"/>

					
					<input type="submit" value="Register" class="submit-btn" name="submit" />
				</form>
				<?php
			} else {
				$username = $_POST['username'];
				$username = mysql_real_escape_string($username);
				
				$password = $_POST['password'];
				$password = mysql_real_escape_string($password);
				
				$passwordConfirm = $_POST['passwordConfirm'];
				$passwordConfirm = mysql_real_escape_string($passwordConfirm);
				
				$mcUsername = $_POST['mcUsername'];
				$mcUsername = mysql_real_escape_string($mcUsername);
				
				$email = $_POST['email'];
				$email = mysql_real_escape_string($email);
				
				$errors = array();
				
				if( !$username ) {
					$errors[1] = "You did not enter a Username.";
				}
				if( !$password ) {
					$errors[2] = "You did not enter a Password.";
				}
				if( !$passwordConfirm ) {
					$errors[3] = "You did not enter a confirmation password.";
				}
				if( !$mcUsername ) {
					$errors[4] = "You did not enter a Minecraft Username.";
				}
				if( !$email ) {
					$errors[5] = "You did not enter an Email.";
				}
				if( $password != $passwordConfirm) {
					$errors[6] = "Your password did not match your password confirmation field!";
				}
				if( (strlen($username) <= 1)) {
					$errors[7] = "Your username was too short! It should be at least 2 characters";
				}
				if( (strlen($password) <= 1)) {
					$errors[8] = "Your password was too short! It should be at least 2 characters";
				}
				if( (strlen($passwordConfirm) <= 1)) {
					$errors[9] = "Your password confirmation was too short! It should be at least 2 characters";
				}
				
				if( count($errors) > 0 ) {
					foreach($errors as $error) {
						echo "$error<br>";
					}
				} else {
				$check_username = mysql_query("SELECT * FROM mctrade_users WHERE username='$username'");
				$check_mcname = mysql_query("SELECT * FROM mctrade_users WHERE minecraft_name='$mcUsername'");
				$check_email = mysql_query("SELECT * FROM mctrade_users WHERE email='$email'");
					if(mysql_num_rows($check_username)>=1) {
						die ("Username already taken! \n");
						exit();
					}
					if(mysql_num_rows($check_mcname)>=1) {
						die ("\nMinecraft account has already been bound to another account! Click the Contact tab if this is an error!");
						exit();
					}
					if(mysql_num_rows($check_email)>=1) {
						die ("\nEmail already used! \n");
						exit();
					}
					else {
					$code = md5($email);
					$messageCompiled = $siteURL."php/activate.php?code=".$code;
					$messageCompiledNoSpace = str_replace(' ', '', $messageCompiled);
					//Send activation email
					$to = $email;
					$subject = "Activate your account - MCTrade";
					$headers = "From: fogestjv@gmail.com";
					$body = "Hello $username, \n\nYou registered and need to activate your account. Click the link below or paste it into the URL bar of your browser\n\n$messageCompiledNoSpace\n\nThanks!";
					
					if(!mail($to,$subject,$body,$header)) {
						die ("We couldn't sign you up at this time. Please try again later. Sending email failed!"); 
						exit();
					}
					else
					{
					$password = md5($password);
					$ip = $_SERVER['REMOTE_ADDR'];
					mysql_query("INSERT INTO mctrade_users VALUES ('','$username','$password', '$mcUsername','$email','0','0','$code','0','$ip')");
					echo "You have been successfully registered! Please check your email ($email) to activate your account!";
					}
					}
			}
			}
			?>
			
<?php include_once 'footer.php'; ?>
</body>
</html>