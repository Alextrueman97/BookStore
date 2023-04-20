package com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.models.UserAccount;
import com.bookstore.repository.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Override
	public UserAccount login(String userName, String password) {
		return userAccountRepository.login(userName, password);
	}
	
	@Override
	public UserAccount register(UserAccount userAccount) {
		return userAccountRepository.save(userAccount);
	}
	
	@Override
	public boolean updatePassword(String userName, String oldPassword, String newPassword, String confirmNewPassword) {
        UserAccount user = userAccountRepository.findByUserNameAndPassword(userName, oldPassword);
        if (user == null) {
            return false; // old password is incorrect
        }
        if (!newPassword.equals(confirmNewPassword)) {
            return false; // new password and confirm new password do not match
        }
        user.setPassword(newPassword);
        userAccountRepository.save(user);
        return true; // password updated successfully
    }

}
