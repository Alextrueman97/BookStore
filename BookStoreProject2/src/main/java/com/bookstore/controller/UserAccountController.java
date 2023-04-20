package com.bookstore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookstore.models.UserAccount;
import com.bookstore.service.UserAccountServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserAccountController {

	@Autowired
	private UserAccountServiceImpl userAccountServiceImpl;
	
	@GetMapping("/Register")
	public String reg() {
		return "Register";
	}
	
	@PostMapping("/Register")
	public String registerAccount(@RequestParam("userName") String userName,
	                              @RequestParam("email") String email,
	                              @RequestParam("password") String password,
	                              @RequestParam("confirmPassword") String confirmPassword,
	                              @RequestParam("firstName") String firstName,
	                              @RequestParam("lastName") String lastName,
	                              RedirectAttributes redirectAttributes) {
	    if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
	        redirectAttributes.addFlashAttribute("message", "All fields are required");
	        return "redirect:/Register";
	    }

	    if (!password.equals(confirmPassword)) {
	        redirectAttributes.addFlashAttribute("message", "Passwords do not match");
	        return "redirect:/Register";
	    }

	    UserAccount ua = new UserAccount(userName, email, password, firstName, lastName);
	    try {
	        UserAccount savedInfo = userAccountServiceImpl.register(ua);
	        return "redirect:/login";
	    } catch(DataIntegrityViolationException ex) {
	        redirectAttributes.addFlashAttribute("message", "Account Not Created Please try again!");
	        return "redirect:/Register";
	    }
	}
	
	@GetMapping("/login")
	public String log() {
		return "Login";
	}
	
	@PostMapping("/Login")
	public String login(@RequestParam("userName") String userName,
					    @RequestParam("password") String password,
					    HttpServletRequest request,
					    Model model, RedirectAttributes redirectAttributes) {
		UserAccount loggedInUser = this.userAccountServiceImpl.login(userName, password);
		if(loggedInUser != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", loggedInUser);
			session.setAttribute("username", userName);
			return  "redirect:/Home";
		}else {
			//model.addAttribute("error", "Invalid username or password");
			redirectAttributes.addFlashAttribute("message", "Login username or password incorrect!");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/Account")
	public String showAccountPage(Model model, HttpSession session) {
	    UserAccount user = (UserAccount) session.getAttribute("user");
	    model.addAttribute("user", user);
	    return "Account";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
	    session.invalidate();
	    redirectAttributes.addFlashAttribute("message", "Successfully logged out!");
	    return "redirect:/Home";
	}
	
	@GetMapping("/passwordChange")
	public String changePassword(HttpSession session) {
		UserAccount user = (UserAccount) session.getAttribute("user");
		return "passwordChange";
	}
	
	@PostMapping("/updatePassword")
    public String updatePassword(@RequestParam("oldPassword") String oldPassword,
                                  @RequestParam("newPassword") String newPassword,
                                  @RequestParam("confirmNewPassword") String confirmNewPassword,
                                  HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String username = (String) request.getSession().getAttribute("username");
        boolean updated = userAccountServiceImpl.updatePassword(username, oldPassword, newPassword, confirmNewPassword);
        if (updated) {
        	redirectAttributes.addFlashAttribute("message", "Password successfully updated!");
            return "redirect:/Account";
        } else {
        	redirectAttributes.addFlashAttribute("message", "Old password incorrect or new passwords do not match! please try again!");
            return "redirect:/passwordChange";
        }
	}
}

