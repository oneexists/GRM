package edu.metrostate.ics370.grm.controller;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *	Connects to database defined in properties file located under .settings/Settings.properties </br>
 *	Processes SQLException handling for database exceptions
 *
 * @author skylar
 */
public class Connector {
	
	private static Connector instance = null;
	private static Connection con = null;

	/**
	 * Singleton no-arg constructor
	 */
	private Connector() {
	}
  	
  	/**
  	 * Sets connection to database
  	 * 
  	 * @return database connection
  	 */
  	public Connection getConnection() {
  		try {
  			// Properties object
  			Properties dbProps = new Properties();
  			// Path object
  			String propPath = ".settings/Settings.properties";
  			// File reader
  			FileReader fReader = new FileReader(propPath);
  			// Load properties from file
  			dbProps.load(fReader);
  			// Property values
  			String dbConnUrl = dbProps.getProperty("db.conn.url");
  			String dbUserName = dbProps.getProperty("db.username");
  			String dbPassword = dbProps.getProperty("db.password");
  			con = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
  		} catch (Exception ex) { 
  			System.err.println(ex);
  		}
  		return con;
  	}
  	
  	/**
  	 * Returns Singleton instance, creates instance if {@code null}
  	 * 
  	 * @return Connector instance
  	 */
  	public static Connector getInstance() {
  		if (instance == null) {
  			instance = new Connector();
  		}
  		return instance;
  	}
  	
  	/**
  	 * Closes the database connection
  	 */
  	public void close() {
  		try {
  			con.close();
  			con = null;
  		} catch (SQLException e) {
  			System.err.println(e);
  		}
  	}
  	
  	/**
  	 * Database exception handling
  	 * 
  	 * @param e
  	 */
  	public static void processException(SQLException e) {
  		System.err.println("Error message: " + e.getMessage());
  		System.err.println("Error code: " + e.getErrorCode());
  		System.err.println("SQL state: " + e.getSQLState());
  	}
}
