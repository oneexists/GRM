package edu.metrostate.ics370.grm.controller;

import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	private static Game[] allGames;
	public static Game[] games;
	private static int qNum = 0;
	
	/**
	 * Adds game to the user hatelist and saves in database
	 * 
	 * @param game to add to the hatelist
	 */
	public static void addHatelist(Game game) {
		   // create list of games from user's hatelist
		   List<Game> hatelist = Arrays.asList(Login.user.getHatelist());
		   // if game is not on list...
		   if (!(hatelist.contains(game))) {
			   // add to user hatelist
			   Login.user.addHatelist(game);
		   
			   // save to database
			   String pSql = "INSERT INTO Hatelist(username, appId) VALUES(?, ?)";
			   try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
					   ) {
				   pStmt.setString(1, Login.user.getUsername());
				   pStmt.setInt(2, game.getId());
				   pStmt.executeUpdate();
			   } catch (SQLException e) {
				   Connector.processException(e);
			   }
		   }
	}

	/**
	 * Adds game to user hatelist and saves in database
	 * 
	 * @param gameIndex the index of the game in games array to add to the hatelist
	 */
	public static void addHatelist(int gameIndex) {
	   // create list of games from user's hatelist
	   List<Game> hatelist = Arrays.asList(Login.user.getHatelist());
	   // if game is not on list...
	   if (!(hatelist.contains(games[gameIndex]))) {
		   // add to user hatelist
		   Login.user.addHatelist(games[gameIndex]);
	   
		   // save to database
		   String pSql = "INSERT INTO Hatelist(username, appId) VALUES(?, ?)";
		   try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
				   ) {
			   pStmt.setString(1, Login.user.getUsername());
			   pStmt.setInt(2, games[gameIndex].getId());
			   pStmt.executeUpdate();
		   } catch (SQLException e) {
			   Connector.processException(e);
		   }
	   }
	}
	
	private static void addPersonalTag(GameTag gameTag) {
		List<GameTag> tags;
		if (Login.user.getPersonalTags() == null) {
			tags = new ArrayList<>();
		} else {
			tags = Arrays.asList(Login.user.getPersonalTags());			
		}
		if (!(tags.contains(gameTag))) {
			Login.user.addPersonalTags(gameTag);
			String pSql = "INSERT INTO UserTags(username, tag_name)"
					+ "VALUES(?, ?)";
			try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
					) {
				pStmt.setString(1, Login.user.getUsername());
				pStmt.setString(2, gameTag.getName());
				pStmt.execute();
			} catch (SQLException e) {
				Connector.processException(e);
			}
		}
	}

	/**
	 * Adds game to user wishlist and saves in the database
	 * 
	 * @param gameIndex the index of the game to add to the wishlist
	 */
	public static void addWishlist(int gameIndex) {
		// add to wishlist
		List<Game> wishlist = Arrays.asList(Login.user.getWishlist());
		if (!(wishlist.contains(games[gameIndex]))) {
			Login.user.addWishlist(games[gameIndex]);

			// save to database
			String pSql = "INSERT INTO Wishlist(username, appId) VALUES(?, ?)";
			try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
					) {
				pStmt.setString(1, Login.user.getUsername());
				pStmt.setInt(2, games[gameIndex].getId());
				pStmt.executeUpdate();
			} catch (SQLException e) {
				Connector.processException(e);
			}
		}
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
	public static Question getQuestion() {
		return getQuestions()[qNum];
	}

	// Returns an array of the questions 
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
				if (!(rs.getString("question_prompt").equalsIgnoreCase(question.getPrompt()))) {
					questions.add(question);
					question.setPrompt(rs.getString("question_prompt"));
				}
				String choiceText = rs.getString("choice_text");
				question.addChoice(new QuestionChoice(choiceText, getChoiceTags(choiceText)));
			}
		} catch (SQLException e) {
			Connector.processException(e);
			return null;
		}
		return prepareQuestions(questions.toArray(new Question[questions.size()]));
	}
	
	private static void nextQuestion() {
		if (qNum < getQuestions().length) {
			qNum++;
			//TODO it should be setting a question here//
		} else {
			// TODO end of questionnaire: out of questions
		}
	}
	
	/**
	 * @param questionArray array of the questions with all choices
	 * @return qSet array of questions with three choices each
	 */
	private static Question[] prepareQuestions(Question[] questionArray) {
		ArrayList<Question> qSet = new ArrayList<Question>();
		QuestionChoice[] choices = new QuestionChoice[3];
		for (Question q : questionArray) {
			for (int i=0; i<12; i+=3) {
				choices[0] = q.getChoices()[i];
				choices[1] = q.getChoices()[i+1];
				choices[2] = q.getChoices()[i+2];
				qSet.add(new Question(q.getPrompt(), choices));				
			}
		}
		return qSet.toArray(new Question[qSet.size()]);
	}

	/**
	 * Removes game from user wishlist and saves in the database
	 * 
	 * @param game to add to the wishlist
	 */
	public static void removeWishlist(Game game) {
		Login.user.removeWishlist(game);
		String pSql = "DELETE FROM Wishlist WHERE appId = ?";
		try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
				) {
			pStmt.setInt(1, game.getId());
			pStmt.execute();
		} catch (SQLException e) {
			Connector.processException(e);
		}
	}

	/**
	 * Adds tags from the choice to the user personal tags
	 * Updates game recommendations
	 * Sets current question to next question
	 * 
	 * @param i the index of the choice selected
	 */
	public static void selectChoice(int i) {
		// save tags
		for (GameTag tag : getQuestion().getChoices()[i].getTags()) {
			addPersonalTag(tag);
			//System.out.println("tag: " + tag.getName());
		}
		//System.out.println("i: " + i);
		updateResults();
		nextQuestion();
	}

	private static void updateResults() {
		// TODO CF: find game recommendations and save them to games array
		//			wishlist -> Login.user.getWishlist()
		//			hatelist -> Login.user.getHatelist()
		if (allGames == null) { getGames(); }

        Game dbPotentialGames[] = new Game[2000];
        for (int i = 0; i < dbPotentialGames.length; i++)
        	dbPotentialGames[i] = new Game();
        int numPotentials = 0;

        for (int x = 0; x < Login.user.getPersonalTags().length; x++)
        {
            for (int y = 0; y < allGames.length; y++)
            {
            	boolean contains = false;
            	for (int i = 0; i < dbPotentialGames.length; i++)
            	{
            		if (dbPotentialGames[i] == allGames[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	for (int i = 0; i < Login.user.getWishlist().length; i++)
            	{
            		if (Login.user.getWishlist()[i] == allGames[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	for (int i = 0; i < Login.user.getHatelist().length; i++)
            	{
            		if (Login.user.getHatelist()[i] == allGames[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	
            	if (!contains)
                {
                    //If game is not in either wishlist or trash//
                    for (int z = 0; z < allGames[y].getTags().length; z++)
                    {
                        if (Login.user.getPersonalTags()[x].getTag() == allGames[y].getTags()[z].getTag())
                        {
                        	//dbPotentialGames.Add(allGames[y]);
                        	dbPotentialGames[numPotentials] = allGames[y];
                        	numPotentials++;
                            break;
                        }
                    }
                }
            }
        }

        //Modify rating by tags//
        for (int x = 0; x < numPotentials; x++)
        {
            for (int y = 0; y < dbPotentialGames[x].getTags().length; y++)
            {
                for (int z = 0; z < Login.user.getPersonalTags().length; z++)
                {
                    var mod = 1;
                    if (dbPotentialGames[x].getTags()[y].getTag() == Login.user.getPersonalTags()[z].getTag())
                    {
                        mod = Math.round(Login.user.getPersonalTags()[z].getVal() * 0.5f);
                        dbPotentialGames[x].setRating(dbPotentialGames[x].getRating() + mod);
                    }
                }
            }
        }

        //Gets the top 5//
        Game dbTopGames[] = new Game[5];
        
        for (int x = 0; x < 5; x++)
        {
        	float bestRating = 0f;
        	int besti = 0;
            for (int i = 0; i < numPotentials; i++)
            {
            	if (dbPotentialGames[i].getRating() > bestRating)
            	{
            		bestRating = dbPotentialGames[i].getRating();
            		besti = i;
            	}
            }
            dbTopGames[x] = dbPotentialGames[besti];
            dbPotentialGames[besti].setRating(0); //So we don't use this same one again//
        }
        
        //Sets the games[] as the top 5//
        for (int x = 0; x < dbTopGames.length; x++)
        {
        	games[x] = dbTopGames[x];
        }
	}

	/**
	 * Returns the games from the library in an array
	 * 
	 * @return games the array of games from the library
	 */
	private static void getGames() {
		ArrayList<Game> newGames = new ArrayList<Game>();
		StringBuffer sb = new StringBuffer();
		try {
		  FileReader reader = new FileReader("lib/library.txt");		// path within project for txt file
		  Scanner sc = new Scanner(reader);
		  while (sc.hasNext()) {
		    sb.append(sc.next());
		  }
		  reader.close();
		  sc.close();
		} catch (IOException e) {
		  // TODO process file IO exception
		  e.printStackTrace();
		}

		String result = sb.toString();
		result = result.replace(",", " ");
		String[] words = result.split(" ");

		boolean inName = false;
		boolean inTags = false;
		int appId = -1;
		String name = null;
		float rating = -1;
		ArrayList<GameTag> tags = new ArrayList<GameTag>();

		// TODO verify games populate correctly
		// populate games
		for (int i=0; i<words.length; i++) {
		  if (words[i].equals("appid")) {
		    appId = Integer.parseInt(words[i+1]);
		  }
		  if (words[i].equals("name")) {
		    inName = true;
		  }
		  if (words[i].equals("rating")) {
		    inName = false;
		    rating = Float.parseFloat(words[i+1]);
		  }
		  if (words[i].equals("tags")) {
		    inTags = true;
		  }
		  if (words[i].equals("END")) {
		    inTags = false;
		    newGames.add(new Game(appId, name, rating, tags.toArray(new GameTag[tags.size()])));        		
		  }
		  if (inName == true) {
		    name += words[i];
		  }
		  if (inTags == true) {
		    tags.add(new GameTag(words[i]));
		  }
		}
		allGames = newGames.toArray(new Game[newGames.size()]);
		  
		  //This shows what's in the games[]
		  /*
		  for (int x = 0; x < 100; x++)
		  {
			  String tstr = games[x].getName() + " " + games[x].getId() + " " + games[x].getRating() + " tags: ";
			  for (int y = 0; y < games[x].getTags().length; y++)
				  tstr += games[x].getTags()[y].getName() + " ";
			  System.out.println(tstr);
		  }
		  */
	}

	/**
	 * Returns all tags used in questionnaire
	 * 
	 * @return GameTag array
	 */
	private static GameTag[] getTags() {
		String sql = "SELECT tag_id, tag_name FROM GameTag";
		try (	Statement stmt = Connector.getInstance().getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {
			List<GameTag> tags = new ArrayList<>();
			while (rs.next()) {
				GameTag tag = new GameTag(rs.getString("tag_name"));
				tags.add(tag);
			}
			return tags.toArray(new GameTag[tags.size()]);
		} catch (SQLException e) {
			Connector.processException(e);
			return null;
		}
	}
}

//	public static void removeHatelist(Game game) {
//		Login.user.removeHatelist(game);
//		String pSql = "DELETE FROM Hatelist WHERE appId = ?";
//		try (	PreparedStatement pStmt = Connector.getInstance().getConnection().prepareStatement(pSql);
//				) {
//			pStmt.setInt(1, game.getId());
//			pStmt.execute();
//		} catch (SQLException e) {
//			Connector.processException(e);
//		}
//	}