/**
 * 
 */
package edu.metrostate.ics370.grm.model;

import java.io.Serializable;

/**
 * @author skylar
 * @author christian
 */
public class GameTag implements Serializable
{
	/**
	 * Version of the bean
	 */
	private static final long serialVersionUID = 202110001L;
	
	private String name;
    private int val;

    
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
	public GameTag(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	public int getVal()
	{
		return val;
	}

	
	public void setVal(int val)
	{
		this.val = val;
	}
}
