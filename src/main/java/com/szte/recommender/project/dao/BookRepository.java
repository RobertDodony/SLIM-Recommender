package com.szte.recommender.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.szte.recommender.project.entity.Book;
	
public interface BookRepository extends JpaRepository<Book, Integer> {

}
