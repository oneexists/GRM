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
    private int val = 1;

    
    /**
	 * No-arg constructor
	 */
	public GameTag() {
	}
	
	/**
	 * Initializes the GameTag
	 * 
	 * @param name name of the GameTag
	 */
	public GameTag(String name) {
		this.name = name;
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