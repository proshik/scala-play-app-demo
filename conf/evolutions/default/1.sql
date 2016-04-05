# --- !Ups

CREATE TABLE raw_word_request (
  id           SERIAL                   NOT NULL PRIMARY KEY,
  word         TEXT                     NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE NOT NULL,
  user_id      BIGINT                   NOT NULL
);

CREATE TABLE word (
  id SERIAL NOT NULL PRIMARY KEY,
  raw_word TEXT NOT NULL,
  translate JSON NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE NOT NULL
);

# --- !Downs

DROP TABLE raw_word_request;
DROP TABLE word;

