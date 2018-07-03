--join_member2.sql
drop table join_member
create table join_member2(
  	join_code number(38) unique not null 
  	/*unique 제약 조건은 중복값을 허용하지 않고 null은 허용*/  	
  , join_id varchar2(20) primary key /*회원아이디*/
  , join_pwd varchar2(20) not null /*회원비번*/
  , join_name varchar2(50) not null /*회원이름*/
  , join_zip varchar2(5) not null /* 5자리 우편번호 */
  , join_addr1 varchar2(100) not null /*주소*/
  , join_addr2 varchar2(100) not null /*나머지 주소 */
  , join_tel varchar2(20) not null /*전번*/
  , join_phone varchar2(20) not null /*폰번호 */
  , join_email varchar2(100) not null /*전자우편 주소*/
  , join_file varchar2(100)  /*변경한 이진파일명*/
  , join_regdate date /*가입 날짜*/
  , join_state number(10) /*가입회원 1, 탈퇴회원 2 */
  , join_delcont varchar2(4000) /*탈퇴 사유 */
  , join_deldate date /*탈퇴 날짜 */
  , join_original varchar2(100) /*원래파일명*/
);

/***** join_member 테이블의 join_code 시퀀스 생성 *****/
create sequence join_member_joincode_seq 
increment by 1 start with 1 nocache;

--drop sequence join_member_joincode_seq; 

select * from join_member2;

delete from join_member2;



