create table projects (
	id_project integer IDENTITY, 
	project_name varchar(100), 
	abbreviation varchar(50), 
	description varchar(1000)
);

create table tasks (
	id_task integer IDENTITY, 
	task_name varchar(100), 
	hours integer, 
	start_date date, 
	finish_date date, 
	state varchar(20)
);

create table employees (
	id_employee integer IDENTITY, 
	last_name varchar(100), 
	name varchar(100), 
	middle_name varchar(100), 
	post varchar(100)
);

create table projects_tasks (
	id_project integer, 
	id_task integer, 
	FOREIGN KEY (id_project) REFERENCES projects (id_project) ON DELETE CASCADE, 
	FOREIGN KEY (id_task) REFERENCES tasks (id_task) ON DELETE CASCADE
);

create table employees_tasks (
	id_employee integer, 
	id_task integer, 
	FOREIGN KEY (id_employee) REFERENCES employees (id_employee) ON DELETE CASCADE, 
	FOREIGN KEY (id_task) REFERENCES tasks (id_task) ON DELETE CASCADE);
	