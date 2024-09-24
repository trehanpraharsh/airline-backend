package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserEntity {
	 @Id
	    @GeneratedValue
	    private Long userId;
	    private String email;
	    private String password;
	    //this should be replaced with list of bookings
	    private int bookingId;
	    private String phoneNumber;

//	    @OneToMany
//	    private List<Bookings> bookingsList; 
	
}
