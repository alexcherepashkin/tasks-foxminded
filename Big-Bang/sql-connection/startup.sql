DROP TABLE IF EXISTS students_courses;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS groups;

CREATE TABLE IF NOT EXISTS groups (
	group_id serial PRIMARY KEY,
	group_name varchar(50));

CREATE TABLE IF NOT EXISTS students (
	student_id serial PRIMARY KEY,
	group_id int REFERENCES groups ON DELETE SET NULL,
	first_name varchar(10) NOT NULL,
	last_name varchar(10) NOT NULL);

CREATE TABLE IF NOT EXISTS courses (
	course_id serial PRIMARY KEY,
	course_name varchar(50),
	course_description varchar(50));

CREATE TABLE IF NOT EXISTS students_courses (
	student_id int NOT NULL REFERENCES students ON DELETE CASCADE ON UPDATE CASCADE,
	course_id int NOT NULL REFERENCES courses ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (student_id, course_id));
