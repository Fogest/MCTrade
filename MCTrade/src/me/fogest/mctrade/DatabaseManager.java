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

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import me.fogest.mctrade.MCTrade;
import me.fogest.mctrade.SQLibrary.*;

public class DatabaseManager {
	private static MCTrade plugin = MCTrade.getPlugin();
	public static File dbFolder = new File("plugins/MCTrade");

	public static me.fogest.mctrade.SQLibrary.MySQL db = new me.fogest.mctrade.SQLibrary.MySQL(MCTrade.getPlugin().getLogger(), "[MCTrade]", "localhost", "3306", "mctrade", "root", "");

	/**
	 * Initializes, opens and confirms the tables and database.
	 */

	public static void enableDB() {
		db.initialize();
		db.open();
		confirmTables();
		plugin.getLogger().info("Database Connectioned Sucessfully Established!");
	}

	/**
	 * Closes the database.
	 */
	public static void disableDB() {
		if (db.checkConnection())
			db.close();
	}

	private static void confirmTables() {
		if (!db.checkTable("MCTrade_users")) {
			String queryString = "CREATE TABLE IF NOT EXISTS `MCTrade_users` (" + "`user_id` int(10) unsigned NOT NULL AUTO_INCREMENT," + "`password` text COLLATE latin1_general_ci NOT NULL,"
					+ "`username` text COLLATE latin1_general_ci NOT NULL," + "`user_level` int(11) NOT NULL," + "`ip` text COLLATE latin1_general_ci NOT NULL," + "PRIMARY KEY (`user_id`))";

			try {
				db.query(queryString);
				MCTrade.getPlugin().getLogger().log(Level.INFO, "Successfully created the requests table.");
			} catch (Exception e) {
				MCTrade.getPlugin().getLogger().log(Level.SEVERE, "Unable to create the requests table.");
				e.printStackTrace();
			}
		}
		if (!db.checkTable("MCTrade_trades")) {
			String queryString = "CREATE TABLE IF NOT EXISTS `MCTrade_trades` (" + "`id` int(10) unsigned NOT NULL AUTO_INCREMENT," + "`Minecraft_Username` text NOT NULL,"
					+ "`Block_ID` int(5) NOT NULL," + "`Block_Name` text COLLATE latin1_general_ci NOT NULL," + "`Quantity` int(3) NOT NULL," + "`Cost` text NOT NULL," + "`TradeNotes` text NOT NULL,"
					+ "`IP` text NOT NULL," + "`Trade_Status` int(11) NOT NULL COMMENT '1 = Open Trade, 2 = Closed Trade, 3 = Hidden Trade'," + "PRIMARY KEY (`id`))";
			try {
				db.query(queryString);
				MCTrade.getPlugin().getLogger().log(Level.INFO, "Successfully created the trades table.");
			} catch (Exception e) {
				MCTrade.getPlugin().getLogger().log(Level.SEVERE, "Unable to create the trades table.");
				e.printStackTrace();
			}
		}
	}

	public static int getUserId(String player) {
		if (!db.checkConnection())
			return 0;
		int userId = 0;
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("SELECT `user_id` FROM `mctrade_users` WHERE `username` = ?");
			ps.setString(1, player);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userId = rs.getInt("user_id");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}

	public static String getUsername(String player) {
		if (!db.checkConnection())
			return "";
		String username = "";
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("SELECT `username` FROM `mctrade_users` WHERE `username` = ?");
			ps.setString(1, player);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				username = rs.getString("username");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return username;
	}

