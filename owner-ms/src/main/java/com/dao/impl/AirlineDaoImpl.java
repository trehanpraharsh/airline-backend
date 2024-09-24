package com.dao.impl;

import com.dao.AirlineDao;
import com.entity.Airline;
import com.repo.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
public class AirlineDaoImpl implements AirlineDao {

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Optional<Airline> findById(Long id) {
        return airlineRepository.findById(id);
    }

    @Override
    public Airline save(Airline airline) {
    	
    	// Encrypt the password before saving
        String hashedPassword = BCrypt.hashpw(airline.getAirlinePassword(), BCrypt.gensalt());
        airline.setAirlinePassword(hashedPassword); // Set the hashed password
    	
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
