/**
 * 
 */
package edu.metrostate.ics370.grm.model;

import java.io.Serializable;

/**
 * @author skylar
 *
 */
public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 202110001L;
	
	private int id;
	private String name;
	private GameTag[] tags;

	/**
	 * No-arg constructor
	 */
	public Game() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Initializes the Game
	 * 
	 * @param id
	 * @param name
	 * @param tags
	 */
	public Game(int id, String name, GameTag[] tags) {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the tags
	 */
	public GameTag[] getTags() {
		return tags;
	}
}
