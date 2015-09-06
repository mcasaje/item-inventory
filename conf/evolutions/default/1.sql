# --- !Ups

CREATE TABLE app_user (
  id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL,
  password_salt VARCHAR(100) NOT NULL,
  password_hash VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_app_users_username(username)
);
