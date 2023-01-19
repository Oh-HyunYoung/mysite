-- user
desc user;
select * from user;

-- join
insert into user values(null, '오현영','ohydud@gmail.com', password('1234'), 'female', now());
select no, name from user where email=? and password = password(?);
-- login
select no, name, email from user where email='dooly@naver.com' and password = password('1234');
select no, name, message, date_format(reg_date, '%Y/%m/%d %H:%i:%s') from guestbook;   
select no, name, email, password, gender from user;

select no, name, email from user where email='ohydud@gmail.com' and password = password('1234');
select no, name from user where email='dooly@naver.com' and password = password('1234');
select no, name, email, gender from user where email='dooly@naver.com' and password = password('1234');

select no, name, email, gender from user where email=? and password = password(?);

update user set name = ?, password = ?, gender = ?;