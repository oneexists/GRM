package edu.metrostate.ics370.grm.controller;

import java.sql.Connection;

import edu.metrostate.ics370.grm.model.User;

/**
 * @author skylar
 *
 */
public class Login {

	private static Connection con;
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
	 */
	@SuppressWarnings("unused")
	public void openLogin() {
		// TODO open login GUI
		// LoginGUI login = new LoginGUI();
		
		// TODO get user credentials
		String user = null;
		String password = null;
		
		// verify user credentials
		con = Connector.signIn(user, password);
		if (con != null) {
			// TODO get user
			// User user = Connector.getUser(user, password);
			
			// TODO open main menu
			// openMenu(user);
		}
		// TODO add error message to login
	}

	public void openMenu(User user) {
		// TODO
	}

	public static Connection getCon() {
		return con;
	}
}
