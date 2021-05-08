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

/* map */
CREATE SEQUENCE map_seq;
CREATE TABLE map
(
    id       bigint       not null primary key default nextval('map_seq'::regclass),
    name     varchar(255) NOT NULL UNIQUE,

    created  timestamp                         DEFAULT NOW(),
    modified timestamp
);

CREATE TRIGGER map_modified
    BEFORE UPDATE
    ON map
    FOR EACH ROW
EXECUTE PROCEDURE update_modified_column();

/* nation */
CREATE SEQUENCE nation_seq;
CREATE TABLE nation
(
    id       bigint       not null primary key default nextval('nation_seq'::regclass),
    name     varchar(255) NOT NULL UNIQUE,

    created  timestamp                         DEFAULT NOW(),
    modified timestamp
);

CREATE TRIGGER nation_modified
    BEFORE UPDATE
    ON nation
    FOR EACH ROW
EXECUTE PROCEDURE update_modified_column();

/* event */
CREATE SEQUENCE event_seq;
CREATE TABLE event
(
    id          bigint                      not null primary key default nextval('event_seq'::regclass),
    title       varchar(255)                NOT NULL,
    description text                        NOT NULL,
    timestamp   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    rounds      int                         NOT NULL,

    map_id      bigint                      NOT NULL,

    created     timestamp                                        DEFAULT NOW(),
    modified    timestamp,

    CONSTRAINT fk_event_map FOREIGN KEY (map_id)
        REFERENCES map (id) MATCH SIMPLE
);

CREATE TRIGGER event_modified
    BEFORE UPDATE
    ON event
    FOR EACH ROW
EXECUTE PROCEDURE update_modified_column();

/* team */
CREATE SEQUENCE team_seq;
CREATE TABLE team
(
    id          bigint                      not null primary key default nextval('team_seq'::regclass),

    clan_id     bigint                      NOT NULL,
    nation_id   bigint                      NOT NULL,

    created     timestamp                                        DEFAULT NOW(),
    modified    timestamp,

    CONSTRAINT fk_team_clan FOREIGN KEY (clan_id)
        REFERENCES clan (id) MATCH SIMPLE,
    CONSTRAINT fk_team_nation FOREIGN KEY (nation_id)
        REFERENCES nation (id) MATCH SIMPLE
);

CREATE TRIGGER team_modified
    BEFORE UPDATE
    ON team
    FOR EACH ROW
EXECUTE PROCEDURE update_modified_column();
