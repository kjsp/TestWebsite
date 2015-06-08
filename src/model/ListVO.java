package model;

import java.util.ArrayList;

public class ListVO {
	private ArrayList<ArticleVO> list;
	private PagingBean pb;
	public ListVO(ArrayList<ArticleVO> list, PagingBean pb) {
		super();
		this.list = list;
		this.pb = pb;
	}
	public ListVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<ArticleVO> getList() {
		return list;
	}
	public void setList(ArrayList<ArticleVO> list) {
		this.list = list;
	}
	public PagingBean getPb() {
		return pb;
	}
	public void setPb(PagingBean pb) {
		this.pb = pb;
	}
	@Override
	public String toString() {
		return "ListVO [list=" + list + ", pb=" + pb + "]";
	}
	
	
}
