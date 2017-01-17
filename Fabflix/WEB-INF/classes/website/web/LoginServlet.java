package website.web;

import java.io.IOException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//import publisher.data.User;
//import publisher.data.UserDAO;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	// private RequestDispatcher jsp;
	/*
	 * public void init(ServletConfig config) throws ServletException {
	 * ServletContext context = config.getServletContext(); jsp =
	 * context.getRequestDispatcher("/jsp/login.jsp"); }
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String passWord = req.getParameter("password");

		String sql = "select * from customers where email = ? and password = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean login = false;

		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			connection = ds.getConnection();

			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, passWord);
			resultSet = statement.executeQuery();
			
			login = resultSet.first();
		} catch (Exception e) {
			throw new ServletException("Login failed for some reason", e);
		} finally {
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
				}
		}

		if (login) {
			req.getSession().setAttribute("email", email);
			resp.sendRedirect("home"); // Redirect to home page.
		} else {
			// req.setAttribute("message", "Unknown username/password, try
			// again"); // This sets the ${message}
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}