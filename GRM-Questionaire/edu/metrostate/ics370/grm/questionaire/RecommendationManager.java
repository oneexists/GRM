package edu.metrostate.ics370.grm.questionaire;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.*;


public class RecommendationManager
{
    public int qnum;
    public int totalQuestions;
    public GuiManager gm;
    public User user;
    public ApiManager api;
    public GuiChoice[] dbGuiChoices = new GuiChoice[3];
    public Question[] dbQuestions = new Question[1000];
    
    
    public RecommendationManager()
    {
    	api = new ApiManager();
    	user = new User();
    	gm = new GuiManager(this);
    }
    
    
    public void initializeQuestions()
    {
    	//**Question 0**//
    	dbQuestions[0] = new Question("What is most important to you?", "Being part of a team", "Spending time with friends online", "Doing something special");
    	dbQuestions[0].dbChoices[0].dbTags[0].tag = "team";
    	dbQuestions[0].dbChoices[1].dbTags[0].tag = "online multiplayer";
    	dbQuestions[0].dbChoices[2].dbTags[0].tag = "story";
    	dbQuestions[0].dbChoices[2].dbTags[1].tag = "engaging";

    	//**Question 1**//
    	dbQuestions[1] = new Question("Would you rather?", "Save the day", "Relax", "Tactically control things");
    	dbQuestions[1].dbChoices[0].dbTags[0].tag = "hero";
    	dbQuestions[1].dbChoices[1].dbTags[0].tag = "relax";
    	dbQuestions[1].dbChoices[2].dbTags[0].tag = "tactical";
    	dbQuestions[1].dbChoices[2].dbTags[1].tag = "rts";

    	//**Question 2**//
    	dbQuestions[2] = new Question("I like", "Fast frantic fun where my reflexes can shine", "Casual no pressure experiences", "Letting my creativity shine");
    	dbQuestions[2].dbChoices[0].dbTags[0].tag = "fast";
    	dbQuestions[2].dbChoices[1].dbTags[0].tag = "frantic";
    	dbQuestions[2].dbChoices[2].dbTags[0].tag = "reflex";
    	dbQuestions[2].dbChoices[1].dbTags[0].tag = "casual";
    	dbQuestions[2].dbChoices[2].dbTags[0].tag = "creative";

    	//**Question 3**//
    	dbQuestions[3] = new Question("I usually prefer", "Bright fun, cartoon lands to explore", "Dark, smelly dungeons", "Realistic cities and locations");
    	dbQuestions[3].dbChoices[0].dbTags[0].tag = "cartoon";
    	dbQuestions[3].dbChoices[0].dbTags[1].tag = "bright";
    	dbQuestions[3].dbChoices[0].dbTags[2].tag = "happy";
    	dbQuestions[3].dbChoices[1].dbTags[0].tag = "dark";
    	dbQuestions[3].dbChoices[1].dbTags[1].tag = "dungeon";
    	dbQuestions[3].dbChoices[2].dbTags[0].tag = "realistic";

    	//**Question 4**//
    	dbQuestions[4] = new Question("I feel most safe and happy when", "I am playing a game by myself", "I am next to friends on a couch", "I am playing with people online");
    	dbQuestions[4].dbChoices[0].dbTags[0].tag = "solo";
    	dbQuestions[4].dbChoices[0].dbTags[1].tag = "single player";
    	dbQuestions[4].dbChoices[1].dbTags[0].tag = "local multiplayer";
    	dbQuestions[4].dbChoices[2].dbTags[0].tag = "online multiplayer";
    	
    	this.totalQuestions = 5;
    }
    

