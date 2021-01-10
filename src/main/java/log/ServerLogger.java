package log;

import util.Init;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerLogger {
    private static final DateTimeFormatter fileDtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static void openLog(long serverID) {
        File log = new File("/logs/serverlogs/" + SysLog.bootTime + "-" + serverID);
        out("Opening log at " + dtf.format(LocalDateTime.now()) + ".", serverID);
        out("-------- SERVER INFO --------", serverID);
        out("SERVER ID: " + serverID, serverID);
        out("SERVER NAME: " + Init.api.getServerById(serverID).get().getName(), serverID);
        out("-------- LOG --------", serverID);
    }

    private static void writeFile(String message, long serverID, String prefix) {
        File log = new File("/logs/serverlogs/" + SysLog.bootTime + "-" + serverID);
        try {
            PrintWriter writer = new PrintWriter(log);
            String formattedMessage = "[" + dtf.format(LocalDateTime.now()) + "] " + prefix + " " + message;
            System.out.println(formattedMessage);
            Init.botLog.sendMessage("**" + formattedMessage + "**");
            writer.println(formattedMessage);
            writer.flush();
        } catch (FileNotFoundException e) {
            SysLog.err("Could not log error for server " + serverID + ": " + message);
        }
    }

    public static void err(String message, long serverID) {
        writeFile(message, serverID, "[ERROR]");
    }

    public static void out(String message, long serverID) {
        writeFile(message, serverID, "[INFO]");
    }
}
