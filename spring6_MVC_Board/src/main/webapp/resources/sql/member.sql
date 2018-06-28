--2. 관리자 계정 admin, 비번 1234를 만듭니다.
--3. 사용자 계정을 3개 만듭니다.

create table member(
	id			varchar2(15),
	password	varchar2(10),
	name		varchar2(15),
	age			Number,
	gender		varchar2(5),
	email		varchar2(30),
	primary key(id)
);

create table member5(
	id			varchar2(15),
	pass	varchar2(10),
	name		varchar2(15),
	age			Number,
	gender		varchar2(5),
	email		varchar2(30),
	primary key(id)
);

 INSERT INTO member5
           VALUES ('admin', '1111','이성희',32,'남','admin@gmail.com')

select * from MEMBER5

delete from member;