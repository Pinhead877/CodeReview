##################################
## In the server users			##
## Create Username by the name:	##
## Username: admin				##
## Password: monkey36more		##
##################################

CREATE schema if not exists codereviewdb;

USE codereviewdb;

-- CREATE TABLE IF NOT EXISTS teams (
--     t_id INT NOT NULL AUTO_INCREMENT,
--     t_name NVARCHAR(50) NOT NULL,
--     t_points INT DEFAULT 0,
--     create_date DATETIME NOT NULL DEFAULT NOW(),
--     PRIMARY KEY (t_id)
-- );
CREATE TABLE IF NOT EXISTS players (
    p_id INT NOT NULL AUTO_INCREMENT,					#1
    p_name NVARCHAR(50) NOT NULL,						#2
    p_points INT DEFAULT 0,								#3
 #   team_id INT,
    is_reviewer BOOL DEFAULT TRUE,						#4
    mail NVARCHAR(50) NOT NULL,							#5
    u_password NVARCHAR(16) NOT NULL,					#6
    times_login INT DEFAULT 0,							#7
    image NVARCHAR(25) NOT NULL DEFAULT "default",	#8
    create_date DATETIME NOT NULL DEFAULT NOW(),		#9
    PRIMARY KEY (p_id)
);
CREATE TABLE IF NOT EXISTS segments (
    s_id INT NOT NULL AUTO_INCREMENT,
    player_id INT NOT NULL,
    code_text NVARCHAR(500) NOT NULL,
    comment_text NVARCHAR(500),
 #   review_id INT,
    create_date DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY (s_id)
);
CREATE TABLE IF NOT EXISTS reviews (
    r_id INT NOT NULL AUTO_INCREMENT,				#1
    segment_id INT NOT NULL,						#2
    score INT NOT NULL,								#3
    review_text NVARCHAR(500),						#4
    player_id INT NOT NULL,							#5
    is_read BOOL DEFAULT FALSE,						#6
    words_in_review FLOAT,							#7
    create_date DATETIME NOT NULL DEFAULT NOW(),	#8
    PRIMARY KEY (r_id)
);
CREATE TABLE IF NOT EXISTS segments_for_review (
    id INT NOT NULL AUTO_INCREMENT,
    player_id INT NOT NULL,
    segment_id INT NOT NULL,
    PRIMARY KEY (id)
);

-- INSERT INTO `codereviewdb`.`teams`
-- (`t_name`)
-- VALUES
-- ("Name");


INSERT INTO `codereviewdb`.`players`
(`p_name`,`is_reviewer`,`mail`,`u_password`)
VALUES
("Alex",true,"alex",1234);

INSERT INTO `codereviewdb`.`players`
(`p_name`,`is_reviewer`,`mail`,`u_password`)
VALUES
("Alexander",true,"alex2",1234);

INSERT INTO `codereviewdb`.`players`
(`p_name`,`is_reviewer`,`mail`,`u_password`)
VALUES
("Alex1",true,"alex3",1234);

INSERT INTO `codereviewdb`.`players`
(`p_name`,`is_reviewer`,`mail`,`u_password`)
VALUES
("Alex2",true,"alex4",1234);

INSERT INTO `codereviewdb`.`players`
(`p_name`,`is_reviewer`,`mail`,`u_password`)
VALUES
("Alex3",true,"alex5",1234);

INSERT INTO `codereviewdb`.`players`
(`p_name`,`is_reviewer`,`mail`,`u_password`)
VALUES
("Alex4",true,"alex6",1234);

INSERT INTO `codereviewdb`.`players`
(`p_name`,`is_reviewer`,`mail`,`u_password`)
VALUES
("Alex5",true,"alex7",1234);