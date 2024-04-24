package database;

import log.SysLog;
import org.javacord.api.entity.user.User;
import util.Init;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class UserData {
    public static void resetAllUsers() {
        try {
            Database.runSchema("/UserDataSchema.sql");
            SysLog.out("Reset userdata.");
            Init.api.getCachedUsers().forEach(user -> {
                try {
                    addUser(user.getId());
                    SysLog.out("Added " + user + " to the database.");
                } catch (SQLException e) {
                    SysLog.err("DATABASE ERROR: Could not add user.");
                    SysLog.err(e.getMessage());
                }
            });
        } catch (SQLException e) {
            SysLog.err("DATABASE ERROR: Could not reset userdata.");
            SysLog.err(e.getMessage());
        }

    }

    public static void addUser(long id) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("INSERT INTO USERDATA " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        User user = Init.api.getCachedUserById(id).orElseThrow(NullPointerException::new);
        statement.setLong(1, id); // Discord ID
        statement.setString(2, "PLACEHOLDER_USERNAME"); // Discord Username
        statement.setInt(3, 0); // Global Warnings
        statement.setBoolean(4, false); // Blacklisted
        statement.setLong(5, 0); // XP
        statement.setInt(6, 0); // Level
        statement.setLong(7, 0); // Credits
        statement.setBoolean(8, false); // Bot Authority
        statement.execute();

        try {
            setUsername(id, user.getDiscriminatedName());
        } catch (SQLException e) {
            setUsername(id, "UNABLE TO ENTER INTO DB");
        }
    }

    public static ResultSet getDBRow(long id) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("SELECT * FROM USERDATA WHERE ID = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet;
    }

    private static long getDBColumnAsLong(long id, String colLabel) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("SELECT * FROM USERDATA WHERE ID = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getLong(colLabel);
    }

    private static int getDBColumnAsInt(long id, String colLabel) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("SELECT * FROM USERDATA WHERE ID = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(colLabel);
    }

    private static String getDBColumnAsString(long id, String colLabel) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("SELECT * FROM USERDATA WHERE ID = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getString(colLabel);
    }

    private static boolean getDBColumnAsBool(long id, String colLabel) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("SELECT * FROM USERDATA WHERE ID = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getBoolean(colLabel);
    }

    public static boolean getBlacklist(long id) throws SQLException {
        return getDBColumnAsBool(id, "Blacklist");
    }

    public static long getCredits(long id) throws SQLException {
        return getDBColumnAsLong(id,"Credits");
    }

    public static int getLevel(long id) throws SQLException {
        return getDBColumnAsInt(id, "Level");
    }

    public static Long getXp(long id) throws SQLException {
        return getDBColumnAsLong(id, "XP");
    }

    private static String getUsername(long id) throws SQLException {
        return getDBColumnAsString(id, "Username");
    }

    public static boolean isBotAuth(long id) throws SQLException {
        return getDBColumnAsBool(id, "BotAuth");
    }

    public static boolean userExists(long id) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("SELECT 1 FROM USERDATA WHERE ID = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    private static void setDBColumnAsLong(long id, String colLabel, long value) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("UPDATE USERDATA SET " + colLabel + " = ? WHERE ID = ?");
        statement.setLong(1, value);
        statement.setLong(2, id);
        statement.execute();
    }

    private static void setDBColumnAsInt(long id, String colLabel, int value) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("UPDATE USERDATA SET " + colLabel + " = ? WHERE ID = ?");
        statement.setInt(1, value);
        statement.setLong(2, id);
        statement.execute();
    }

    private static void setDBColumnAsString(long id, String colLabel, String value) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("UPDATE USERDATA SET " + colLabel + " = ? WHERE ID = ?");
        statement.setString(1, value);
        statement.setLong(2, id);
        statement.execute();
    }

    private static void setDBColumnAsBool(long id, String colLabel, boolean value) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("UPDATE USERDATA SET " + colLabel + " = ? WHERE ID = ?");
        statement.setBoolean(1, value);
        statement.setLong(2, id);
        statement.execute();
    }

    public static void setBlacklist(long id, boolean blacklist) throws SQLException {
        setDBColumnAsBool(id, "blacklist", blacklist);
    }

    public static void setCredits(long id, long credits) throws SQLException {
        setDBColumnAsLong(id, "Credits", credits);
    }

    public static void setLevel(long id, int level) throws SQLException {
        setDBColumnAsInt(id, "Level", level);
    }

    public static void setXp(long id, long xp) throws SQLException {
        setDBColumnAsLong(id, "XP", xp);
    }

    private static void setUsername(long id, String username) throws SQLException {
        setDBColumnAsString(id, "Username", username);
    }

    public static void setBotAuth(long id, Boolean mode) throws SQLException {
        setDBColumnAsBool(id, "BotAuth", mode);
    }

    public static void incrementLevel(long id) throws SQLException {
        setDBColumnAsInt(id, "Level", getDBColumnAsInt(id, "Level") + 1);
    }

    public static void changeCredits(long id, long change) throws SQLException {
        setDBColumnAsLong(id, "Credits", getDBColumnAsLong(id, "Credits") + change);
    }

    public static void changeXP(long id, long change) throws SQLException {
        setDBColumnAsLong(id, "XP", getDBColumnAsLong(id, "XP") + change);
    }

    public static void refreshUsers() {
        Init.api.getCachedUsers().forEach(user -> {
            try {
                if (userExists(user.getId())) {
                    String dbUsername = getUsername(user.getId());
                    try {
                        String currentUsername = Init.api.getUserById(user.getId()).get(5, TimeUnit.SECONDS).getDiscriminatedName();
                        if (!dbUsername.equals(currentUsername)) {
                            try {
                                setUsername(user.getId(), currentUsername);
                                SysLog.out("Updated username for " + user);
                            } catch (SQLException e) {
                                SysLog.err("DATABASE ERROR: Could not set username for " + user);
                                SysLog.err(e.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        SysLog.err("DISCORD ERROR: Could not get username for " + user);
                        SysLog.err(e.getMessage());
                    }
                } else {
                    SysLog.out(user + " does not exist in the userdata table. Adding them now...");
                    addUser(user.getId());
                }
            } catch (SQLException e) {
                SysLog.err("DATABASE ERROR: Could not get entry for " + user);
                SysLog.err(e.getMessage());
            }
        });
    }
}