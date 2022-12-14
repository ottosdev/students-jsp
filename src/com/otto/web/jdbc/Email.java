package com.otto.web.jdbc;

public class Email {
	private int id;
	private String empresa;
	private String email;

	public Email() {
		super();
	}
	
	

	public Email(int id, String empresa, String email) {
		super();
		this.empresa = empresa;
		this.email = email;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
