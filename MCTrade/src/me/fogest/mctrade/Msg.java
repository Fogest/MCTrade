package me.fogest.mctrade;

public enum Msg {
	COMMAND_USAGE("Command Usage : /mctrade <totalCost> [Amount]. The item in your hand is the item being traded!"),
	ACCOUNT_REQUIRED("You need an account with MCTrade to do this! Visit the following link to register: "),
	TRADE_ALREADY_ACCEPTED("This trade has already been accepted!"),
	TRADE_ALREADY_HIDDEN("This trade is hidden"),
	TRADE_CANNOT_ACCEPT_OWN("You cannot accept your own trades"),
	TRADE_ACCEPT_USAGE("Please enter the trade ID using /mctrade accept <id>"),
	NONE("<none>");
	private String msg;
	
    private Msg(String msg) {
        this.msg = msg;
    }
    public void set(String msg) {
    	this.msg = msg;
    }
	
}
