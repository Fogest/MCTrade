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
import me.fogest.mctrade.MessageHandler;

public class Admin implements CommandExecutor {
	private MessageHandler m;
	public Admin(final MCTrade plugin, MessageHandler m) {
		this.m = m;
	}

	public boolean onCommand(final CommandSender sender, final Command command,
	final String cmdLabel, final String[] args) {
		Player player = (Player) sender;
			if(checkPerms(player)) {
				m.tellPlayer(player, "This command is in the test phase right now! It literally does nothing, but nice job finding it!");
				return true;
			}
		return false;
	}
	//Checks global mctrade permissions
	private boolean checkPerms(Player player) {
		if(MCTrade.perms.has(player, "mctrade.trade")) {
			return true;
		}
		else if(MCTrade.perms.has(player, "mctrade.*")) {
			return true;
		}
		
		return false;
	}
	//Checks specific permissions for sub commands in mctrade and global.
	private boolean checkPerms(Player player, String p) {
		if(MCTrade.perms.has(player,"mctrade.trade." + p)){
			return true;
		}
		else if(MCTrade.perms.has(player, "mctrade.trade")) {
			return true;
		}
		else if(MCTrade.perms.has(player, "mctrade.*")) {
			return true;
		}
		return false;
	}
}
