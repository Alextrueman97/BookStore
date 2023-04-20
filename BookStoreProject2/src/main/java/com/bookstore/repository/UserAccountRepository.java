package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.models.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	@Query(value = "select new UserAccount(ua.accountId, ua.userName, ua.email, ua.password, ua.firstName, ua.lastName) from UserAccount ua where ua.userName = :userName and ua.password = :password")
	public UserAccount login(@Param("userName")String userName, @Param("password")String password);
	
	
	    UserAccount findByUserNameAndPassword(String userName, String password);
}
