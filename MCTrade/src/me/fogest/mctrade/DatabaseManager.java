package me.fogest.mctrade;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.Location;

import me.fogest.mctrade.MCTrade;
import me.fogest.mctrade.SQLibrary.*;

public class DatabaseManager {
    public static File dbFolder = new File("plugins/MCTrade");

    public static SQLite db = new SQLite(MCTrade.getPlugin().getLogger(), "[MCTrade]", "MCTrade", dbFolder.getPath());

    /**
     * Initializes, opens and confirms the tables and database.
     */
    
    public static void enableDB(){
        db.initialize();
        db.open();
        confirmTables();
    }

    /**
     * Closes the database.
     */
    public static void disableDB(){ if(db.checkConnection()) db.close(); }

    private static void confirmTables(){
        if(!db.checkTable("MCTrade_request")){
            String queryString = "CREATE TABLE MCTrade_request (id integer primary key,"
                    + " user_id integer no null,"
                    + " mod_id integer,"
                    + " mod_timestamp bigint,"
                    + " mod_comment varchar(255),"
                    + " tstamp bigint not null,"
                    + " world varchar(255) not null,"
                    + " x integer not null,"
                    + " y integer not null,"
                    + " z integer not null,"
                    + " text varchar(255) not null,"
                    + " status integer,"
                    + " notified_of_completion integer)";
            try {
                db.query(queryString);
                MCTrade.getPlugin().getLogger().log(Level.INFO, "Successfully created the requests table.");
            } catch (Exception e) {
                MCTrade.getPlugin().getLogger().log(Level.SEVERE, "Unable to create the requests table.");
                e.printStackTrace();
            }
        }
        if(!db.checkTable("MCTrade_user")){
            String queryString = "CREATE TABLE MCTrade_user (id integer primary key,"
                    + " name varchar(255) not null,"
                    + " banned integer)";
            try {
                db.query(queryString);
                MCTrade.getPlugin().getLogger().log(Level.INFO, "Successfully created the users table.");
            } catch (Exception e) {
                MCTrade.getPlugin().getLogger().log(Level.SEVERE, "Unable to create the users table.");
                e.printStackTrace();
            }
        }
    }

    public static int getUserIdCreateIfNotExists(String player){
        if(!db.checkConnection()) return 0;
        int userId = 0;
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT `id` FROM `MCTrade_user` WHERE `name` = ?");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            userId = !rs.isBeforeFirst() ? createUser(player) : rs.getInt("id");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    private static int createUser(String player){
        if(!db.checkConnection()) return 0;
        int userId = 0;
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO `MCTrade_user` (`name`, `banned`) VALUES (?, '0')");
            ps.setString(1, player);
            if(ps.executeUpdate() < 1) return 0;
            ps.close();
            ps = db.getConnection().prepareStatement("SELECT `id` FROM `MCTrade_user` WHERE `name` = ?");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();

            if(!rs.isBeforeFirst()) return 0;
            userId = rs.getInt(1);
            ps.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
    /**
     * Files a request, inserting it into the database.
     * @param player String Name of player
     * @param world String Name of world
     * @param location getLocation() object
     * @param message String help request message
     * @return True if successful.
     */
    public static boolean createTrade(String player, String world, Location location, String message, int userId){
        if(!db.checkConnection() || userId == 0) return false;
        long tstamp = System.currentTimeMillis()/1000;
        try {
            ResultSet rs = db.query("SELECT `banned` FROM MCTrade_user WHERE `id` = '" + userId + "'");
            if(rs.getInt("banned") == 1){
                rs.close();
                return false;
            }
            rs.close();
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO `MCTrade_request` (`user_id`, `tstamp`, `world`, `x`, `y`, `z`," +
                    " `text`, `status`, `notified_of_completion`) VALUES" +
                    " (?, ?, ?, ?, ?, ?, ?, '0', '0')");
            ps.setInt(1, userId);
            ps.setLong(2, tstamp);
            ps.setString(3, world);
            ps.setInt(4, location.getBlockX());
            ps.setInt(5, location.getBlockY());
            ps.setInt(6, location.getBlockZ());
            ps.setString(7, message);
            if(ps.executeUpdate() < 1) return false;
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static int getTicketById(int tradeId){
        if(!db.checkConnection()) return 0;
        int ticketId = 0;
        ResultSet result = db.query("SELECT `id` FROM `MCTrade_request` WHERE `user_id` = '" + userId + "' ORDER BY `tstamp` DESC LIMIT 1");
        try {
            ticketId = result.getInt("id");
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketId;
    }

    /**
     * Sets a ticket to the status specified
     * @param id
     * @param name
     * @return true if successful
     */
    public static String getOpenTrades(String player){
        if(!db.checkConnection()) return false;
        ResultSet rs = db.query("SELECT `status` FROM MCTrade_request WHERE `id` = " + id);
        try {
            if(!rs.isBeforeFirst()) return false;
            if(rs.getInt("status") == status) {
                rs.close();
                return false;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        int modId = getUserIdCreateIfNotExists(name);
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("UPDATE MCTrade_request SET `status` = ?, mod_id = ?, mod_timestamp = ? WHERE `id` = ?");
            ps.setInt(1, status);
            ps.setInt(2, modId);
            ps.setLong(3, System.currentTimeMillis() / 1000);
            ps.setInt(4, id);
            if(ps.executeUpdate() < 1) {
                ps.close();
                return false;
            }
            ps.close();
            //db.query("UPDATE MCTrade_request SET `status` = '" + status + "', mod_id = '" + modId + "', mod_timestamp = '" + System.currentTimeMillis() / 1000 + "' WHERE `id` = " + id).close();

            ResultSet result = db.query("SELECT `status` FROM `MCTrade_request` WHERE `id` = " + id);
            if(result.getInt("status") != status){
                result.close();
                return false;
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static boolean resetDB(){
        db.query("DELETE FROM MCTrade_request");
        db.query("DELETE FROM MCTrade_user");
        return true;
    }
}