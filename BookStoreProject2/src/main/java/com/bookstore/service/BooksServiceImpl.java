package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.models.Basket;
import com.bookstore.models.Books;
import com.bookstore.models.UserAccount;
import com.bookstore.repository.BooksRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public List<Books> showAllBooks(Books books) {
		return booksRepository.showAllBooks(books);
	}

	@Override
	public Books showBookInfo(int bookId) {
		return booksRepository.showBookInfo(bookId);
	}

	@Override // this may not be needed unless i want a search option feature added and can be changed to search for title rather than Id
	public Optional<Books> findById(int bookId) {
		return booksRepository.findById(bookId);
	}

	@Override
	public Books getBook(int bookId) {
	    Optional<Books> book = booksRepository.findById(bookId);
	    return book.orElse(null);
	}
	
	@Override
	public void addToBasket(int bookId) {
		Books book = getBook(bookId);
		if (book == null) {
			return;
		}
		Basket basket = (Basket) httpSession.getAttribute("basket");
		if(basket == null) {
			basket = new Basket();
		}
		basket.addItem(book);
		httpSession.setAttribute("basket", basket);
	}
	
	@Override
	public Basket getUserBasket() {
		Basket basket = (Basket) httpSession.getAttribute("basket");
		if(basket == null) {
			basket = new Basket();
			httpSession.setAttribute("basket", basket);
		}
		return basket;
	}
	

}
