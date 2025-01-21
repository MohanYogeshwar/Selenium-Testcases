package model;


import io.cucumber.java.it.Date;

public class Patient {
	
	
	private String email;
	private String password ;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Patient(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	

}
