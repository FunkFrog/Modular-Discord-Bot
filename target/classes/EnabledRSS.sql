DROP TABLE IF EXISTS enabledRSS;

CREATE TABLE enabledRSS
(
    RSSFeed     VARCHAR(15)   NOT NULL,
    ServerID    BIGINT        NOT NULL,
    ChannelID   BIGINT        NOT NULL
);