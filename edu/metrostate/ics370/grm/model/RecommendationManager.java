package edu.metrostate.ics370.grm.model;
import edu.metrostate.ics370.grm.controller.GameLoader;
import edu.metrostate.ics370.grm.controller.InterfaceSqlLoad;
import edu.metrostate.ics370.grm.controller.InterfaceSqlSave;
import edu.metrostate.ics370.grm.view.GuiChoice;
import edu.metrostate.ics370.grm.view.GuiManager;
import edu.metrostate.ics370.grm.view.GuiResult;


public class RecommendationManager
{
    private int qnum;
    private GuiManager gm;
    //private User user;
    private GameLoader gl;
    private int numTagsUsed;
    private int numWishlist;
    private int numHatelist;
    private GuiChoice[] dbGuiChoices = new GuiChoice[3];
    private Question[] dbQuestions;
    private Game[] dbGamesWishlist = new Game[200];
    private Game[] dbGamesHatelist = new Game[200];
    private GameTag[] dbTags = new GameTag[100];
    
    
    public RecommendationManager() throws Exception
    {
    	for (int i = 0; i < dbGamesWishlist.length; i++)
    	{
    		dbGamesWishlist[i] = new Game();
    		dbGamesHatelist[i] = new Game();
    		if (i < 100)
    			dbTags[i] = new GameTag();
    	}
		
    	iss = new InterfaceSqlSave();
    	
    	// get questions from database
    	setDbQuestions();
    	
		gl = new GameLoader();
    	//setUser(new User());
    	gm = new GuiManager(this);
    }
    
    
    public void add_choice(String ttag, int val)
    {
        boolean included = false;
        for (int x = 0; x < dbTags.length; x++)
        {
            if (dbTags[x].getTag() == ttag)
            {
                included = true;
                dbTags[x].setVal(dbTags[x].getVal() + val);
                break;
            }
        }

        if (!included)
        {
            var ntag = new GameTag();
            ntag.setTag(ttag);
            ntag.setVal(val);
            dbTags[numTagsUsed] = ntag;
            numTagsUsed++;
            //dbTags.add(ntag);
        }
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
            	for (int i = 0; i < numWishlist; i++)
            	{
            		if (dbGamesWishlist[i] == gl.dbGames[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	for (int i = 0; i < numHatelist; i++)
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
                        if (dbTags[x].getTag() == gl.dbGames[y].getTags()[z].getTag())
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
                    if (dbPotentialGames[x].getTags()[y].getTag() == dbTags[z].getTag())
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
        	gm.getDbGuiResults()[x].game = dbTopGames[x];
        }
    }

    public void guiChoiceSelected(GuiChoice tgchoice)
    {
        for (int x = 0; x < tgchoice.getDbTags().length; x++)
            add_choice(tgchoice.getDbTags()[x].getTag(), 1); //tgchoice.dbTags[x].val);
        qnum++;

        if (qnum >= totalQuestions) //Out of questions//
            qnum = 0;

        showResults();
        setQuestion();
        iss.saveToSqlTags(dbTags);
    }


    public void btnAddGame(GuiResult tgresult)
    {
        //user.dbGamesWishlist.Add(tgresult.game_s);
        dbGamesWishlist[numWishlist] = tgresult.game;
        numWishlist++;
        //tgresult.state = "moving"; //Animation game being added to profile/wishlist//
        //StartCoroutine(delayed_show_results(1));
        showResults();
        iss.saveToSqlWishlist(dbGamesWishlist);
    }


    public void btnRemoveGame(GuiResult tgresult)
    {
        //user.dbGamesRemoved.Add(tgresult.game_s);
        dbGamesHatelist[numHatelist] = tgresult.game;
        numHatelist++;
        //tgresult.state = "deleting"; //Animation game being deleted//
        //StartCoroutine(delayed_show_results(1));
        showResults();
        iss.saveToSqlHatelist(dbGamesHatelist);
    }


	public GuiChoice[] getDbGuiChoices()
	{
		return dbGuiChoices;
	}


	public void setDbGuiChoices(GuiChoice[] dbGuiChoices)
	{
		this.dbGuiChoices = dbGuiChoices;
	}


	public int getNumWishlist()
	{
		return numWishlist;
	}


	public void setNumWishlist(int numWishlist)
	{
		this.numWishlist = numWishlist;
	}


	public int getNumHatelist()
	{
		return numHatelist;
	}


	public void setNumHatelist(int numHatelist)
	{
		this.numHatelist = numHatelist;
	}


	public int getNumTagsUsed()
	{
		return numTagsUsed;
	}


	public void setNumTagsUsed(int numTagsUsed)
	{
		this.numTagsUsed = numTagsUsed;
	}


	public Game[] getDbGamesWishlist()
	{
		return dbGamesWishlist;
	}


	public void setDbGamesWishlist(Game[] dbGamesWishlist)
	{
		this.dbGamesWishlist = dbGamesWishlist;
		
		for (int i = 0; i < this.dbGamesWishlist.length; i++)
		{
			if (this.dbGamesWishlist[i] == null)
			{
				this.numWishlist = i - 1;
				break;
			}
		}
	}


	public Game[] getDbGamesHatelist()
	{
		return dbGamesHatelist;
	}


	public void setDbGamesHatelist(Game[] dbGamesHatelist)
	{
		this.dbGamesHatelist = dbGamesHatelist;
		
		for (int i = 0; i < this.dbGamesHatelist.length; i++)
		{
			if (this.dbGamesHatelist[i] == null)
			{
				this.numHatelist = i - 1;
				break;
			}
		}
	}


	public GameTag[] getDbTags()
	{
		return dbTags;
	}


	public void setDbTags(GameTag[] dbTags)
	{
		this.dbTags = dbTags;
		
		for (int i = 0; i < this.dbTags.length; i++)
		{
			if (this.dbTags[i] == null)
			{
				this.numTagsUsed = i - 1;
				break;
			}
		}
	}

	/**
	 * @return the dbQuestions
	 */
	public Question[] getDbQuestions() {
		return dbQuestions.clone();
	}


	/**
	 * @param dbQuestions the dbQuestions to set
	 */
	public void setDbQuestions() {
		this.dbQuestions = InterfaceSqlLoad.getQuestions();
	}
}
