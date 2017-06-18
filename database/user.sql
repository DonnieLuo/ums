CREATE database ums;
use ums;
DROP TABLE user if EXISTS ;
CREATE TABLE user(
  id bigint(11) PRIMARY KEY auto_increment,
  username varchar(50) NOT NULL ,
  firstName varchar(50) NOT NULL ,
  lastName varchar(50) NOT NULL ,
  password VARCHAR (50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;