# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "REGISTRATION" ("ID" SERIAL NOT NULL PRIMARY KEY,"FNAME" VARCHAR(254) DEFAULT '' NOT NULL,"LNAME" VARCHAR(254) DEFAULT '' NOT NULL,"UNAME" VARCHAR(254) DEFAULT '' NOT NULL,"EMAIL" VARCHAR(254) DEFAULT '' NOT NULL,"PASSWORD" VARCHAR(254) DEFAULT '' NOT NULL);

# --- !Downs

drop table "REGISTRATION";

