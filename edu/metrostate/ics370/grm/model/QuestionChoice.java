/**
 * 
 */
package edu.metrostate.ics370.grm.model;

/**
 * @author skylar
 * @author christian
 *
 */
public class QuestionChoice {

	private String text;
	private GameTag[] tags;
	
	/**
	 * No-arg constructor
	 */
	public QuestionChoice() {
	}
	/**
	 * Initializes the QuestionChoice
	 * 
	 * @param text text of the QuestionChoice
	 * @param tags tags of the QuestionChoice
	 */
	public QuestionChoice(String text, GameTag[] tags) {
		this.text = text;
		this.tags = tags;
	}
	
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(GameTag[] tags) {
		this.tags = tags;
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @return the tags
	 */
	public GameTag[] getTags() {
		return tags;
	}
}
