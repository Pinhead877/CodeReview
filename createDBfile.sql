##################################
## In the server users			##
## Create Username by the name:	##
## Username: admin				##
## Password: monkey36more		##
##################################

create schema if not exists codereviewdb;

use codereviewdb;

CREATE TABLE IF NOT EXISTS teams (
    t_id INT NOT NULL AUTO_INCREMENT,
    t_name NVARCHAR(50) NOT NULL,
    t_points INT DEFAULT 0,
    PRIMARY KEY (t_id)
);
CREATE TABLE IF NOT EXISTS players (
    p_id INT NOT NULL AUTO_INCREMENT,	#1
    p_name NVARCHAR(50) NOT NULL,		#2
    p_points INT DEFAULT 0,				#3
    team_id INT, 						#4
    is_reviewer bool default false,		#5
    mail NVARCHAR(50) NOT NULL,			#6
    u_password NVARCHAR(16) NOT NULL,	#7
    PRIMARY KEY (p_id)
);
CREATE TABLE IF NOT EXISTS segments (
    s_id INT NOT NULL AUTO_INCREMENT,	#1
    player_id INT NOT NULL,				#2
    code_text NVARCHAR(500) NOT NULL,	#3
    comment_text NVARCHAR(500),			#4
    review_id INT,						#5
    PRIMARY KEY (s_id)
);
CREATE TABLE IF NOT EXISTS reviews (
    r_id INT NOT NULL AUTO_INCREMENT,	#1
    segment_id INT NOT NULL,			#2
    score INT NOT NULL,					#3
    review_text NVARCHAR(500),			#4
    player_id INT NOT NULL,				#5
    is_read bool default false,			#6
    words_in_review FLOAT,				#7
    PRIMARY KEY (r_id)
);
CREATE TABLE IF NOT EXISTS segments_for_review(
	id int not null auto_increment,
	player_id int not null,
    segment_id int not null,
    primary key(id)
);

INSERT INTO `codereviewdb`.`teams`
(`t_name`)
VALUES
("Name");


INSERT INTO `codereviewdb`.`players`
(`p_name`,`team_id`,`is_reviewer`,`mail`,`u_password`)
VALUES
("test",1,true,"test",1234);

INSERT INTO `codereviewdb`.`players`
(`p_name`,`team_id`,`is_reviewer`,`mail`,`u_password`)
VALUES
("test2",1,true,"test2",1234);
