DROP DATABASE airline;

CREATE DATABASE IF NOT exists airline /* !40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE airline;

CREATE TABLE if not exists `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE if not exists `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE if not exists `destination` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `place` varchar(255) NOT NULL, 
  `domestic` int(11) DEFAULT NULL,
  `international` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `place` (`place`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE if not exists `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `uname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  
  PRIMARY KEY (`id`),
  UNIQUE KEY `uname` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE if not exists `passenger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `class` varchar(255) DEFAULT NULL,
  `prime` int(11) DEFAULT NULL,
  `uname` varchar(255) DEFAULT NULL,
  `random` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `uname` (`uname`),
  CONSTRAINT `fk_passenger` FOREIGN KEY (`uname`) 
  REFERENCES `user` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE if not exists `route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `distance` float DEFAULT NULL,
  `from1` varchar(255) DEFAULT NULL,
  `to1` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `from1` (`from1`),
  KEY `to1` (`to1`),
  CONSTRAINT `fk_route_1` FOREIGN KEY (`from1`) REFERENCES `destination` (`place`),
  CONSTRAINT `fk_route_2` FOREIGN KEY (`to1`) REFERENCES `destination` (`place`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE if not exists `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from2` varchar(255) DEFAULT NULL,
  `to2` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `company` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `airplane_id` int(11) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `seats` int(11) DEFAULT NULL,
  `uname` varchar(255) NOT NULL,
  `random` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `from2` (`from2`),
  KEY `to2` (`to2`),
  KEY `airplane_id` (`airplane_id`),
  CONSTRAINT `fk_ticket_1` FOREIGN KEY (`from2`) REFERENCES `destination` (`place`),
  CONSTRAINT `fk_ticket_2` FOREIGN KEY (`to2`) REFERENCES `destination` (`place`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE if not exists `flight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` time DEFAULT NULL,
  `dest_time` time DEFAULT NULL,
  `seat_eco` int(11) DEFAULT NULL,
  `seat_bus` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `domestic` int(11) DEFAULT NULL,
  `mon` int(11) DEFAULT NULL,
  `tue` int(11) DEFAULT NULL,
  `wed` int(11) DEFAULT NULL,
  `thu` int(11) DEFAULT NULL,
  `fri` int(11) DEFAULT NULL,
  `sat` int(11) DEFAULT NULL,
  `sun` int(11) DEFAULT NULL,
  `price2` float DEFAULT NULL,
  `mon_seats` int(11) DEFAULT NULL,
  `tue_seats` int(11) DEFAULT NULL,
  `wed_seats` int(11) DEFAULT NULL,
  `thu_seats` int(11) DEFAULT NULL,
  `fri_seats` int(11) DEFAULT NULL,
  `sat_seats` int(11) DEFAULT NULL,
  `sun_seats` int(11) DEFAULT NULL,
  `mon_seats_bus` int(11) DEFAULT NULL,
  `tue_seats_bus` int(11) DEFAULT NULL,
  `wed_seats_bus` int(11) DEFAULT NULL,
  `thu_seats_bus` int(11) DEFAULT NULL,
  `fri_seats_bus` int(11) DEFAULT NULL,
  `sat_seats_bus` int(11) DEFAULT NULL,
  `sun_seats_bus` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `route_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  KEY `route_id` (`route_id`),
  CONSTRAINT `fk_airplane_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `fk_airplane_2` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





insert into admin(uname,password) values('admin','123');
insert into user(id,name,email,uname,password) values('12345','rania','raniah@bu.edu','Gabe','123');


insert into company(id,company) values('12345','Alitalia');
insert into company(id,company) values('12346','Delta');
insert into company(id,company) values('12347','Egyptair');
insert into company(id,company) values('12348','Iberia');
insert into company(id,company) values('12349','SouthWest');
insert into company(id,company) values('12350','United');
insert into company(id,company) values('12351','AmericanAirlines');



insert into destination(id,place,domestic,international) values('12345','Rome','0','1');
insert into destination(id,place,domestic,international) values('12346','Boston','1','0');
insert into destination(id,place,domestic,international) values('12347','Cairo','0','1');
insert into destination(id,place,domestic,international) values('12348','Bogota','0','1');
insert into destination(id,place,domestic,international) values('12349','Chicago','0','1');
insert into destination(id,place,domestic,international) values('12350','New-York','0','1');
insert into destination(id,place,domestic,international) values('12351','Philadelphia','0','1');



insert into route(id,distance,from1,to1) values('12345','111.00','Boston','Rome');
insert into route(id,distance,from1,to1) values('12346','111.00','Boston','Philadelphia');
insert into route(id,distance,from1,to1) values('12347','111.00','Boston','Cairo');
insert into route(id,distance,from1,to1) values('12348','111.00','Boston','Bogota');
insert into route(id,distance,from1,to1) values('12349','111.00','Boston','New-York');
insert into route(id,distance,from1,to1) values('12350','111.00','Boston','Chicago');



