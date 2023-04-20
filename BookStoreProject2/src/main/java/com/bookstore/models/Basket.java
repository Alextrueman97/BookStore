package com.bookstore.models;

import java.util.ArrayList;
import java.util.List;

public class Basket {

	private List<Books> books;
	private double totalPrice;
	
	public Basket() {
		books = new ArrayList<>();
		totalPrice = 0.0;
	}
	
	public List<Books> getBooks(){
		return books;
	}
	
	public void setBooks(List<Books> books) {
		this.books = books;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void addItem(Books book) {
		books.add(book);
		totalPrice += book.getBookPrice();
	}
	
	public void removeItem(int index) {
		Books book = books.remove(index);
		totalPrice -= book.getBookPrice();
	}
	
	public void clear() {
		books.clear();
		totalPrice = 0.0;
	}
	
	public double calculateTotalPrice() {
		double totalPrice = 0.0;
		for(Books book : books) {
			totalPrice += book.getBookPrice();
		}
		return totalPrice;
	}
	
	public int getQuantity(Books book) {
		if(book == null) {
			return 0;
		}
		int count = 0;
		for (Books b : books) {
			if(b.getBookId() == book.getBookId()) {
				count++;
			}
		}
		return count;
	}
}
