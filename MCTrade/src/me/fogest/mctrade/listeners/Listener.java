package me.fogest.mctrade.listeners;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerChatEvent;

import me.fogest.mctrade.MCTrade;

public class Listener extends PlayerListener {
	private MCTrade plugin;

	public Listener(final MCTrade plugin){
		this.plugin = plugin;
	}

	@Override
	public void onPlayerChat(final PlayerChatEvent event) {
		// TODO handle that event
	}


}
