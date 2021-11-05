/**
 * 
 */
package edu.metrostate.ics370.grm.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.metrostate.ics370.grm.model.Game;
import edu.metrostate.ics370.grm.model.GameTag;

/**
 * @author skylar
 * @author nick
 *
 */
public abstract class LoadData {

	private List<Game> games;
	
	/**
	 * No-arg constructor
	 */
	public LoadData() {
		games = new ArrayList<>();
	}
	
	/**
	 * Gets games from JSON file
	 */
	public static void loadGames() {
		// get tags from database 
		GameTag[] tags = getTags();
		System.out.println(tags);
		// for each (jsonGame)	{ 
		// 	if (jsonGame has a tag in tags)		{ games.add(jsonGame) }
		// }
	}
	
	/**
	 * Returns all tags used in questionnaire
	 * 
	 * @return GameTag array
	 */
	public static GameTag[] getTags() {
		String sql = "SELECT tag_id, tag_name FROM GameTag";
		try (	Connection con = Connector.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {
			List<GameTag> tags = new ArrayList<>();
			while (rs.next()) {
				GameTag tag = new GameTag(rs.getInt("tag_id"), rs.getString("tag_name"));
				tags.add(tag);
			}
			return tags.toArray(new GameTag[tags.size()]);
		} catch (SQLException e) {
			Connector.processException(e);
			return null;
		}
	}
	
	public void addGame(Game game) {
		games.add(game);
	}
	
	/**
	 * Returns all loaded games
	 * 
	 * @return all games
	 */
	public Game[] getGames() {
		return games.toArray(new Game[games.size()]);
	}

}
