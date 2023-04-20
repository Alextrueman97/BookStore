package com.bookstore.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Books {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private String bookTitle;
	private String bookAuthor;
	private double bookPrice;
	@Lob
	private String bookDescription; //lob is used for larger text files
	@OneToMany(mappedBy = "bookId")
	private List<OrderHistory> orderHistory;
	@Lob
	private String bookImageLink;
	
	public Books() {
		super();
	}

	public Books(int bookId, String bookTitle, String bookAuthor, double bookPrice, String bookDescription, String bookImageLink) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookPrice = bookPrice;
		this.bookDescription = bookDescription;
		this.bookImageLink = bookImageLink;
		
	}

	public Books(String bookTitle, String bookAuthor, double bookPrice, String bookDescription, String bookImageLink) {
		super();
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookPrice = bookPrice;
		this.bookDescription = bookDescription;
		this.bookImageLink = bookImageLink;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public List<OrderHistory> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(List<OrderHistory> orderHistory) {
		this.orderHistory = orderHistory;
	}
	
	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
	
	public String getBookImageLink() {
		return bookImageLink;
	}

	public void setBookImageLink(String bookImageLink) {
		this.bookImageLink = bookImageLink;
	}

	@Override
	public String toString() {
		return "Books [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor + ", bookPrice="
				+ bookPrice + ", bookDescription=" + bookDescription + ", orderHistory=" + orderHistory
				+ ", bookImageLink=" + bookImageLink + "]";
	}
	
	

}
