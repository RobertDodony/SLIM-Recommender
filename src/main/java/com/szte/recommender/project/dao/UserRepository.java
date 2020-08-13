package com.szte.recommender.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.szte.recommender.project.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
