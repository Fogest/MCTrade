package me.fogest.mctrade;


import java.util.*;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AcceptTrade {
	private Material tradeItem;
	private int tradeId;
	private int itemAmount = 0;
	private int itemCost = 0;
	private Player player;
	private ItemStack[] istack;
	World world;
	
	public AcceptTrade(int tradeId, Player player) {
		this.setTradeId(tradeId);
		this.setPlayer(player);
		itemAmount = DatabaseManager.getTradeAmount(getTradeId());
		itemCost = DatabaseManager.getItemCost(getTradeId());
		DatabaseManager.acceptTrade(getTradeId());
		DatabaseManager.addRepOnTradeAccept(getUsername(getTradeId()),DatabaseManager.getUserLevel(getUsername(getTradeId())));
		getItemName();
		world = player.getWorld();
		ItemStack item = new ItemStack(getTradeItem(),itemAmount);
		addItemsWithDrops(player, item);
	}
	public Material getItemName() {
		String itemString = DatabaseManager.getTradeItem(getTradeId());
		setTradeItem(Material.getMaterial(itemString));
		return getTradeItem();
	}
	 public void addItemsWithDrops(Player p, ItemStack is){
		 HashMap<Integer, ItemStack> extra = p.getInventory().addItem(is);
		 Iterator<Entry<Integer, ItemStack>> it = extra.entrySet().iterator();
		 while (it.hasNext()) {
		 Map.Entry pairs = (Map.Entry)it.next();
		 world.dropItemNaturally(p.getLocation(), (ItemStack) pairs.getValue());
		 it.remove();
		 }
		 }
	
	public String getUsername(int id) {
		return DatabaseManager.getTradeUsername(id);
	}
	 
	public Material getTradeItem() {
		return tradeItem;
	}

	public void setTradeItem(Material tradeItem) {
		this.tradeItem = tradeItem;
	}

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getAmount() {
		return itemAmount;
	}
	public void setAmount(int itemAmount) {
		this.itemAmount = itemAmount;
	}
}
