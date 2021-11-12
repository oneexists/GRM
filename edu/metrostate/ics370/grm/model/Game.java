/**
 * 
 */
package edu.metrostate.ics370.grm.model;

/**
 * @author skylar
 */
public class Game {
	
	private int appId;
	private String name;
	private GameTag[] tags;
	
	/**
	 * No-arg constructor
	 */
	public Game() {}
	/**
	 * Initializes the Game
	 * 
	 * @param appId id of the game
	 * @param name name of the game
	 * @param tags tags of the game
	 */
	public Game(int appId, String name, GameTag[] tags) {
		this.appId = appId;
		this.name = name;
		this.tags = tags;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return appId;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return the tags
	 */
	public GameTag[] getTags()
	{
		return tags;
	}
}
