import database.UserData;
import discord.listeners.JoinListener;
import log.ServerLogger;
import log.SysLog;
import org.javacord.api.entity.user.User;
import util.Init;

public class Main {
    public static void main(String[] args) {
        Init.init(args[0]);
        SysLog.initializeLogger();
        // Everything must happen after these two lines

        //UserData.resetAllUsers();

        ServerLogger.initializeServerLoggers();

        JoinListener.init();
        //RSS.initializeRSS();
    }
}
