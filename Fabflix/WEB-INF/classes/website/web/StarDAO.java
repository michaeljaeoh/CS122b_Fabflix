package website.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StarDAO {

	public Star findStar(String id) {

		Connection connection = null;
		ResultSet resultSet = null;
		Star result = null;
		PreparedStatement statement = null;

		String sql = "select * from stars where id = ?";

		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			connection = ds.getConnection();

			statement = connection.prepareStatement(sql);
			statement.setString(1, id);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result = new Star();
				result.setId(resultSet.getInt("id"));
				result.setFirstName(resultSet.getString("first_name"));
				result.setLastName(resultSet.getString("last_name"));
				result.setDob(resultSet.getString("dob"));
				result.setPhoto_url(resultSet.getString("photo_url"));

			}

		} catch (Exception e) {
			System.out.println(e);
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

	public List<Star> findStarsFromMovies(String query) throws Exception {
		List<Star> result = new ArrayList<Star>();

		String sql = "select * from stars where id in (select star_id from stars_in_movies where"
				+ " movie_id in (select id from movies where title = ?));";

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
				Star star = new Star();
				star.setId(resultSet.getInt("id"));
				star.setFirstName(resultSet.getString("first_name"));
				star.setLastName(resultSet.getString("last_name"));
				star.setDob(resultSet.getString("dob"));
				star.setPhoto_url(resultSet.getString("photo_url"));
				result.add(star);

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
