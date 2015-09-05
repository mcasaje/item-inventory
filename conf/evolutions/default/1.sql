# --- !Ups

CREATE TABLE app_user (
  id int NOT NULL AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL,
  password_salt VARCHAR(100) NOT NULL,
  password_hash VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_app_users_username(username)
);


# --- !Downs

DROP TABLE app_user;