# --- !Ups

CREATE TABLE raw_word_request (
  id           SERIAL                   NOT NULL PRIMARY KEY,
  word         TEXT                     NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE NOT NULL,
  user_id      BIGINT                   NOT NULL
);

# --- !Downs

DROP TABLE raw_word_request;
