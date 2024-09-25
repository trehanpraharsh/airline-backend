package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class secureUser {
	
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
