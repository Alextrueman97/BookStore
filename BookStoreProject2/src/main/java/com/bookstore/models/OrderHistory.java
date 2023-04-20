package com.bookstore.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderHistory {

	@Id
	private String orderHistoryId;
	@ManyToOne
	private UserAccount accountId;
	@ManyToOne
	private Books bookId;
	private int quantity;
	private double totalPrice;
	private LocalDate date;
	private String checkoutId;
	
	
	public OrderHistory() {
		super();
	}

	public OrderHistory(String orderHistoryId, UserAccount accountId, Books bookId, int quantity, double totalPrice, LocalDate date) {
		super();
		this.orderHistoryId = orderHistoryId;
		this.accountId = accountId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.date = date;
	}

	public OrderHistory(UserAccount accountId, Books bookId, int quantity, double totalPrice, LocalDate date, String checkoutId) {
		super();
		this.accountId = accountId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.date = date;
		this.checkoutId = checkoutId;
	}

	public String getOrderHistoryId() {
		return orderHistoryId;
	}

	public void setOrderHistoryId(String orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}

	public UserAccount getAccountId() {
		return accountId;
	}

	public void setAccountId(UserAccount accountId) {
		this.accountId = accountId;
	}

	public Books getBookId() {
		return bookId;
	}

	public void setBookId(Books bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	
	public String getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(String checkoutId) {
		this.checkoutId = checkoutId;
	}

	@Override
	public String toString() {
		return "OrderHistory [orderHistoryId=" + orderHistoryId + ", accountId=" + accountId + ", bookId=" + bookId
				+ ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", date=" + date + ", checkoutId="
				+ checkoutId + "]";
	}
	
	
}
