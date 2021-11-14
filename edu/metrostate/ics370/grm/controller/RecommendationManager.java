package edu.metrostate.ics370.grm.controller;

import edu.metrostate.ics370.grm.model.Game;
import edu.metrostate.ics370.grm.model.GameTag;
import edu.metrostate.ics370.grm.model.Question;

/**
 * @author skylar
 * @author christian
 */
public abstract class RecommendationManager {
	private static int qNum = 0;
	
	/**
	 * No-arg constructor
	 */
	public RecommendationManager() {
	}
	
	public static void getResults() {
		// TODO get results
	}

	public static void selectChoice(int i) {
		// save tags
		for (GameTag tag : getQuestions()[qNum].getChoices()[i].getTags()) {
			QuestionnaireInterface.addPersonalTag(tag);
		}
		getResults();
		nextQuestion();
	}
	
	public static Question getQuestion() {
		return getQuestions()[qNum];
	}
	private static void nextQuestion() {
		if (qNum < getGames().length) {
			getQuestions()[qNum] = getQuestions()[qNum];
			qNum++;
		} else {
			// TODO end questionnaire: out of questions
		}
	}
	
	private static Question[] getQuestions() {
		return QuestionnaireInterface.getQuestions().clone();
	}
	private static Game[] getGames() {
		return QuestionnaireInterface.games.clone();
	}
}
