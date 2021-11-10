package edu.metrostate.ics370.grm.questionaire;
import edu.metrostate.ics370.grm.model.Game;
import edu.metrostate.ics370.grm.model.GameTag;


public class InterfaceSqlLoad
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
}
