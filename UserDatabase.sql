DROP DATABASE IF EXISTS ProjectUserDatabase;

CREATE DATABASE ProjectUserDatabase;

USE ProjectUserDatabase;

CREATE TABLE ProjectUserTable
(
	userID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    username VARCHAR(50) NOT NULL,
    userPassword VARCHAR(50) NOT NULL,
    userLevel INT(2) NOT NULL, -- Capped at lvl 99
    userWins INT(4) NOT NULL, -- Capped at 9999
    userLosses INT(4) NOT NULL -- Capped at 9999
    
);
-- What about cards and decks?

-- Insert one user to the table to start off

INSERT INTO ProjectUserTable (username, userpassword, userLevel, userWins, userLosses)
	VALUES ('rayandrie', 'qwerty', 1, 0, 0);


