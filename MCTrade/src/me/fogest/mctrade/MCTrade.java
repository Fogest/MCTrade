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

package me.fogest.mctrade;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.fogest.mctrade.commands.Admin;
import me.fogest.mctrade.commands.PlayerCommands;
import me.fogest.mctrade.listeners.Chat;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

public class MCTrade extends JavaPlugin {
	private static MCTrade plugin;
	private MessageHandler msg;
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
	private Updater u;

	public static String mysqlHostname; 
	public static String mysqlPort;
	public static String mysqlUsername;
	public static String mysqlPassword;
	public static String mysqlDatabase;
	
	public static double tax;
	
	public static String webAddress;
	
	//public String[] moderators;
	//public String[] admins;
	
	public boolean update;
    
	public MCTrade() {
		plugin = this;
		msg = new MessageHandler("[MCTrade]", this);
		u = new Updater();
	}
	
	@Override
	public void onEnable() {
		// Registering the listeners (with the new 1.3 API)
		getServer().getPluginManager().registerEvents(new Chat(this), this);

		// Registering the command executors
		getCommand("mctrade").setExecutor(new PlayerCommands(this,msg));
		getCommand("trade").setExecutor(new Admin(this));
		DatabaseManager.enableDB();
		
		econ = getProvider(Economy.class);
		perms = getProvider(Permission.class);
		chat = getProvider(Chat.class);
		
		if(update == true)
			msg.sendToConsoleInfo(u.checkForUpdate());
	}
	public void onReload() {
		
	}
	public <T> T getProvider(final Class<T> c) {
        final org.bukkit.plugin.RegisteredServiceProvider<T> provider
            = Bukkit.getServicesManager().getRegistration(c);
        if (provider != null)
            return provider.getProvider();
        return null;
    }
	public void reloadSettings() {
		reloadConfig();
		getConfig().options().copyDefaults(true);
		saveConfig();
		mysqlHostname = getConfig().getString("mysql.hostname");
		mysqlPort = getConfig().getString("mysql.port");
		mysqlUsername = getConfig().getString("mysql.username");
		mysqlPassword = getConfig().getString("mysql.password");
		mysqlDatabase = getConfig().getString("mysql.database");
		
        List<String> modsList = getConfig().getStringList("Web.Access.Moderator");
        for (String s : modsList){
        	DatabaseManager.setUserLevelForMod(s);
        	//int i = 0;
            //moderators[i] = s;
           // i++;
        }
        List<String> adminList = getConfig().getStringList("Web.Access.Admin");
        for (String s : adminList){
        	DatabaseManager.setUserLevelForAdmin(s);
        	//int i = 0;
           // admins[i] = s;
            //i++;
        }
        update = getConfig().getBoolean("TradeOptions.UpdateChecker");
        tax = getConfig().getDouble("TradeOptions.Tax");
        webAddress = getConfig().getString("Web.MctradeDirectory");
		
	}
	public void onDisable() {
		DatabaseManager.disableDB();
	}
    public static MCTrade getPlugin(){
        return plugin;
    }
}
