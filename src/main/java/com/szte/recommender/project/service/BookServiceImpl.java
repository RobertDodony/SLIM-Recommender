package com.szte.recommender.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szte.recommender.project.dao.BookRepository;
import com.szte.recommender.project.dao.UserRepository;
import com.szte.recommender.project.entity.Book;
import com.szte.recommender.project.entity.User;
import com.szte.recommender.project.recommender.Recommender;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;
	private UserRepository userRepository;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book findById(int id) {

		Optional<Book> result = bookRepository.findById(id);

		Book book = null;

		if (result.isPresent()) {
			book = result.get();
		} else {
			throw new RuntimeException("Did not find book id : " + id);
		}

		return book;
	}

	@Override
	public void save(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void deleteById(int id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> recommend() {
		List<User> allUsers = userRepository.findAll();
		List<Book> allBooks = bookRepository.findAll();
		List<Book> recommendedBooks = new ArrayList<Book>();
		try {
			recommendedBooks = Recommender.recommendedBooksForUser(allUsers, allBooks);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recommendedBooks;
	}

	@Override
	public List<Book> findPurchased() {
		List<Book> allBooks = bookRepository.findAll();
		List<Book> purchasedBooks = new ArrayList<Book>();
		Optional<User> optionalUser = userRepository.findById(UserDetailsImpl.getUserId());
		User user = null;
		
		if(optionalUser.isPresent()) {
			user = optionalUser.get();
			purchasedBooks = Recommender.purchasedBooks(allBooks, user);
		}
		
		return purchasedBooks;
	}

}
