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

@SuppressWarnings("serial")
public class Dashboard extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String passWord = req.getParameter("password");

		String sql = "select * from employees where email = ? and password = ?";

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
			req.getSession().setAttribute("dashboard", email);
			req.getRequestDispatcher("/jsp/dashboard_tools.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/jsp/dashboard.jsp").forward(req, resp);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
