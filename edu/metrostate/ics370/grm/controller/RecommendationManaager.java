package edu.metrostate.ics370.grm.controller;

import edu.metrostate.ics370.grm.model.Game;
import edu.metrostate.ics370.grm.model.GameTag;
import edu.metrostate.ics370.grm.model.Question;

/**
 * @author skylar
 * @author christian
 */
public abstract class RecommendationManaager {
	private static int qNum = 0;
	private static Question question;
	
	/**
	 * No-arg constructor
	 */
	public RecommendationManaager() {
		// get questions from database
		QuestionnaireInterface.getQuestions();
		// load games from file
		QuestionnaireInterface.getGames();
		// set first question
		question = getQuestions()[qNum];
	}
	
	public static void getResults() {
		// TODO get results
	}

	public static void selectChoice(int i) {
		// save tags
		for (GameTag tag : question.getChoices()[i].getTags()) {
			QuestionnaireInterface.addPersonalTag(tag);
		}
		getResults();
		nextQuestion();
		// TODO refresh gui
	}
	
	private static void nextQuestion() {
		if (qNum < getGames().length) {
			question = getQuestions()[qNum];
			qNum++;
		} else {
			// TODO end questionnaire: out of questions
		}
	}
	
	private static Question[] getQuestions() {
		return QuestionnaireInterface.questions.clone();
	}
	private static Game[] getGames() {
		return QuestionnaireInterface.games.clone();
	}
}
