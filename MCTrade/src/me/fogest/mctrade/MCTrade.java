package me.fogest.mctrade;

import org.bukkit.plugin.java.JavaPlugin;
import me.fogest.mctrade.commands.CommExec;
import me.fogest.mctrade.listeners.Listener;

public class MCTrade extends JavaPlugin {

	@Override
	public void onEnable() {

		// Registering the listeners (with the new 1.3 API)
		getPluginManager().createRegisteredListeners(new Listener(this), this);

		// Registering the command executors
		getCommand("mctrade").setExecutor(new CommandExecutor_Mctrade(this));
	}
}
