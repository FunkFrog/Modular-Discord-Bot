package database.cache;

import database.ServerData;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import util.Init;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBCache {
    private List<CachedServer> cachedServers;
    private List<CachedUser> cachedUsers;

    public DBCache() {
        this.cachedServers = new ArrayList<>();
        this.cachedUsers = new ArrayList<>();

        List<Server> activeServers = Collections.synchronizedList(new ArrayList<>());
        List<User> activeUsers = Collections.synchronizedList(new ArrayList<>());

        Init.api.getServers().parallelStream().map(s -> {
            try {
                if (ServerData.getLastAccessed(s.getId()) > (LocalDateTime.now().getSecond() - 604800 /* 7 days */))
                    return s;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(activeServers::add);

        activeServers.parallelStream().forEach(as -> {
            if (as != null) cachedServers.add(new CachedServer(as));
        });

        //todo same thing for users
    }
}
