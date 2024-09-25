package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class secureAdmin {
	
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
