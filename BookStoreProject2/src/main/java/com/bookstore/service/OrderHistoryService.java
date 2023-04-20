package com.bookstore.service;

import java.util.List;

import com.bookstore.models.Basket;
import com.bookstore.models.Books;
import com.bookstore.models.OrderHistory;
import com.bookstore.models.UserAccount;

import jakarta.servlet.http.HttpSession;

public interface OrderHistoryService {

	public OrderHistory findOrderHistoryByUserAndBook(UserAccount user, Books book);
	
	public void saveOrderHistory(OrderHistory orderHistory);

	public List<OrderHistory> findByAccountId(UserAccount user);

	
	
}
