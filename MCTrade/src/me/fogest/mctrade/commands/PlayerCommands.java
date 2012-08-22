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

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.fogest.mctrade.MCTrade;

public class PlayerCommands implements CommandExecutor {
	private MCTrade plugin;
	private int itemId;
	private int itemAmount;
	private Material itemMaterial;
	
	
	public PlayerCommands(final MCTrade plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(final CommandSender sender, final Command command,
	String cmdLabel, String[] args) {
		if (cmdLabel.equalsIgnoreCase("mctrade")) {
			if(sender.hasPermission("mctrade.mctrade")) {
				plugin.getLogger().info("Console: MCTrade Works");
				sender.sendMessage("Player: MCTrade Works");
				
				if(args.length < 1 || args.length > 2) {
					sender.sendMessage(ChatColor.RED + "Command Usage : /mctrade <costPerItem> [Amount]. The item in your hand is the item being traded!");
				}
				else if(args.length == 1) {
					Player player = (Player) sender;
					setItemId(player.getItemInHand().getTypeId());
					setItemAmount(player.getItemInHand().getAmount());
					setItemMaterial(player.getItemInHand().getType());
					
					sender.sendMessage(ChatColor.DARK_AQUA + "[MCTrade]"+ChatColor.RED + "Your trade has been sucessful and has been priced at: " + args[0] + " per item");
					plugin.getLogger().info("Player " + sender.getName() + " has created a trade with the following info: Price:" + args[0] + " Item Amount: " + getItemAmount() + " Item: " + getItemMaterial() + " Item ID: " + getItemId());
				}
				else if(args.length == 2) {
					//TODO Check inventory for item in hand and make sure there is enough of that item
				}
				
				return true;
			}
		}
		return false;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Material getItemMaterial() {
		return itemMaterial;
	}

	public void setItemMaterial(Material itemMaterial) {
		this.itemMaterial = itemMaterial;
	}

	public int getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(int itemAmount) {
		this.itemAmount = itemAmount;
	}
}
