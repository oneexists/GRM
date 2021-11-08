package edu.metrostate.ics370.grm.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	 * Creates new user
	 * @param password 
	 * @param username 
	 */
	public static boolean newUser(String username, String password) {
		boolean validCredentials = validateUser(username, password);
		
		if (validCredentials == true) {
			String pSql = "INSERT INTO User(username, user_password) VALUES (?, ?)";
			try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
					) {
				pStmt.setString(1, username);
				pStmt.setString(2, password);
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
	private static boolean validateUser(String username, String password) {
		// TODO validate username and password
		return true;
	}

	/**
	 * Validates username and password using database connection
	 * 
	 * @param username
	 * @param password
	 * @return {@code true} iff valid username and password
	 * @throws SQLException
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
