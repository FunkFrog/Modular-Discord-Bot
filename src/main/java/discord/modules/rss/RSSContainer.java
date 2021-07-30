package discord.modules.rss;

import com.rometools.rome.feed.synd.SyndEntry;

public class RSSContainer {
    private final String category;
    private final String title;
    private final String url;
    private final String author;
    private final String content;

    public RSSContainer(String category, String title, String url, String author, String content) {
        this.category = category;
        this.title = title;
        this.url = url;
        this.author = author;
        this.content = content;
    }

    public RSSContainer(String category, SyndEntry entry) {
        this.category = category;
        this.title = entry.getTitle();
        this.url = entry.getUri();
        this.author = entry.getAuthor();
        this.content = entry.getContents().get(0).toString();
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }
}
