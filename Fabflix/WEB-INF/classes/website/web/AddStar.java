package website.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


@SuppressWarnings("serial")
public class AddStar extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String first = req.getParameter("first");
		if(first.isEmpty()) first = "''";
		
		String last = req.getParameter("last");
		if(last.isEmpty()){
			req.setAttribute("message", "Add Failed");
			req.getRequestDispatcher("/jsp/dashboard_tools.jsp").forward(req, resp);
			return;
		}
		
		String dob = req.getParameter("dob");
		String photo_url = req.getParameter("photo_url");
		
		String sql = "insert into stars (first_name, last_name, dob, photo_url) values (?, ?, ?, ?)";
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			Connection connection = ds.getConnection();

			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, first);
			statement.setString(2, last);
			if(dob.isEmpty())
				statement.setNull(3, Types.DATE);
			else
				statement.setString(3, dob);
			if(photo_url.isEmpty())
				statement.setNull(4, Types.VARCHAR);
			else
				statement.setString(4, photo_url);
			
			statement.executeUpdate();
			
		} catch(Exception e){
			req.setAttribute("message", "Add Failed!");
			req.getRequestDispatcher("/jsp/dashboard_tools.jsp").forward(req, resp);
			throw new ServletException("failed for some reason", e);
		}
		req.setAttribute("message", "Add Success!");
		req.getRequestDispatcher("/jsp/dashboard_tools.jsp").forward(req, resp);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String dashboard = (String) session.getAttribute("dashboard");
		if(dashboard == null)
			req.getRequestDispatcher("/jsp/dashboard.jsp").forward(req, resp);
		doGet(req, resp);
	}
}
