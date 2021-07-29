package util;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Init {
    public static double version;
    public static DiscordApi api;
    public static Server discord;
    public static User self;
    public static ServerTextChannel botLog, liveChannel, staffChannel, botChannel, generalChannel, welcomeChannel;
    public static int maxUserWarnings;
    public static Color embedColor;
    public static String invite, icon, prefix, botName;
    public static ScheduledExecutorService scheduler;

    public static void init(String token) {
        version = 1.2;

        api = new DiscordApiBuilder().setToken(token).login().join();
        discord = api.getServerById(501632980834320415L).orElseThrow(IllegalStateException::new);
        botLog = api.getServerTextChannelById(563216633099976724L).orElseThrow(IllegalStateException::new);
        liveChannel = api.getServerTextChannelById(502903626268672031L).orElseThrow(IllegalStateException::new);
        staffChannel = api.getServerTextChannelById(505025708666650624L).orElseThrow(IllegalStateException::new);
        botChannel = api.getServerTextChannelById(502904418782674954L).orElseThrow(IllegalStateException::new);
        generalChannel = api.getServerTextChannelById(501632980834320417L).orElseThrow(IllegalStateException::new);
        welcomeChannel = api.getServerTextChannelById(502903053570277376L).orElseThrow(IllegalStateException::new);
        self = api.getYourself();
        embedColor = Init.discord.getRoleColor(Init.self).orElseThrow(IllegalStateException::new);
        maxUserWarnings = 3;

        icon = "https://cdn.discordapp.com/attachments/501632980834320417/503015325042737152/Psychon.png";
        prefix = "cum!";
        botName = "Thing";

        scheduler = Executors.newScheduledThreadPool(1);
    }
}