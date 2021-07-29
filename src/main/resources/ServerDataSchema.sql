DROP TABLE IF EXISTS serverData;

CREATE TABLE serverData
(
    ID                                    BIGINT  NOT NULL PRIMARY KEY,
    BlacklistedTotal                      BIGINT  NOT NULL,
    DevModuleEnabled                      BOOLEAN NOT NULL,
    EconModuleEnabled                     BOOLEAN NOT NULL,
    FunModuleEnabled                      BOOLEAN NOT NULL,
    GamesModuleEnabled                    BOOLEAN NOT NULL,
    LevelsModuleEnabled                   BOOLEAN NOT NULL,
    MusicModuleEnabled                    BOOLEAN NOT NULL,
    PunishmentModuleEnabled               BOOLEAN NOT NULL,
    RSSModuleEnabled                      BOOLEAN NOT NULL,
    ChannelManagerModuleEnabled           BOOLEAN NOT NULL,
    RoleManagerModuleEnabled              BOOLEAN NOT NULL,
    VoiceChannelManagerModuleEnabled      BOOLEAN NOT NULL,
    SocialModuleEnabled                   BOOLEAN NOT NULL
);