package model;

public class CategoryVO {
	private int categoryNo;
	private String categoryName;

	public CategoryVO(int categoryNo, String string) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = string;
	}

	@Override
	public String toString() {
		return "CategoryVO [categoryNo=" + categoryNo + ", categoryName="
				+ categoryName + "]";
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
