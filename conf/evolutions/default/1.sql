# Tasks schema

# --- !Ups

CREATE SEQUENCE speaker_id_seq;
CREATE TABLE speaker (
    id integer NOT NULL DEFAULT nextval('speaker_id_seq'),
    name varchar(255),
    title varchar(255),
    bio varchar(4000),
    imageUrl varchar(255)
);

# --- !Downs

DROP TABLE speaker;
DROP SEQUENCE speaker_id_seq;