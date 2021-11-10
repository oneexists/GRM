/**
 * 
 */
package edu.metrostate.ics370.grm.model;

import java.io.Serializable;

/**
 * @author skylar
 *
 */
public class GameTag implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 202110001L;
	
	private int id;
	private String name;
    private int val;

    
    /**
	 * No-arg constructor
	 */
	public GameTag()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Initializes the GameTag
	 * 
	 * @param id
	 * @param name
	 */
	public GameTag(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	
	public void setTag(String string)
	{
		// TODO Auto-generated method stub
		name = string;
	}
	

	public String getTag()
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
