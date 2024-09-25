package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "secureuser")
public class secureUser {

	@Id
	@GeneratedValue
	private int secureUID;
	private String secureUEmail;
	private String secureUPassword;
	private String roles;
	
	public secureUser(String secureUEmail, String secureUPassword, String roles) {
		super();
		this.secureUEmail = secureUEmail;
		this.secureUPassword = secureUPassword;
		this.roles = roles;
	}
	
	
	
}
