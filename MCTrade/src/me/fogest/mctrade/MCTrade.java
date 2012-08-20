/*
 * MCTrade - by Fogest
 * http://fogest.net16.net
 *
 * powered by Kickstarter
 */

package me.fogest.mctrade;


import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import me.fogest.mctrade.commands.CommandExecutor_Mctrade;
import me.fogest.mctrade.listeners.Listener_Player;
import me.fogest.mctrade.listeners.Listener_Server;


public class MCTrade extends JavaPlugin{
	private Logger log;
	private PluginDescriptionFile description;

	private String prefix;
	private Listener_Player listenerPlayer;
	private Listener_Server listenerServer;

	
	@Override
	public void onEnable(){
		log = Logger.getLogger("Minecraft");
		description = getDescription();
		prefix = "["+description.getName()+"] ";

		log("loading "+description.getFullName());

		listenerPlayer = new Listener_Player(this);
		listenerServer = new Listener_Server(this);

		
		getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, listenerPlayer, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLUGIN_ENABLE, listenerServer, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLUGIN_DISABLE, listenerServer, Priority.Normal, this);

		
		getCommand("mctrade").setExecutor(new CommandExecutor_Mctrade(this));

		

	}
	
	@Override
	public void onDisable(){
		log("disabled "+description.getFullName());

	}
	public void log(String message){
		log.info(prefix+message);
	}



	

}
