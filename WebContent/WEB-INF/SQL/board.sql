select b.article_no,b.title,u.nickname ,to_char(b.reg_date,'YYYY.MM.DD') as reg_date,b.view_count from board b,user_info u where b.user_no = u.user_no

select b.article_no,b.title,u.nickname,content ,to_char(b.reg_date,'YYYY.MM.DD') as reg_date,b.view_count from board b,user_info u
where b.user_no = u.user_no and b.article_no='4';

select * from board;
update board set title='service',content='what?' where article_no=3;


create table board(
	article_no number primary key,
	title varchar2(100) not null,
	content varchar2(2000) not null,
	reg_date date default sysdate,
	view_count number default 0,
	user_no number not null constraint fk_user_no references user_info(user_no) ON DELETE set null,
	category_no constraint fk_category_no references category(category_no) ON DELETE set null
);

-- 페이지 번호 주기
select article_no,title,view_count,reg_date,rn,page from(
select article_no,title,view_count, reg_date,rownum as rn,ceil(rownum/3) as page from(
select article_no,title,view_count,to_char(reg_date,'YYYY.MM.DD') as reg_date from board  order by article_no desc))where page=2;
		
select b.article_no, b.title,u.nickname ,to_char(b.reg_date,'YYYY.MM.DD') as reg_date, b.view_count,rn,page from(
select b.article_no, b.title,u.nickname ,to_char(b.reg_date,'YYYY.MM.DD') as reg_date, b.view_count,rownum as rn,ceil(rownum/2) as page from(
select b.article_no, b.title,u.nickname ,to_char(b.reg_date,'YYYY.MM.DD') as reg_date, b.view_count from board b,user_info u where b.user_no = u.user_no order by article_no desc))
where page=3;

select b.article_no, b.title,  b.reg_date, b.view_count, u.nickname, category_name from(
select article_no, title, content, reg_date, view_count, user_no, category_name,ceil(rownum/3)as page from(
select b.article_no, b.title, b.content, b.reg_date, b.view_count, b.user_no, c.category_name from board b,category c
where b.category_no=c.category_no(+) order by article_no desc
))b, user_info u where page=1 and b.user_no=u.user_no

select b.article_no,b.title,u.nickname ,to_char(b.reg_date,'YYYY.MM.DD') as reg_date,b.view_count from board b,user_info u where b.user_no = u.user_no order by article_no desc;
		

select count(*) from board;

select b.article_no, b.title, b.content, b.reg_date, b.view_count, b.user_no, b.category_no, u.nickname from(
			select article_no, title, content, reg_date, view_count, user_no, category_no,ceil(rownum/3)as page from(
			select article_no, title, content, reg_date, view_count, user_no, category_no from board order by article_no desc
			))b, user_info u where page=1 and b.user_no=u.user_no;
			
select b.article_no,b.title,u.nickname,b.content ,to_char(b.reg_date,'YYYY.MM.DD') as reg_date,b.view_count from board b,user_info u " +
						"where b.user_no = u.user_no and b.article_no=14
						
insert into board(article_no,category_no,title,content,user_no) values(article_seq.nextval,null,'test','test',1);