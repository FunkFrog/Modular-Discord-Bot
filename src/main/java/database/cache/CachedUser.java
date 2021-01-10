package database.cache;

import database.UserData;
import log.SysLog;
import org.javacord.api.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CachedUser {
    private Boolean blacklist;
    private long XP;
    private long credits;
    public boolean cachedSuccessfully;

    public CachedUser (User user) {
        try {
            ResultSet userData = UserData.getDBRow(user.getId());
            this.blacklist = userData.getBoolean("blacklist");
            this.XP = userData.getLong("XP");
            this.credits = userData.getLong("credits");
            cachedSuccessfully = true;
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

    public long getXP() {
        return XP;
    }

    public long getCredits() {
        return credits;
    }
}
