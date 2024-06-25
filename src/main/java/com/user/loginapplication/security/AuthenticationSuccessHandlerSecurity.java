package com.user.loginapplication.security;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * This class handles successful authentication events.
 * 
 * @author 
 * @version 1.0
 * @since 2024-04-22
 */
@Component
public class AuthenticationSuccessHandlerSecurity implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private static Logger logger = LogManager.getLogger(AuthenticationSuccessHandlerSecurity.class);

	@Autowired
	HttpSession session;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		PrincipalObject loggedUser = (PrincipalObject) authentication.getPrincipal();
		session.setAttribute("loggedUser", loggedUser.getUser());

		if (loggedUser.getUser().getRole().equals("ROLE_Manager")) {
			logger.info("Manager has logged in");
			redirectStrategy.sendRedirect(request, response, "/manager/dashboard");
		} else {
			logger.info("User has logged in");
			redirectStrategy.sendRedirect(request, response, "/dashboard");
		}
	}

}
