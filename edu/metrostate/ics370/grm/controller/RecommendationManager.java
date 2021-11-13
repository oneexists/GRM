package edu.metrostate.ics370.grm.controller;
import edu.metrostate.ics370.grm.model.Game;
import edu.metrostate.ics370.grm.model.GameTag;
import edu.metrostate.ics370.grm.model.QuestionChoice;


public class RecommendationManager {
	private int qnum;
    private QuestionChoice[] questionChoices;
    
    
    public RecommendationManager() { 	
    	// get questions from database
    	QuestionnaireInterface.getQuestions();
    	
    	// load games from file
    	QuestionnaireInterface.getGames();
    }

    public void showResults() {
        Game dbPotentialGames[] = new Game[QuestionnaireInterface.games.length];
        //for (int i = 0; i < dbPotentialGames.length; i++)
        //	dbPotentialGames[i] = new Game();

        int numPotentials = 0;

        for (int x = 0; x < getTags().length; x++)
        {
            for (int y = 0; y < QuestionnaireInterface.games.length; y++)
            {
            	boolean contains = false;
            	for (int i = 0; i < dbPotentialGames.length; i++)
            	{
            		if (dbPotentialGames[i] == QuestionnaireInterface.games[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	for (int i = 0; i < getWishlist().length; i++)
            	{
            		if (getWishlist()[i] == QuestionnaireInterface.games[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	for (int i = 0; i < getHatelist().length; i++)
            	{
            		if (getHatelist()[i] == QuestionnaireInterface.games[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	
                //if (!dbPotentialGames.Contains(gl.dbGames[y]) && !user.dbGamesWishlist.Contains(gl.dbGames[y]) && !user.dbGamesRemoved.Contains(gl.dbGames[y]))
            	//List<Game> dbCheck = new ArrayList<>(Arrays.asList(dbPotentialGames));
            	//if (!dbCheck.contains(gl.dbGames[y]) && !dbCheckWishlist.contains(gl.dbGames[y]) && !dbCheckRemoved.contains(gl.dbGames[y]))
            	if (!contains)
                {
                    //If game is not in either wishlist or trash//
                    for (int z = 0; z < QuestionnaireInterface.games[y].getTags().length; z++)
                    {
                        if (getTags()[x].getName() == QuestionnaireInterface.games[y].getTags()[z].getName())
                        {
                        	//dbPotentialGames.Add(gl.dbGames[y]);
                        	dbPotentialGames[numPotentials] = QuestionnaireInterface.games[y];
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
                for (int z = 0; z < getTags().length; z++)
                {
                    var mod = 1;
                    if (dbPotentialGames[x].getTags()[y].getName() == getTags()[z].getName())
                    {
                        mod = Math.round(getTags()[z].getVal() * 0.5f);
                        dbPotentialGames[x].setRating(dbPotentialGames[x].getRating() + mod);
                    }
                }
            }
        }


        //tohs.db_morphs = tohs.db_morphs.OrderBy(w => (w.delay + w.dur)).ToList();
        //dbPotentialGames = dbPotentialGames.OrderBy(w => w.rating).ToList();
        //Arrays.sort(dbPotentialGames);
        
        //Inefficient temporary sorting code, can make nicer later//
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
            //System.out.println("x: " + x); //+ " dbTop " + dbTopGames[x].getName());
            dbTopGames[x] = dbPotentialGames[besti];
            dbPotentialGames[besti].setRating(0); //So we don't use this same one again//
        }
        

        //int num = dbPotentialGames.length;
        //num = Math.Clamp(num, 0, 5);
        //System.out.println(dbTopGames.length);
        for (int x = 0; x < dbTopGames.length; x++)
        {
            //var tgo = (GameObject) Instantiate(prefab_gui_result, prefab_gui_result.transform.position, prefab_gui_result.transform.rotation, prefab_gui_result.transform.parent);
        	//var gres = tgo.GetComponent<gui_result>();
        	//gres.txt_nam.text = dbPotentialGames[x].nam;
        	//gres.game_s = dbPotentialGames[x];
        	//db_gui_results.Add(gres);
        	//tgo.SetActive(true);
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

	/**
	 * @return the question choices
	 */
	public QuestionChoice[] getQuestionChoices() {
		return questionChoices;
	}

	/**
	 * @return the user's wishlist
	 */
	public Game[] getWishlist() {
		return Login.user.getWishlist();
	}

	/**
	 * @return the user's hatelist
	 */
	public Game[] getHatelist() {
		return Login.user.getHatelist();
	}

	/**
	 * @return the dbTags
	 */
	public GameTag[] getTags() {
		return QuestionnaireInterface.getTags().clone();
	}
}
