package me.fogest.mctrade;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageHandler {
	private String prefix;
	private MCTrade plugin = MCTrade.getPlugin();
	
	public MessageHandler(String prefix) {
		this.prefix = prefix;
	}
	public void sendPlayerMessage(Player p, String message) {
		p.sendMessage(ChatColor.GOLD + prefix + ChatColor.WHITE + message);
	}
	public void serverBroadCast(String message) {
		plugin.getServer().broadcastMessage(ChatColor.GOLD + prefix + ChatColor.WHITE + message);
	}
	public void sendToConsoleInfo( String message) {
		plugin.getLogger().info(prefix + message);
	}
	public void sendToConsoleWarning( String message) {
		plugin.getLogger().warning(prefix + message);
	}
}
