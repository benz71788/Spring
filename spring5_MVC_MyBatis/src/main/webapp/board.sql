drop table board5;
create table BOARD5(
	BOARD_NUM		NUMBER(38),					--글번호
	BOARD_NAME		VARCHAR2(50) not null,	--작성자
	BOARD_PASS 		VARCHAR2(30) not null,	--비밀번호
	BOARD_SUBJECT	VARCHAR2(100) not null,	--제목
	BOARD_CONTENT	VARCHAR2(4000) not null,--내용
	BOARD_RE_REF	NUMBER,					--답변 글 작성시 참조되는 글의 번호
	BOARD_RE_LEV	NUMBER,					--답변 글의 깊이
	BOARD_RE_SEQ	NUMBER,					--답변 글의 순서
	BOARD_READCOUNT	NUMBER,					--글의 조회수
	BOARD_DATE		DATE,					--글의 작성 날짜
	PRIMARY KEY(BOARD_NUM)		
);

create sequence board5_num_seq
	increment by 1 start with 1 nocache;
	
select * from board5;


insert into board5
 values(board5_num_seq.nextval, 'hello', '1111', 'asdf', 'asdf', 0, 0, 0, 0, sysdate);