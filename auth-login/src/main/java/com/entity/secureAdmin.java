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
@Table(name = "secureadmin")
public class secureAdmin {

	@Id
	@GeneratedValue
	private int secureAdID;
	private String secureAdEmail;
	private String secureAdPassword;
	private String roles;
	
	public secureAdmin(String secureAdEmail, String secureAdPassword, String roles) {
		super();
		this.secureAdEmail = secureAdEmail;
		this.secureAdPassword = secureAdPassword;
		this.roles = roles;
	}
	
	
	
}
