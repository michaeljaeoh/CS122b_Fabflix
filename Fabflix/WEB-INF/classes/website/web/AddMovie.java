package website.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@SuppressWarnings("serial")
public class AddMovie extends HomeServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String title = req.getParameter("title");
		String year = req.getParameter("year");
		String director = req.getParameter("director");
		String banner_url = req.getParameter("banner_url");
		String trailer_url = req.getParameter("trailer_url");
		String first = req.getParameter("first");
		String last = req.getParameter("last");
		String genre = req.getParameter("genre");

		String sql1 = "INSERT INTO movies (title, year, director, banner_url, trailer_url) SELECT ?, ?, ?, ?, ? FROM movies"
				+ " WHERE NOT EXISTS (SELECT * FROM movies WHERE title=? and year=?) LIMIT 1";

		String sql2 = "INSERT INTO stars (first_name, last_name) SELECT ?, ? FROM stars"
				+ " WHERE NOT EXISTS (SELECT * FROM stars WHERE first_name =? AND last_name =?) LIMIT 1";

		String sql3 = "INSERT INTO genres (name) SELECT ? FROM genres"
				+ " WHERE NOT EXISTS (SELECT * FROM genres WHERE name=?) LIMIT 1";

		String sql4 = "SELECT movie.id, star.id, genre.id FROM movies AS movie, stars AS star, genres AS genre"
				+ " WHERE movie.title=? AND movie.year=? AND star.first_name=? AND star.last_name=? AND genre.name=?";

		String sql5 = "INSERT INTO stars_in_movies  (star_id, movie_id) SELECT ?, ? FROM stars_in_movies"
				+ " WHERE NOT EXISTS (SELECT * FROM stars_in_movies WHERE star_id=? and movie_id=?) LIMIT 1";

		String sql6 = "INSERT INTO genres_in_movies  (genre_id, movie_id) SELECT ?, ? FROM genres_in_movies"
				+ " WHERE NOT EXISTS (SELECT * FROM genres_in_movies WHERE genre_id=? and movie_id=?) LIMIT 1";

		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			Connection connection = ds.getConnection();

			PreparedStatement statement = connection.prepareStatement(sql1);

			statement.setString(1, title);
			statement.setInt(2, Integer.parseInt(year));
			statement.setString(3, director);
			statement.setString(4, banner_url);
			statement.setString(5, trailer_url);
			statement.setString(6, title);
			statement.setInt(7, Integer.parseInt(year));
			statement.executeUpdate();

			statement = connection.prepareStatement(sql2);
			statement.setString(1, first);
			statement.setString(2, last);
			statement.setString(3, first);
			statement.setString(4, last);
			statement.executeUpdate();

			statement = connection.prepareStatement(sql3);
			statement.setString(1, genre);
			statement.setString(2, genre);
			statement.executeUpdate();

			statement = connection.prepareStatement(sql4);
			statement.setString(1, title);
			statement.setInt(2, Integer.parseInt(year));
			statement.setString(3, first);
			statement.setString(4, last);
			statement.setString(5, genre);

			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				int movie_id = resultSet.getInt(1);
				int star_id = resultSet.getInt(2);
				int genre_id = resultSet.getInt(3);

				statement = connection.prepareStatement(sql5);
				statement.setInt(1, star_id);
				statement.setInt(2, movie_id);
				statement.setInt(3, star_id);
				statement.setInt(4, movie_id);
				statement.executeUpdate();
	
				statement = connection.prepareStatement(sql6);
				statement.setInt(1, genre_id);
				statement.setInt(2, movie_id);
				statement.setInt(3, genre_id);
				statement.setInt(4, movie_id);
				statement.executeUpdate();
			}

		} catch (Exception e) {
			req.setAttribute("message", "Add failed!");
			req.getRequestDispatcher("/jsp/dashboard_tools.jsp").forward(req, resp);
			throw new ServletException("failed for some reason", e);
		}
		req.setAttribute("message", "Add Success!");
		req.getRequestDispatcher("/jsp/dashboard_tools.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String dashboard = (String) session.getAttribute("dashboard");
		if (dashboard == null)
			req.getRequestDispatcher("/jsp/dashboard.jsp").forward(req, resp);
		doGet(req, resp);
	}
}
