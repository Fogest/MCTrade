    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"></a> <a class="brand" href="index.php">MCTrade</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <?php if($bodyTab == "home"){ ?> <li class='active'> <?php } else { ?> <li> <?php } ?>
                <a href="index.php">Home</a>
              </li>
			  <?php if($bodyTab == "help"){ ?> <li class='active'> <?php } else { ?> <li> <?php } ?>
                <a href="help.php">Help</a>
              </li>
            </ul>
			<div class="pull-right">
			<?php
			if(loggedin()) {
				$usernameForm = getUsername();
				echo "<a class='header-right' href='member.php'>$usernameForm</a>";
				$userLevelForm = getUserlevel();
				if($userLevelForm == "4") {
					echo " <a class='header-right' href='admincp.php'>(Admin CP)</a>";
				}
				echo " <a class='header-right' href='logout.php'>(Logout)</a>";
			} else {
			?>
			
            <form action="login.php" method="post" class="navbar-form">
              <input class="span2" name="username" type="text" placeholder="Username"> 
			  <input class="span2" name="password" type="password" placeholder="Password"> 
			  <input type="checkbox" value="" title="Remember Password?" name="remPass"/>
			  <button type="submit" name="login" class="btn">Sign in</button>
            </form>
			<?php } ?>
			</div>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>




