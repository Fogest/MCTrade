    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"></a> <a class="brand" href="index.php">MCTrade</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <?php if($_SESSION['bodyTab'] == "home"){ ?> <li class='active'> <?php } else { ?> <li> <?php } ?>
                <a href="index.php">Home</a>
              </li>
			  <?php if($_SESSION['bodyTab']== "help"){ echo '<li class="active">'; } else {  echo'<li>'; } ?>
                <a href="help.php">Help</a>
              </li>
			  <?php if(loggedin() == true) { 
				if($_SESSION['bodyTab'] == "member"){ echo '<li class="active">'; } else {  echo'<li>'; }
				echo '<a href="member.php">Profile</a>';
				}
			  ?>
			  <?php if(loggedin() == true) { 
				if($_SESSION['bodyTab'] == "admin"){ echo '<li class="active">'; } else {  echo'<li>'; }
				echo '<a href="admin.php">Admin CP</a>';
				}
			  ?>
            </ul>
			<div class="pull-right">
			<?php
			if(loggedin()) {
			$usernameForm = getUsername();
			echo "<form action='logout.php' method='post' class='navbar-form form-inline pull-right'>";
			echo "<div class='input-append'>";
			echo "<input class='input uneditable-input' id='disabledOutput' type='text' value='$usernameForm'>";
			echo "<button type='submit' name='logout' class='btn btn-primary'>Logout</button>";
			echo "</div>";
			echo "</form>";
			
			} else {
			?>

            <form action="login.php" method="post" class="navbar-form pull-right">
				<input class="span2" name="username" type="text"  placeholder="Username"> 
				<input class="span2" name="password" type="password"  placeholder="Password"> 
				<div class="btn-group">
					<button type="submit" name="login" class="btn btn-primary">Sign in</button>
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
					<span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="help.php#forgetpass">Forget your password?</a></li>
					</ul>
				</div>
            </form>
			<?php } ?>
			</div>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>




