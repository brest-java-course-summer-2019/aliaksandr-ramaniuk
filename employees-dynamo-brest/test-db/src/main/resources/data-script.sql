INSERT INTO department (department_id, department_name, department_access_rights) VALUES (1, 'ADMINISTRATOR', 'admin');
INSERT INTO department (department_id, department_name, department_access_rights) VALUES (2, 'COACH', 'write');
INSERT INTO department (department_id, department_name, department_access_rights) VALUES (3, 'PLAYER', 'read');
INSERT INTO department (department_id, department_name, department_access_rights) VALUES (4, 'SUPERVISOR', 'write');
INSERT INTO department (department_id, department_name, department_access_rights) VALUES (5, 'ACCOUNTANT', 'read');
INSERT INTO department (department_id, department_name, department_access_rights) VALUES (6, 'SERVICE STAFF', 'read');

INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (1, 'romanukalex', 'RAMANIUK', 'ALIAKSANDR','NIKOLAEVICH', '2019-09-01', 1);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (2, 'marcel02', 'MARCEL', 'LICHKA','', '2019-09-02', 2);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (3, 'kovalcuk01', 'KOVALCHUK', 'SERGEY','PETROVICH', '2019-09-03', 2);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (4, 'noiok19', 'НОЙОК', 'ALEXANDER','VIKTOROVICH', '2019-09-04', 3);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (5, 'nekhaychik33', 'NEHAYCHIK', 'PAVEL','ALEXANDROVICH', '2019-09-05', 3);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (6, 'dir01', 'RAKUSOV', 'DMITRY','', '2019-09-06', 3);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (7, 'dir02', 'KRASNOBAEV', 'MAKSIM','', '2019-09-07', 4);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (8, 'buh01', 'RAPATIUK', 'SERGEY','NIKOLAEVICH', '2019-09-08', 5);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (9, 'rab01', 'ROGALEVICH', 'DMITRY','VALERYEVICH', '2019-09-09', 6);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (10, 'rab02', 'KHRUTSKY', 'ALEXANDER','YURIEVICH', '2019-09-10', 6);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (11, 'rab03', 'KUZMICH', 'GEORGY','GEORGIEVICH', '2019-09-11', 6);
