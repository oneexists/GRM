package edu.metrostate.ics370.grm.controller;
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
	public static Question getQuestions() {
		String sql = "SELECT question_prompt, choice_text "
				+ "FROM Question, SelectChoices, Choice "
				+ "WHERE Question.question_id = SelectChoices.question_id "
				+ "AND SelectChoices.choice_id = Choice.choice_id"
				+ "ORDER BY question_prompt";
		// TODO get question from database
		return new Question("question", new QuestionChoice[3]);
	}
}
