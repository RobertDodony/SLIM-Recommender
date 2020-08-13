package com.szte.recommender.project.service;

import java.util.List;

import com.szte.recommender.project.entity.User;

public interface UserService {

	public List<User> findAll();
	
	public User findById(int id);
	
	public void save(User user);
	
	public void deleteById(int id);
	
	public User findByEmail(String email);
	
}