    public void showResults()
    {
        //for (int x = 0; x < dbGuiResults.length; x++)
    	//    DestroyImmediate(dbGuiResults[x].gameObject);
    	//dbGuiResults.Clear();
    	//List<SubGame> dbCheckWishlist = new ArrayList<>(Arrays.asList(user.dbGamesWishlist));
    	//List<SubGame> dbCheckRemoved = new ArrayList<>(Arrays.asList(user.dbGamesRemoved));


        SubGame dbPotentialGames[] = new SubGame[1000];
        int numPotentials = 0;

        for (int x = 0; x < user.dbTags.length; x++)
        {
            for (int y = 0; y < api.dbGames.length; y++)
            {
            	boolean contains = false;
            	for (int i = 0; i < dbPotentialGames.length; i++)
            	{
            		if (dbPotentialGames[i] == api.dbGames[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	for (int i = 0; i < user.dbGamesWishlist.length; i++)
            	{
            		if (user.dbGamesWishlist[i] == api.dbGames[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	for (int i = 0; i < user.dbGamesHatelist.length; i++)
            	{
            		if (user.dbGamesHatelist[i] == api.dbGames[y])
            		{
            			contains = true;
            			break;
            		}
            	}
            	
                //if (!dbPotentialGames.Contains(api.dbGames[y]) && !user.dbGamesWishlist.Contains(api.dbGames[y]) && !user.dbGamesRemoved.Contains(api.dbGames[y]))
            	//List<SubGame> dbCheck = new ArrayList<>(Arrays.asList(dbPotentialGames));
            	//if (!dbCheck.contains(api.dbGames[y]) && !dbCheckWishlist.contains(api.dbGames[y]) && !dbCheckRemoved.contains(api.dbGames[y]))
            	if (!contains)
                {
                    //If game is not in either wishlist or trash//
                    for (int z = 0; z < api.dbGames[y].dbTags.length; z++)
                    {
                        if (user.dbTags[x].tag == api.dbGames[y].dbTags[z].tag)
                        {
                        	//dbPotentialGames.Add(api.dbGames[y]);
                        	dbPotentialGames[numPotentials] = api.dbGames[y];
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
            for (int y = 0; y < dbPotentialGames[x].dbTags.length; y++)
            {
                for (int z = 0; z < user.dbTags.length; z++)
                {
                    var mod = 1;
                    if (dbPotentialGames[x].dbTags[y].tag == user.dbTags[z].tag)
                    {
                        mod = Math.round(user.dbTags[z].val * 0.5f);
                        dbPotentialGames[x].rating += mod;
                    }
                }
            }
        }


        //tohs.db_morphs = tohs.db_morphs.OrderBy(w => (w.delay + w.dur)).ToList();
        //dbPotentialGames = dbPotentialGames.OrderBy(w => w.rating).ToList();
        //Arrays.sort(dbPotentialGames);
        
        //Inefficient temporary sorting code, can make nicer later//
        SubGame dbTopGames[] = new SubGame[5];
        
        for (int x = 0; x < 5; x++)
        {
        	float bestRating = 0f;
        	int besti = 0;
            for (int i = 0; i < numPotentials; i++)
            {
            	if (dbPotentialGames[i].rating > bestRating)
            	{
            		bestRating = dbPotentialGames[i].rating;
            		besti = i;
            	}
            }
            dbTopGames[x] = dbPotentialGames[besti];
            dbPotentialGames[besti].rating = 0; //So we don't use this same one again//
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
        	gm.dbBtnGames[x].setText(dbTopGames[x].nam);
        	gm.dbGuiResults[x].game = dbTopGames[x];
        }
    }
    
    
    
    public boolean contains()
    {
    	
    	return false;
    }


    public void setQuestion()
    {
        //**Clear Old Choices**//
        for (int x = 0; x < dbGuiChoices.length; x++)
            //dbGuiChoices[x].dbTags.Clear();
        {
        	for (int y = 0; y < dbGuiChoices[x].dbTags.length; y++)
        	{
        		dbGuiChoices[x].dbTags[y].tag = "";
        		dbGuiChoices[x].dbTags[y].val = 0;
        	}
        	dbGuiChoices[x].num = 0;
        }
        

        //**Add New Choices**//
        gm.txtQuestion.setText(dbQuestions[qnum].quest);

    	for (int x = 0; x < dbQuestions[qnum].dbChoices.length; x++)
        {
			//dbGuiChoices[x].txt_choice.text = dbQuestions[qnum].dbChoices[x].choice;
    		gm.dbBtnAnswers[x].setText(dbQuestions[qnum].dbChoices[x].choice);

        	for (int y = 0; y < dbQuestions[qnum].dbChoices[x].dbTags.length; y++)
            {
                var ntag = new SubTag();
                ntag.tag = dbQuestions[qnum].dbChoices[x].dbTags[y].tag;
                ntag.val = dbQuestions[qnum].dbChoices[x].dbTags[y].val;
                
                //dbGuiChoices[x].dbTags.Add(ntag);
                dbGuiChoices[x].dbTags[dbGuiChoices[x].num] = ntag;
                dbGuiChoices[x].num++;
            }
        }
    }


    public void guiChoiceSelected(GuiChoice tgchoice)
    {
        for (int x = 0; x < tgchoice.dbTags.length; x++)
            user.add_choice(tgchoice.dbTags[x].tag, 1); //tgchoice.dbTags[x].val);
        qnum++;

        if (qnum >= totalQuestions) //Out of questions//
            qnum = 0;

        showResults();
        setQuestion();
    }


    public void btnAddGame(GuiResult tgresult)
    {
        //user.dbGamesWishlist.Add(tgresult.game_s);
        user.dbGamesWishlist[user.numWishlist] = tgresult.game;
        user.numWishlist++;
        //tgresult.state = "moving"; //Animation game being added to profile/wishlist//
        //StartCoroutine(delayed_show_results(1));
        showResults();
    }


    public void btnRemoveGame(GuiResult tgresult)
    {
        //user.dbGamesRemoved.Add(tgresult.game_s);
        user.dbGamesHatelist[user.numHatelist] = tgresult.game;
        user.numHatelist++;
        //tgresult.state = "deleting"; //Animation game being deleted//
        //StartCoroutine(delayed_show_results(1));
        showResults();
    }
}
