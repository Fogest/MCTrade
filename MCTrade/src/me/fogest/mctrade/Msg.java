package me.fogest.mctrade;

import org.bukkit.ChatColor;

public enum Msg {
	COMMAND_USAGE("Type /mct help for a list of commands and some info about them!"),
	COMMAND_HELP(ChatColor.DARK_RED +" Help (Page 1/1) " +
			ChatColor.GRAY + "\n/mct <price> "+ChatColor.RED + "--> " + ChatColor.WHITE + 
			"Used to create a trade. Replace price with the your asking price for your current item stack." +
			"\n" + ChatColor.GRAY + "/mct accept <tradeID> "+ChatColor.RED + "--> " + ChatColor.WHITE +
			"Used to accept a trade. TradeID should be replaced with the id of the trade you want to accept." +
			"\n" + ChatColor.GRAY + "/mct verify "+ChatColor.RED + "--> " + ChatColor.WHITE +
			"Used to verify your online forum account." +
			ChatColor.BLACK + "\n---------------------------------------------------"),
	ACCOUNT_REQUIRED("You need an account with MCTrade to do this! Visit the following link to register: "),
	TRADE_ALREADY_ACCEPTED("This trade has already been accepted!"),
	TRADE_ALREADY_HIDDEN("This trade is hidden"),
	TRADE_CANNOT_ACCEPT_OWN("You cannot accept your own trades"),
	TRADE_ACCEPT_USAGE("Please enter the trade ID using /mctrade accept <id>"),
	TRADE_NOT_ENOUGH_ITEMS("Sorry, you don't have that much of that item!"),
	TRADE_AIR("I know air is cool an all, but I just cannot let you sell that :)"),
	PERMISSION_DENIED("Sorry, but you don't have the correct permissions for this command!"),
	USERID_GET_ERROR("Error getting userId. This may be a database error! Inform a staff member of this issue!"),
	ALREADY_VERIFIED("No need to verify again, our records indicate you already have been verified"),
	GENERAL_ERROR("It appears some sort of error occured! Database down?"),
	NOT_ACTIVE("It appears you have not verified your account yet! Do /mct verify to get started!"),
	NONE("<none>");
	private String msg;
	
    private Msg(String msg) {
        this.msg = msg;
    }
    public void set(String msg) {
    	this.msg = msg;
    }
    public String toString() {
    	return this.msg.toString();
    }
	
}
