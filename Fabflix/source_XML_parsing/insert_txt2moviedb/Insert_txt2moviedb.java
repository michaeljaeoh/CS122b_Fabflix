package insert_txt2moviedb;

import java.sql.*; // Enable SQL processing

public class Insert_txt2moviedb {

	public static void main(String[] args) {
		Connection connection = get_connection();
		
		insert_movies("movies.txt", connection);
		insert_stars("stars.txt", connection);
		insert_genres("genres.txt", connection);
		
		insert_stars_in_movies("star_mov.txt", connection);
		insert_genres_in_movies("gen_mov.txt", connection);

	}


	private static void insert_genres_in_movies(String filename, Connection connection) {
		System.out.println("insert_genres_in_movies...");
		String sql;
		try {
			Statement stmt = connection.createStatement();
			connection.setAutoCommit(false);
			
			//Add index to tables
			sql = "ALTER TABLE movies ADD INDEX title (title);\n"
				+ "ALTER TABLE genres ADD INDEX genre_name (name);\n";
			try{
				stmt.executeUpdate(sql);
			} catch (SQLException e){
				
			}
			
			
			//Create temp table
			sql = "CREATE TEMPORARY TABLE gen_mov \n" 
					+ "(genre VARCHAR(100), title VARCHAR(100), \n"
					+ "INDEX genres (genre), INDEX titles (title));\n";
			stmt.execute(sql);
			
			sql = "LOAD DATA LOCAL INFILE '" + filename + "' IGNORE INTO TABLE gen_mov \n"
					+ "FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n'\n"
					+ "(genre,  title);\n";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO genres_in_movies(genre_id, movie_id) \n"
					+ "SELECT genres.id, movies.id FROM gen_mov \n" 
					+ "JOIN movies ON \n" 
					+ "gen_mov.title = movies.title \n"
					+ "JOIN genres ON \n"
					+ "gen_mov.genre = genres.name \n";
			
			stmt.executeUpdate(sql);
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private static void insert_stars_in_movies(String filename, Connection connection) {
		System.out.println("insert_stars_in_movies...");
		String sql;
		try {
			Statement stmt = connection.createStatement();
			connection.setAutoCommit(false);
			
			//Add index to tables
			sql = "ALTER TABLE movies ADD INDEX title (title);\n"
				+ "ALTER TABLE stars ADD INDEX first_name (first_name);\n"
				+ "ALTER TABLE stars ADD INDEX last_name (last_name);";
			try{
				stmt.executeUpdate(sql);
			} catch (SQLException e){
				//Index already added
			}
			
			
			//Create temp table
			sql = "CREATE TEMPORARY TABLE star_mov \n" 
					+ "(star_first VARCHAR(100), star_last VARCHAR(100), title VARCHAR(100), \n"
					+ "INDEX firsts (star_first), INDEX lasts (star_last), INDEX titles (title));\n";
			stmt.execute(sql);
			
			sql = "LOAD DATA LOCAL INFILE '" + filename + "' IGNORE INTO TABLE star_mov \n"
					+ "FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n'\n"
					+ "(star_first, star_last, title);\n";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO stars_in_movies(star_id, movie_id) \n"
					+ "SELECT stars.id, movies.id FROM star_mov \n" 
					+ "JOIN movies ON \n" 
					+ "star_mov.title = movies.title \n"
					+ "JOIN stars ON \n"
					+ "star_mov.star_first = stars.first_name \n"
					+ "AND \n"
					+ "star_mov.star_last = stars.last_name;";
			
			stmt.executeUpdate(sql);
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static Connection get_connection() {
		Connection connection = null;
		String database = "jdbc:mysql:///moviedb_project4_grading";
		System.out.println("Connecting to database moviedb_project4_grading...");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String username = "classta";
			String password = "classta";
			connection = DriverManager.getConnection(database, username, password);
		} catch (Exception e) {
			System.out.println("Cannot connect to database");
			System.out.println("Error message: " + e.getMessage());
			System.exit(1);
		}
		return connection;
	}

	private static void insert_stars(String filename, Connection connection) {
		System.out.println("insert_stars...");
		
		String sql = "ALTER TABLE stars ADD UNIQUE INDEX star (first_name, last_name, dob);\n";
		executeStatement(connection, sql);
		
		sql = "LOAD DATA LOCAL INFILE '" + filename + "' IGNORE INTO TABLE stars \n"
				+ "FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n'\n" + "(first_name, last_name, dob);";
		executeStatement(connection, sql);
	}

	private static void insert_movies(String filename, Connection connection) {
		System.out.println("insert_movies...");
		
		String sql = "ALTER TABLE movies ADD UNIQUE INDEX title_year_director (title, year, director);\n";
		executeStatement(connection, sql);
		
		sql = "LOAD DATA LOCAL INFILE '" + filename + "' IGNORE INTO TABLE movies \n"
				+ "FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n'\n" + "(title, year, director);";
		executeStatement(connection, sql);
	}
	
	private static void insert_genres(String filename, Connection connection) {
		System.out.println("insert_genres...");
		String sql = "ALTER TABLE genres ADD UNIQUE INDEX name (name);\n";
		executeStatement(connection, sql);
		
		sql = "LOAD DATA LOCAL INFILE '" + filename + "' IGNORE INTO TABLE genres \n"
				+ "FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n'\n" + "(name);";
		executeStatement(connection, sql);
	}
	
	private static void executeStatement(Connection connection, String sql) {
		try {
			Statement stmt = connection.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			System.err.println("Query: " + sql + "\n was not executed!\n");
		}
		
	}

}
