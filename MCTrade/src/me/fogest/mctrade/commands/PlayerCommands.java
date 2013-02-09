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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.fogest.mctrade.DatabaseManager;
import me.fogest.mctrade.MCTrade;
import me.fogest.mctrade.MessageHandler;
import me.fogest.mctrade.Msg;
import me.fogest.mctrade.Trade;

public class PlayerCommands implements CommandExecutor {
	private int itemId;
	private int itemAmount;
	private Material itemMaterial;
	private MessageHandler m;

	private double tax = MCTrade.tax;
	private double taxAmount;
	private int userId, id;

	// Command Handlers

	public PlayerCommands(final MCTrade plugin, MessageHandler m) {
		this.m = m;
	}

	public boolean onCommand(final CommandSender sender, final Command command, String cmdLabel, String[] args) {
		Player player = (Player) sender;
		userId = DatabaseManager.getUserId(player.getName());
		// Gives usage message if player simply inputs /mct
		if (args.length <= 0 && checkPerms(player, "mctrade")) {
			m.tellPlayer(player, Msg.COMMAND_USAGE);
		}
		// Checking if the user actually put something after /mct
		else if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("help") && checkPerms(player, "help")) {
				m.tellPlayer(player, Msg.COMMAND_HELP);
			}
			// Attempt trade creation command if second var is number
			if (args[0].matches("[0-9]+") && checkPerms(player, "trade")) {
				prepareTrade(player);
				if (userId > 0) {
					CreateTrade(player, args);
				} else if (userId < 1) {
					m.tellPlayer(player, Msg.ACCOUNT_REQUIRED);
				} else {
					m.tellPlayer(player, Msg.USERID_GET_ERROR);
				}
			}
			// Creating Online Account
			else if (args[0].equalsIgnoreCase("create") && checkPerms(player, "create")) {
				int userId = DatabaseManager.getUserId(player.getName());
				if (args.length == 2 && userId < 1) {
					String playerIP = player.getAddress().getAddress().getHostAddress();
					DatabaseManager.createUser(args[1], player.getName(), playerIP);
				} else if (userId > 0) {
					m.tellPlayer(player, "You've already created an account!");
				} else {
					m.tellPlayer(player, Msg.CREATE_USAGE);
				}
			}
			// Accepting Trade
			else if (args[0].equalsIgnoreCase("accept") && checkPerms(player, "accept")) {
				if (userId > 0) {
					AcceptTrade(player, args);
				} else if (userId < 1) {
					m.tellPlayer(player, Msg.ACCOUNT_REQUIRED);
				} else {
					m.tellPlayer(player, Msg.GENERAL_ERROR);
				}

			}
		}
		return false;
	}

	/**
	 * @param player
	 *            The player creating the command
	 * @param args
	 *            The command arguments
	 * @return true if success, false if error
	 */
	private boolean AcceptTrade(Player player, String[] args) {
		if (!(args.length == 2 && args[1].matches("[0-9]+")))
			return false;
		id = Integer.parseInt(args[1]);
		Trade trade = DatabaseManager.getTrade(id);
		// Checking if user is trying to accept own trade.
		if (trade.getUsername().equals(player.getName())) {
			m.tellPlayer(player, Msg.TRADE_CANNOT_ACCEPT_OWN);
			return false;
		}
		// Checking trade status to make sure it is still open!
		if (trade.getStatus() != 1) {
			if (trade.getStatus() == 2)
				m.tellPlayer(player, Msg.TRADE_ALREADY_ACCEPTED);
			else if (trade.getStatus() == 3)
				m.tellPlayer(player, Msg.TRADE_ALREADY_HIDDEN);
			return false;
		}
		
		if(MCTrade.econ.getBalance(player.getName()) < trade.getCost()) {
			m.tellPlayer(player, "Sorry, that trade costs: " + trade.getCost() + " and you only have: " + MCTrade.econ.getBalance(player.getName()));
			return false;
		}
		MCTrade.econ.withdrawPlayer(player.getName(), trade.getCost());
		MCTrade.econ.depositPlayer(trade.getUsername(), trade.getCost());
		
		//Give items	
		HashMap<Integer, ItemStack> extra = player.getInventory().addItem(trade.getItem());
		Iterator<Entry<Integer, ItemStack>> it = extra.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			player.getWorld().dropItemNaturally(player.getLocation(), (ItemStack) pairs.getValue());
			it.remove();
		}
		DatabaseManager.acceptTrade(id);
		m.tellAll(player.getName() + " accepted trade " + id + " giving them " + trade.getAmount() + " " + trade.getItemName());
		m.tellPlayer(player, "Your trade has been processed and items awarded! " + trade.getCost() + " has been removed from your account");
		
		return true;
	}

	/**
	 * @param player
	 *            The player creating the command
	 * @param args
	 *            The command arguments
	 * @return false if command fails, true if success
	 */
	private boolean CreateTrade(Player player, String[] args) {
		prepareTrade(player);
		// Checking if trying to trade air (empty hand)
		if (getItemMaterial().toString().equals("AIR")) {
			m.tellPlayer(player, Msg.TRADE_AIR);
			return false;
		}
		int cost = Integer.parseInt(args[0]);
		Trade trade = new Trade(player.getItemInHand(), getItemMaterial().toString(), player.getName(), cost, 1);
		taxAmount = (cost * tax);
		double balance = (MCTrade.econ.getBalance(player.getName()));
		// Confirming that a user has enough money to pay tax on trade.
		if (balance >= taxAmount) {
			int id = DatabaseManager.createTrade(trade);
			player.getInventory().remove(trade.getItem());
			MCTrade.econ.withdrawPlayer(player.getName(), taxAmount);
			m.tellAll(player.getName() + " has created a new trade (#" + id + ")");
			m.tellAll("Selling: " + trade.getAmount() + " " + trade.getItemName() + " for " + trade.getCost());
			m.tellAll("Type /mct accept " + id + " to accept this trade!");
			m.tellPlayer(player, Msg.TRADE_CREATION_SUCCESS);
			m.tellPlayer(player, "Tax charged: " + taxAmount);
			return true;
		} else {
			m.tellPlayer(player, Msg.NOT_ENOUGH_MONEY);
			m.tellPlayer(player, "Trade Creation fee is: " + taxAmount);
			return false;
		}
	}

	// Checks global mctrade permissions
	private boolean checkPerms(Player player) {
		if (MCTrade.perms.has(player, "mctrade.*")) {
			return true;
		}
		m.tellPlayer(player, Msg.PERMISSION_DENIED);
		return false;
	}

	// Checks specific permissions for sub commands in mctrade and global.
	private boolean checkPerms(Player player, String p) {
		if (MCTrade.perms.has(player, "mctrade." + p)) {
			return true;
		} else if (MCTrade.perms.has(player, "mctrade.*")) {
			return true;
		}
		m.tellPlayer(player, Msg.PERMISSION_DENIED);
		return false;
	}

	public int checkItemMax(Player p) {
		int amount = 0;
		for (ItemStack i : p.getInventory().getContents()) {
			if (i == null) {
				continue;
			}
			if (i.getType().equals(getItemMaterial())) {
				amount = amount + i.getAmount();
			}
		}
		return amount;
	}

	public void removeItem(Player player, ItemStack playerStack, int amount) {
		int typeId = playerStack.getTypeId();
		ItemStack[] contents = player.getInventory().getContents();
		int counter = 0;
		for (ItemStack stack : Arrays.asList(contents)) {
			if (stack == null) {
				continue;
			}
			if (stack.getTypeId() != typeId) {
				continue;
			}
			if (stack.getAmount() < amount) {
				contents[counter] = null;
				amount -= stack.getAmount();
			}
			if (stack.getAmount() == amount) {
				contents[counter] = null;
				break;
			}
			if (stack.getAmount() > amount) {
				stack.setAmount(stack.getAmount() - amount);
				contents[counter] = stack;
				break;
			}
			counter++;
		}
		player.getInventory().setContents(contents);
	}
	

	public boolean onTradeRemoveItem(ItemStack is, Player p) {
		p.getInventory().removeItem(is);
		return true;
	}

	private void prepareTrade(Player player) {
		setItemId(player.getItemInHand().getTypeId());
		setItemAmount(player.getItemInHand().getAmount());
		setItemMaterial(player.getItemInHand().getType());
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

	public short getItemDurability(ItemStack item) {
		return item.getDurability();
	}

	public void setItemAmount(int itemAmount) {
		this.itemAmount = itemAmount;
	}
}
