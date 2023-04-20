package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.models.Basket;
import com.bookstore.models.Books;
import com.bookstore.models.UserAccount;

public interface BooksService {

	public List<Books> showAllBooks(Books books);
	
	public Books showBookInfo(int bookId);
	
	public Optional<Books> findById(int bookId);

	Books getBook(int bookId);
	
	public void addToBasket(int bookId);
	
	public Basket getUserBasket();
	
	
}
