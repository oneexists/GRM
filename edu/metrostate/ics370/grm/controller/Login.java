package edu.metrostate.ics370.grm.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import edu.metrostate.ics370.grm.model.User;

/**
 * @author skylar
 *
 */
public abstract class Login {

	public static User user;
	/**
	 * No-arg constructor
	 */
	public Login() {
	}	
	
	/**
	 * Validates username and password using database connection
	 * 
	 * @param username
	 * @param password
	 * @return {@code true}
	 * @throws SQLException
	 */
	public static boolean signIn(String username, String password) throws SQLException {
		String sql = "SELECT username, user_password, user_first_name, user_last_name, user_date_of_birth, gender FROM User WHERE username =\"" + username + "\" AND user_password = \"" + password + "\"";
		try (	Connection con = Connector.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {
			parseUser(rs);
			if (user.getUsername() != null) {
				return true; 				
			} else { return false; }
		} catch (SQLException e) {
			Connector.processException(e);
			return false;
		} finally {
			Connector.close();
		}
	}
	
	/**
	 * Signs out user and returns true when done
	 * 
	 * @return {@code true} when user is null
	 */
	public static boolean signOut() {
		if (user != null) {
			user = null;
		}
		return true;
	}
	
	// creates and sets user from result set
	private static void parseUser(ResultSet userRS) throws SQLException {
		String username = null;
		String firstName = null;
		String lastName = null;
		LocalDate dateOfBirth = null;
		User.Gender gender = null;
		while (userRS.next()) {
			username = userRS.getString("username");
			firstName = userRS.getString("user_first_name");
			lastName = userRS.getString("user_last_name");
			dateOfBirth = LocalDate.parse(userRS.getString("user_date_of_birth"));
			gender = User.Gender.valueOf(userRS.getString("gender"));
		}
		user = new User(username, firstName, lastName, dateOfBirth, gender);
	}
}
