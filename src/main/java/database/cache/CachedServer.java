package database.cache;

import database.ServerData;
import log.SysLog;
import org.javacord.api.entity.server.Server;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CachedServer {
    private long ID;
    private int blacklistedTotal;
    private boolean cachedSuccessfully;

    public CachedServer(Server server) {
        try {
            ResultSet serverData = ServerData.getDBRow(server.getId());
            this.ID = server.getId();
            this.blacklistedTotal = serverData.getInt("blacklistedtotal");
            this.cachedSuccessfully = true;
        } catch (SQLException e) {
            SysLog.err("Couldn't retrieve data to cache for user " + server.getName()
                    + " (" + server.getId() + ")");
        }
    }

    public int getBlacklistedTotal() {
        return blacklistedTotal;
    }

    public void setBlacklistedTotal(int blacklistedTotal) {
        this.blacklistedTotal = blacklistedTotal;
    }

    public long getID() {
        return ID;
    }

    public boolean isCached() {
        return cachedSuccessfully;
    }
}
