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
