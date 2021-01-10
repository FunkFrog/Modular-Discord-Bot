package database.cache;

import database.UserData;
import log.SysLog;
import org.javacord.api.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CachedUser {
    private String username;
    private int globalwarns;
    private Boolean blacklist;
    private long XP;
    private int level;
    private long credits;
    private boolean botauth;
    public boolean cachedSuccessfully;

    public CachedUser (User user) {
        try {
            ResultSet userData = UserData.getDBRow(user.getId());
            this.username = userData.getString("username");
            this.globalwarns = userData.getInt("globalwarns");
            this.blacklist = userData.getBoolean("blacklist");
            this.XP = userData.getLong("XP");
            this.level = userData.getInt("level");
            this.credits = userData.getLong("credits");
            this.botauth = userData.getBoolean("botauth");
            cachedSuccessfully = true;
        } catch(SQLException e) {
            SysLog.err("Couldn't retrieve data to cache for user " + user.getDiscriminatedName()
                    + " (" + user.getId() + ")");
        }
    }

    public boolean isCached() {
        return cachedSuccessfully;
    }

    public String getUsername() {
        return username;
    }

    public int getGlobalwarns() {
        return globalwarns;
    }

    public Boolean isBlacklist() {
        return blacklist;
    }

    public long getXP() {
        return XP;
    }

    public int getLevel() {
        return level;
    }

    public long getCredits() {
        return credits;
    }

    public boolean isBotauth() {
        return botauth;
    }
}
