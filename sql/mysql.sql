BEGIN;
CREATE DATABASE java_finalproject_chatapplication;

BEGIN;
USE java_finalproject_chatapplication;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	UserID varchar(36) NOT NULL,
    UserName varchar(50) COLLATE utf8_general_ci NOT NULL UNIQUE,
    FullName nvarchar(200) COLLATE utf8_general_ci,
    Address nvarchar(200) COLLATE utf8_general_ci,
    DOB date,
    Sex varchar(6) COLLATE utf8_general_ci CHECK (Sex IN ('Male','Female')),
    Email varchar(100) COLLATE utf8_general_ci NOT NULL UNIQUE,
    Pass binary(60) NOT NULL,
    CreateTime datetime,
    LockAccount bool default(false),
    Avatar varchar(100),
    PRIMARY KEY(UserID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

BEGIN;
DROP TABLE IF EXISTS loginhistory;
CREATE TABLE loginhistory (
	UserID varchar(36) NOT NULL,
    LoginTime datetime,
    FOREIGN KEY (UserID) REFERENCES users(UserID),
    PRIMARY KEY(UserID,LoginTime)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

BEGIN;
DROP TABLE IF EXISTS friendlist;
CREATE TABLE friendlist (
	UserID varchar(36) NOT NULL,
    FriendID varchar(36) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES users(UserID),
    FOREIGN KEY (FriendID) REFERENCES users(UserID),
    PRIMARY KEY(UserID,FriendID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

BEGIN;
DROP TABLE IF EXISTS activestatus;
CREATE TABLE activestatus (
	UserID varchar(36) NOT NULL,
    OnlineStatus bool default(false),
    OfflineTime datetime,
    FOREIGN KEY (UserID) REFERENCES users(UserID),
    PRIMARY KEY(UserID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

BEGIN;
DROP TABLE IF EXISTS inbox;
CREATE TABLE inbox (
	InboxID varchar(36) NOT NULL,
    TypeInbox char(10) default('individual') CHECK (TypeInbox IN ('individual','group')),
    LastMessage text COLLATE utf8_general_ci,
    LastSentUserID varchar(36),
    FOREIGN KEY (LastSentUserID) REFERENCES users(UserID),
    PRIMARY KEY(InboxID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

BEGIN;
DROP TABLE IF EXISTS inboxparticipants;
CREATE TABLE inboxparticipants (
	InboxID varchar(36) NOT NULL,
    UserID varchar(36) NOT NULL,
    FOREIGN KEY (InboxID) REFERENCES inbox(InboxID),
    FOREIGN KEY (UserID) REFERENCES users(UserID),
    PRIMARY KEY(InboxID,UserID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

BEGIN;
DROP TABLE IF EXISTS messages;
CREATE TABLE messages (
	MessID varchar(36) NOT NULL,
    InboxID varchar(36) NOT NULL,
    UserID varchar(36) NOT NULL,
    Message text COLLATE utf8_general_ci,
    TypeMess varchar(10) default('text') CHECK (TypeMess IN ('text','image','file','video','sound','icon')),
    CreateTime datetime,
    FOREIGN KEY (InboxID) REFERENCES inbox(InboxID),
    FOREIGN KEY (UserID) REFERENCES users(UserID),
    PRIMARY KEY(MessID,InboxID,UserID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

BEGIN;
DROP TABLE IF EXISTS messageaccess;
CREATE TABLE messageaccess (
	MessID varchar(36) NOT NULL,
    InboxID varchar(36) NOT NULL,
    SentUserID varchar(36) NOT NULL,
    UserID varchar(36) NOT NULL,
    FOREIGN KEY (MessID,InboxID,SentUserID) REFERENCES messages(MessID,InboxID,UserID),
    FOREIGN KEY (UserID) REFERENCES users(UserID),
    PRIMARY KEY(MessID,InboxID,SentUserID,UserID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

BEGIN;
DROP TABLE IF EXISTS sendrequestfriendlist;
CREATE TABLE sendrequestfriendlistfriendlist (
	UserID varchar(36) NOT NULL,
    AddFriendID varchar(36) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES users(UserID),
    FOREIGN KEY (AddFriendID) REFERENCES users(UserID),
    PRIMARY KEY(UserID,AddFriendID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;







