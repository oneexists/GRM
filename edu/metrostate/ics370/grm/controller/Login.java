package edu.metrostate.ics370.grm.controller;

/**
 * @author skylar
 *
 */
public class Login {

	/**
	 * No-arg constructor
	 */
	public Login() {

	}	
	
	/**
	 * Opens login GUI, gets credentials, signs in
	 * If sign in successful, open menu
	 * Otherwise display error and allow user to sign in again
	 */
	public void openLogin() {
		String user = null;
		String password = null;
		
		// TODO get credentials
		
		// sign in
		while ( !(Connector.signIn(user, password)) ) {
			// TODO display error message
		}
		openMenu();
	}

	public void openMenu() {
		// TODO
	}
}
