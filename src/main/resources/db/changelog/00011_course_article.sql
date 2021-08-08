--liquibase formatted sql
--changeset iivkin:2

CREATE TABLE courses
(
    id    bigserial    NOT NULL,
    title varchar(255) NOT NULL,
    CONSTRAINT courses_pkey PRIMARY KEY (id)
);

CREATE TABLE course_article
(
    course_id  int8 NOT NULL,
    article_id int8 NOT NULL,
    "order"    int4 NOT NULL DEFAULT 0,
    CONSTRAINT course_article_pkey PRIMARY KEY (course_id, article_id),
    FOREIGN KEY (course_id) REFERENCES courses (id),
    FOREIGN KEY (article_id) REFERENCES articles (id)
);