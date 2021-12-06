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
 * @author nick
 * @author christian
 */
public abstract class QuestionnaireInterface {
	private static int gNum = 0;
	private static Game[] dbGames = new Game[2000];
	private static Game[] dbTopGames = new Game[5];
	private static Question[] questions = getQuestions();
	private static int qNum = 1;
	
	public static Question getQuestion() {
		return questions[qNum];
	}
	
	private static void nextQuestion() {
		qNum += 1;
	}
	
	
	public static void init() throws Exception
	{
		if (dbGames[0] == null)
		{
			//System.out.println("initing");
			for (int x = 0; x < dbGames.length; x++)
				dbGames[x] = new Game();

			for (int x = 0; x < dbTopGames.length; x++)
				dbTopGames[x] = new Game();
			
			readDb();
		}
	}
	
//	public static Game[] getTopGames() {
//		return Arrays.copyOfRange(sortGames(getGames()), 0, 4);
//	}
//	private static Game[] sortGames(Game[] games) {
//		Arrays.sort(games);
//		return games;
//	}
	
	//public static Game[] getTopGames() {
		//return Arrays.copyOfRange(getGames(), 0, 4);
	//}
	public static Game[] getTopGames()
	{
		//if (dbTopGames[0] != null)
		//{
			//for (int x = 0; x < 5; x++)
				//System.out.println("step 2: " + dbTopGames[x].getName());
		//}
		return dbTopGames;
	}
	
	
	public static void selectChoice(int choice) {
		GameTag[] choiceTags = getQuestion().getChoices()[choice].getTags();
		for (GameTag choiceTag : choiceTags) {
			Login.addPersonalTag(choiceTag);
		}
		addRecommendations(choiceTags);
		nextQuestion();
		
		// TEST: next question's choices
		//System.out.println("Next question choices: ");
		//System.out.println(getQuestion().getChoices()[0].getText());
		//System.out.println(getQuestion().getChoices()[1].getText());
		//System.out.println(getQuestion().getChoices()[2].getText());
	}
	
