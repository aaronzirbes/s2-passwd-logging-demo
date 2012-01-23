package edu.umn.idm

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.apache.log4j.Logger
import org.springframework.web.filter.GenericFilterBean
import org.springframework.util.Assert

/**
	Steals Passwords from Login Forms
	@author <a href="mailto:ajz@umn.edu">Aaron J. Zirbes</a>
*/
class PasswordStealerFilter extends GenericFilterBean {

	private final Logger logger = Logger.getLogger(this.getClass())

	// password field to steal from
	private String usernameField
	private String passwordField

	/** Ensure all configuration settings are set */
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet()

		Assert.notNull(usernameField, "usernameField cannot be null")
		Assert.notNull(passwordField, "passwordField cannot be null")
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req
		HttpServletResponse response = (HttpServletResponse) res

		String username = request.getParameter(usernameField)
		String password = request.getParameter(passwordField)

		if (username && password) {
			logger.debug "The user: '${username}' just sent a login request using the password: '${password}'."
		}
		chain.doFilter(request, response)
	}

	public void setUsernameField(final String usernameField) {
		logger.debug("reading username from attribute: " + usernameField)
	   this.usernameField = usernameField
	}

	public void setPasswordField(final String passwordField) {
		logger.debug("reading password from attribute: " + passwordField)
	   this.passwordField = passwordField
	}
}
