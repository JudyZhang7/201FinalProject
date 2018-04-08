DROP DATABASE IF EXISTS ProjectUserDatabase;

CREATE DATABASE ProjectUserDatabase;

USE ProjectUserDatabase;

CREATE TABLE ProjectUserTable
(
    username VARCHAR(50) NOT NULL UNIQUE,
    userPassword VARCHAR(50) NOT NULL,
    userLevel INT(2) NOT NULL, -- Capped at lvl 99
    userWins INT(4) NOT NULL, -- Capped at 9999
    userLosses INT(4) NOT NULL -- Capped at 9999

); 

CREATE TABLE PlayerObject
(
    username VARCHAR(50) NOT NULL UNIQUE,
	serialized_id_player int(11) NOT NULL auto_increment,
	serialized_object_player blob NOT NULL, -- null lets you leave off the name
    PRIMARY KEY (serialized_id_player),
    
    FOREIGN KEY fk1(username) REFERENCES ProjectUserTable(username)
);

CREATE TABLE DeckObject
(
    username VARCHAR(50) NOT NULL UNIQUE,
	serialized_id_deck int(11) NOT NULL auto_increment,
	serialized_object_deck blob NOT NULL, -- null lets you leave off the name
    PRIMARY KEY (serialized_id_deck),
    
    FOREIGN KEY fk1(username) REFERENCES ProjectUserTable(username)
);
-- Insert one user to the table to start off

INSERT INTO ProjectUserTable (username, userpassword, userLevel, userWins, userLosses)
	VALUES ('rayandrie', 'qwerty', 1, 0, 0);


