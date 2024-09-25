package com.dao.impl;

import com.dao.AirlineDao;
import com.entity.Airline;
import com.model.secureAdmin;
import com.repo.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
public class AirlineDaoImpl implements AirlineDao {

    @Autowired
    private AirlineRepository airlineRepository;
    
    @Lazy
	@Autowired
	@Qualifier("webclient")
	private WebClient.Builder builder;

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Optional<Airline> findById(Long id) {
        return airlineRepository.findById(id);
    }
    
    
    public Optional<Airline> findByAirlineEmail(String airlineEmail){
    	return airlineRepository.findByAirlineEmail(airlineEmail);
    }

    @Override
    public Airline save(Airline airline) {
    	
    	// Encrypt the password before saving
        String hashedPassword = BCrypt.hashpw(airline.getAirlinePassword(), BCrypt.gensalt());
        airline.setAirlinePassword(hashedPassword); // Set the hashed password
        
      //concept to save the secured admin in the auth while the time of logging in
        secureAdmin securedAdmin = new secureAdmin(airline.getAirlineEmail(), airline.getAirlinePassword(), "ADMIN");
        
        String registerSecuredAdminURL = "http://localhost:8089/auth/secureadmin";
        
        secureAdmin loadedSecureAdmin = builder.build()
        								.post()
        								.uri(registerSecuredAdminURL)
        								.bodyValue(securedAdmin)
        								.retrieve()
        								.bodyToMono(secureAdmin.class)
        								.block();
    	
        return airlineRepository.save(airline);
    }

    @Override
    public void deleteById(Long id) {
        airlineRepository.deleteById(id);
    }

	@Override
	public Optional<Airline> findByAirlineUniqueCode(String airlineUniqueCode) {
		// TODO Auto-generated method stub
		return airlineRepository.findByAirlineUniqueCode(airlineUniqueCode);
	}
}
