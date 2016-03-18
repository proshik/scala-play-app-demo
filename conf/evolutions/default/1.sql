# --- !Ups

CREATE TABLE word (
  id          SERIAL                   NOT NULL PRIMARY KEY,
  word        TEXT                     NOT NULL
--   ,created_date TIMESTAMP WITH TIME ZONE NOT NULL
);

# --- !Downs

DROP TABLE word;
