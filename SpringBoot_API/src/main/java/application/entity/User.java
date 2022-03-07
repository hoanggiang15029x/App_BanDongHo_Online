package application.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="tendn",unique = true)
	private String username;
	
	@Column(name = "matkhau")
	private String matkhau;

	@Column(name = "role")
	private String role;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(int id, String username, String matkhau, String role) {
		super();
		this.id = id;
		this.username = username;
		this.matkhau = matkhau;
		this.role = role;
	}


	public User(String username, String matkhau, String role) {
		super();
		this.username = username;
		this.matkhau = matkhau;
		this.role = role;
	}


	public User(String username) {
		super();
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", matkhau=" + matkhau + ", role=" + role + "]";
	}
	
}
