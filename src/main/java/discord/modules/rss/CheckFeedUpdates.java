package discord.modules.rss;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;

public class CheckFeedUpdates implements Runnable{
    public RSS run() {
        RSS.FEEDS.forEach((title, url) -> {
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
            System.out.println(feed.getTitle());
        });
    }
}
