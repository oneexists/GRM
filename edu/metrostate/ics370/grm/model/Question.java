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
	 * No-arg constructor
	 */
	public Question() {
		// TODO Auto-generated constructor stub
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

	/**
	 * @param prompt the prompt
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	/**
	 * Adds new choice to the question
	 * 
	 * @param newChoice choice to add
	 */
	public void addChoice(QuestionChoice newChoice) {
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
