package model;

public class UserVO {
	private int user_no;
	private String user_id;
	private String password;
	private String nickname;
	private String email;
	
	public UserVO(int user_no,String user_id, String nickname) {
		super();
		this.user_no = user_no;
		this.user_id = user_id;
		this.nickname = nickname;
	}
	
	public UserVO(int user_no, String user_id, String nickname, String email) {
		super();
		this.user_no = user_no;
		this.user_id = user_id;
		this.nickname = nickname;
		this.email = email;
	}

	public UserVO(int user_no, String user_id, String password,
			String nickname, String email) {
		super();
		this.user_no = user_no;
		this.user_id = user_id;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
	}
	
	public UserVO(String user_id, String password, String nickname, String email) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
	}

	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "UserVO [user_no=" + user_no + ", user_id=" + user_id
				+ ", password=" + password + ", nickname=" + nickname
				+ ", email=" + email + "]";
	}
	
	
	
	

}
