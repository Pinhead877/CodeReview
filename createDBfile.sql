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
    s_id INT NOT NULL AUTO_INCREMENT,
    player_id INT NOT NULL,
    code_text NVARCHAR(500) NOT NULL,
    comment_text NVARCHAR(500),
    review_id INT,
    PRIMARY KEY (s_id)
);
CREATE TABLE IF NOT EXISTS reviews (
    r_id INT NOT NULL AUTO_INCREMENT,
    segment_id INT NOT NULL,
    score INT NOT NULL,
    review_text NVARCHAR(500),
    player_id INT NOT NULL,
    PRIMARY KEY (r_id)
);
CREATE TABLE IF NOT EXISTS segments_for_review(
	id int not null auto_increment,
	player_id int not null,
    segment_id int not null,
    primary key(id)
);