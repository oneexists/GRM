drop database if exists grmdb;
create database grmdb;

use grmdb;

CREATE TABLE User (
  username varchar(25) NOT NULL PRIMARY KEY,
  user_first_name varchar(30),
  user_last_name varchar(30),
  user_password varchar(255),
  user_date_of_birth date,
  gender varchar(20)
);

CREATE TABLE Game_Tag (
  game_tag_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  game_tag_name varchar(255)
);

CREATE TABLE Wishlist (
  username varchar(25),
  game_tag_id int,
  FOREIGN KEY(username) REFERENCES User(username),
  FOREIGN KEY(game_tag_id) REFERENCES Game_Tag(game_tag_id)
);

CREATE TABLE Hatelist (
  username varchar(25),
  game_tag_id int,
  FOREIGN KEY(username) REFERENCES User(username),
  FOREIGN KEY(game_tag_id) REFERENCES Game_Tag(game_tag_id)
);

CREATE TABLE Question (
  question_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  question_text varchar(255)
);

CREATE TABLE Options (
  option_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  option_text varchar(255)
);

CREATE TABLE Select_Options (
  question_id int,
  option_id int,
  FOREIGN KEY(question_id) REFERENCES Question(question_id),
  FOREIGN KEY(option_id) REFERENCES Options(option_id)
);

CREATE TABLE Option_Tags (
  option_id int,
  game_tag_id int,
  FOREIGN KEY(option_id) REFERENCES Options(option_id),
  FOREIGN KEY(game_tag_id) REFERENCES Game_Tag(game_tag_id)
);

/* populate questions */
INSERT INTO Question (question_text) VALUES
  ("What is most important to you?"),
  ("Would you rather?"),
  ("I like"),
  ("I usually prefer"),
  ("I feel most safe and happy when");

/* populate options */
INSERT INTO Options (option_text) VALUES
  ("Being part of a team"),
  ("Spending time with friends online"),
  ("Doing something special"),
  ("Save the day"),
  ("Relax"),
  ("Tactically control things"),
  ("Fast frantic fun where my reflexes can shine"),
  ("Casual no pressure experiences"),
  ("Letting my creativity shine"),
  ("Bright, fun cartoon lands to explore"),
  ("Dark, smelly dungeons"),
  ("Realistic cities and locations"),
  ("I am playing a game by myself"),
  ("I am next to friends on a couch"),
  ("I am playing with people online");

/* populate tags */
INSERT INTO Game_Tag(game_tag_name) VALUES
  ("team"),
  ("online multiplayer"),
  ("story"),
  ("engaging"),
  ("hero"),
  ("relax"),
  ("tactical"),
  ("rts"),
  ("fast"),
  ("frantic"),
  ("reflex"),
  ("casual"),
  ("creative"),
  ("cartoon"),
  ("bright"),
  ("happy"),
  ("dark"),
  ("dungeon"),
  ("realistic"),
  ("solo"),
  ("single player"),
  ("local multiplayer"),
  ("online multiplayer");

/* associate options with questions */
INSERT INTO Select_Options VALUES
  (1, 1), (1, 2), (1, 3),
  (2, 4), (2, 5), (2, 6),
  (3, 7), (3, 8), (3, 9),
  (4, 10), (4, 11), (4, 12),
  (5, 13), (5, 14), (5, 15);

/* associate tags with options */
/* TODO add third question tags */
INSERT INTO Option_Tags VALUES
  (1, 1), (2, 2), (3, 3), (3, 4),
  (4, 5), (5, 6), (6, 7), (6, 8),
  (7, 9), (8, 10), (9, 11), (8, 12), (9, 13),
  (10, 14), (10, 15), (10, 16), (11, 17), (11, 18), (12, 19),
  (13, 20), (13, 21), (14, 22), (15, 23);

/* add default user */
INSERT INTO User VALUES ("user", "Gus", "Fring", "p@ssword", curdate(), "Male");
