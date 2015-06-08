package model;

public class ArticleVO {
	private int article_no;
	private String category;
	private String title;
	private String writer;
	private String content;
	private String reg_date;
	private int view_count;

	public ArticleVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticleVO(int article_no, String title, String reg_date,
			int view_count, String writer, String category) {
		super();
		this.article_no = article_no;
		this.title = title;
		this.reg_date = reg_date;
		this.view_count = view_count;
		this.writer = writer;
		this.category = category;
	}

	// article write
	public ArticleVO(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	// article update post
	public ArticleVO(int article_no, String title, String content) {
		super();
		this.article_no = article_no;
		this.title = title;
		this.content = content;
	}

	// article update view
	public ArticleVO(int article_no, String title, String content,
			int view_count) {
		super();
		this.article_no = article_no;
		this.title = title;
		this.content = content;
		this.view_count = view_count;

	}

	// article list
	public ArticleVO(int article_no, String title, String writer,
			String reg_date, int view_count) {
		super();
		this.article_no = article_no;
		this.title = title;
		this.writer = writer;
		this.reg_date = reg_date;
		this.view_count = view_count;
	}

	// article info
	public ArticleVO(int article_no, String title, String writer,
			String content, String reg_date, int view_count, String category) {
		super();
		this.article_no = article_no;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.reg_date = reg_date;
		this.view_count = view_count;
		this.category = category;
	}

	public ArticleVO(String category, String title, String content) {
		super();
		this.category = category;
		this.title = title;
		this.content = content;
	}

	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ArticleVO [article_no=" + article_no + ", category=" + category
				+ ", title=" + title + ", writer=" + writer + ", content="
				+ content + ", reg_date=" + reg_date + ", view_count="
				+ view_count + "]";
	}

	

}
