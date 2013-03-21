# Tasks schema

# --- !Ups

CREATE SEQUENCE kiosk_id_seq;
CREATE TABLE kiosk (
    id integer NOT NULL DEFAULT nextval('kiosk_id_seq'),
    store varchar(255),
    address varchar(255),
    city varchar(255),
    state varchar(255),
    zipCode varchar(255),
    inside boolean
);

# --- !Downs

DROP TABLE kiosk;
DROP SEQUENCE kiosk_id_seq;