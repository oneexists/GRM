package edu.metrostate.ics370.grm.controller;

import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import edu.metrostate.ics370.grm.model.Game;
import edu.metrostate.ics370.grm.model.GameTag;
import edu.metrostate.ics370.grm.model.Question;
import edu.metrostate.ics370.grm.model.QuestionChoice;

/**
 * @author skylar
 * @author nick
 * @author christian
 */
public abstract class QuestionnaireInterface {
	private static Question[] questions = getQuestions();
	private static int qNum = 1;
	
	public static Question getQuestion() {
		return questions[qNum];
	}
	
	private static void nextQuestion() {
		qNum += 1;
	}
	
	public static void selectChoice(int choice) {
		GameTag[] choiceTags = getQuestion().getChoices()[choice].getTags();
		for (GameTag choiceTag : choiceTags) {
			Login.addPersonalTag(choiceTag);
		}
		nextQuestion();
		System.out.println(getQuestion().getChoices()[0].getText());
		System.out.println(getQuestion().getChoices()[1].getText());
		System.out.println(getQuestion().getChoices()[2].getText());
	}
	
	/**
	 * @param choiceText choice of the question
	 * @return tags the array of GameTags associated with the choice
	 */
	private static GameTag[] getChoiceTags(String choiceText) {
		ArrayList<GameTag> tags = new ArrayList<GameTag>();
		String pSql = "SELECT tag_name "
				+ "FROM Choice, GameTag "
				+ "WHERE Choice.choice_text = ?";
		try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
				) {
			pStmt.setString(1, choiceText);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				tags.add(new GameTag(rs.getString("tag_name")));
			}
		} catch (SQLException e) {
			Connector.processException(e);
			return null;
		}
		return tags.toArray(new GameTag[tags.size()]);
	}
	
	private static Question[] getQuestions() {
		ArrayList<Question> questions = new ArrayList<Question>();
		String sql = "SELECT question_prompt, choice_text "
				+ "FROM Question, SelectChoices, Choice "
				+ "WHERE Question.question_id = SelectChoices.question_id "
				+ "AND SelectChoices.choice_id = Choice.choice_id "
				+ "ORDER BY question_prompt";
		try (	Statement stmt = Connector.getInstance().getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {
			Question question = new Question();
			while (rs.next()) {
				String promptText = rs.getString("question_prompt");
				String choiceText = rs.getString("choice_text");
				
				if (question.getPrompt() == null) {
					question.setPrompt(promptText);
					question.addChoice(new QuestionChoice(choiceText, getChoiceTags(choiceText)));
				}
				if (question.getPrompt().equals(promptText)) {
					question.addChoice(new QuestionChoice(choiceText, getChoiceTags(choiceText)));
				} else { 
					questions.add(question);
					question = new Question();
				}
			}
		} catch (SQLException e) {
			Connector.processException(e);
			return null;
		}
		return prepareQuestions(questions.toArray(new Question[questions.size()]));
	}
	
	/**
	 * @param questionArray array of the questions with all choices
	 * @return qSet array of questions with three choices each
	 */
	private static Question[] prepareQuestions(Question[] questionArray) {
		ArrayList<Question> qSet = new ArrayList<Question>();
		for (Question question : questionArray) {
			QuestionChoice[] firstSet = {question.getChoices()[3], question.getChoices()[4], question.getChoices()[5]};
			QuestionChoice[] secondSet = {question.getChoices()[6], question.getChoices()[7], question.getChoices()[8]};
			QuestionChoice[] thirdSet = {question.getChoices()[9], question.getChoices()[10], question.getChoices()[11]};
			Question secondQuestion = new Question(question.getPrompt(), firstSet);
			Question thirdQuestion = new Question(question.getPrompt(), secondSet);
			Question fourthQuestion = new Question(question.getPrompt(), thirdSet);
			qSet.add(question);
			qSet.add(secondQuestion);
			qSet.add(thirdQuestion);
			qSet.add(fourthQuestion);
		}
		for (Question question : qSet) {
			System.out.println(question.getPrompt());
		}
		return qSet.toArray(new Question[qSet.size()]);
	}
	
	private static Game[] getGames() {
		ArrayList<Game> allGames = new ArrayList<Game>();
		StringBuffer gameBuffer = new StringBuffer();
		int appId = -1;
		String name = null;
		float rating = -1;
		boolean inName = false;
		boolean inTags = false;
		ArrayList<GameTag> tags = new ArrayList<GameTag>();
		
		try {
			FileReader reader = new FileReader("lib/library.txt");
			Scanner gameScanner = new Scanner(reader);
			while (gameScanner.hasNext()) {
				gameBuffer.append(gameScanner.next());
			}
			reader.close();
			gameScanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String gameString = gameBuffer.toString();
		String[] splitGame = gameString.split("END");
		System.out.println(splitGame);
		for (String gameSplit : splitGame) {
			String[] lineSplits = gameSplit.split(",");
			for (int i=0; i<lineSplits.length; i++) {
				// parse name
				if (inName) {
					name += " " + lineSplits[i];
					if (lineSplits[i + 1] == "rating") {
						inName = false;
					}
				}
				// parse tags
				if (inTags) {
					tags.add(new GameTag(lineSplits[i]));
				}
				// parse appId
				if (lineSplits[i].equals("appid")) {
					i++;
					appId = Integer.parseInt(lineSplits[i]);
				}
				if (lineSplits[i].equals("name")) {
					inName = true;
				}
				// parse rating
				if (lineSplits[i].equals("rating")) {
					i++;
					rating = Float.parseFloat(lineSplits[i]);
				}
				if (lineSplits[i].equals("tags")) {
					inTags = true;
				}
				if (lineSplits[i].equals("END")) {
					// end of line
				}
			}
			allGames.add(new Game(appId, name, rating, tags.toArray(new GameTag[tags.size()])));
		}
		return allGames.toArray(new Game[allGames.size()]);
		
	}
}
