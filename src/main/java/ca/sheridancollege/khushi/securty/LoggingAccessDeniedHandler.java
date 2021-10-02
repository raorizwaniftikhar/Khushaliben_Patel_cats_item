package ca.sheridancollege.khushi.securty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class LoggingAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		// If there is a bad login this class will log it and deny the access
		// to the secure page by retrieving the error
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			System.out.println(auth.getName() + " was trying to access protected resource: " + request.getRequestURI());
		}

		response.sendRedirect(request.getContextPath() + "/permission-denied");
	}
}