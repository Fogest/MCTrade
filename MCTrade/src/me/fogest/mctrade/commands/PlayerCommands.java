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
import org.bukkit.inventory.ItemStack;

import me.fogest.mctrade.DatabaseManager;
import me.fogest.mctrade.MCTrade;
import me.fogest.mctrade.MessageHandler;
import me.fogest.mctrade.Verify;
import me.fogest.mctrade.AcceptTrade;

public class PlayerCommands implements CommandExecutor {
	private MCTrade plugin;
	private int itemId;
	private int itemAmount;
	private Material itemMaterial;
	private MessageHandler m = new MessageHandler("[MCTrade]");
	
	
	public PlayerCommands(final MCTrade plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(final CommandSender sender, final Command command,
	String cmdLabel, String[] args) {
		if (cmdLabel.equalsIgnoreCase("mctrade")) {
			if(sender.hasPermission("mctrade.mctrade")) {
				
				if(args.length <= 0) {
					Player player = (Player) sender;
					m.sendPlayerMessage(player, "Command Usage : /mctrade <costPerItem> [Amount]. The item in your hand is the item being traded!");
				}
				else if(args.length >= 1) {
					Player player = (Player) sender;
					setItemId(player.getItemInHand().getTypeId());
					setItemAmount(player.getItemInHand().getAmount());
					setItemMaterial(player.getItemInHand().getType());
					int userId = DatabaseManager.getUserId(sender.getName());
					if(userId == 0) {
						String longLink = "http://fogest.net16.net/mctrade/registration.html";
						m.sendPlayerMessage(player, "You need an account with MCTrade to do this! Visit the following link to register: ");
						m.sendPlayerMessage(player, longLink);
					}else {
						if(args[0].equalsIgnoreCase("verify")) {
							int ver = Verify.createUserVerification(sender.getName());
							m.sendPlayerMessage(player, "Your verification code is: " + ver);
						}
						else if(args[0].equalsIgnoreCase("accept")) {
							if(args[1].matches("[0-9]+")) {
								int id = Integer.parseInt(args[1]);
								String mcTrader = DatabaseManager.getTradeUsername(id);
									
									if(!(mcTrader.equals(sender.getName()))) {
									int tradeStatus = DatabaseManager.getTradeStatus(id);
										if(tradeStatus == 1) {
										AcceptTrade accept = new AcceptTrade(Integer.parseInt(args[1]),player);
										m.sendPlayerMessage(player, "You have sucessfully purchased " + accept.getAmount() + " " + accept.getTradeItem() + "'s");
										}else if(tradeStatus == 2) {
											m.sendPlayerMessage(player, "This trade has already been accepted!");
										}else if(tradeStatus == 3) {
											m.sendPlayerMessage(player, "This trade is hidden");
										}
									}else {
										m.sendPlayerMessage(player, "You cannot accept your own trades");
									}
							} else {
								m.sendPlayerMessage(player, "Please enter the trade ID using /mctrade accept <id>");
							}
						}else if(args[0].matches("[0-9]+")){
						ItemStack tradeItem = new ItemStack(getItemMaterial(),itemAmount);
						boolean trade = onTradeRemoveItem(tradeItem , player);
						if(trade == true) {
							m.serverBroadCast(sender.getName() +" has created a trade with the id of: " + DatabaseManager.createTrade(sender.getName(), getItemId(),getItemMaterial().toString(),getItemAmount(), args[0], player.getAddress().getAddress().getHostAddress()) + " and is selling " + getItemAmount() + " " + getItemMaterial() + ", priced at $" + args[0] + " per item");
							
							m.sendPlayerMessage(player, "Your trade has been sucessful and has been priced at: " + args[0] + " per item");
							
							m.sendToConsoleInfo("Player " + sender.getName() + " has created a trade with the following info: Price:" + args[0] + " Item Amount: " + getItemAmount() + " Item: " + getItemMaterial() + " Item ID: " + getItemId());
						}else if(trade == false) {
							m.sendPlayerMessage(player, "Sorry, you don't have that much of that item!");
						}
						}
					}
			}
			//else if(args.length == 2) {
					//TODO Check inventory for item in hand and make sure there is enough of that item
			//	}
				
				return true;
			}
		}
		return false;
	}

	public boolean onTradeRemoveItem(ItemStack is, Player p) {
		p.getInventory().removeItem(is);
		return true;
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
