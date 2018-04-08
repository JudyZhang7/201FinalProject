DROP DATABASE IF EXISTS ProjectUserDatabase;

CREATE DATABASE ProjectUserDatabase;

USE ProjectUserDatabase;

CREATE TABLE ProjectUserTable
(
    username VARCHAR(50) NOT NULL UNIQUE,
    userPassword VARCHAR(50) NOT NULL,
    userLevel INT(2) NOT NULL, -- Capped at lvl 99
    userWins INT(4) NOT NULL, -- Capped at 9999
    userLosses INT(4) NOT NULL, -- Capped at 9999
    
    serialized_id_player int(11) NOT NULL auto_increment,
	serialized_object_player blob NOT NULL, -- null lets you leave off the name
    PRIMARY KEY (serialized_id_player),
    
    serialized_id_decks int(11) NOT NULL auto_increment,
	serialized_object_decks blob NOT NULL, -- null lets you leave off the name
    PRIMARY KEY (serialized_id_decks)
); 

CREATE TABLE TEST
(
	serialized_id_player int(11) NOT NULL auto_increment,
	serialized_object_player blob NOT NULL, -- null lets you leave off the name
    PRIMARY KEY (serialized_id_player)
);

-- Insert one user to the table to start off

INSERT INTO ProjectUserTable (username, userpassword, userLevel, userWins, userLosses)
	VALUES ('rayandrie', 'qwerty', 1, 0, 0);


