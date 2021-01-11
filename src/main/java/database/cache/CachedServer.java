package database.cache;

import org.javacord.api.entity.server.Server;

import java.sql.SQLException;

public class CachedServer {
    private int ID;
    private boolean blacklistedTotal;

    public CachedServer(Server server) {
        try {
            //Try to retrieve the current blacklist total from the database
        } catch (SQLException e) {
            //Use SysLog.err(); to output an error in console
        }
    }

    //Add a method for getting the blacklisted total

    //Add a method for setting the blacklisted total
}
