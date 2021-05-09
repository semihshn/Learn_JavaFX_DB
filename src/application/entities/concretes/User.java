package application.entities.concretes;

import application.entities.abstracts.Entity;

public class User implements Entity{
	private int id;
	private String userName;
	private String password;
	private int authority;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int id, String userName, String password, int authority) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.authority = authority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}
}
