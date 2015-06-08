
-- user info 생성
create table user_info(
	user_no number primary key,
	user_id varchar2(50) UNIQUE,
	nickname varchar2(50) not null,
	password varchar2(50),
	email varchar2(100)
);

create sequence user_seq nocache;
drop sequence user_seq;
-- 테스트용 아이디
insert into USER_INFO(user_no,user_id,nickname,password,email) values (user_seq.nextval,'test','테스트','test','test@test.com');
select * from USER_INFO;
delete from USER_INFO where user_id='pjh4847'

-- 카테고리 생성
create table category(
	category_no number primary key,
	category_name varchar2(10) not null
);

create sequence category_seq nocache;

-- 테스트용 카테고리
insert into category values(category_seq.nextval, '자유');

-- 게시판 생성
drop table board;

create table board(
	article_no number primary key,
	title varchar2(100) not null,
	content varchar2(2000) not null,
	reg_date date default sysdate,
	view_count number default 0,
	user_no number not null constraint fk_user_no references user_info(user_no) ON DELETE set null,
	category_no constraint fk_category_no references category(category_no) ON DELETE set null
);

drop sequence article_seq;
create sequence article_seq nocache;
select article_seq.nextval from dual;
select article_seq.currval from dual;

-- 테스트용 게시글
insert into board(article_no,title,content,user_no) values(article_seq.nextval,'test','test',1);
select * from board;
select article_no,title,to_char(reg_date,'YYYY.MM.DD') as reg_date,view_count from board;

insert into board(article_no,title,content,user_no) values(article_seq.nextval,'ㅇ','ㅇㅇㅇ',1);

drop table board 
drop table user_info
drop table reply
drop table category;
