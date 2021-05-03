CREATE OR REPLACE FUNCTION update_modified_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.modified = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

/* player */
CREATE SEQUENCE player_seq;
CREATE TABLE player
(
    id         bigint       not null primary key default nextval('player_seq'::regclass),

    name       varchar(255) NOT NULL,
    email      varchar(255) NOT NULL UNIQUE,

    steam_id   varchar(255),
    discord_id varchar(255),

    created    timestamp                         DEFAULT NOW(),
    modified   timestamp
);

CREATE TRIGGER player_modified
    BEFORE UPDATE
    ON player
    FOR EACH ROW
EXECUTE PROCEDURE update_modified_column();


/* clan */
CREATE SEQUENCE clan_seq;
CREATE TABLE clan
(
    id         bigint       not null primary key default nextval('clan_seq'::regclass),

    name       varchar(255) NOT NULL UNIQUE,
    tag        varchar(255) NOT NULL,

    discord_id varchar(255),

    created    timestamp                         DEFAULT NOW(),
    modified   timestamp
);

CREATE TRIGGER clan_modified
    BEFORE UPDATE
    ON clan
    FOR EACH ROW
EXECUTE PROCEDURE update_modified_column();

/* clan member */
CREATE TABLE clan_member
(
    clan_id   bigint not null,
    player_id bigint not null,

    CONSTRAINT fk_clan FOREIGN KEY (clan_id)
        REFERENCES clan (id) MATCH SIMPLE,
    CONSTRAINT fk_player FOREIGN KEY (player_id)
        REFERENCES player (id) MATCH SIMPLE
);

/* event info */
CREATE SEQUENCE event_info_seq;
CREATE TABLE event_info
(
    id       bigint       not null primary key default nextval('event_info_seq'::regclass),
    name     varchar(255) NOT NULL,
    enabled  boolean      NOT NULL,

    created  TIMESTAMP WITHOUT TIME ZONE,
    modified TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT cache_settings_pk UNIQUE (id),
    CONSTRAINT cache_settings_name UNIQUE (name)
);
