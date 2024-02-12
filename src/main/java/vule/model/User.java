package vule.model;

public class User {
	private int userid;
	private String userfname;
	private String userlname;
	private String username;
	private String password;
	private boolean admin=false;
	
	
	public User() {
		super();
	}
	
	public User(String userfname, String userlname, String username, String password, boolean admin) {
		super();
		this.userfname = userfname;
		this.userlname = userlname;
		this.username = username;
		this.password = password;
		this.admin = admin;
	}

	public User(int userid, String userfname, String userlname, String username, String password, boolean admin) {
		super();
		this.userid = userid;
		this.userfname = userfname;
		this.userlname = userlname;
		this.username = username;
		this.password = password;
		this.admin = admin;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUserfname() {
		return userfname;
	}
	public void setUserfname(String userfname) {
		this.userfname = userfname;
	}
	public String getUserlname() {
		return userlname;
	}
	public void setUserlname(String userlname) {
		this.userlname = userlname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	

}
