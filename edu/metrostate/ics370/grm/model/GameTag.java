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
	
	private int val;
	private String name;
	
	/**
	 * Initializes the GameTag
	 * 
	 * @param id id of the GameTag
	 * @param name name of the GameTag
	 */
	public GameTag(String name) {
		this.name = name;
		this.val = 1;
	}
	
	@Override
	public String toString() {
		return "GameTag [val=" + val + ", name=" + name + "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public int getVal() {
		return val;
	}

	public void upVal() {
		this.val++;
	}
}
