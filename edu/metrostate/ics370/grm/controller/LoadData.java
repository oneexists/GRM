/**
 * 
 */
package edu.metrostate.ics370.grm.controller;

import edu.metrostate.ics370.grm.model.Game;

/**
 * @author skylar
 * @author nick
 *
 */
public class LoadData {

	private Game[] games;
	/**
	 * 
	 */
	public LoadData() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets games from JSON file
	 */
	public static void loadGames() {
		
	}

	/**
	 * @return the games
	 */
	public Game[] getGames() {
		return games.clone();
	}

	/**
	 * @param games the games to set
	 */
	public void setGames(Game[] games) {
		this.games = games;
	}

}
