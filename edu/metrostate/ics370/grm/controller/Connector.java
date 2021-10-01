package edu.metrostate.ics370.grm.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
  	static final String DB_URL = "jdbc:mysql://localhost:3306/grmdata";
    private static Connection con;
  	/*
  	 * 	DB sign in
  	 *
  	 *  @param user, password
  	 */
  	public static boolean signIn(String user, String password) {
  		Connection con = null;

  		try {
  			con = DriverManager.getConnection(DB_URL, user, password);
  			setCon(con);
  			System.out.println("Connection Object Created : " + con);
  			return true;
  		} catch (Exception ex) { ex.printStackTrace(); }
  		return false;
  	}
    /*
     * 	Returns DB connection
     *
     *  @return connection
     */
     public static Connection getCon() {
       return con;
     }
}
