drop database if exists steamspydb;
create database steamspydb;

use steamspydb;

CREATE TABLE Library (
	appid INT NOT NULL PRIMARY KEY,
	action VARCHAR(255),
	adventure VARCHAR(255),
	base_building VARCHAR(255),
	battle_royale VARCHAR(255),
	card_game VARCHAR(255),
	cartoon VARCHAR(255),
	casual VARCHAR(255),
	co_op VARCHAR(255),
	competitive VARCHAR(255),
	crafting VARCHAR(255),
	exploration VARCHAR(255),
	fps VARCHAR(255),
	fantasy VARCHAR(255),
	fighting VARCHAR(255),
	moba VARCHAR(255),
	massively_multiplayer VARCHAR(255),
	open_world VARCHAR(255),
	puzzle VARCHAR(255),
	rpg VARCHAR(255),
	rts VARCHAR(255),
	racing VARCHAR(255),
	realistic VARCHAR(255),
	relaxing VARCHAR(255),
	simulation VARCHAR(255),
	singleplayer VARCHAR(255),
	sports VARCHAR(255),
	strategy VARCHAR(255),
	survival VARCHAR(255),
	tactical VARCHAR(255),
	turn_based VARCHAR(255)
);	

LOAD DATA INFILE '/var/lib/mysql-files/steamspy_tags.csv' 
INTO TABLE Library 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;