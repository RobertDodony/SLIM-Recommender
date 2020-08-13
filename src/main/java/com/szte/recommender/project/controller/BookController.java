package com.szte.recommender.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.szte.recommender.project.entity.Book;
import com.szte.recommender.project.entity.User;
import com.szte.recommender.project.service.BookService;
import com.szte.recommender.project.service.UserDetailsImpl;
import com.szte.recommender.project.service.UserService;

@Controller
@RequestMapping("/books")
public class BookController {

	private BookService bookService;
	private UserService userService;

	@Autowired
	public BookController(BookService bookService, UserService userService) {
		this.bookService = bookService;
		this.userService = userService;
	}

	@GetMapping("/index")
	public String redirectToIndex() {

		return "redirect:/index";
	}

	@GetMapping("/list")
	public String listBooks(Model model) {

		List<Book> books = bookService.recommend();

		model.addAttribute("books", books);

		return "books/list-books";
	}

	@GetMapping("/listAll")
	public String listAllBooks(Model model) {

		List<Book> books = bookService.findAll();

		model.addAttribute("books", books);

		return "books/list-books";
	}

	@GetMapping("/listPurchased")
	public String listPurchasedBooks(Model model) {

		List<Book> books = bookService.findPurchased();

		model.addAttribute("books", books);

		return "books/list-books";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		Book book = new Book();

		model.addAttribute("book", book);

		return "books/book-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bookId") int id, Model model) {

		int userId = UserDetailsImpl.getUserId();

		User user = userService.findById(userId);

		user.setPurchases(user.getPurchases() + "," + (id - 1));

		userService.save(user);

		return "redirect:/books/list";
	}

	@PostMapping("/save")
	public String saveBook(@ModelAttribute("book") Book book) {

		bookService.save(book);

		return "redirect:/books/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("bookId") int id) {

		bookService.deleteById(id);

		return "redirect:/books/list";
	}
}
