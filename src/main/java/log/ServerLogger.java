package log;

import util.Init;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerLogger {
    private static final DateTimeFormatter fileDtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void initializeServerLoggers() {
        Init.api.getServers().forEach(server -> {
            ServerLogger.openLog(server.getId());
            SysLog.out("Started log for server " + server.getId());
        });
    }

    public static void openLog(long serverID) {
        File log = new File("logs/serverlogs/" + SysLog.bootTime + "-" + serverID + ".txt");
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(log, true));
            writer.println("Opening log at " + fileDtf.format(LocalDateTime.now()));
            writer.println("-- SERVER INFO");
            writer.println("SERVER ID: " + serverID);
            //TODO uncomment this when the discord side is working
            //writer.println("SERVER NAME: " + Init.api.getServerById(serverID).get().getName());
            writer.println("-- LOG");
            writer.close();
        } catch (IOException e) {
            SysLog.err("Couldn't create log file for " + serverID);
            e.printStackTrace();
        }
    }

    public static void out(String message, long serverID) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("logs/serverlogs/" + SysLog.bootTime + "-" + serverID + ".txt", true));
            String formattedMessage = "[" + dtf.format(LocalDateTime.now()) + "] [SERVER] [INFO] " + message;
            System.out.println(formattedMessage);
            writer.println(formattedMessage);
            writer.flush();
        } catch (Exception e) {
            SysLog.err("Could not log message for server " + serverID + ": " + message);
        }
    }
}
