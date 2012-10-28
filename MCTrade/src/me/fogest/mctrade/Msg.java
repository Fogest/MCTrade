package me.fogest.mctrade;

public enum Msg {
	COMMAND_USAGE("Command Usage : /mctrade <totalCost> [Amount]. The item in your hand is the item being traded!"),
	ACCOUNT_REQUIRED("You need an account with MCTrade to do this! Visit the following link to register: "),
	
	
	NONE("<none>");
	private String msg;
	
    private Msg(String msg) {
        this.msg = msg;
    }
    public void set(String msg) {
    	this.msg = msg;
    }
	
}
