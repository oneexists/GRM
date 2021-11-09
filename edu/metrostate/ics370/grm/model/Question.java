/**
 * 
 */
package edu.metrostate.ics370.grm.model;

/**
 * @author skylar
 * @author christian
 */
public class Question {

	private String prompt;
	private QuestionChoice[] choices;

	/**
	 * Initializes the Question
	 * 
	 * @param prompt prompt of the question
	 * @param choices choices of the question
	 */
	public Question(String prompt, QuestionChoice[] choices) {
		this.prompt = prompt;
		this.choices = choices;
	}	

	/**
	 * @return the prompt
	 */
	public String getPrompt() {
		return prompt;
	}
	
	/**
	 * @return the choices
	 */
	public QuestionChoice[] getChoices() {
		return choices;
	}
}
