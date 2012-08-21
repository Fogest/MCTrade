package me.fogest.mctrade.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.fogest.mctrade.MCTrade;

public class Chat implements Listener {
	private MCTrade plugin;

	public Chat(final MCTrade plugin){
		this.plugin = plugin;
	}

	public void onPlayerChat(final AsyncPlayerChatEvent event) {
		// TODO handle that event
	}
}
