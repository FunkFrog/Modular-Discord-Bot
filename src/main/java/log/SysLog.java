package log;

import util.Init;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SysLog {
    private static final DateTimeFormatter fileDtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static PrintWriter writer;
    public static String bootTime;

    public static void initializeLogger() {
        checkDir();
        bootTime = fileDtf.format(LocalDateTime.now());
        openLog("logs/" + bootTime + ".log");

    }

    private static void checkDir() {
        File logsFolder = new File("logs/");
        if (!logsFolder.exists()) {
            System.out.println("Main logs folder not found, creating...");
            logsFolder.mkdir();
        }
        File serverLogsFolder = new File("logs/serverlogs/");
        if (!serverLogsFolder.exists()) {
            System.out.println("Server logs folder not found, creating...");
            serverLogsFolder.mkdir();
        }
    }

    public static void err(String message) {
        String formattedMessage = "[" + dtf.format(LocalDateTime.now()) + "] [ERROR] " + message;
        System.out.println(formattedMessage);
        //Init.botLog.sendMessage("**" + formattedMessage + "**");
        writer.println(formattedMessage);
        writer.flush();
    }

    public static void wrn(String message) {
        String formattedMessage = "[" + dtf.format(LocalDateTime.now()) + "] [WARN] " + message;
        System.out.println(formattedMessage);
        //Init.botLog.sendMessage("**" + formattedMessage + "**");
        writer.println(formattedMessage);
        writer.flush();
    }

    public static void out(String message) {
        String formattedMessage = "[" + dtf.format(LocalDateTime.now()) + "] [INFO] " + message;
        System.out.println(formattedMessage);
        //Init.botLog.sendMessage(formattedMessage);
        writer.println(formattedMessage);
        writer.flush();
    }

    private static void openLog(String path) {
        File log = new File(path);
        try {
            writer = new PrintWriter(log);
            out("Opening log at " + dtf.format(LocalDateTime.now()) + ".");
            out("-------- ENVIRONMENT --------");
            out("BOT VERSION: " + Init.version);
            out("USERNAME: " + System.getProperty("user.name"));
            out("TIMEZONE: " + System.getProperty("user.timezone"));
            out("SYSTEM OS: " + System.getProperty("os.name"));
            out("OS ARCH TYPE: " + "x" + System.getProperty("sun.arch.data.model"));
            out("JAVA VERSION: " + System.getProperty("java.version"));
            out("JAVA HOME: " + System.getProperty("java.home"));
            out("-------- LOG --------");
        } catch (IOException e) {
            SysLog.err(e.getMessage());
        }
    }

    public static void closeLog() {
        writer.println("-------- END LOG --------");
        writer.println("Closing log at " + dtf.format(LocalDateTime.now()) + ".");
        writer.close();
    }
}