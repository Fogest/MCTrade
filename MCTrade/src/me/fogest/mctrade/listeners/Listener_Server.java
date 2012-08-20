/*
 * MCTrade - by Fogest
 * http://fogest.net16.net
 *
 * powered by Kickstarter
 */

package me.fogest.mctrade.listeners;


import org.bukkit.event.server.ServerListener;

import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.PluginDisableEvent;

import me.fogest.mctrade.MCTrade;


public class Listener_Server extends ServerListener {
	private MCTrade plugin;

	public Listener_Server(MCTrade plugin){
		this.plugin = plugin;
	}

	@Override
	public void onPluginEnable(PluginEnableEvent event){
		// TODO handle that event
	}

	@Override
	public void onPluginDisable(PluginDisableEvent event){
		// TODO handle that event
	}


}
