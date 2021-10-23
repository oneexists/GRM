/**
 * 
 */
package edu.metrostate.ics370.grm.model;

/**
 * @author skylar
 *
 */
public class Question {

	private String prompt;
	private QuestionChoice[] choices;
	/**
	 * @return the prompt
	 */
	public String getPrompt() {
		return prompt;
	}
	/**
	 * @param prompt the prompt to set
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	/**
	 * @return the choices
	 */
	public QuestionChoice[] getChoices() {
		return choices;
	}
	/**
	 * @param choices the choices to set
	 */
	public void setChoices(QuestionChoice[] choices) {
		this.choices = choices;
	}
	/**
	 * No-arg constructor
	 */
	public Question() {
		// TODO Auto-generated constructor stub
	}

}
