CREATE TABLE if not exists employee
 (
	employee_id serial PRIMARY KEY,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	department_id int unsigned NOT NULL,
	job_title varchar(100) NOT NULL,
	gender varchar(6) check ('gender' in ('MALE' , 'FEMALE')),
	date_of_birth date
);