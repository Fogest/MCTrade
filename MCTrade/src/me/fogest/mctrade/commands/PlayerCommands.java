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

package me.fogest.mctrade.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.fogest.mctrade.MCTrade;

public class PlayerCommands implements CommandExecutor {
	private MCTrade plugin;

	public PlayerCommands(final MCTrade plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(final CommandSender sender, final Command command,
	String cmdLabel, String[] args) {
		if (cmdLabel.equalsIgnoreCase("mctrade")) {
			if(sender.hasPermission("mctrade.mctrade")) {
				plugin.getLogger().info("Console: Works");
				sender.sendMessage("Player: Works");
				return true;
			}
		}
		return false;
	}
}
