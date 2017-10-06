package br.com.glsoftware.conting.entities;

public class Contact {
	
	private int id;
	private String email;
	private String phone;
	
	public Contact(int id, String email, String phone) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
	}

	public Contact (String email, String phone) {
		this.email = email;
		this.phone = phone;
	}
	
	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