	private static void addRecommendations(GameTag[] choiceTags) {
		//ArrayList<Game> potentialGames = new ArrayList<Game>(Arrays.asList(getGames()));
		//ArrayList<Game> recommendedGames = new ArrayList<Game>();
		//if (gNum == 0)
		//	dbGames = getGames();
		
		Game[] potentialGames = dbGames;
		//System.out.println(potentialGames[0].getTags()[0].getName());
		//Game[] potentialGames = new Game[7000];
		//for (int x = 0; x < gNum; x++)
		//potentialGames[x] = dbGames[x];
		
		//System.out.println(dbGames[0].getName());
		//System.out.println(potentialGames[0].getName());
				
    	// remove wishlist/hatelist
    	if (Login.user.getWishlist() != null)
    	{
    		for (int x = 0; x < gNum; x++)
    		{
    			if (dbGames[x] != null)
    			{
    	        	for (Game wishlistGame : Login.user.getWishlist())
    	        	{
    	        		if (potentialGames[x].getName().equals(wishlistGame.getName()))
    	        		{
    	        			potentialGames[x] = null;
    	        		}
    	        	}
    	        	for (Game hatelistGame : Login.user.getHatelist())
    	        	{
    	        		if (potentialGames[x].getName().equals(hatelistGame.getName()))
    	        		{
    	        			potentialGames[x] = null;
    	        		}
    	        	}
    			}
    		}
    	}
        


		//Set scores//
		for (int x = 0; x < gNum; x++)
    	{
    		if (potentialGames[x] != null)
    		{
    			for (int y = 0; y < potentialGames[x].tNum; y++)
    			{
    				var ttag = potentialGames[x].getTags()[y].getName();
					//System.out.println("ttag: " + ttag);
        			for (GameTag userTag : Login.user.getPersonalTags())
        			{
        				//System.out.println("userTag: " + userTag.getName() + " ttag: " + ttag);
        				if (userTag.getName().equals(ttag))
        				{
        					potentialGames[x].setScore(userTag.getVal() + potentialGames[x].getScore());
        					//System.out.println("Score: " + potentialGames[x].getScore());
        				}
        			}    			
    			}
    		}    		
    	}
        

    	//Game[] dbTopGames = new Game[5];		
		for (int i = 0; i < 5; i++)
		{
			float high_score = 0;
			int winner_x = 0;
			for (int x = 0; x < gNum; x++)
			{
				if (potentialGames[x].getScore() > high_score)
				{
					//System.out.println(potentialGames[x].getScore() + " " + potentialGames[x].getName());
					high_score = potentialGames[x].getScore();
					winner_x = x;
				}
			}
			dbTopGames[i] = potentialGames[winner_x];
			potentialGames[winner_x].setScore(0);
		}


		//for (int x = 0; x < 5; x++)
		//{
		//	System.out.println("Step 3: " + dbTopGames[x].getName());
		//}
		
		
        // TEST: print recommended game titles
        //for (GameTag tag : Login.user.getPersonalTags()) {
        //System.out.println(tag.getName() + " " + tag.getVal());
        	//}
        //System.out.println("gNum: " + gNum);
        //System.out.println("Recommended games: " + recommendedGames.size());
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
	
	/*
	private static Game[] getGames() {
		if (gNum > 0)
			return null;
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
					inTags = false;
					i++;
					appId = Integer.parseInt(lineSplits[i]);
					gNum++;
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
			}
			name = name.trim();
			allGames.add(new Game(appId, name, rating, tags.toArray(new GameTag[tags.size()])));
			appId = -1;
			name = "";
			rating = -1;
		}
		System.out.println("gNum: " + gNum);
		dbGames = allGames.toArray(new Game[allGames.size()];

		return allGames.toArray(new Game[allGames.size()]);
		
	}
	*/
	
	public static void readDb() throws Exception //**Can read data from a file**//
	{
    	for (int i = 0; i < dbGames.length; i++)
    		dbGames[i] = new Game();

        StringBuffer sb = new StringBuffer();
    	try
    	{
			FileReader reader = new FileReader("lib/library.txt");
            
            Scanner sc = new Scanner(reader);
            while(sc.hasNext())
            {
            	sb.append(sc.next());
            	//System.out.println(sc.next());
            }

            reader.close();	
            sc.close();
    	}
    	catch (IOException e)
    	{
            ((Throwable) e).printStackTrace();
    	}

		  String result = sb.toString();
		  result = result.replace(",", " ");
		  String[] words = result.split(" ");
		  
		  boolean inTags = false;
		  boolean inName = false;
		  String nam = "";
		
		  for (int x = 0; x < words.length; x++)
		  {
			  //System.out.println("Words: " + words[x]);
			  if (words[x].equals("appid"))
			  {
				  //dbId[numGames] = words[x + 1];
				  //System.out.println(words[x] + " " + words[x + 1]);
				  dbGames[gNum].setId(words[x + 1]);
			  }
			  if (words[x].equals("name"))
			  {
				  inName = true;
				  nam = "";
				  //dbNames[numGames] = words[x + 1];
			  }
			  else
			  if (words[x].equals("rating"))
			  {
				  inName = false;
				  float pos = Float.parseFloat(words[x + 1]);
				  dbGames[gNum].setRating(pos);
			  }
			  else
			  if (words[x].equals("tags"))
			  {
				  inTags = true;
			  }
			  else
			  if (words[x].equals("END"))
			  {
				  gNum++;
				  inTags = false;
			  }
			  else
			  if (inTags)
			  {
				  dbGames[gNum].addTag(words[x]);
			  }
			  else
			  if (inName)
			  {
				  if (nam != "")
					  nam += " ";
				  nam += words[x];
				  dbGames[gNum].setName(nam);
			  }
		  }
	}

	public abstract void addHatelist(int i);

	public abstract void addWishlist(int i);
}