	public static int createUser(String password, String player, String ip) {
		if (!db.checkConnection())
			return 0;
		int userId = 0;
		String hashtext = "";
		try {
			MessageDigest m;
			m = MessageDigest.getInstance("MD5");

			m.update(password.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO mctrade_users VALUES (NULL,?,?,'1',?)");
			ps.setString(1, password);
			ps.setString(2, player);
			ps.setString(3, ip);
			ps.executeUpdate();
			ps.close();
			ps = db.getConnection().prepareStatement("SELECT MAX(id) FROM mctrade_trades");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userId = rs.getInt("MAX(user_id)");
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}

	public static int createTrade(String player, int blockId, String block, int amount, String cost, String Ip) {
		String username = getUsername(player);
		int id = 0;
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO mctrade_trades VALUES (NULL,?,?,?,?,?,?,?,'1')");
			ps.setString(1, player);
			ps.setInt(2, blockId);
			ps.setString(3, block);
			ps.setInt(4, amount);
			ps.setString(5, cost);
			ps.setString(6, "Trade Created using MCTrade Plugin");
			ps.setString(8, Ip);
			ps.executeUpdate();
			ps.close();
			ps = db.getConnection().prepareStatement("SELECT MAX(id) FROM mctrade_trades");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("MAX(id)");
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static void acceptTrade(int id) {
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("UPDATE  `mctrade_trades` SET  `Trade_Status` =  '2' WHERE  `mctrade_trades`.`id` =?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getUserLevel(String mcUsername) {
		int userLevel = 0;
		if (!db.checkConnection())
			return 0;
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("SELECT `user_level` FROM `mctrade_users` WHERE `username` = ?");
			ps.setString(1, mcUsername);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userLevel = rs.getInt("user_level");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userLevel;
	}

	public static void setUserLevelForMod(String mcUsername) {
		int curLevel = getUserLevel(mcUsername);
		if (curLevel != 3) {
			try {
				PreparedStatement ps = db.getConnection().prepareStatement("UPDATE  `mctrade_users` SET  `user_level` =  ? WHERE  `mctrade_users`.`username` = ?");
				ps.setInt(1, (3));
				ps.setString(2, mcUsername);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setUserLevelForAdmin(String mcUsername) {
		int curLevel = getUserLevel(mcUsername);
		if (curLevel != 4) {
			try {
				PreparedStatement ps = db.getConnection().prepareStatement("UPDATE  `mctrade_users` SET  `user_level` =  ? WHERE  `mctrade_users`.`username` = ?");
				ps.setInt(1, (4));
				ps.setString(2, mcUsername);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getTradeUsername(int id) {
		String tradeUsername = "";
		if (!db.checkConnection())
			return "";
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("SELECT `Minecraft_Username` FROM `mctrade_trades` WHERE `id` = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tradeUsername = rs.getString("Minecraft_Username");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tradeUsername;
	}

	public static int getTradeStatus(int id) {
		int tradeStatus = 0;
		if (!db.checkConnection())
			return 0;
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("SELECT `Trade_Status` FROM `mctrade_trades` WHERE `id` = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tradeStatus = rs.getInt("Trade_Status");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tradeStatus;
	}

	public static int getTradeItemId(int id) {
		int tradeItemId = 0;
		if (!db.checkConnection())
			return 0;
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("SELECT `Block_ID` FROM `mctrade_trades` WHERE `id` = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tradeItemId = rs.getInt("Block_ID");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tradeItemId;
	}

	public static String getTradeItem(int id) {
		String tradeItem = "";
		if (!db.checkConnection())
			return "";
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("SELECT `Block_Name` FROM `mctrade_trades` WHERE `id` = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tradeItem = rs.getString("Block_Name");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tradeItem;
	}

	public static int getItemCost(int id) {
		int tradeCost = 0;
		if (!db.checkConnection())
			return 0;
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("SELECT `Cost` FROM `mctrade_trades` WHERE `id` = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tradeCost = rs.getInt("Cost");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tradeCost;
	}

	public static int getTradeAmount(int id) {
		int tradeAmount = 0;
		if (!db.checkConnection())
			return 0;
		try {
			PreparedStatement ps = db.getConnection().prepareStatement("SELECT `Quantity` FROM `mctrade_trades` WHERE `id` = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tradeAmount = rs.getInt("Quantity");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tradeAmount;
	}

	public static String getTraderIP(int id) {
		return "";
	}

	public static boolean resetDB() {
		db.query("DELETE FROM MCTrade_trades");
		db.query("DELETE FROM MCTrade_users");
		return true;
	}
}