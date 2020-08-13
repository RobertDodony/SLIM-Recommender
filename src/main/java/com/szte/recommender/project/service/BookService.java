package com.szte.recommender.project.service;

import java.util.List;

import com.szte.recommender.project.entity.Book;

public interface BookService {

	public List<Book> findAll();

	public Book findById(int id);

	public void save(Book book);

	public void deleteById(int id);
	
	public List<Book> recommend();

	public List<Book> findPurchased();

}
