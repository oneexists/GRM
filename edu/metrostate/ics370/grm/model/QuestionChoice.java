/**
 * 
 */
package edu.metrostate.ics370.grm.model;

/**
 * @author skylar
 *
 */
public class QuestionChoice {

	private String text;
	private GameTag[] tags;
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the tags
	 */
	public GameTag[] getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(GameTag[] tags) {
		this.tags = tags;
	}
	/**
	 * No-arg constructor
	 */
	public QuestionChoice() {
		// TODO Auto-generated constructor stub
	}

}
