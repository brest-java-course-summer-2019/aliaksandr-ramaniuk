INSERT INTO department (department_id, department_name, department_access_rights) VALUES (1, 'АДМИНИСТРАТОР', 'admin');
INSERT INTO department (department_id, department_name, department_access_rights) VALUES (2, 'ТРЕНЕР', 'write');
INSERT INTO department (department_id, department_name, department_access_rights) VALUES (3, 'ИГРОК', 'read');
INSERT INTO department (department_id, department_name, department_access_rights) VALUES (4, 'РУКОВОДСТВО', 'write');
INSERT INTO department (department_id, department_name, department_access_rights) VALUES (5, 'БУХГАЛТЕР', 'read');
INSERT INTO department (department_id, department_name, department_access_rights) VALUES (6, 'ОБСЛУЖИВАЮЩИЙ ПЕРСОНАЛ', 'read');

INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (1, 'romanukalex', 'РОМАНЮК', 'АЛЕКСАНДР','НИКОЛАЕВИЧ', '2019-01-01', 1);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (2, 'marcel02', 'МАРЦЕЛ', 'ЛИЧКА','', '2019-01-02', 2);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (3, 'kovalcuk01', 'КОВАЛЬЧУК', 'СЕРГЕЙ','ПЕТРОВИЧ', '2019-01-03', 2);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (4, 'noiok19', 'НОЙОК', 'АЛЕКСАНДР','ВИКТОРОВИЧ', '2019-01-04', 3);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (5, 'nekhaychik33', 'НЕХАЙЧИК', 'ПАВЕЛ','АЛЕКСАНДРОВИЧ', '2019-01-05', 3);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (6, 'dir01', 'РАКУСОВ', 'ДМИТРИЙ','', '2019-01-06', 3);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (7, 'dir02', 'КРАСНОБАЕВ', 'МАКСИМ','', '2019-01-07', 4);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (8, 'buh01', 'РАПАТЮК', 'СЕРГЕЙ','НИКОЛАЕВИЧ', '2019-01-08', 5);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (9, 'rab01', 'РОГАЛЕВИЧ', 'ДМИТРИЙ','ВАЛЕРЬЕВИЧ', '2019-01-09', 6);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (10, 'rab02', 'ХРУЦКИЙ', 'АЛЕКСАНДР','ЮРЬЕВИЧ', '2019-01-10', 6);
INSERT INTO employee (employee_id, login, last_name, first_name, patronic_name, local_date, department_id) VALUES (11, 'rab03', 'ХРУЦКИЙ', 'ДМИТРИЙ','ЮРЬЕВИЧ', '2019-01-10', 6);
