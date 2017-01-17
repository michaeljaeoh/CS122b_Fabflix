package website.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (!(request instanceof HttpServletRequest)
				|| !("POST".equalsIgnoreCase(((HttpServletRequest) request).getMethod()))) {
			chain.doFilter(request, response);
			return;
		}

		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();

		// Allow access to login functionality.
		if (servletPath.equals("/login")) {
			chain.doFilter(req, resp);
			return;
		}

		// All other functionality requires authentication.
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		if (email != null) {
			// User is logged in.
			chain.doFilter(req, resp);
			return;
		}

		// Request is not authorized.
		resp.sendRedirect("login");
		return;
	}
}