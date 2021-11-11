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
    private int totalQuestions;
    private GuiManager gm;
    //private User user;
    private GameLoader gl;
    private InterfaceSqlSave iss;
    private InterfaceSqlLoad isl;
    private int numTagsUsed;
    private int numWishlist;
    private int numHatelist;
    private GuiChoice[] dbGuiChoices = new GuiChoice[3];
    private Question[] dbQuestions = new Question[1000];
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
    	Question questions = InterfaceSqlLoad.getQuestions();
    	
		gl = new GameLoader();
    	//setUser(new User());
    	gm = new GuiManager(this);

    	//This will be replaced when the Sql loader is setup//
    	initializeQuestions(); //Makes the database of questions//
		setQuestion(); //Sets the first question//
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
    
    
    public void initializeQuestions()
    {
    	//**Question 0**//
    	dbQuestions[0] = new Question("What is most important to you?", "Being part of a team", "Spending time with friends online", "Doing something special");
    	dbQuestions[0].getDbChoices()[0].getDbTags()[0].setTag("team");
    	dbQuestions[0].getDbChoices()[1].getDbTags()[0].setTag("online multiplayer");
    	dbQuestions[0].getDbChoices()[2].getDbTags()[0].setTag("story");
    	dbQuestions[0].getDbChoices()[2].getDbTags()[1].setTag("engaging");

    	//**Question 1**//
    	dbQuestions[1] = new Question("Would you rather?", "Save the day", "Relax", "Tactically control things");
    	dbQuestions[1].getDbChoices()[0].getDbTags()[0].setTag("hero");
    	dbQuestions[1].getDbChoices()[1].getDbTags()[0].setTag("relax");
    	dbQuestions[1].getDbChoices()[2].getDbTags()[0].setTag("tactical");
    	dbQuestions[1].getDbChoices()[2].getDbTags()[1].setTag("rts");

    	//**Question 2**//
    	dbQuestions[2] = new Question("I like", "Fast frantic fun where my reflexes can shine", "Casual no pressure experiences", "Letting my creativity shine");
    	dbQuestions[2].getDbChoices()[0].getDbTags()[0].setTag("fast");
    	dbQuestions[2].getDbChoices()[1].getDbTags()[0].setTag("frantic");
    	dbQuestions[2].getDbChoices()[2].getDbTags()[0].setTag("reflex");
    	dbQuestions[2].getDbChoices()[1].getDbTags()[0].setTag("casual");
    	dbQuestions[2].getDbChoices()[2].getDbTags()[0].setTag("creative");

    	//**Question 3**//
    	dbQuestions[3] = new Question("I usually prefer", "Bright fun, cartoon lands to explore", "Dark, smelly dungeons", "Realistic cities and locations");
    	dbQuestions[3].getDbChoices()[0].getDbTags()[0].setTag("cartoon");
    	dbQuestions[3].getDbChoices()[0].getDbTags()[1].setTag("bright");
    	dbQuestions[3].getDbChoices()[0].getDbTags()[2].setTag("happy");
    	dbQuestions[3].getDbChoices()[1].getDbTags()[0].setTag("dark");
    	dbQuestions[3].getDbChoices()[1].getDbTags()[1].setTag("dungeon");
    	dbQuestions[3].getDbChoices()[2].getDbTags()[0].setTag("realistic");

    	//**Question 4**//
    	dbQuestions[4] = new Question("I feel most safe and happy when", "I am playing a game by myself", "I am next to friends on a couch", "I am playing with people online");
    	dbQuestions[4].getDbChoices()[0].getDbTags()[0].setTag("solo");
    	dbQuestions[4].getDbChoices()[0].getDbTags()[1].setTag("single player");
    	dbQuestions[4].getDbChoices()[1].getDbTags()[0].setTag("local multiplayer");
    	dbQuestions[4].getDbChoices()[2].getDbTags()[0].setTag("online multiplayer");
    	
    	this.totalQuestions = 5;
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
    
    

    /*
    public boolean contains()
    {
    	
    	return false;
    }
    */


    public void setQuestion()
    {
        //**Clear Old Choices**//
        for (int x = 0; x < getDbGuiChoices().length; x++)
            //dbGuiChoices[x].dbTags.Clear();
        {
        	for (int y = 0; y < getDbGuiChoices()[x].getDbTags().length; y++)
        	{
        		getDbGuiChoices()[x].getDbTags()[y].setTag("");
        		getDbGuiChoices()[x].getDbTags()[y].setVal(0);
        	}
        	getDbGuiChoices()[x].setNum(0);
        }
        

        //**Add New Choices**//
        gm.getTxtQuestion().setText(dbQuestions[qnum].getQuest());

    	for (int x = 0; x < dbQuestions[qnum].getDbChoices().length; x++)
        {
			//dbGuiChoices[x].txt_choice.text = dbQuestions[qnum].dbChoices[x].choice;
    		gm.getDbBtnAnswers()[x].setText(dbQuestions[qnum].getDbChoices()[x].getChoice());

        	for (int y = 0; y < dbQuestions[qnum].getDbChoices()[x].getDbTags().length; y++)
            {
                var ntag = new GameTag();
                ntag.setTag(dbQuestions[qnum].getDbChoices()[x].getDbTags()[y].getTag());
                ntag.setVal(dbQuestions[qnum].getDbChoices()[x].getDbTags()[y].getVal());
                
                //dbGuiChoices[x].dbTags.Add(ntag);
                getDbGuiChoices()[x].getDbTags()[getDbGuiChoices()[x].getNum()] = ntag;
                getDbGuiChoices()[x].setNum(getDbGuiChoices()[x].getNum() + 1);
            }
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
	
	
	public void setDbQuestions(Question[] dbQ)
	{
		this.dbQuestions = dbQ;
		
		for (int i = 0; i < this.dbQuestions.length; i++)
		{
			if (this.dbQuestions[i] == null)
			{
				this.totalQuestions = i - 1;
				break;
			}
		}
	}


	public InterfaceSqlLoad getIsl()
	{
		return isl;
	}


	public void setIsl(InterfaceSqlLoad isl)
	{
		this.isl = isl;
	}


	public InterfaceSqlSave getIss()
	{
		return iss;
	}


	public void setIss(InterfaceSqlSave iss)
	{
		this.iss = iss;
	}
}
