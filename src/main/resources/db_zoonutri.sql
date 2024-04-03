-- create schema
--create schema zoonutri;

-- create new roles
--insert into zoonutri.user_role values (1, 'ROLE_ADMIN');
--insert into zoonutri.user_role values (2, 'ROLE_MANAGER');
--insert into zoonutri.user_role values (3, 'ROLE_DOCTOR');
--insert into zoonutri.user_role values (4, 'ROLE_OPERATOR');

--select * from zoonutri.user_role ur;

-- create sample user

--insert into zoonutri.user (cd_user, name, email, password, cd_role, active, creation_date) values (1, 'King Gustavo', 'gustavo.luiz8402@gmail.com', '123', 1, true, CURRENT_TIMESTAMP);
--insert into zoonutri.user (cd_user, name, email, password, cd_role, active, creation_date) values (4, 'Operator', 'operator@zoonutri.com', '123', 4, true, CURRENT_TIMESTAMP);
--insert into zoonutri.user (cd_user, name, email, password, cd_role, active, creation_date) values (2, 'Manager', 'manager@zoonutri.com', '123', 2, true, CURRENT_TIMESTAMP);
--insert into zoonutri.user (cd_user, name, email, password, cd_role, active, creation_date) values (3, 'Doctor', 'doctor@zoonutri.com', '123', 3, true, CURRENT_TIMESTAMP);
--insert into zoonutri.user (cd_user, name, email, password, cd_role, active, creation_date) values (5, 'xx', 'xx', 'xx', 3, true, CURRENT_TIMESTAMP);

--select * from zoonutri.user;
--delete from zoonutri.user;

-- delete schema
--drop schema zoonutri cascade;