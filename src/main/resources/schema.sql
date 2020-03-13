CREATE TABLE IF NOT EXISTS USERS 
(
  ID int NOT NULL AUTO_INCREMENT,
  USER_ID varchar(45) NOT NULL,
  USER_PW varchar(100) NOT NULL,
  USER_NAME varchar(45) NOT NULL,
  USER_EMAIL varchar(45) NOT NULL,
  ENABLED tinyint NOT NULL DEFAULT '1',
  REG_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  MOD_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (ID),
  UNIQUE KEY USERID_UNIQUE (USER_ID),
  KEY FK_USERID (USER_ID),
  KEY FK_POST_USERID (USER_ID),
  KEY FK_APP_USERID (USER_ID),
  KEY FK_APP_USERNAME (USER_NAME)
);

CREATE TABLE IF NOT EXISTS AUTHORITIES 
(
  USER_ID varchar(45) NOT NULL,
  AUTHORITY varchar(45) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (USER_ID)
);

CREATE TABLE IF NOT EXISTS STUDY_GROUPS 
(
  group_id int NOT NULL AUTO_INCREMENT,
  group_category varchar(45) NOT NULL,
  group_master_id varchar(45) NOT NULL,
  group_name varchar(45) NOT NULL,
  group_detail longtext NOT NULL,
  group_member_limit int NOT NULL,
  group_period varchar(45) DEFAULT NULL,
  group_area varchar(45) NOT NULL,
  group_status varchar(45) NOT NULL DEFAULT '모집중',
  group_start_age int DEFAULT NULL,
  group_end_age int DEFAULT NULL,
  group_view_cnt int NOT NULL DEFAULT '0', 
  reg_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  mod_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  group_comment_cnt int NOT NULL DEFAULT '0',
  PRIMARY KEY (group_id),
  UNIQUE KEY group_id_UNIQUE (group_id),
  KEY FK_GROUPID (GROUP_ID),
  KEY FK_COMMENT_GROUPID (GROUP_ID),
  KEY FK_BOARD_GROUPID (GROUP_ID),
  KEY FK_SCHEDULE_GROUPID (GROUP_ID),
  KEY FK_ATTENDANCE_GROUPID (GROUP_ID)
);

CREATE TABLE IF NOT EXISTS group_members
(
  group_id int NOT NULL AUTO_INCREMENT,
  user_id varchar(45) NOT NULL,
  group_authority varchar(45) NOT NULL DEFAULT 'USER',
  demerit_cnt int unsigned NOT NULL DEFAULT '0',
  reg_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  mod_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY GROUP_MEMBER_UNIQUE (GROUP_ID, USER_ID),
  KEY FK_ATTENDANCE_USERID (USER_ID)
);

CREATE TABLE IF NOT EXISTS GROUP_APPLICATIONS 
(
  APPLICATION_ID int NOT NULL AUTO_INCREMENT,
  GROUP_ID int NOT NULL,
  USER_ID varchar(45) NOT NULL,
  MSG longtext DEFAULT NULL,
  REG_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (APPLICATION_ID)
);

CREATE TABLE IF NOT EXISTS OAUTH_USERS 
(
  USER_ID int NOT NULL,
  USER_NAME varchar(45) NOT NULL,
  USER_EMAIL varchar(45) DEFAULT NULL,
  ENABLED tinyint NOT NULL DEFAULT '1',
  ACCESS_TOKEN varchar(100) NOT NULL,
  REFRESH_TOKEN varchar(100) NOT NULL,
  REG_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  MOD_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (USER_ID),
  UNIQUE KEY USER_ID_UNIQUE (USER_ID),
  KEY FK_OAUTH_USERID (USER_ID)
);

CREATE TABLE IF NOT EXISTS OAUTH_AUTHORITIES 
(
  USER_ID int NOT NULL,
  AUTHORITY varchar(45) NOT NULL DEFAULT 'OAUTH_USER',
  REG_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  MOD_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (USER_ID)
);

CREATE TABLE IF NOT EXISTS group_comment 
(
  comment_id int NOT NULL AUTO_INCREMENT,
  group_id int NOT NULL,
  user_id varchar(45) NOT NULL,
  comment_msg longtext NOT NULL,
  reg_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  mod_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `comment_id_UNIQUE` (`comment_id`)
);

CREATE TABLE IF NOT EXISTS GROUP_SCHEDULES 
(
  SCHEDULE_ID INT NOT NULL AUTO_INCREMENT,
  GROUP_ID INT NOT NULL,
  PLACE_LATITUDE double NOT NULL,
  PLACE_LONGITUDE double NOT NULL,
  PLACE_ADDRESS varchar(80) DEFAULT NULL,
  BUILDING_NAME varchar(45) DEFAULT NULL,
  BEGIN_TIME DATETIME NOT NULL,
  END_TIME DATETIME NOT NULL,
  REG_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  MOD_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (SCHEDULE_ID),
  KEY FK_SCHEDULEID (SCHEDULE_ID)
);

CREATE TABLE IF NOT EXISTS GROUP_ATTENDANCES 
(
  GROUP_ID INT NOT NULL,
  SCHEDULE_ID INT NOT NULL,
  USER_ID VARCHAR(45) NOT NULL,
  ATTENDANCE_STATUS VARCHAR(45) NOT NULL DEFAULT '결석',
  REG_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  MOD_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY UK_ATTENDANCE_INFO (GROUP_ID, SCHEDULE_ID, USER_ID)
);

ALTER TABLE AUTHORITIES ADD CONSTRAINT FK_USERID FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE GROUP_MEMBERS ADD CONSTRAINT FK_GROUPID FOREIGN KEY (GROUP_ID) REFERENCES STUDY_GROUPS (GROUP_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE GROUP_MEMBERS ADD CONSTRAINT FK_GROUP_USERID FOREIGN KEY(USER_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE GROUP_APPLICATIONS ADD CONSTRAINT FK_APP_GROUPID FOREIGN KEY (GROUP_ID) REFERENCES STUDY_GROUPS (GROUP_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE GROUP_APPLICATIONS ADD CONSTRAINT FK_APP_USERID FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE OAUTH_AUTHORITIES ADD CONSTRAINT FK_OAUTH_USERID FOREIGN KEY (USER_ID) REFERENCES OAUTH_USERS (USER_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE GROUP_COMMENT ADD CONSTRAINT FK_COMMENT_GROUPID FOREIGN KEY (GROUP_ID) REFERENCES STUDY_GROUPS (GROUP_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE GROUP_SCHEDULES ADD CONSTRAINT FK_SCHEDULE_GROUPID FOREIGN KEY (GROUP_ID) REFERENCES STUDY_GROUPS (GROUP_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE GROUP_ATTENDANCES ADD CONSTRAINT FK_ATTENDANCE_GROUPID FOREIGN KEY (GROUP_ID) REFERENCES STUDY_GROUPS (GROUP_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE GROUP_ATTENDANCES ADD CONSTRAINT FK_ATTENDANCE_USERID FOREIGN KEY (USER_ID) REFERENCES GROUP_MEMBERS (USER_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE GROUP_ATTENDANCES ADD CONSTRAINT FK_SCHEDULEID FOREIGN KEY (SCHEDULE_ID) REFERENCES GROUP_SCHEDULES (SCHEDULE_ID) ON DELETE CASCADE ON UPDATE CASCADE;