DROP TABLE IF EXISTS userData;

CREATE TABLE userData
(
    ID            BIGINT       NOT NULL PRIMARY KEY,
    Username      VARCHAR(225) NOT NULL,
    GlobalWarns   INT          NOT NULL,
    Blacklist     BOOL         NOT NULL,
    XP            BIGINT       NOT NULL,
    Level         INT          NOT NULL,
    Credits       BIGINT       NOT NULL,
    BotAuth       BOOL         NOT NULL
);