package userImpormation;

public class MemberVO {

	private int no;
	private String name;
	private String id;
	private String passwd;
	private String birthday;
	private String gender;
	private String phone;
	private String mail;
	
	public MemberVO() {
		super();
	}

	public MemberVO(int no, String name, String id, String passwd, String birthday, String gender, String phone,
			String mail) {
		super();
		this.no = no;
		this.name = name;
		this.id = id;
		this.passwd = passwd;
		this.birthday = birthday;
		this.gender = gender;
		this.phone = phone;
		this.mail = mail;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return no + "\t" + name + "\t" + id + "\t" + passwd + "\t" + birthday
				+ "\t" + gender + "\t" + phone + "\t" + mail;
	}
	
}
