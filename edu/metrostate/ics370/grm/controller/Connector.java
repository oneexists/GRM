package edu.metrostate.ics370.grm.controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author skylar
 *
 */
public class Connector {
	
  	static final String DB_URL = "jdbc:mysql://localhost:3306/grmdata";
    private static Connection con;
  	
    /**
  	 * 	Database sign in, sets static connection
  	 *
  	 * @param user
  	 * @param password
  	 * @return {@code true} if connection successfully set
  	 */
  	public static boolean signIn(String user, String password) {
  		Connection connection = null;

  		try {
  			connection = DriverManager.getConnection(DB_URL, user, password);
  			Connector.con = connection;
  			return true;
  		} catch (Exception ex) { ex.printStackTrace(); }
  		return false;
  	}
    
  	/**
     * 	Returns database connection
     *
     *  @return con
     */
     public static Connection getCon() {
       return con;
     }
}
