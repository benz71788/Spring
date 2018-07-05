drop table products
drop sequence products_id_seq

create table products(
	id number(11) not null,
	name varchar2(50),
	modelnumber varchar2(15),
	series varchar2(30)
)

create sequence products_id_seq 
increment by 1 start with 1 nocache;

select * from products

insert into products
values(1, '이성희', 'kh1234', '아이폰')

delete from products;

select max(id) from products


