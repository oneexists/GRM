package edu.metrostate.ics370.grm.controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.metrostate.ics370.grm.model.Game;
import edu.metrostate.ics370.grm.model.GameTag;
import edu.metrostate.ics370.grm.model.Question;
import edu.metrostate.ics370.grm.model.QuestionChoice;
import edu.metrostate.ics370.grm.model.RecommendationManager;


public abstract class InterfaceSqlLoad
{
	
	//Can take all the database of games and put them in the SQL if desired//
	public void loadFromSql(RecommendationManager rms)
	{
		//Load all info from sql into these//
		Game[] dbGamesHatelist = new Game[200];
		Game[] dbGamesWishlist = new Game[200];
		Question[] dbQuestions = new Question[1000];
		GameTag[] dbTags = new GameTag[100];
		
		rms.setDbQuestions(dbQuestions);
		rms.setDbTags(dbTags);
		rms.setDbGamesWishlist(dbGamesWishlist);
		rms.setDbGamesHatelist(dbGamesHatelist);
	}
	public static Question[] getQuestions() {
		Question[] questionArray = null;
		String sql = "SELECT question_prompt, choice_text, tag_name "
				+ "FROM Question, SelectChoices, Choice, ChoiceTags, GameTag "
				+ "WHERE Question.question_id = SelectChoices.question_id "
				+ "AND SelectChoices.choice_id = Choice.choice_id "
				+ "AND Choice.choice_id = ChoiceTags.choice_id "
				+ "AND ChoiceTags.tag_id = GameTag.tag_id";
		try (	Statement stmt = Connector.getInstance().getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {
			ArrayList<Question> questions = new ArrayList<Question>();
			while (rs.next()) {
				String prompt = rs.getString("question_prompt");
				ArrayList<QuestionChoice> choices = new ArrayList<QuestionChoice>();
				while (prompt == rs.getString("question_prompt")) {
					String choice = rs.getString("choice_text");
					ArrayList<GameTag> tags = new ArrayList<GameTag>();
					while (choice == rs.getString("choice_text")) {
						String tag = rs.getString("tag_name");						
						tags.add(new GameTag(tag));
					}
					GameTag[] tagArray = tags.toArray(new GameTag[tags.size()]);
					choices.add(new QuestionChoice(choice, tagArray));
				}
				QuestionChoice[] choiceArray = choices.toArray(new QuestionChoice[choices.size()]);
				questions.add(new Question(prompt, choiceArray));
			}
			questionArray = questions.toArray(new Question[questions.size()]);
		} catch (SQLException e) {
			
		}
		return questionArray;
	}
}
