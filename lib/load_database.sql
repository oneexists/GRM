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

CREATE TABLE GameTag (
  tag_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tag_name varchar(255)
);

CREATE TABLE Question (
  question_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  question_prompt varchar(255)
);

CREATE TABLE Choice (
  choice_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  choice_text varchar(255)
);

CREATE TABLE SelectChoices (
  question_id int,
  choice_id int,
  FOREIGN KEY(question_id) REFERENCES Question(question_id),
  FOREIGN KEY(choice_id) REFERENCES Choice(choice_id)
);

CREATE TABLE ChoiceTags (
  choice_id int,
  tag_id int,
  FOREIGN KEY(choice_id) REFERENCES Choice(choice_id),
  FOREIGN KEY(tag_id) REFERENCES GameTag(tag_id)
);

/* add tags */
INSERT INTO GameTag(tag_name) VALUES
("survival"), 
("massively_multiplayer"), 
("puzzle"), 
("co_op"), 
("moba"), 
("racing"), 
("rts"), 
("exploration"), 
("base_building"), 
("turn_based"),
("competitive"), 
("rpg"), 
("realistic"), 
("card_game"), 
("strategy"), 
("fighting"), 
("cartoon"), 
("sports"), 
("singleplayer"), 
("crafting"), 
("fantasy"), 
("fps"), 
("open_world"), 
("casual"), 
("relazing"), 
("battle_royale"),  
("tactical"), 
("simulation"),
("adventure"), 
("action");

/* add default user */
INSERT INTO User VALUES ("user", "Gus", "Fring", "p@ssword", curdate(), "Male");
