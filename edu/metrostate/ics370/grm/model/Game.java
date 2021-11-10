/**
 * 
 */
package edu.metrostate.ics370.grm.model;
import java.io.Serializable;

/**
 * @author skylar
 *
 */
public class Game implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 202110001L;
	
	private String id;
	private String name;
	private float rating;
	private int numTags;
	private GameTag[] tags = new GameTag[100];

	
	/**
	 * No-arg constructor
	 */
	public Game()
	{
		// TODO Auto-generated constructor stub
    	for (int i = 0; i < getTags().length; i++)
    		getTags()[i] = new GameTag();
	}
	
	/**
	 * Initializes the Game
	 * 
	 * @param id
	 * @param name
	 * @param tags
	 */
	public Game(String id, String name, GameTag[] tags)
	{
		
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}
	
	
	public void setId(String tid)
	{
		this.id = tid;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	
	public void setName(String string)
	{
		this.name = string;
	}
	
	
	public void addTag(String nam)
	{
		this.tags[getNumTags()].setTag(nam);
		setNumTags(getNumTags() + 1);
	}

	
	/**
	 * @return the tags
	 */
	public GameTag[] getTags()
	{
		return tags;
	}
	
	
	public GameTag getTag(int num)
	{
		return this.tags[num];
	}
	
	
	public void setTags(GameTag[] dbTags)
	{
		this.tags = dbTags;
	}

	
	public float getRating()
	{
		return rating;
	}


	public void setRating(float rating)
	{
		this.rating = rating;
	}

	
	public int getNumTags()
	{
		return numTags;
	}
	

	public void setNumTags(int numTags)
	{
		this.numTags = numTags;
	}
}
