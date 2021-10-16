package edu.metrostate.ics370.grm.controller;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import edu.metrostate.ics370.grm.model.User;

/**
 * @author skylar
 *
 */
public class Connector {
	  	
    /**
  	 * 	Database sign in, sets static connection
  	 *
  	 * @param user
  	 * @param password
  	 * @return {@code true} if connection successfully set
  	 */
  	public static Connection signIn() {
  		try {
  			// Properties object
  			Properties dbProps = new Properties();
  			// Path object
  			String propPath = "../../../../../Settings.properties";
  			// File reader
  			FileReader fReader = new FileReader(propPath);
  			// Load properties from file
  			dbProps.load(fReader);
  			// Property values
  			String dbConnUrl = dbProps.getProperty("db.conn.url");
  			String dbUserName = dbProps.getProperty("db.username");
  			String dbPassword = dbProps.getProperty("db.password");
  			return DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
  		} catch (Exception ex) { ex.printStackTrace(); }
  		return null;
  	}
  	
  	/**
  	 * Finds user by username
  	 * 
  	 * @param username
  	 * @return the user
  	 */
  	public static User getUser(String username, String password) {
  		// TODO get user from DB
  		// TODO create User object
  		return null;
  	}
}
