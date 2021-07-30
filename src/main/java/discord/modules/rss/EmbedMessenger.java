package discord.modules.rss;

import database.ServerData;
import org.javacord.api.entity.message.embed.Embed;
import org.javacord.api.entity.server.Server;
import util.Init;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EmbedMessenger {
    public static void serverCycle(RSSContainer container) throws SQLException {
        ResultSet subscribedServers = ServerData.getSubscribedServers(container.getCategory());
        HashMap<Long, Long> serverChannels = new HashMap<>();

        while (subscribedServers.next()) { //todo make sure this doesn't skip the first server
            serverChannels.put(subscribedServers.getLong(1), subscribedServers.getLong(2));
        }

        for (Server server : Init.api.getServers()) { //todo replace with cached servers/try from list subscribed
            if (serverChannels.containsKey(server.getId())) {
                sendEmbed(container, server, serverChannels.get(server.getId()));
            }
        }
    }

    public static void sendEmbed(RSSContainer container, Server server, long channelID) { //todo check if valid, if not, remove from table

    }

    public static Embed formatEmbed(RSSContainer container) {
        return null;
    }
}
