<?php
include 'assets/php/functions.php';
?>
<!DOCTYPE html>
<html lang="en"> 
<head>
<?php include_once 'assets/php/header.php'; ?>
<link href="css/data_table.css" rel="stylesheet">
</head>
<body>

<?php 
$_SESSION['bodyTitle'] = "Help";
$_SESSION['bodyTab'] = "help";
include_once 'assets/php/body-head.php'?>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<div class="container">
<h1>Help</h1>
	<h3>Website:</h3>
		<dl>
			<dt>Account</dt>
			<dd>Your account must be created in game on the server MCTrade is being used on (command found below). This is done to ensure your Minecraft username is actually owned by yourself, and not someone simply inserting your username.</dd>
			
			<dt>Logging in</dt>
			<dd>Logging in allows you to see all your trades you've made, including your accepted trades (closed ones). To login use the form displayed in the top right of every page!</dd>
			
			<dt></dt>
			<dd></dd>
			<a name="forgotpass"></a><dt>Forgot your password</dt>
			<dd>To recover your password you will need to go in game and type in /mct password &lt;newPassword&gt;</dd>
		</dl>
		<h3>In Game Commands:</h3>
			<dl>
				<dt>/mct create &lt;password&gt;</dt>
				<dd>This creates an account for you to use on MCTrade which is needed for trading and accepting of trades.</dd>
				
				<dt>/mct accept &lt;trade id&gt;</dt>
				<dd>This will accept a trade. You get a trade id from the in game trade creation message which states the trade id, or you search for it online! To find a trade online simply search for the item you want via the homepage. Once you have found a trade, use the trade id and this command to accept that trade.</dd>
				
				<dt>/mct &lt;price&gt;</dt>
				<dd>This will create a trade with the current item stack you are holding. Say you want to sell a stack of dirt. Simply hold that stack in your hand and then type /mct, followed by the price that you are selling the stack for.</dd>
			</dl>
			<p><em>Note: You may notice that there are many id&#39;s missing in the main table. This is because closed trades are not seen! You can only see closed trades if you are a mod/admin on the website. You can see your own closed, and open trades by clicking on your Profile at the top when logged in</em></p>
</div> 


<?php include_once 'assets/php/footer.php'; ?>
</body>

</html>

