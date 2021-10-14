package edu.metrostate.ics370.grm.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import edu.metrostate.ics370.grm.model.User;

/**
 * @author skylar
 *
 */
public class Connector {
	
  	static final String DB_URL = "jdbc:mysql://localhost:3306/grmdata";
  	
    /**
  	 * 	Database sign in, sets static connection
  	 *
  	 * @param user
  	 * @param password
  	 * @return {@code true} if connection successfully set
  	 */
  	public static Connection signIn(String user, String password) {
  		try {
  			return DriverManager.getConnection(DB_URL, user, password);
  		} catch (Exception ex) { ex.printStackTrace(); }
  		return null;
  	}
  	
  	/**
  	 * Finds user by username
  	 * 
  	 * @param username
  	 * @return the user
  	 */
  	public static User getUser(String username) {
  		// TODO get user from DB
  		// TODO create User object
  		return null;
  	}
}
