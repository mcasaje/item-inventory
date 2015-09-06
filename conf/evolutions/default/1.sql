# --- !Ups

CREATE TABLE app_user (
  id            INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  username      VARCHAR(100)     NOT NULL,
  password_salt VARCHAR(100)     NOT NULL,
  password_hash VARCHAR(100)     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_app_users_username(username)
);

-- Item --

CREATE TABLE item (
  id       INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  name     VARCHAR(100)     NOT NULL,
  username VARCHAR(100)     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_item_name_per_user (name, username),
  INDEX fk_item_owner (username),
  CONSTRAINT fk_item_owner FOREIGN KEY (username) REFERENCES app_user (username)
);

CREATE TABLE item_type (
  id       INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  name     VARCHAR(100)     NOT NULL,
  username VARCHAR(100)     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_item_type_name_per_user (name, username),
  INDEX fk_item_type_owner (username),
  CONSTRAINT fk_item_type_owner FOREIGN KEY (username) REFERENCES app_user (username)
);

-- Tag --

CREATE TABLE tag (
  id       INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  name     VARCHAR(100)     NOT NULL,
  username VARCHAR(100)     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_tag_name_per_user (name, username),
  INDEX fk_tag_owner (username),
  CONSTRAINT fk_tag_owner FOREIGN KEY (username) REFERENCES app_user (username)
);

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

-- Field --

CREATE TABLE field (
  id           INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  name         VARCHAR(100)     NOT NULL,
  item_type_id INT(11) UNSIGNED NOT NULL,
  username     VARCHAR(100)     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_field_name_per_user (name, username),
  INDEX fk_field_item_type (item_type_id),
  CONSTRAINT fk_field_item_type FOREIGN KEY (item_type_id) REFERENCES item_type (id),
  INDEX fk_field_owner (username),
  CONSTRAINT fk_field_owner FOREIGN KEY (username) REFERENCES app_user (username)
);

CREATE TABLE item_field (
  id        INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  item_id   INT(11) UNSIGNED NOT NULL,
  field_id  INT(11) UNSIGNED NOT NULL,
  username  VARCHAR(100)     NOT NULL,
  field_val VARCHAR(100) NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX uq_item_field_per_user (item_id, field_id, username),
  INDEX fk_item_field_item (item_id),
  CONSTRAINT fk_item_field_item FOREIGN KEY (item_id) REFERENCES item (id),
  INDEX fk_item_field_field (field_id),
  CONSTRAINT fk_item_field_field FOREIGN KEY (field_id) REFERENCES field (id),
  INDEX fk_item_field_owner (username),
  CONSTRAINT fk_item_field_owner FOREIGN KEY (username) REFERENCES app_user (username)
);