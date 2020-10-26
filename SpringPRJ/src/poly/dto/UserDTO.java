package poly.dto;

public class UserDTO {
	private String user_no; // 유저 번호, 기본키
	private String user_id; // 아이디
	private String user_pwd; // 패스워드
	private String user_email; // 이메일
	private String user_age; // 나이
	private String user_gender; // 성별
	private String user_interest; // 관심사
	private String user_authNum; //인증코드

	public String getUser_authNum() {
		return user_authNum;
	}

	public void setUser_authNum(String user_authNum) {
		this.user_authNum = user_authNum;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_age() {
		return user_age;
	}

	public void setUser_age(String user_age) {
		this.user_age = user_age;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_interest() {
		return user_interest;
	}

	public void setUser_interest(String user_interest) {
		this.user_interest = user_interest;
	}

}
