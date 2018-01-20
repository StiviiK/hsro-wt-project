# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table actor_user (
  id                            bigint auto_increment not null,
  constraint pk_actor_user primary key (id)
);

create table answer (
  id                            bigint auto_increment not null,
  creator_id                    bigint,
  message                       varchar(255),
  post_id                       bigint,
  constraint pk_answer primary key (id)
);

create table forum (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  color                         varchar(255),
  constraint pk_forum primary key (id)
);

create table forum_post (
  id                            bigint auto_increment not null,
  topic                         varchar(255),
  question                      varchar(255),
  views                         integer not null,
  forum_id                      bigint,
  creator_id                    bigint,
  votes                         bigint,
  last_update                   datetime(6),
  constraint pk_forum_post primary key (id)
);

create table message (
  id                            bigint auto_increment not null,
  text                          varchar(255),
  constraint pk_message primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  email                         varchar(255),
  avatar                        varchar(255),
  constraint pk_user primary key (id)
);

alter table answer add constraint fk_answer_creator_id foreign key (creator_id) references user (id) on delete restrict on update restrict;
create index ix_answer_creator_id on answer (creator_id);

alter table answer add constraint fk_answer_post_id foreign key (post_id) references forum_post (id) on delete restrict on update restrict;
create index ix_answer_post_id on answer (post_id);

alter table forum_post add constraint fk_forum_post_forum_id foreign key (forum_id) references forum (id) on delete restrict on update restrict;
create index ix_forum_post_forum_id on forum_post (forum_id);

alter table forum_post add constraint fk_forum_post_creator_id foreign key (creator_id) references user (id) on delete restrict on update restrict;
create index ix_forum_post_creator_id on forum_post (creator_id);


# --- !Downs

alter table answer drop foreign key fk_answer_creator_id;
drop index ix_answer_creator_id on answer;

alter table answer drop foreign key fk_answer_post_id;
drop index ix_answer_post_id on answer;

alter table forum_post drop foreign key fk_forum_post_forum_id;
drop index ix_forum_post_forum_id on forum_post;

alter table forum_post drop foreign key fk_forum_post_creator_id;
drop index ix_forum_post_creator_id on forum_post;

drop table if exists actor_user;

drop table if exists answer;

drop table if exists forum;

drop table if exists forum_post;

drop table if exists message;

drop table if exists user;

