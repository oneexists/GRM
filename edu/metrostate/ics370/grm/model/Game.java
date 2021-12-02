package edu.metrostate.ics370.grm.model;

/**
 * @author skylar
 */
public class Game {
	
	private int appId;
	private String name;
	private float rating;
	private GameTag[] tags;
	
	/**
	 * Initializes the Game
	 * 
	 * @param appId id of the game
	 * @param name name of the game
	 * @param rating rating of the game
	 * @param tags tags of the game
	 */
	public Game(int appId, String name, float rating, GameTag[] tags) {
		this.appId = appId;
		this.name = name;
		this.rating = rating;
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
	 * @return the rating
	 */
	public float getRating() {
		return rating;
	}
	
	/**
	 * @return the tags
	 */
	public GameTag[] getTags()
	{
		return tags;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}
}