# --- !Ups

CREATE TABLE app_user (
  id            INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  username      VARCHAR(100)     NOT NULL,
  password_salt VARCHAR(100)     NOT NULL,
  password_hash VARCHAR(100)     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_app_users_username(username)
);


CREATE TABLE item (
  id       INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  name     VARCHAR(100)     NOT NULL,
  username VARCHAR(100)     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_item_name_per_user (name, username),
  INDEX fk_item_owner (username),
  CONSTRAINT fk_item_owner FOREIGN KEY (username) REFERENCES app_user (username)
);


CREATE TABLE tag (
  id       INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  name     VARCHAR(100)     NOT NULL,
  username VARCHAR(100)     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_tag_name_per_user (name, username),
  INDEX fk_tag_owner (username),
  CONSTRAINT fk_tag_owner FOREIGN KEY (username) REFERENCES app_user (username)
);


/* Association Tables */

CREATE TABLE item_tag (
  id       INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  item_id  INT(11) UNSIGNED NOT NULL,
  tag_id   INT(11) UNSIGNED NOT NULL,
  username VARCHAR(100)     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_item_tag_per_user (item_id, tag_id, username),
  INDEX fk_item_tag_item (item_id),
  CONSTRAINT fk_item_tag_item FOREIGN KEY (item_id) REFERENCES item (id),
  INDEX fk_item_tag_tag (tag_id),
  CONSTRAINT fk_item_tag_tag FOREIGN KEY (tag_id) REFERENCES tag (id),
  INDEX fk_item_tag_owner (username),
  CONSTRAINT fk_item_tag_owner FOREIGN KEY (username) REFERENCES app_user (username)
);