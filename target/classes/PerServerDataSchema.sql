DROP TABLE IF EXISTS perServerData;

CREATE TABLE perServerData
(
    ServerID    BIGINT        NOT NULL,
    UserID      BOOLEAN       NOT NULL,
    Role        VARCHAR(2)    NOT NULL,
    Warnings    INT           NOT NULL
);