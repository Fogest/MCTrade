<div class="wrapper">
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ PAGE -->

<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
											 HEADER
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	<header id="header-wrapper">
	
		<!-- top part: sign / registration -->
		<div id="header-top">
			<div class="container">
                <div class="half-left italic">
					It is reconmended to disable Adblocker if you use it, as the site renders horribly with it on.
				</div>
				<div class="half-right">
				<?php if(loggedin()) {$usernameUnused = getUsername(); echo "<mark class='dashed'><a href='member.php'>$usernameUnused</a></mark>";if($usernameUnused=="Fogest") {echo "<a href='admincp.php'> (Admin CP)</a>";} echo "<a href='logout.php'> (Logout)</a>";}
				else { 
				?>
					<a href="#sign-popup" class="toggle-sign">Sign In</a>
					<a href="registration.php" class="left_wrapper">Registration</a>
					<?php } ?>
				</div>
				<div class="clear"></div>
				<div class="sign-popup" id="sign-popup">
					<h3>Sign Up</h3>
					<form action="login.php" method="post" class="sign-form" />
						<label>Username:</label> <input type="text" value="" name="username"/>
						<div class="clear"></div>
						<label>Password:</label> <input type="password" value="" name="password"/>
						<div class="clear"></div>
						<label>Remember Password?</label> <input type="checkbox" value="" name="remPass"/>
						<div class="clear"></div>
						<p class="al-right"><a href="#">Forgot password?</a>
						<input type="submit" value="Sign In" class="submit-btn" name="login"/></p>
					</form> 
				</div>
				<div class="clear"></div>
				
			</div>
		</div>
		<!-- middle part: logo / tagline / contact info / menu -->
		<div id="header-middle">
			<div class="container">
				<!-- logo / tagline-->
				<div class="two-thirds column">
					<div class="logo-slogan">
						<div class="logo"><a href="index.php"><img src="layout/styles/images/blue/logo.png" alt="" /></a></div>
						<!-- text: <div class="logo font_gautami"><a href="index.php"><img src="layout/styles/images/blue/footer-logo.png" alt="BeautyMind"></a></div> -->
						<div class="slogan">...trading made easy.</div>
					</div>
					<div class="tagline">Making trading just a tad simpler. </div>
				</div>
			</div>
		</div>
		
	<!-- middle part: logo / tagline / contact info / menu -->
		<div id="header-bottom" class="container">
			<div class="header-bottom-wrapper">
				<nav class="twelve columns alpha" id="main-menu-wrapper">
					<ul id="main-menu">
						<li><a <?php if($bodyTab == "index") { ?> class="active" <?php } ?> href="index.php">Home</a></li>
						<li><a <?php if($bodyTab == "trades") { ?> class="active" <?php } ?> href="trades.php">Trades</a></li>
					</ul>
					<select id="main-menu-mobile">
						<option value="#" />Navigate to
						<option value="index.php" />&nbsp;-&nbsp;Home	
						<option value="trades.php" />&nbsp;-&nbsp;Trades
					</select>
				</nav>
				<div class="four columns omega">
					<form action="php/search.php" method="post" class="search_block" />
						<input type="text" value="Search for a trade " onFocus="if(this.value=='Search for a trade ')this.value='';" onBlur="if(this.value=='')this.value='Search for a trade ';" />
						<input type="submit" value="" />
					</form>
				</div>
			</div>					
		</div>		
	</header>
	<!-- end header -->
	
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
										 CONTENT
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

	<div id="content-wrapper">
	<div class="container">
			<!-- crumbs -->
			<div class="whole-width crumbs">
				You are here: <a href="index.php">Home</a>  <?php echo $bodyLocation;?>
			</div>
		</div>
		<!-- title -->
		<div class="title">
			<div class="left-arr"></div>
			<div class="right-arr"></div>
			<div class="container">
				<div class="columns ten alpha title-inner">
					<h1><?php echo $bodyTitle ?></h1>
					<p><?php echo $bodySubTitle ?></p>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!-- /title -->
		<div class="separator31"></div>
		<div id="content-wrapper">
		<div class="container">