package me.fogest.mctrade;

import java.util.Map;

import org.bukkit.enchantments.Enchantment;
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
		this.setItem(item);
		this.setItemName(itemName);
		this.setUsername(username);
		this.setCost(cost);
		this.setStatus(status);
	}

	public int getItemId() {
		return item.getTypeId();
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public short getDurability() {
		return item.getDurability();
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getAmount() {
		return item.getAmount();
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

	public Map<Enchantment, Integer> getEnchant() {
		return item.getEnchantments();
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
