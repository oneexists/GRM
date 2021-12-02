package edu.metrostate.ics370.grm.controller;

import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
		addRecommendations(choiceTags);
		nextQuestion();
		// TEST: next question's choices
		System.out.println("Next question choices: ");
		System.out.println(getQuestion().getChoices()[0].getText());
		System.out.println(getQuestion().getChoices()[1].getText());
		System.out.println(getQuestion().getChoices()[2].getText());
	}
	
	private static void addRecommendations(GameTag[] choiceTags) {
		ArrayList<Game> potentialGames = new ArrayList<Game>(Arrays.asList(getGames()));
		ArrayList<Game> recommendedGames = new ArrayList<Game>();
				
        for (Game game : potentialGames) {
        	// remove wishlist
        	if (Login.user.getWishlist() != null) {
	        	for (Game wishlistGame : Login.user.getWishlist()) {
	        		if (game.getName().equals(wishlistGame.getName())) {
	        			potentialGames.remove(game);
	        		}
	        	}
        	}
        	// remove hatelist
        	if (Login.user.getHatelist() != null) {
        		for (Game hatelistGame : Login.user.getHatelist()) {
        			if (game.getName().equals(hatelistGame.getName())) {
        				potentialGames.remove(game);
        			}
        		}
        	}
        	// add games to recommended games
        	for (GameTag gameTag : game.getTags()) {
        		for (GameTag choiceTag : choiceTags) {
        			if (gameTag.getName().equals(choiceTag.getName()) && !(recommendedGames.contains(game))) {
        				recommendedGames.add(game);
        			}
        		}
        	}
        }
        
        // TEST: print recommended game titles
//        for (Game game : potentialGames) {
//        	System.out.println(game.getName());
//        }
        for (GameTag tag : Login.user.getPersonalTags()) {
        	System.out.println(tag.getName() + " " + tag.getVal());
        }
        System.out.println("Potential games: " + potentialGames.size());
        System.out.println("Recommended games: " + recommendedGames.size());
	}

	/**
	 * @param choiceText choice of the question
	 * @return tags the array of GameTags associated with the choice
	 */
	private static GameTag[] getChoiceTags(String choiceText) {
		ArrayList<GameTag> tags = new ArrayList<GameTag>();
		ArrayList<Integer> tag_ids = new ArrayList<Integer>();
		String pSql = "SELECT tag_id "
				+ "FROM ChoiceTags, Choice "
				+ "WHERE Choice.choice_id = ChoiceTags.choice_id "
				+ "AND choice_text = ?";
		
		try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
				) {
			pStmt.setString(1, choiceText);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				tag_ids.add(rs.getInt("tag_id"));
			}
						
			String prepSql = "SELECT tag_name "
					+ "FROM GameTag "
					+ "WHERE tag_id = ?";
			try (	PreparedStatement prepStmt = Connector.getInstance().getConnection().prepareStatement(prepSql);
					) {
				for (Integer id : tag_ids) {
					prepStmt.setInt(1, id);
					ResultSet res = prepStmt.executeQuery();
					while (res.next()) {
						tags.add(new GameTag(res.getString("tag_name")));						
					}
				}
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
		// TEST: prepared questions
//		for (Question question : qSet) {
//			System.out.println(question.getPrompt());
//		}
		return qSet.toArray(new Question[qSet.size()]);
	}
	
	private static Game[] getGames() {
		ArrayList<Game> allGames = new ArrayList<Game>();
		StringBuffer gameBuffer = new StringBuffer();
		int appId = -1;
		String name = "";
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
		for (String gameSplit : splitGame) {
			String[] lineSplits = gameSplit.split(",");
			for (int i=0; i<lineSplits.length; i++) {
				// parse name
				if (inName && !(lineSplits[i].equals("rating"))) {
					name += " " + lineSplits[i];
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
					inName = false;
					i++;
					rating = Float.parseFloat(lineSplits[i]);
				}
				if (lineSplits[i].equals("tags")) {
					inTags = true;
				}
				if (lineSplits[i].equals("END")) {
					inTags = false;
					// end of line
				}
			}
			name = name.trim();
			allGames.add(new Game(appId, name, rating, tags.toArray(new GameTag[tags.size()])));
			appId = -1;
			name = "";
			rating = -1;
		}
		return allGames.toArray(new Game[allGames.size()]);
		
	}
}
