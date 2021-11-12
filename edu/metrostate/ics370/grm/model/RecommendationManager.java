package edu.metrostate.ics370.grm.model;
import edu.metrostate.ics370.grm.controller.GameLoader;
import edu.metrostate.ics370.grm.controller.InterfaceSqlLoad;
import edu.metrostate.ics370.grm.controller.InterfaceSqlSave;
import edu.metrostate.ics370.grm.controller.LoadData;
import edu.metrostate.ics370.grm.controller.Login;
import edu.metrostate.ics370.grm.controller.QuestionnaireInterface;
import edu.metrostate.ics370.grm.view.GuiChoice;
import edu.metrostate.ics370.grm.view.GuiManager;


public class RecommendationManager
{
	private Question[] dbQuestions;
	private Game[] dbGamesWishlist;
	private Game[] dbGamesHatelist;
	private GameTag[] dbTags;

	private int qnum;
    private GuiManager gm;
    private GuiChoice[] dbGuiChoices = new GuiChoice[3];
    
    
    public RecommendationManager() {
    	for (int i = 0; i < dbGamesWishlist.length; i++)
    	{
    		dbGamesWishlist[i] = new Game();
    		dbGamesHatelist[i] = new Game();
    		if (i < 100)
    			dbTags[i] = new GameTag();
    	}
		    	
    	// get questions from database
    	setDbQuestions();
    	
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


        Game dbPotentialGames[] = new Game[gl.numGames];
        //for (int i = 0; i < dbPotentialGames.length; i++)
        //	dbPotentialGames[i] = new Game();

        int numPotentials = 0;

        for (int x = 0; x < dbTags.length; x++)
        {
            for (int y = 0; y < gl.numGames; y++)
            {
            	boolean contains = false;
            	for (int i = 0; i < dbPotentialGames.length; i++)
            	{
            		if (dbPotentialGames[i] == gl.dbGames[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	for (int i = 0; i < Login.user.getWishlist().length; i++)
            	{
            		if (dbGamesWishlist[i] == gl.dbGames[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	for (int i = 0; i < Login.user.getHatelist().length; i++)
            	{
            		if (dbGamesHatelist[i] == gl.dbGames[y])
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
                    for (int z = 0; z < gl.dbGames[y].getTags().length; z++)
                    {
                        if (dbTags[x].getName() == gl.dbGames[y].getTags()[z].getName())
                        {
                        	//dbPotentialGames.Add(gl.dbGames[y]);
                        	dbPotentialGames[numPotentials] = gl.dbGames[y];
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
                for (int z = 0; z < dbTags.length; z++)
                {
                    var mod = 1;
                    if (dbPotentialGames[x].getTags()[y].getName() == dbTags[z].getName())
                    {
                        mod = Math.round(dbTags[z].getVal() * 0.5f);
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

    public void guiChoiceSelected(GuiChoice tgchoice)
    {
        for (int x = 0; x < tgchoice.getDbTags().length; x++)
        	// TODO Login.user.addPersonalTags(tag);
            // add_choice(tgchoice.getDbTags()[x].getTag(), 1); //tgchoice.dbTags[x].val);
        qnum++;

        if (qnum >= dbQuestions.length) //Out of questions//
            qnum = 0;

        showResults();
        setQuestion();
        iss.saveToSqlTags(dbTags);
    }


    public void btnAddGame(Game tgresult)
    {
    	// add game to wishlist
    	InterfaceSqlSave.addWishlist(tgresult);
    	
        //user.dbGamesWishlist.Add(tgresult.game_s);
        dbGamesWishlist[Login.user.getWishlist().length] = tgresult;
        //tgresult.state = "moving"; //Animation game being added to profile/wishlist//
        //StartCoroutine(delayed_show_results(1));
        showResults();
    }


    public void btnRemoveGame(Game tgresult)
    {
    	// add game to hatelist
    	InterfaceSqlSave.addHatelist(tgresult);
    	
        //user.dbGamesRemoved.Add(tgresult.game_s);
        dbGamesHatelist[Login.user.getHatelist().length] = tgresult;
        //tgresult.state = "deleting"; //Animation game being deleted//
        //StartCoroutine(delayed_show_results(1));
        showResults();
    }


	public GuiChoice[] getDbGuiChoices()
	{
		return dbGuiChoices;
	}

	public Game[] getDbGamesWishlist() {
		return Login.user.getWishlist();
	}

	public Game[] getDbGamesHatelist() {
		return Login.user.getHatelist();
	}

	/**
	 * @return the dbTags
	 */
	public GameTag[] getDbTags() {
		return dbTags.clone();
	}

	/**
	 * Sets the tags from the database
	 */
	public void setDbTags() {
		this.dbTags = LoadData.getTags();
	}

	/**
	 * @return the dbQuestions
	 */
	public Question[] getDbQuestions() {
		return dbQuestions.clone();
	}

	/**
	 * Sets the questions from the database
	 */
	public void setDbQuestions() {
		this.dbQuestions = InterfaceSqlLoad.getQuestions();
	}
}
