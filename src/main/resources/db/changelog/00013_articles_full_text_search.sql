--liquibase formatted sql
--changeset iivkin:4

CREATE INDEX articles_full_text_idx ON articles USING GIN (to_tsvector('russian', title || ' ' || "content"));