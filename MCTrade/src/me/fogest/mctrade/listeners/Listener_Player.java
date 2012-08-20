/*
 * MCTrade - by Fogest
 * http://fogest.net16.net
 *
 * powered by Kickstarter
 */

package me.fogest.mctrade.listeners;


import org.bukkit.event.player.PlayerListener;

import org.bukkit.event.player.PlayerChatEvent;

import me.fogest.mctrade.MCTrade;


public class Listener_Player extends PlayerListener {
	private MCTrade plugin;

	public Listener_Player(MCTrade plugin){
		this.plugin = plugin;
	}

	@Override
	public void onPlayerChat(PlayerChatEvent event){
		// TODO handle that event
	}


}
