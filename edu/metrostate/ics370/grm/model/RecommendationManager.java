package edu.metrostate.ics370.grm.model;
import edu.metrostate.ics370.grm.controller.LoadData;
import edu.metrostate.ics370.grm.controller.Login;
import edu.metrostate.ics370.grm.controller.QuestionnaireInterface;
import edu.metrostate.ics370.grm.view.GuiChoice;
import edu.metrostate.ics370.grm.view.GuiManager;


public class RecommendationManager {
	private int qnum;
    private GuiManager gm;
    private QuestionChoice[] questionChoices;
    
    
    public RecommendationManager() { 	
    	// get questions from database
    	QuestionnaireInterface.getQuestions();
    	
    	// load games from file
    	QuestionnaireInterface.getGames();
    	
    	gm = new GuiManager(this);
    }

    public void showResults()
    {
        //for (int x = 0; x < dbGuiResults.length; x++)
    	//    DestroyImmediate(dbGuiResults[x].gameObject);
    	//dbGuiResults.Clear();
    	//List<Game> dbCheckWishlist = new ArrayList<>(Arrays.asList(user.dbGamesWishlist));
    	//List<Game> dbCheckRemoved = new ArrayList<>(Arrays.asList(user.dbGamesRemoved));


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

    public void guiChoiceSelected(QuestionChoice tgchoice)
    {
        for (int x = 0; x < tgchoice.getTags().length; x++) {
        	QuestionnaireInterface.addPersonalTag(tgchoice.getTags()[x]);
        }
        qnum++;

        if (qnum >= QuestionnaireInterface.questions.length) //Out of questions//
            qnum = 0;

        showResults();
        setQuestion();
    }


    public void btnAddGame(Game tgresult)
    {
    	// add game to wishlist
    	QuestionnaireInterface.addWishlist(tgresult);
    	
        //tgresult.state = "moving"; //Animation game being added to profile/wishlist//
        //StartCoroutine(delayed_show_results(1));
        showResults();
    }


    public void btnRemoveGame(Game tgresult)
    {
    	// add game to hatelist
    	QuestionnaireInterface.addHatelist(tgresult);
    	
        //user.dbGamesRemoved.Add(tgresult.game_s);
        getHatelist()[getHatelist().length] = tgresult;
        //tgresult.state = "deleting"; //Animation game being deleted//
        //StartCoroutine(delayed_show_results(1));
        showResults();
    }


	public QuestionChoice[] getQuestionChoices()
	{
		return questionChoices;
	}

	public Game[] getWishlist() {
		return Login.user.getWishlist();
	}

	public Game[] getHatelist() {
		return Login.user.getHatelist();
	}

	/**
	 * @return the dbTags
	 */
	public GameTag[] getTags() {
		return LoadData.getTags().clone();
	}
}
