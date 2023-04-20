package com.bookstore.service;

import com.bookstore.models.UserAccount;

public interface UserAccountService {

	public UserAccount login(String userName, String password);
	
	public UserAccount register(UserAccount userAccount);

	boolean updatePassword(String userName, String oldPassword, String newPassword, String confirmNewPassword);
}
