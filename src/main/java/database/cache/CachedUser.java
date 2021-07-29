package database.cache;

import database.UserData;
import log.SysLog;
import org.javacord.api.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CachedUser {
    private long ID;
    private String username;
    private int globalWarns;
    private boolean blacklist;
    private boolean cachedSuccessfully;

    public CachedUser (User user) {
        try {
            ResultSet userData = UserData.getDBRow(user.getId());
            this.ID = user.getId();
            this.blacklist = userData.getBoolean("blacklist");
            this.username = userData.getString("username");
            this.globalWarns = userData.getInt("globalwarns");
            this.cachedSuccessfully = true;
        } catch(SQLException e) {
            SysLog.err("Couldn't retrieve data to cache for user " + user.getDiscriminatedName()
                    + " (" + user.getId() + ")");
        }
    }

    public boolean isCached() {
        return cachedSuccessfully;
    }

    public Boolean isBlacklist() {
        return blacklist;
    }

    public void setBlacklist(boolean blacklist) {
        this.blacklist = blacklist;
        try {
            UserData.setBlacklist(this.ID, blacklist);
        } catch(SQLException e) {
            SysLog.err("Unable to set blacklist value in database.");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        //todo set in database
    }

    public int getGlobalWarns() {
        return globalWarns;
    }

    public void setGlobalWarns(int globalWarns) {
        this.globalWarns = globalWarns;
        //todo set in database
    }
}
