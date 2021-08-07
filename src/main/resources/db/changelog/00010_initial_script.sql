--liquibase formatted sql
--changeset iivkin:1

CREATE TABLE users
(
    id         bigserial    NOT NULL,
    username   varchar(255) NOT NULL,
    "password" varchar(255) NOT NULL,
    title      varchar(255) NOT NULL,
    email      varchar(255) NULL,
    oauth_id   varchar(255) NULL,
    "role"     int4         NOT NULL DEFAULT 1,
    enabled    bool         NOT NULL DEFAULT true,
    created_at timestamp    NOT NULL DEFAULT now(),
    updated_at timestamp    NOT NULL DEFAULT now(),
    CONSTRAINT users_pkey PRIMARY KEY (id)
);



create INDEX users_title_idx on users(title);



CREATE TABLE topics
(
    id    bigserial    NOT NULL,
    title varchar(255) NOT NULL,
    CONSTRAINT topics_pkey PRIMARY KEY (id)
);



CREATE TABLE articles
(
    id         bigserial    NOT NULL,
    title      varchar(255) NOT NULL,
    "content"  text         NOT NULL,
    author_id  int8         NOT NULL,
    created_at timestamp    NOT NULL DEFAULT now(),
    updated_at timestamp    NOT NULL DEFAULT now(),
    CONSTRAINT articles_pkey PRIMARY KEY (id),
    FOREIGN KEY(author_id) REFERENCES users(id)
);



CREATE TABLE articles_topics
(
    id_article int8 NOT NULL,
    id_topic   int8 NOT NULL,
    CONSTRAINT articles_topics_pkey PRIMARY KEY (id_topic, id_article)
);



INSERT INTO users (username, "password", title, email, oauth_id, "role", enabled, created_at, updated_at)
VALUES ('admin@example.com', '$2a$10$g/Jjj1Wvplf9dEYcHTU.g.0T8SBPw2ibT6G0qLafFT8QL84D1D4K2', 'Administrator', NULL,
        NULL, 1, true, '2021-06-27 15:31:29.488156', '2021-06-27 15:31:29.489371');