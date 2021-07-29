import discord.modules.rss.RSS;
import log.ServerLogger;
import log.SysLog;
import util.Init;

public class Main {
    public static void main(String[] args) {
        Init.init(args[0]);
        SysLog.initializeLogger();
        ServerLogger.initializeServerLoggers();
        RSS.initializeRSS();
    }
}
