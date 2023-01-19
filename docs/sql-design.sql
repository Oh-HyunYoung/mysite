-- user
desc user;
select * from user;

-- join
insert into user values(null, '오현영','ohydud@gmail.com', password('1234'), 'female', now());

-- login
select no, name from user where email='ohydud@gmail.com' and password = password('1234');
