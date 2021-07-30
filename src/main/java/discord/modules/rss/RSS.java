package discord.modules.rss;

import java.time.LocalDateTime;
import java.util.HashMap;

public class RSS {
    protected static final HashMap<String, String> FEEDS = new HashMap<String, String>() {{
        put("GenTech", "http://www.theverge.com/rss/full.xml");
        put("GenTech", "https://www.technologyreview.com/topnews.rss");
        put("GenTech", "https://techcrunch.com/feed/");

        put("Software", "http://feeds.feedburner.com/ItsFoss");

        put("WorldNews", "http://atlasobscura.com/blog/rss.xml");
        put("WorldNews", "https://www.nationalgeographic.com/news/rss/index.rss");
        put("WorldNews", "https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml");
    }};

    protected static long lastCheckTimeSeconds = 0;

    public static void initializeRSS() {
        lastCheckTimeSeconds = LocalDateTime.now().getSecond();
        Scheduler.startScheduledRefresh(10);
    }
}
