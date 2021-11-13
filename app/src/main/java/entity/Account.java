package entity;

import java.io.Serializable;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7219916113860449953L;
	
	private String email;
	private Integer role;
	
	public Account() {
	}

	public Account(String email, Integer role) {
		this.email = email;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Account [email=" + email + ", role=" + role + "]";
	}

	
}
