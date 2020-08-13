package com.szte.recommender.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.szte.recommender.project.dao.UserRepository;
import com.szte.recommender.project.entity.User;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int id) {

		Optional<User> result = userRepository.findById(id);
		
		User user = null;
		
		if(result.isPresent()) {
			user = result.get();
		} else {
			throw new RuntimeException("Did not find user id : " + id);
		}
		
		return user;
	}
	
	public User findByEmail(String email) {
		
		User user = userRepository.findByEmail(email);
		
		return user;
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if(user == null) {
			System.out.println("No such user found!");
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(user);
	}

}
