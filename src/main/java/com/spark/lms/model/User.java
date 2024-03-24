package com.spark.lms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public User() {}



	public User(@NotNull String firstName,@NotNull String displayName, @NotNull String username, @NotNull String password, @NotNull String role) {
		super();
		this.firstName =firstName;
		this.displayName = displayName;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "*Please enter fisrt name")
	@NotNull(message = "*Please enter fisrt name")
	@Column(name = "firstName")
	private String firstName;
//	@Column(name = "last_name")
//	private String lastName;

//	@Column(name = "email")
//	private String email;
//
	@NotEmpty(message = "*Please enter display name")
	@NotNull(message = "*Please enter display name")
	@Column(name = "displayName")
	private String displayName;

	@NotNull
	@Column(name = "username")
	private String username;

	@NotNull
	@Column(name = "password")
	private String password;

	//@NotNull
	@Column(name = "active")
	private Integer active;

	//@NotNull
	@Column(name = "created_date")
	private Date createdDate;

	@NotNull
	@Column(name = "role")
	private String role;



	@Column(name = "last_modified_date")
	private Date lastModifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}



	public String getUsername() {
		return username;
	}


	public String getFirstName() {return firstName;}



//	public String getLastName() {return lastName;}
//
//	public void setLastName(String lastName) {this.lastName = lastName;}

//	public String getEmail() {return email;}
//
//	public void setEmail(String email) {this.email = email;}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
