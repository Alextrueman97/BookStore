package com.bookstore.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookstore.models.Basket;
import com.bookstore.models.Books;
import com.bookstore.models.OrderHistory;
import com.bookstore.models.UserAccount;
import com.bookstore.service.BooksServiceImpl;
import com.bookstore.service.OrderHistoryServiceImpl;
import com.bookstore.service.UserAccountServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class BooksController {
	
	@Autowired
	private BooksServiceImpl booksServiceImpl;
	@Autowired
	private OrderHistoryServiceImpl orderHistoryServiceImpl;
	

	@GetMapping("/books")
	public String showAllBooks(Model model, HttpSession session) {
		UserAccount user = (UserAccount) session.getAttribute("user");
		List<Books> booksList = booksServiceImpl.showAllBooks(new Books());
		model.addAttribute("user", user);
		model.addAttribute("booksList", booksList);
		return "books";
	}
	
	@GetMapping("/bookInfo/{bookId}")
	public String showBookInfo(@PathVariable("bookId") int bookId, Model model, HttpSession session) {
		UserAccount user = (UserAccount) session.getAttribute("user");
		Optional<Books> book = booksServiceImpl.findById(bookId);
		if(book.isPresent()) {
			model.addAttribute("user", user);
			model.addAttribute("book", book.get());
			return "bookInfo";
		}else {
			return "books";
		}	
	}
	
	@PostMapping("/searchBook")
	public String searchBook(@RequestParam("bookTitle") String bookTitle, Model model, RedirectAttributes redirectAttributes) {
	    List<Books> booksList = booksServiceImpl.showAllBooks(new Books());

	    for (Books book : booksList) {
	        if (book.getBookTitle().equals(bookTitle)) {
	            return "redirect:/bookInfo/" + book.getBookId();
	        }
	    }
	    redirectAttributes.addFlashAttribute("message", "Book not found!");
	    return "redirect:/books";
	}
}
