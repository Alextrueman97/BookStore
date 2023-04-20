package com.bookstore.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookstore.models.Basket;
import com.bookstore.models.Books;
import com.bookstore.models.OrderHistory;
import com.bookstore.models.UserAccount;
import com.bookstore.service.BooksServiceImpl;
import com.bookstore.service.OrderHistoryServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class BasketController {

	@Autowired
	private BooksServiceImpl booksServiceImpl;
	@Autowired
	private OrderHistoryServiceImpl orderHistoryServiceImpl;
	
	@PostMapping("/addToBasket/{bookId}")
	public String addToBasket(@PathVariable("bookId") int bookId, RedirectAttributes redirectAttributes) {
		booksServiceImpl.addToBasket(bookId);
		redirectAttributes.addFlashAttribute("message", "Book added to basket.");
		return "redirect:/books";
	}
	
	@GetMapping("/basket")
	public String showBasket(Model model, HttpSession session) {
		UserAccount user = (UserAccount) session.getAttribute("user");
		Basket basket = booksServiceImpl.getUserBasket();
		if(basket == null) {
			basket = new Basket();
		}
		model.addAttribute("user", user);
		model.addAttribute("basket", basket);
		return "basket";
	}
	
	@GetMapping("/checkoutPayment")
	public String payment(HttpSession session, Model model) {
		UserAccount user = (UserAccount) session.getAttribute("user");
	    if(user == null) {
	        return "redirect:/login";
	    }
	    else {
	    model.addAttribute("user", user);
		return "checkoutPayment";
		}
	}
	
	@PostMapping("/checkout")  
	public String checkout(HttpSession session, Model model ) {
	    UserAccount user = (UserAccount) session.getAttribute("user");
	    if(user == null) {
	        return "redirect:/login";
	    }
	    Basket basket = (Basket) session.getAttribute("basket");
	    if (basket == null || basket.getBooks().isEmpty()) {
	        return "redirect:/books";
	    }
	    
	    // generate a new checkout id for this session
	    String checkoutId = UUID.randomUUID().toString();

	    // Create OrderHistory entry for each book with the new checkout id
	    for (Books book : basket.getBooks()) {
	        double bookPrice = book.getBookPrice(); // price of a single book
	        OrderHistory existingOrderHistory = orderHistoryServiceImpl.findOrderHistoryByUserAndBookAndCheckoutId(user, book, checkoutId);
	        if (existingOrderHistory != null) {
	            int quantity = existingOrderHistory.getQuantity() + 1; // set quantity to 1 per book
	            double totalPrice = existingOrderHistory.getTotalPrice() + bookPrice; // add the price of the current book
	            existingOrderHistory.setQuantity(quantity);
	            existingOrderHistory.setTotalPrice(totalPrice); // set total price of the books in the order
	            orderHistoryServiceImpl.saveOrderHistory(existingOrderHistory);
	        } else {
	            double totalPrice = bookPrice; // set the total price as the price of the current book
	            OrderHistory orderHistory = new OrderHistory();
	            orderHistory.setOrderHistoryId(UUID.randomUUID().toString()); // set the order number to a new UUID
	            orderHistory.setAccountId(user);
	            orderHistory.setBookId(book);
	            orderHistory.setQuantity(1);
	            orderHistory.setTotalPrice(totalPrice);
	            orderHistory.setDate(LocalDate.now());
	            orderHistory.setCheckoutId(checkoutId); // set the checkout id for this order
	            orderHistoryServiceImpl.saveOrderHistory(orderHistory);
	        }
	    }
	    basket.clear();
	    session.setAttribute("basket", basket);
	    model.addAttribute("user", user);
	    return "order-complete";
	}
	
	
	@PostMapping("/removeFromBasket/{index}")
	public String removeFromBasket(@PathVariable("index") int index, HttpSession session) {
		Basket basket = booksServiceImpl.getUserBasket();
		if(basket != null) {
			basket.removeItem(index);
			session.setAttribute("basket", basket);
		}
		return "redirect:/basket";
	}
	
	/*
	 * @GetMapping("/orderHistory") public String getOrderHistory(Model model,
	 * HttpSession session) { UserAccount user = (UserAccount)
	 * session.getAttribute("user"); if (user == null) { return "redirect:/login"; }
	 * 
	 * List<OrderHistory> orderHistoryList =
	 * orderHistoryServiceImpl.findByAccountId(user);
	 * model.addAttribute("orderHistoryList", orderHistoryList);
	 * model.addAttribute("user", user);
	 * 
	 * return "orderHistory"; }
	 */
	
	@GetMapping("/orderHistory")
	public String getOrderHistory(Model model, HttpSession session) {
	    UserAccount user = (UserAccount) session.getAttribute("user");
	    if (user == null) {
	        return "redirect:/login";
	    }

	    List<OrderHistory> orderHistoryList = orderHistoryServiceImpl.findByAccountId(user);
	    Map<String, Double> checkoutTotalMap = new HashMap<>();

	    // Calculate total prices for each checkout id
	    for (OrderHistory orderHistory : orderHistoryList) {
	        String checkoutId = orderHistory.getCheckoutId();
	        double totalPrice = checkoutTotalMap.getOrDefault(checkoutId, 0.0) + orderHistory.getTotalPrice();
	        checkoutTotalMap.put(checkoutId, totalPrice);
	    }

	    model.addAttribute("orderHistoryList", orderHistoryList);
	    model.addAttribute("checkoutTotalMap", checkoutTotalMap);
	    model.addAttribute("user", user);

	    return "orderHistory";
	}

	@GetMapping("/checkout/{checkoutId}")
	public String showCheckout(@PathVariable("checkoutId") String checkoutId, Model model, HttpSession session) {
	    UserAccount user = (UserAccount) session.getAttribute("user");
	    if (user == null) {
	        return "redirect:/login";
	    }

	    List<OrderHistory> orderHistoryList = orderHistoryServiceImpl.findByAccountIdAndCheckoutId(user, checkoutId);
	    double totalCheckoutPrice = 0.0;

	    for (OrderHistory orderHistory : orderHistoryList) {
	        totalCheckoutPrice += orderHistory.getTotalPrice();
	    }

	    model.addAttribute("orderHistoryList", orderHistoryList);
	    model.addAttribute("checkoutId", checkoutId);
	    model.addAttribute("totalCheckoutPrice", totalCheckoutPrice);
	    model.addAttribute("user", user);

	    return "orderHistoryMore";
	}
	
	
	
	
	
}

