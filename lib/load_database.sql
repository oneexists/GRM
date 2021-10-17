create database grmdb;

use grmdb;

CREATE TABLE User (
  username varchar(25) NOT NULL PRIMARY KEY,
  user_first_name varchar(30),
  user_last_name varchar(30),
  user_password char(255),
  user_date_of_birth date,
  gender char(20)
);

CREATE TABLE Game_Tag (
  game_tag_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  game_tag_name char(255)
);

CREATE TABLE Wishlist (
  username varchar(25) FOREIGN KEY,
  game_tag_id varchar(25) FOREIGN KEY
);

CREATE TABLE Hatelist (
  username varchar(25) FOREIGN KEY,
  game_tag_id varchar(25) FOREIGN KEY
);

CREATE TABLE Question (
  question_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  question_text char(255)
);

CREATE TABLE Options (
  option_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  option_text char(255)
);

CREATE TABLE Select_Options (
  question_id int FOREIGN KEY,
  option_id int FOREIGN KEY
);

/*find user*/
/* SELECT username FROM User WHERE username = user AND password = pass; */
