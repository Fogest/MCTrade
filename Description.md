![MCTrade - Logo](http://i.imgur.com/Pskba.png)

*- Making trading a tad bit easier*

# Download: #
[http://dev.bukkit.org/server-mods/mctrade/files/](http://dev.bukkit.org/server-mods/mctrade/files/)

# Description: #
MCTrade is much like that of [WebAuctionPlus](http://dev.bukkit.org/server-mods/webauctionplus/) which is a trading plugin, with a web interface. WebAuction is a great plugin, and works great on peaceful-nonpvp servers. WebAuctionPlus posses a huge security risk when used on a PvP server.

MCTrade has been designed with the intention to be used on a PvP server as there are multiple features (listed below), that prevent any forms of abuse. Of course MCTrade works fine for non pvp servers as well.

MCTrade is designed to making trading: simple, fast, and secure. No one likes working hard for an item and then getting scammed by some jerk. With MCTrade you don't need to worry about that! All you do is, type in a simple command stating how much of the item you are selling, and how much of the item you are holding do you want to sell. 

From there you can go online and view currently open trades and then go in game and purchase them via a simple command.  There even is a configurable tax in place, to even further prevent some forms of abuse by using the trade system to store items. 

# Security:  #
Some of the features in the plugin to prevent abuse are:

- **Tax on items(configurable) Attempts to make trading and then buying later on alt not worth the cost**
- **Player cannot accept their own trade or close their trade, though an admin can do this if needed.**
- Trades can be closed by admins and have the option to return the items and tax OR to not give this back
- Trades can be deleted by admins, which removes them from display on the webpage and prevents them from being accepted and giving rep.
- Admins can monitor trade stats from the Admin Control Panel and also view players stats in game.
- **Rep based system for users which will come into play in the future.**
- **Players must create an account, linking them to their trades which allows for easy monitoring.**
- **Accounts must be verified preventing users from putting in another users Minecraft Name**

# Commands: #
**/mctrade <item price> [item amount]** *Basic command to used to create a trade.*

**/mctrade accept <trade id>** *Command used to accept an existing trade.*

**/mctrade verify** *Command used in the website verification process to verify a user is who they say they are.*

**/trade** *Command used to administrate trades.*

# Permissions: #
**mctrade.*** *Gives access to all commands.*

**mctrade.mctrade** *Gives access all the /mctrade commands.*

**mctrade.trade.*** *Gives access to /trade which is an admin based command meant to administrate trades! As of now you may only give a user all of the /trade commands, eventually when more commands are added for the /trade command then this will be changed.*

# Configuration: #
The configuration is explained in the setup but the examples are quite useful. The examples explain the variables:

Examples: [config.yml example](http://dev.bukkit.org/server-mods/mctrade/pages/examples/config-yml/) | [config.php example](http://dev.bukkit.org/server-mods/mctrade/pages/examples/config-php/)

More examples: [http://dev.bukkit.org/server-mods/mctrade/pages/examples/](http://dev.bukkit.org/server-mods/mctrade/pages/examples/)

# Setup: #
- Place the file named MCTrade.jar in your plugins folder.
- Extract the folder named web and navigate into the php folder in there.
- Once in the php folder open config.php with some kind of text editor.
- Simply edit the fields with your database information. The config already contains examples, simply change those values, while making sure to leave the data in the quotation marks!
- You can now upload the folder labeled web to a directory on your website (probably using some sort of ftp client). Note, you can rename it the folder name to something other than web.
- Optionally; you can edit the logo (please leave the rest unless you ask for permission), by navigating to "layout\styles\images\blue\logo.png" in the web folder. Simply remove logo.png and put in your own logo labeled logo.png
- Now that the simple web part is done you now want to generate the plugin config. To do this simply restart the server (step 1 must have been done already).
- Now you should see a new folder in your plugins folder called "MCTrade", go into that folder and you should also see a folder called "config.yml". Open up this file using some kind of text editor.
- You now want to edit this file.

Examples: [config.yml example](http://dev.bukkit.org/server-mods/mctrade/pages/examples/config-yml/) | [config.php example](http://dev.bukkit.org/server-mods/mctrade/pages/examples/config-php/)

More examples: [http://dev.bukkit.org/server-mods/mctrade/pages/examples/](http://dev.bukkit.org/server-mods/mctrade/pages/examples/)

# Code: #
This project is available on GitHub, feel free to make pull requests, or even PM me and we can discuss you being added as a contributor if you really want to help! Click the image below to be taken to the Github repo.

[![Github](http://i.imgur.com/buiZx.png)](https://github.com/Fogest/MCTrade)
