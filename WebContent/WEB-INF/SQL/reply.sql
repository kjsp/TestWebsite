select * from reply;

select r.reply_no, r.content,r.reg_date,r.child_reply,u.nickname,level
from reply r,user_info u 
where r.user_no = u.user_no 
start with r.child_reply is null
CONNECT BY PRIOR r.reply_no=r.child_reply
order SIBLINGS by r.reg_date asc;

select *
from reply
start with article_no = 11
CONNECT BY PRIOR child_reply = reply_no