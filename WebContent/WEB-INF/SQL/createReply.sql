drop table reply;

create table reply(
	reply_no number primary key,
	content varchar2(300) not null,
	reg_date date default sysdate,
	child_reply constraint fk_reply_no references reply(reply_no),
	article_no not null constraint fk_reply_article_no references board(article_no) ON DELETE cascade,
	user_no number not null constraint fk_reply_user_no references user_info(user_no) ON DELETE set null
);

create sequence reply_seq nocache;

select * from reply;
insert into reply(reply_no,content,child_reply,article_no,user_no) values (reply_seq.nextval,'test',null,40,1);
insert into reply(reply_no,content,child_reply,article_no,user_no) values (reply_seq.nextval,'test',8,11,1);

insert into reply(reply_no,content,child_reply,article_no,user_no) values (reply_seq.nextval,'리플이당',null,40,1);
insert into reply(reply_no,content,child_reply,article_no,user_no) values (reply_seq.nextval,'대댓글입니다',9,40,1);

select reply_seq.nextval from reply;
select reply_seq.currval from reply;