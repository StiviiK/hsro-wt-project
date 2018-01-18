# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table actor_user (
  id                            bigint auto_increment not null,
  constraint pk_actor_user primary key (id)
);

create table forum (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_forum primary key (id)
);

create table forum_post (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  text                          varchar(255),
  user_id                       bigint,
  forum_id                      bigint,
  constraint pk_forum_post primary key (id)
);

create table message (
  id                            bigint auto_increment not null,
  text                          varchar(255),
  constraint pk_message primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  username                      varchar(255),
  password                      varchar(255),
  email                         varchar(255),
  current_token                 varchar(255),
  constraint pk_user primary key (id)
);

alter table forum_post add constraint fk_forum_post_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_forum_post_user_id on forum_post (user_id);

alter table forum_post add constraint fk_forum_post_forum_id foreign key (forum_id) references forum (id) on delete restrict on update restrict;
create index ix_forum_post_forum_id on forum_post (forum_id);


# --- !Downs

alter table forum_post drop foreign key fk_forum_post_user_id;
drop index ix_forum_post_user_id on forum_post;

alter table forum_post drop foreign key fk_forum_post_forum_id;
drop index ix_forum_post_forum_id on forum_post;

drop table if exists actor_user;

drop table if exists forum;

drop table if exists forum_post;

drop table if exists message;

drop table if exists user;

