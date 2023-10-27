create table if not exists person (
id serial primary key not null,
login varchar(2000),
password varchar(2000)
);

insert into person (login, password) values ('parsentev', '123');
insert into person (login, password) values ('ban', '123');
insert into person (login, password) values ('ivan', '123');

create table if not exists employee (
id serial primary key not null,
name varchar(2000),
surname varchar(2000),
inn integer,
created timestamp
);

INSERT INTO employee (name, surname, inn, created)
VALUES
('Иван', 'Иванов', 123456789, '2023-10-27 10:00:00'),
('Мария', 'Петрова', 987654321, '2023-10-27 11:00:00');

create table if not exists employee_person(
id serial primary key,
employee_id int not null references employee(id),
person_id int references person(id)
);

INSERT INTO employee_person (employee_id, person_id)
VALUES
(1, 2),
(1, 3),
(2, 1);