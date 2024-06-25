package com.user.loginapplication.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.user.loginapplication.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping("/index")
	public String welcomePage() {
		return "index";
	}

    @GetMapping("/login")
	public String loginPage() {
		return "login";
	}
    
    @GetMapping("/login_error")
	public String loginPageError(Model model) {
		model.addAttribute("errorMessage", true);
		logger.warn("Invalid username or password to register");
		return "login";
	}

    @GetMapping("/logout")
	public String logoutPage(HttpSession session) {
		logger.info("Customer has logged out.");
		session.invalidate();
		return "redirect:/login";
	}

    @GetMapping("/dashboard")
	public String dashboardPage(HttpSession session, Model model) {
		User returnedUser = (User) session.getAttribute("loggedUser");
		model.addAttribute("user", returnedUser);
		logger.info("Redirecting to dashboard");
		return "dashboard";
	}

    @GetMapping("/manager/dashboard")
	public String adminDashboardPage(HttpSession session, Model model) {
		User returnedUser = (User) session.getAttribute("loggedUser");
		model.addAttribute("user", returnedUser);
		logger.info("Redirecting to manager's dashboard");
		return "manager-dashboard";
	}
}
