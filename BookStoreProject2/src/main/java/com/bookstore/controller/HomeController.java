package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookstore.models.UserAccount;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("/Home")
	public String home(Model model, HttpSession session) {
		UserAccount user = (UserAccount) session.getAttribute("user");
		if(user != null) {
			model.addAttribute("user", user);
		}
		return "Home";
	}
	
	@GetMapping("/")
	public String firstHomePage(Model model, HttpSession session) {
	UserAccount user = (UserAccount) session.getAttribute("user");
	if(user != null) {
		model.addAttribute("user", user);
	}
	return "Home";
	}
}
