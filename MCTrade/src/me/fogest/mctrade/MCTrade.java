package me.fogest.mctrade;

import org.bukkit.plugin.java.JavaPlugin;
import me.fogest.mctrade.commands.CommExec;
import me.fogest.mctrade.listeners.Chat;

public class MCTrade extends JavaPlugin {

	@Override
	public void onEnable() {

		// Registering the listeners (with the new 1.3 API)
		getServer().getPluginManager().registerEvents(new Chat(this), this);

		// Registering the command executors
		getCommand("mctrade").setExecutor(new CommExec(this));
	}
}
