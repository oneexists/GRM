package edu.metrostate.ics370.grm.model;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author skylar
 */
public class Game implements Comparable<Game> {
	private static final Comparator<Game> GAME_RATING_COMPARATOR = Comparator.comparing(Game::getRating);
	
	private int appId;
	private String name;
	private float rating;
	private float score;
	public int tNum;
	private GameTag[] tags = new GameTag[200];
	
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

	public Game() {
		// TODO Auto-generated constructor stub
		for (int x = 0; x < tags.length; x++)
			tags[x] = new GameTag("");
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

	@Override
	public int compareTo(Game game) {
		return GAME_RATING_COMPARATOR.compare(this, game);
	}
	
	@Override
	public String toString() {
		return "Game [appId=" + appId + ", name=" + name + ", rating=" + rating + ", tags=" + Arrays.toString(tags)
				+ "]";
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setId(String string) {
		this.appId = Integer.parseInt(string);
	}

	public void addTag(String string) {
		GameTag ttag = new GameTag(string);
		this.tags[tNum] = ttag;
		tNum++;
	}

	public void setName(String nam) {
		this.name = nam;
	}
}