delete from user_role;
delete from role;
delete from user; 

insert into user (user_id,active,email,last_name,name,password) values (1,1,'admin@sb2.com','Administrator','Admin', '$2a$10$CDYCJIVlym0qebMcGUPeG.Es00ZuuicMwF8kpLbbJ3P9lcS0QpkQm');
insert into role (role_id,role) values (1,'ROLE_ADMIN');
insert into user_role (user_id,role_id) values (1,1);
