package model;

public class ReplyVO {
	private int reply_no;
	private String content;
	private String reg_date;
	private int child_reply;
	private int article_no;
	private String writer;
	private int depth;
	
	public ReplyVO(String content, int child_reply, int article_no) {
		super();
		this.content = content;
		this.child_reply = child_reply;
		this.article_no = article_no;
	}


	public ReplyVO(int reply_no, String content, String reg_date,
			String writer, int depth) {
		super();
		this.reply_no = reply_no;
		this.content = content;
		this.reg_date = reg_date;
		this.writer = writer;
		this.depth = depth;
	}

	
	public ReplyVO(int reply_no, String content, String reg_date,
			int child_reply, int article_no, String writer, int depth) {
		super();
		this.reply_no = reply_no;
		this.content = content;
		this.reg_date = reg_date;
		this.child_reply = child_reply;
		this.article_no = article_no;
		this.writer = writer;
		this.depth = depth;
	}

	public ReplyVO(int reply_no, String content, String reg_date,
			int child_reply, String writer, int depth) {
		super();
		this.reply_no = reply_no;
		this.content = content;
		this.reg_date = reg_date;
		this.child_reply = child_reply;
		this.writer = writer;
		this.depth = depth;
	}


	
	@Override
	public String toString() {
		return "ReplyVO [reply_no=" + reply_no + ", content=" + content
				+ ", reg_date=" + reg_date + ", child_reply=" + child_reply
				+ ", article_no=" + article_no + ", writer=" + writer
				+ ", depth=" + depth + "]";
	}
	
	
	
	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public int getChild_reply() {
		return child_reply;
	}

	public void setChild_reply(int child_reply) {
		this.child_reply = child_reply;
	}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_id(int reply_no) {
		this.reply_no = reply_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

}
