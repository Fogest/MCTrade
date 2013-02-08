package me.fogest.mctrade;

import org.bukkit.inventory.ItemStack;

public class Trade {
	// Trade ID: Row number in database
	// Block ID: Corresponding trades item id
	// Durability: The current durability of the item
	// Amount: The amount of items in the stack
	// Status: The current trade status. 1 = Open Trade. 2 = Closed Trade. 3 =
	// Hidden Trade
	private int itemId, durability, amount, status;
	// Username: The username of trade creator
	// The enchant values for item
	private String username, enchant, itemName;
	// Cost for the trade
	private double cost;
	
	private ItemStack item;
	public Trade(ItemStack item, String itemName, String username, double cost, int status) {
		this.setItemId(itemId);
		this.setDurability(durability);
		this.setAmount(amount);
		this.setStatus(status);
		this.setItemName(itemName);
		this.setUsername(username);
		this.setCost(cost);
		this.setItem(item);
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEnchant() {
		return enchant;
	}

	public void setEnchant(String enchant) {
		this.enchant = enchant;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}
}
