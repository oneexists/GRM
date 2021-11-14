/**
 * 
 */
package edu.metrostate.ics370.grm.model;

import java.util.ArrayList;

/**
 * @author skylar
 * @author christian
 */
public class Question {

	private String prompt;
	private QuestionChoice[] choices;

	/**
	 * No-arg constructor
	 */
	public Question() {
	}
	/**
	 * @param prompt the prompt to set
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
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
	public void addChoice(QuestionChoice newChoice) {
		// TODO Auto-generated method stub
		ArrayList<QuestionChoice> newChoices = new ArrayList<QuestionChoice>();
		if (choices != null) {
			for (QuestionChoice choice : choices) {
				newChoices.add(choice);
			}			
		}
		newChoices.add(newChoice);
		choices = newChoices.toArray(new QuestionChoice[newChoices.size()]);
	}
}
