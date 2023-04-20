package com.bookstore.service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.models.Basket;
import com.bookstore.models.Books;
import com.bookstore.models.OrderHistory;
import com.bookstore.models.UserAccount;
import com.bookstore.repository.BooksRepository;
import com.bookstore.repository.OrderHistoryRepository;
import com.bookstore.repository.UserAccountRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private OrderHistoryRepository orderHistoryRepository;
	
	@Override
	public void saveOrderHistory(OrderHistory orderHistory) {
		orderHistoryRepository.save(orderHistory);
		
	}
	
	@Override
	public OrderHistory findOrderHistoryByUserAndBook(UserAccount user, Books book) {
	    return orderHistoryRepository.findByAccountIdAndBookId(user, book);
	}

	@Override
	public List<OrderHistory> findByAccountId(UserAccount user) {
		return orderHistoryRepository.findByAccountId(user);
	}

	public OrderHistory findOrderHistoryByUserAndBookAndCheckoutId(UserAccount user, Books book, String checkoutId) {
		return orderHistoryRepository.findByAccountIdAndBookIdAndCheckoutId(user, book, checkoutId);
	}

	//testing
	public List<OrderHistory> findByAccountIdAndCheckoutId(UserAccount user, String checkoutId) {
		// TODO Auto-generated method stub
		return orderHistoryRepository.findByAccountIdAndCheckoutId(user, checkoutId);
	}



}
