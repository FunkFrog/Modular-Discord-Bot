package discord;

import org.javacord.api.entity.activity.ActivityType;
import util.Init;

import java.time.Duration;
import java.time.Instant;

public class StatusManager {

//    public static void startTask() {
//        task = scheduler.scheduleAtFixedRate(StatusManager::updateState, 0, 15, TimeUnit.SECONDS);
//    }
//
//    public static void stopTask() {
//        task.cancel(true);
//    }

    public static void APIUpdate() {
        //poll api with Warframe.CetusStatus, Warframe.VallisStatus, and Warframe.CambionDriftStatus
    }

    public static String formatTime(Duration time) {
        long seconds = time.getSeconds();
        if (seconds >= 3600) {
            long hours = seconds / 3600;
            long minutes = (seconds % 3600) / 60;
            return String.format("%d hrs %d mins", hours, minutes);
        } else if (seconds >= 60) {
            long minutes = seconds / 60;
            return String.format("%d mins", minutes);
        } else {
            return String.format("%d sec", seconds);
        }
    }

    public static void checkTimestamp(Instant timestamp) {
        Instant currentTime = Instant.now();
        if (currentTime.isAfter(timestamp)) {
            APIUpdate();
        } else {
            Duration timeRemaining = Duration.between(currentTime, timestamp);
            System.out.println("Time remaining: " + timeRemaining);
        }
    }

    public static void updateState() {
        int cetusTime = 10;
        boolean isCetusDay = true; // true for sun, false for moon

        int cambionTime = 20;
        String cambionState = "Fass"; // or "Vome"

        int vallisTime = 30;
        boolean isVallisCold = true; // true for snow, false for flame

        // Generate the status string
        String status = "Cetus: " + cetusTime + (isCetusDay ? " ☀️" : " 🌕") +
                " / Cambion: " + cambionTime + " " + cambionState + " 🐍" +
                " / Vallis: " + vallisTime + (isVallisCold ? " ❄️" : " 🔥");

        // Set the status
        Init.api.updateActivity(ActivityType.WATCHING, status);
    }
}
