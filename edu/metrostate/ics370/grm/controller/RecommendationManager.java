package edu.metrostate.ics370.grm.controller;
import edu.metrostate.ics370.grm.model.Game;
import edu.metrostate.ics370.grm.model.GameTag;
import edu.metrostate.ics370.grm.model.Question;
import edu.metrostate.ics370.grm.model.QuestionChoice;


public class RecommendationManager {
	private int qnum;    
    
    public RecommendationManager() { 	
    	// get questions from database
    	QuestionnaireInterface.getQuestions();
    	// load games from file
    	QuestionnaireInterface.getGames();
    }

    public void showResults() {
        int numPotentials = 0;

        for (int x = 0; x < getTags().length; x++)
        {
            for (int y = 0; y < getGames().length; y++)
            {
            	boolean contains = false;
            	for (int i = 0; i < getGames().length; i++)
            	{
            		if (getGames()[i] == getGames()[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	for (int i = 0; i < getWishlist().length; i++)
            	{
            		if (getWishlist()[i] == getGames()[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	for (int i = 0; i < getHatelist().length; i++)
            	{
            		if (getHatelist()[i] == getGames()[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	
            	if (!contains)
                {
                    //If game is not in either wishlist or trash//
                    for (int z = 0; z < getGames()[y].getTags().length; z++)
                    {
                        if (getTags()[x].getName() == getGames()[y].getTags()[z].getName())
                        {
                        	//dbPotentialGames.Add(gl.dbGames[y]);
                        	getGames()[numPotentials] = getGames()[y];
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
            for (int y = 0; y < getGames()[x].getTags().length; y++)
            {
                for (int z = 0; z < getTags().length; z++)
                {
                    var mod = 1;
                    if (getGames()[x].getTags()[y].getName() == getTags()[z].getName())
                    {
                        mod = Math.round(getTags()[z].getVal() * 0.5f);
                        getGames()[x].setRating(getGames()[x].getRating() + mod);
                    }
                }
            }
        }
        
        //Inefficient temporary sorting code, can make nicer later//
        Game dbTopGames[] = new Game[5];
        
        for (int x = 0; x < 5; x++)
        {
        	float bestRating = 0f;
        	int besti = 0;
            for (int i = 0; i < numPotentials; i++)
            {
            	if (getGames()[i].getRating() > bestRating)
            	{
            		bestRating = getGames()[i].getRating();
            		besti = i;
            	}
            }
            dbTopGames[x] = getGames()[besti];
            getGames()[besti].setRating(0); //So we don't use this same one again//
        }
        

        for (int x = 0; x < dbTopGames.length; x++)
        {
        	gm.getDbBtnGames()[x].setText(dbTopGames[x].getName());
        	gm.getDbGuiResults()[x] = dbTopGames[x];
        }
    }

    /**
     * Saves tags from QuestionChoice to user's personal tags
     * Updates results
     * Sets next question
     * 
     * @param choice that was selected
     */
    public void selectChoice(QuestionChoice choice) {
    	// save tags
    	for (GameTag tag : choice.getTags()) {
    		QuestionnaireInterface.addPersonalTag(tag);
    	}
    	// TODO show results
    	// set next question
    	nextQuestion();
    }

    // increments question number to next question
	private void nextQuestion() {
		// TODO set next question
		if (qnum < QuestionnaireInterface.games.length) {
			qnum++;			
		}
	}
	
	private Game[] getGames() {
		return QuestionnaireInterface.games.clone();
	}

	private Game[] getWishlist() {
		return Login.user.getWishlist();
	}

	private Game[] getHatelist() {
		return Login.user.getHatelist();
	}

	private GameTag[] getTags() {
		return QuestionnaireInterface.getTags().clone();
	}
}
