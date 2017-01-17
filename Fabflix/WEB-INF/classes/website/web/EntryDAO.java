package website.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class EntryDAO
{
	
	public List<Entry> findMovieByAutoTitle(String query)
	{

		List<Entry> result = new ArrayList<Entry>();
		
    	String sql = "select * from movies where match (title) against (?);";

    	Connection connection = null;
    	PreparedStatement statement = null;
    	ResultSet resultSet = null;    	
    	
		
    	try 
    	{
    		Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			connection = ds.getConnection();
			query.trim().toLowerCase();
    	    statement = connection.prepareStatement(sql);
    	    statement.setString(1, "+" + query + "* IN BOOLEAN MODE");
    	    
    	    resultSet = statement.executeQuery();
    	    while(resultSet.next())
			{
    	    	Entry entry = new Entry();
				entry.setId(resultSet.getInt("id"));
				entry.setTitle(resultSet.getString("title"));
				entry.setYear(resultSet.getString("year"));
				entry.setDirector(resultSet.getString("director"));
				entry.setBanner(resultSet.getString("banner_url"));
				entry.setTrailer(resultSet.getString("trailer_url"));
				entry.setStarList(new StarDAO().findStarsFromMovies(resultSet.getString("title")));
				entry.setGenreList(new GenreDAO().findGenresFromMovie(resultSet.getString("title")));
				result.add(entry);
				
			}
    	    
    	    
    	} 
    	catch (Exception e) 
    	{
    		System.out.println("error: " + query + " " + e);
    	} 
    	finally 
    	{
    	    if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
    	    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
    	    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
    	}
    	
    	return result;

	}
	
	public Entry findSingleMovie(String id)
	{
		
		Connection connection = null;
		ResultSet resultSet = null;
		Entry result = null;
		PreparedStatement statement = null;
		
    	String sql = "select * from movies where id = ?";

    	
        	
    	
		
    	try 
    	{
    		Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			connection = ds.getConnection();
			
    	    statement = connection.prepareStatement(sql);
    	    statement.setString(1, id);
    	    
    	    resultSet = statement.executeQuery();
    	    while(resultSet.next())
			{
    	    	result = new Entry();
    	    	result.setId(resultSet.getInt("id"));
    	    	result.setTitle(resultSet.getString("title"));
    	    	result.setYear(resultSet.getString("year"));
    	    	result.setDirector(resultSet.getString("director"));
    	    	result.setBanner(resultSet.getString("banner_url"));
    	    	result.setTrailer(resultSet.getString("trailer_url"));
    	    	result.setStarList(new StarDAO().findStarsFromMovies(resultSet.getString("title")));
    	    	result.setGenreList(new GenreDAO().findGenresFromMovie(resultSet.getString("title")));
				
			}
    	    
    	    
    	} 
    	catch (Exception e) 
    	{
    		System.out.println(e);
    	} 
    	finally 
    	{
    	    if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
    	    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
    	    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
    	}
    	
    	return result;
		
	}
		
		
		public List<Entry> findMoviesFromGenre(String query) throws Exception
		{
			List<Entry> result = new ArrayList<Entry>();
			
	    	String sql = "select * from movies where id in (select movie_id from genres_in_movies where genre_id in (select id from genres where name = ?));";

	    	Connection connection = null;
	    	PreparedStatement statement = null;
	    	ResultSet resultSet = null;    	
	    	
			
	    	try 
	    	{
	    		Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
				connection = ds.getConnection();
				
	    	    statement = connection.prepareStatement(sql);
	    	    statement.setString(1, query);
	    	    
	    	    resultSet = statement.executeQuery();
	    	    while(resultSet.next())
				{
	    	    	Entry entry = new Entry();
					entry.setId(resultSet.getInt("id"));
					entry.setTitle(resultSet.getString("title"));
					entry.setYear(resultSet.getString("year"));
					entry.setDirector(resultSet.getString("director"));
					entry.setBanner(resultSet.getString("banner_url"));
					entry.setTrailer(resultSet.getString("trailer_url"));
					entry.setStarList(new StarDAO().findStarsFromMovies(resultSet.getString("title")));
					entry.setGenreList(new GenreDAO().findGenresFromMovie(resultSet.getString("title")));
					result.add(entry);
					
				}
	    	    
	    	    
	    	} 
	    	catch (Exception e) 
	    	{
	    		System.out.println(e);
	    	} 
	    	finally 
	    	{
	    	    if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
	    	    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	    	    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    	}
	    	
	    	return result;

		}
		
		public List<Entry> findMoviesByTitle(String query) throws Exception
		{
			List<Entry> result = new ArrayList<Entry>();
			
	    	String sql = "select * from movies where title like ?";

	    	Connection connection = null;
	    	PreparedStatement statement = null;
	    	ResultSet resultSet = null;    	
	    	
			
	    	try 
	    	{
	    		Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
				connection = ds.getConnection();
				
	    	    statement = connection.prepareStatement(sql);
	    	    statement.setString(1, "%" + query + "%");
	    	    
	    	    resultSet = statement.executeQuery();
	    	    while(resultSet.next())
				{
	    	    	Entry entry = new Entry();
					entry.setId(resultSet.getInt("id"));
					entry.setTitle(resultSet.getString("title"));
					entry.setYear(resultSet.getString("year"));
					entry.setDirector(resultSet.getString("director"));
					entry.setBanner(resultSet.getString("banner_url"));
					entry.setTrailer(resultSet.getString("trailer_url"));
					entry.setStarList(new StarDAO().findStarsFromMovies(resultSet.getString("title")));
					entry.setGenreList(new GenreDAO().findGenresFromMovie(resultSet.getString("title")));
					result.add(entry);
					
				}
	    	    
	    	    
	    	} 
	    	catch (Exception e) 
	    	{
	    		System.out.println(e);
	    	} 
	    	finally 
	    	{
	    	    if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
	    	    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	    	    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    	}
	    	
	    	return result;

		}
		
		
		public List<Entry> findMovieBySearch(String title, String year, String director, String firstName, String lastName) throws ServletException
		{

			List<Entry> result = new ArrayList<Entry>();

	        String sql = "select * from stars_in_movies join movies on movies.id = stars_in_movies.movie_id join stars on stars_in_movies.star_id = stars.id"
	                   + " where title like ? or year = ? or director like ? or first_name like ? or last_name like ?";
	        Connection connection = null;
	        PreparedStatement statement = null; 
	        ResultSet resultSet = null;

	        try 
	        {
	        	
	        	Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
				connection = ds.getConnection();
				
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, "%" + title + "%");
	            statement.setString(2, "%" + year + "%");
	            statement.setString(3, "%" + director + "%");
	            statement.setString(4, "%" + firstName + "%");
	            statement.setString(5, "%" + lastName + "%");

	            resultSet = statement.executeQuery();
	            
	            while(resultSet.next())
	            {
	            	Entry entry = new Entry();
					entry.setId(resultSet.getInt("id"));
					entry.setTitle(resultSet.getString("title"));
					entry.setYear(resultSet.getString("year"));
					entry.setDirector(resultSet.getString("director"));
					entry.setBanner(resultSet.getString("banner_url"));
					entry.setTrailer(resultSet.getString("trailer_url"));
					entry.setStarList(new StarDAO().findStarsFromMovies(resultSet.getString("title")));
					entry.setGenreList(new GenreDAO().findGenresFromMovie(resultSet.getString("title")));
					result.add(entry);
	            }
	            //return [star_id, movie_id, id, title, year, director, banner_url, trailer_url, id, first_name, last_name, dod, photo_url]
	            //*id is just movie_id
	        } 
	        catch (Exception e) 
	        {
	            throw new ServletException("My SQL search failed...", e);
	        } 
	        finally 
	        {
	            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
	            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	        }
	        
	        return result;
		}
		
		public List<Entry> findSingleMovieList(String id) {

			Connection connection = null;
			ResultSet resultSet = null;
			List<Entry> result = new ArrayList<Entry>();
			PreparedStatement statement = null;

			String sql = "select * from movies where id = ?";

			try {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
				connection = ds.getConnection();

				statement = connection.prepareStatement(sql);
				statement.setString(1, id);

				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Entry entry = new Entry();
					entry.setId(resultSet.getInt("id"));
					entry.setTitle(resultSet.getString("title"));
					entry.setYear(resultSet.getString("year"));
					entry.setDirector(resultSet.getString("director"));
					entry.setBanner(resultSet.getString("banner_url"));
					entry.setTrailer(resultSet.getString("trailer_url"));
					entry.setStarList(new StarDAO().findStarsFromMovies(resultSet.getString("title")));
					entry.setGenreList(new GenreDAO().findGenresFromMovie(resultSet.getString("title")));
					result.add(entry);
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
}
