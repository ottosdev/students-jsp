package com.otto.web.jdbc;

public class Student {

	private int id;
	private String firstName;
	private String email;
	private String lastname;
	
	public Student(int id, String firstName, String email, String lastname) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.email = email;
		this.lastname = lastname;
	}
	
	

	public Student(String firstName, String email, String lastname) {
		super();
		this.firstName = firstName;
		this.email = email;
		this.lastname = lastname;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", email=" + email + ", lastname=" + lastname + "]";
	}
	
	
	
	
}
