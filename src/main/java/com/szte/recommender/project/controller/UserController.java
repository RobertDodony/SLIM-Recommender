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

import com.szte.recommender.project.entity.User;
import com.szte.recommender.project.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/list")
	public String listUsers(Model model) {
		
		List<User> users = userService.findAll();
		
		model.addAttribute("users", users);
		
		return "users/list-users";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		User user = new User();
		
		model.addAttribute("user", user);
		
		return "users/user-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int id, Model model) {
		
		User user = userService.findById(id);
		
		model.addAttribute("user", user);
		
		return "users/user-form";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User user) {
		user.setRole("USER");
		userService.save(user);
		if(user.getRole().equalsIgnoreCase("USER")) {
			return "redirect:/index";
		}
		
		return "redirect:/users/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("userId") int id) {
		
		userService.deleteById(id);
		
		return "redirect:/users/list";
	}
	
	@RequestMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		System.out.println("In userCont reg");
		return "users/user-form";
	}
}
