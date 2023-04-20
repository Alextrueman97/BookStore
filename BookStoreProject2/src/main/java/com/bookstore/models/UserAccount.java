package com.bookstore.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Auto generate
	private int accountId;
	@Column(unique= true)
	private String userName;
	@Column(unique= true)
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	@OneToMany(mappedBy = "accountId")
	private List<OrderHistory> orderHistory;
	
	
	public UserAccount() {
		super();
	}

	public UserAccount(int accountId, String userName, String email, String password, String firstName,
			String lastName) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserAccount(String userName, String email, String password, String firstName, String lastName) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<OrderHistory> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(List<OrderHistory> orderHistory) {
		this.orderHistory = orderHistory;
	}
	
	@Override
	public String toString() {
		return "UserAccount [accountId=" + accountId + ", userName=" + userName + ", email=" + email + ", password="
				+ password + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
