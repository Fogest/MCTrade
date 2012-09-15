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
import me.fogest.mctrade.MCTrade;
import me.fogest.mctrade.MessageHandler;

public class Admin implements CommandExecutor {
	private MCTrade plugin;
	private MessageHandler m;
	public Admin(final MCTrade plugin, MessageHandler m) {
		this.plugin = plugin;
		this.m = m;
	}

	public boolean onCommand(final CommandSender sender, final Command command,
	final String cmdLabel, final String[] args) {
			if(MCTrade.perms.has(sender,"mctrade.trade") || MCTrade.perms.has(sender, "mctrade.*")) {
				plugin.getLogger().info("Console: Trade Works");
				sender.sendMessage("Player: Trade Works");
				plugin.getLogger().info("Console: Trade Works");
				return true;
			}
		return false;
	}
}
