-- P0 Database

-- The different spaceships
CREATE TABLE spaceships(

	spaceship_id serial PRIMARY KEY,
	spaceship_name TEXT UNIQUE NOT NULL,
	spaceship_max_crew int DEFAULT 4

)

-- The different individual crew members
CREATE TABLE astronauts(
	
	astronaut_id serial PRIMARY KEY,
	astronaut_name TEXT NOT NULL,
	astronaut_work_speed DECIMAL(3,2),
	astronaut_combat_speed DECIMAL(3,2),
	astronaut_spaceship_fk int REFERENCES spaceships(spaceship_id) ON DELETE SET NULL

)

-- Adding The Kestrel and The Pequod
INSERT INTO spaceships(spaceship_name, spaceship_max_crew)
VALUES ('The Kestrel', 3)

INSERT INTO spaceships(spaceship_name)
VALUES ('The Pequod')
	
-- Adding The Kestrel crew
INSERT INTO astronauts(astronaut_name, astronaut_work_speed, astronaut_combat_speed, astronaut_spaceship_fk)
VALUES ('Kirby', 1.2, 1.25, 1),
	('Ma Fan', 1.1, .95, 1),
	('Jon', 1, 1.3, 1)
	
-- Adding the Pequod
INSERT INTO astronauts(astronaut_name, astronaut_work_speed, astronaut_combat_speed, astronaut_spaceship_fk)
VALUES ('Ahab', 1.7, 1.7, 2)
	
-- Astronauts without a spaceship
INSERT INTO astronauts(astronaut_name, astronaut_work_speed, astronaut_combat_speed, astronaut_spaceship_fk)
VALUES ('Ishmael', 1.6, 1.4, NULL),
	('Queequeg', 1.4, 1.6, NULL)
	
-- All astronauts information with a INNER JOIN
SELECT * FROM astronauts INNER JOIN spaceships ON astronaut_spaceship_fk = spaceship_id

-- All astronauts information with a FULL OUTER JOIN
SELECT * FROM astronauts FULL OUTER JOIN spaceships ON astronaut_spaceship_fk = spaceship_id
	
-- All astronauts information with a LEFT OUTER JOIN
SELECT * FROM astronauts LEFT OUTER JOIN spaceships ON astronaut_spaceship_fk = spaceship_id

-- All astronauts information with a RIGHT OUTER JOIN
SELECT * FROM astronauts RIGHT OUTER JOIN spaceships ON astronaut_spaceship_fk = spaceship_id

-- Current astronauts on The Kestrel
SELECT * FROM astronauts WHERE astronaut_spaceship_fk = 1

SELECT * FROM astronauts

DROP TABLE astronauts
DROP TABLE spaceships
	
DELETE FROM spaceships WHERE spaceship_name = 'The Kestrel'
	
SELECT * FROM astronauts WHERE astronaut_spaceship_fk = NULL
	
	
