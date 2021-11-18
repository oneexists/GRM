package edu.metrostate.ics370.grm.controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import edu.metrostate.ics370.grm.model.User;

/**
 * Controller class provides new user functionality, signs into and out of the database.
 * DAO for User class
 * 
 * @author skylar
 */
public abstract class Login {

	public static User user;
	/**
	 * No-arg constructor
	 */
	public Login() {
	}	
	
	/**
	 * Creates new user
	 * 
	 * @param password password of the new user
	 * @param username username of the new user
	 * @return {@code true} if the user was added to the database
	 */
	public static boolean newUser(String username, String password, String dob) {
		boolean validCredentials = validateUser(username, password, dob);
		
		if (validCredentials == true) {
			String pSql = "INSERT INTO User(username, user_password, user_date_of_birth) VALUES (?, ?, ?)";
			try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
					) {
				pStmt.setString(1, username);
				pStmt.setString(2, password);
				pStmt.setDate(3, Date.valueOf(LocalDate.parse(dob, DateTimeFormatter.ofPattern("MM/dd/uuuu"))));
				int added = pStmt.executeUpdate();
				// verify user is added
				if (added == 1) {
					Connector.getInstance().close();
					return true;
				} 
			} catch (SQLException e) {
				Connector.processException(e);
			}
			// user not added
			return false;	
		} else {
			// invalid user credentials
			return false;
		}
		
	}
	
	// validates newUser credentials
	private static boolean validateUser(String username, String password, String dob) {
		// TODO validate username and password
		// TODO validate dob
		return true;
	}

	/**
	 * Validates username and password using database connection
	 * 
	 * @param username username to search
	 * @param password password to search
	 * @return {@code true} iff valid username and password
	 * @throws SQLException exception from the database
	 */
	public static boolean signIn(String username, String password) throws SQLException {
		String pSql = "SELECT username, user_password, user_first_name, user_last_name, user_date_of_birth, gender FROM User WHERE username = ? AND user_password = ?";
		try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
				ResultSet rs = executeStmt(pStmt, username, password);
				) {
			parseUser(rs);
			if (user.getUsername() != null) {
				return true; 				
			} else { return false; }
		} catch (SQLException e) {
			Connector.processException(e);
			return false;
		}
	}
	
	// sets parameters in PreparedStatement and executes query
	private static ResultSet executeStmt(PreparedStatement pStmt, String username, String password) throws SQLException {
		pStmt.setString(1, username);
		pStmt.setString(2, password);
		return pStmt.executeQuery();
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
			// set dob if not null
			if (userRS.getString("user_date_of_birth") != null) {
				dateOfBirth = LocalDate.parse(userRS.getString("user_date_of_birth"));				
			}
			// set gender if not null
			if (userRS.getString("gender") != null) {
				gender = User.Gender.valueOf(userRS.getString("gender"));				
			}
		}
		user = new User(username, firstName, lastName, dateOfBirth, gender);
	}
}
