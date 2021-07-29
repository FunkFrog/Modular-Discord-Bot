DROP TABLE IF EXISTS enabledRSS;

CREATE TABLE enabledRSS
(
    ServerID    BIGINT        NOT NULL,
    RSSFeed     VARCHAR(15)   NOT NULL
);