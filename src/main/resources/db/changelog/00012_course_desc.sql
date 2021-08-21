--liquibase formatted sql
--changeset iivkin:3

ALTER TABLE courses ADD COLUMN description text NULL;