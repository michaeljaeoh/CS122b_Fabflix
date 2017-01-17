package website.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GenreDAO {
	public List<Genre> genreList() throws Exception {
		Connection connection = null;
		ResultSet resultSet = null;

		List<Genre> result = new ArrayList<Genre>();

		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			connection = ds.getConnection();

			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from genres");

			while (resultSet.next()) {
				Genre genre = new Genre();
				genre.setId(resultSet.getInt("id"));
				genre.setName(resultSet.getString("name"));

				result.add(genre);

			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (result != null) {
				try {
					resultSet.close();
				} catch (Exception ex) {

				}
				;
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception ex) {

				}
				;
			}
		}

		return result;
	}

	public List<Genre> findGenresFromMovie(String query) throws Exception {
		List<Genre> result = new ArrayList<Genre>();

		String sql = "select * from genres where id in (select genre_id from genres_in_movies where movie_id in (select id from movies where title = ?));";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			connection = ds.getConnection();
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, query);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Genre genre = new Genre();
				genre.setId(resultSet.getInt("id"));
				genre.setName(resultSet.getString("name"));
				result.add(genre);

			}

		} catch (SQLException e) {
			e.printStackTrace();
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

		return result;
	}

}
