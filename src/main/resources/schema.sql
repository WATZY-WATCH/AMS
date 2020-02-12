CREATE TABLE IF NOT EXISTS USERS (
  ID int NOT NULL AUTO_INCREMENT,
  USERID varchar(45) NOT NULL,
  USERPW varchar(100) NOT NULL,
  USERNAME varchar(45) NOT NULL,
  USEREMAIL varchar(45) NOT NULL,
  ENABLED tinyint NOT NULL DEFAULT '1',
  REGDATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  MODDATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
);

CREATE TABLE IF NOT EXISTS AUTHORITIES (
  USERID varchar(45) NOT NULL,
  AUTHORITY varchar(45) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`USERID`)
);

ALTER TABLE AUTHORITIES ADD CONSTRAINT FK_USERID FOREIGN KEY (USERID) REFERENCES USERS ON DELETE CASCADE ON UPDATE CASCADE;