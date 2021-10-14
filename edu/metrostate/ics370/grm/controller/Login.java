package edu.metrostate.ics370.grm.controller;

import java.sql.Connection;

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
	 * @return 
	 */
	public static boolean signIn(String username, String password) {
		// get datbase connection with credentials
		con = Connector.signIn(username, password);
		// if connection is successful, get user and return true
		if (con != null) {
			user = Connector.getUser(username);
			return true;
		}
		return false;
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
