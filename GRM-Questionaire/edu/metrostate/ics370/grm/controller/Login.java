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

	private static Connection con;
	private static User user;
	/**
	 * No-arg constructor
	 */
	public Login() {

	}	
	
	/**
	 * Opens login GUI, gets credentials, signs in
	 * 
	 * If sign in successful, open menu
	 * 
	 * Otherwise display error and allow user to sign in again
	 * 
	 * @return {@code true} if sign in is successful 
	 * @throws SQLException 
	 */
	public static boolean signIn(String username, String password) throws SQLException {
		// get datbase connection with credentials
		con = Connector.signIn();
		user = Connector.getUser(username, password);
		// sign into database
		Statement st = con.createStatement();
		ResultSet userRS = st.executeQuery("SELECT * FROM User WHERE username = '" + username + "' AND user_password = '" + password + "'");
		// set user
		user = parseUser(userRS);
		// return false if connection or user is null
		if (!(con == null || user == null)) {
			return true;
		}
		return false;
	}

	private static User parseUser(ResultSet userRS) throws SQLException {
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
		return user;
	}

	/**
	 * @return database connection
	 */
	public static Connection getCon() {
		return con;
	}

	/**
	 * @return the user
	 */
	public static User getUser() {
		return user;
	}
}
