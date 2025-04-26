package com.dsquare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsquare.model.User;
import com.dsquare.repository.UserRepository;

@Service
public class UserService {
	@Autowired 
	UserRepository userRepository;
	
	public void saveAll(List<User> users) {
		for(User u : users) userRepository.save(u);
	}

	public void save(User user) {
		userRepository.save(user);
		
	}
}
