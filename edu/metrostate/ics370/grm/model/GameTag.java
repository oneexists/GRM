/**
 * 
 */
package edu.metrostate.ics370.grm.model;

import java.io.Serializable;

/**
 * @author skylar
 * @author christian
 */
public class GameTag implements Serializable {
	/**
	 * Version of the bean
	 */
	private static final long serialVersionUID = 202110001L;
	
	private int id;
	private String name;

	/**
	 * No-arg constructor
	 */
	public GameTag() {
	}
	
	/**
	 * Initializes the GameTag
	 * 
	 * @param id id of the GameTag
	 * @param name name of the GameTag
	 */
	public GameTag(int id, String name) {
		this.id = id;
		this.name = name;
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
}
