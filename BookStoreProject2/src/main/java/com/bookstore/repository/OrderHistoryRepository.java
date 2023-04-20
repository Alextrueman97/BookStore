package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.models.Books;
import com.bookstore.models.OrderHistory;
import com.bookstore.models.UserAccount;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer>{

	
	
	 OrderHistory findByAccountIdAndBookId(UserAccount accountId, Books bookId);

	 List<OrderHistory> findByAccountId(UserAccount user);
	 
	 OrderHistory findByAccountIdAndBookIdAndCheckoutId(UserAccount user, Books book, String checkoutId);

	 //trsting
	List<OrderHistory> findByAccountIdAndCheckoutId(UserAccount user, String checkoutId);
	 
	 

}
