package discord.modules.rss;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final ScheduledExecutorService refreshScheduler = Executors.newScheduledThreadPool(1);

    protected void startScheduledRefresh(int minuteDelay) {
        refreshScheduler.scheduleAtFixedRate(new CheckFeedUpdates(), 0, 10, TimeUnit.MINUTES);
    }

}
