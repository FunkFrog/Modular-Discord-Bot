package discord.modules.rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;

public class CheckFeedUpdates implements Runnable {
    public void run() {
        RSS.FEEDS.forEach((category, url) -> {
            try {
                SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
                for (SyndEntry entry : feed.getEntries()) {
                    if (entry.getPublishedDate().getTime() > RSS.lastCheckTimeSeconds) {
                        EmbedMessenger.serverCycle(new RSSContainer(category, entry));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Could not resolve URL " + url);
            }
        });
    }
}
