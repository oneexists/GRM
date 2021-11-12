package edu.metrostate.ics370.grm.controller;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import edu.metrostate.ics370.grm.model.Game;
import edu.metrostate.ics370.grm.model.GameTag;


/**
 * @author skylar
 */
public class InterfaceSqlSave {
	public void saveToSqlTags(GameTag[] dbTags)
	{
		//Save the array into the sql//
	}

	/**
	 * Adds game to hatelist of user and saves in database
	 * 
	 * @param game the game to add to the hatelist
	 */
	public static void addHatelist(Game game) {
	   // create list of games from user's hatelist
	   List<Game> hatelist = Arrays.asList(Login.user.getHatelist());
	   // if game is not on list...
	   if (!(hatelist.contains(game))) {
		   // add to user hatelist
		   Login.user.addHatelist(game);
	   }
	   
	   // save to database
	   String pSql = "INSERT INTO Hatelist(username, appId) VALUES(?, ?)";
	   try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
			   ) {
		   pStmt.setString(1, Login.user.getUsername());
		   pStmt.setInt(2, game.getId());
		   pStmt.executeUpdate();
	   } catch (SQLException e) {
		   Connector.processException(e);
	   }
	}

	/**
	 * Adds game to the wishlist and saves in the database
	 * 
	 * @param game the game to add to the wishlist
	 */
	public static void addWishlist(Game game) {
		// add to wishlist
		List<Game> wishlist = Arrays.asList(Login.user.getWishlist());
		if (!(wishlist.contains(game))) {
			Login.user.addWishlist(game);
		}
		
		// save to database
		String pSql = "INSERT INTO Wishlist(username, appId) VALUES(?, ?)";
		try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
				) {
			pStmt.setString(1, Login.user.getUsername());
			pStmt.setInt(2, game.getId());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			Connector.processException(e);
		}
	}
}
