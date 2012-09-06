/*
 * MCTrade
 * Copyright (C) 2012 Fogest <http://fogest.net16.net> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package me.fogest.mctrade;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import me.fogest.mctrade.commands.Admin;
import me.fogest.mctrade.commands.PlayerCommands;
import me.fogest.mctrade.listeners.Chat;

public class MCTrade extends JavaPlugin {
	private static MCTrade plugin;
	@Override
	public void onEnable() {
		plugin = this;
		// Registering the listeners (with the new 1.3 API)
		getServer().getPluginManager().registerEvents(new Chat(this), this);

		// Registering the command executors
		getCommand("mctrade").setExecutor(new PlayerCommands(this));
		getCommand("trade").setExecutor(new Admin(this));
		DatabaseManager.enableDB();
		
	}
	public void onDisable() {
		DatabaseManager.disableDB();
	}
    public static MCTrade getPlugin(){
        return plugin;
    }
}
