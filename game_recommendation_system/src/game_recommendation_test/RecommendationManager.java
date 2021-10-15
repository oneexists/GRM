package game_recommendation_test;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class RecommendationManager
{
    public int qnum;
    public int totalQuestions;
    public User user;
    public ApiManager api;
    public JFrame frame;
    public JLabel txtQuestion;
    public JLabel bg;
    //public Text txtResults;
    //public GameObject prefab_gui_result;
    //public List<GameObject> dbGos;
    public JButton btnProfile;
    public GuiChoice[] dbGuiChoices = new GuiChoice[3];
    public Question[] dbQuestions = new Question[1000];
    public GuiResult[] dbGuiResults = new GuiResult[5];
    public JButton[] dbBtnAnswers = new JButton[3];
    public JButton[] dbBtnGames = new JButton[5];
    public JButton[] dbBtnWishlist = new JButton[5];
    public JButton[] dbBtnRemove = new JButton[5];
    public JButton[] dbBtnGamesWishlist = new JButton[1000];
    
    
    public RecommendationManager()
    {
    	api = new ApiManager();
    	user = new User();    	
    	
    	for (int i = 0; i < dbGuiChoices.length; i++)
    		dbGuiChoices[i] = new GuiChoice();
    	
    	for (int i = 0; i < dbGuiResults.length; i++)
    		dbGuiResults[i] = new GuiResult();


    	//**Make Frame**//
    	frame = new JFrame(); //creating instance of JFrame
		

		//**Fonts**//
		Font fontQuestion = new Font("serif", Font.BOLD, 25);
		Font fontAnswers = new Font("serif", Font.BOLD, 20);
		Font fontGames = new Font("serif", Font.BOLD, 20);
		Font fontSmallButtons = new Font("serif", Font.BOLD, 25);
		
		
		//**Colors**//
		Color teal = new Color(0, 128, 128);
		
		
		//**Button Profile**//
		btnProfile = new JButton("Profile"); //creating instance of JButton
		btnProfile.setBounds(1700, 50, 150, 150); //x axis, y axis, width, height
		btnProfile.setFont(fontQuestion);
		btnProfile.setBackground(Color.red);
		btnProfile.addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	btnProfile();
		    }
		});
		frame.add(btnProfile); //adding button in JFrame

		
		//**Question Text**//
    	txtQuestion = new JLabel();
    	txtQuestion.setBounds(200, 100, 400, 200);
		String wrd = "Would you rather:";
		txtQuestion.setText(wrd);
		txtQuestion.setHorizontalAlignment(JLabel.CENTER);
		txtQuestion.setBackground(Color.black);
		txtQuestion.setForeground(Color.white);
		txtQuestion.setOpaque(true);
		txtQuestion.setFont(fontQuestion);
		//txtQuest = quest;
		frame.add(txtQuestion);
		
		
		//**Answers**//
		dbBtnAnswers[0] = new JButton("Answer 0"); //creating instance of JButton
		dbBtnAnswers[0].setBounds(200, 320, 400, 120); //x axis, y axis, width, height
		dbBtnAnswers[0].setFont(fontAnswers);
		dbBtnAnswers[0].setBackground(Color.white);
		dbBtnAnswers[0].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnAnswer(0);
		    }
		});
		frame.add(dbBtnAnswers[0]); //adding button in JFrame
		

		dbBtnAnswers[1] = new JButton("Answer 1");
		dbBtnAnswers[1].setBounds(200, 460, 400, 120);
		dbBtnAnswers[1].setFont(fontAnswers);
		dbBtnAnswers[1].setBackground(Color.white);
		dbBtnAnswers[1].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnAnswer(1);
		    }
		});
		frame.add(dbBtnAnswers[1]);

		dbBtnAnswers[2] = new JButton("");
		dbBtnAnswers[2].setBounds(200, 600, 400, 120);
		dbBtnAnswers[2].setFont(fontAnswers);
		dbBtnAnswers[2].setBackground(Color.white);
		dbBtnAnswers[2].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnAnswer(2);
		    }
		});
		frame.add(dbBtnAnswers[2]);

        
		//**Top 5 Game Recommendations**//
		dbBtnGames[0] = new JButton("");
		dbBtnGames[0].setBounds(1000, 300, 400, 50);
		dbBtnGames[0].setFont(fontGames);
		dbBtnGames[0].setBackground(Color.black);
		dbBtnGames[0].setForeground(Color.white);
		frame.add(dbBtnGames[0]);

		dbBtnGames[1] = new JButton("");
		dbBtnGames[1].setBounds(1000, 350, 400, 50);
		dbBtnGames[1].setFont(fontGames);
		dbBtnGames[1].setBackground(Color.black);
		dbBtnGames[1].setForeground(Color.white);
		frame.add(dbBtnGames[1]);
		
		dbBtnGames[2] = new JButton("");
		dbBtnGames[2].setBounds(1000, 400, 400, 50);
		dbBtnGames[2].setFont(fontGames);
		dbBtnGames[2].setBackground(Color.black);
		dbBtnGames[2].setForeground(Color.white);
		frame.add(dbBtnGames[2]);
		
		dbBtnGames[3] = new JButton("");
		dbBtnGames[3].setBounds(1000, 450, 400, 50);
		dbBtnGames[3].setFont(fontGames);
		dbBtnGames[3].setBackground(Color.black);
		dbBtnGames[3].setForeground(Color.white);
		frame.add(dbBtnGames[3]);
		
		dbBtnGames[4] = new JButton("");
		dbBtnGames[4].setBounds(1000, 500, 400, 50);
		dbBtnGames[4].setFont(fontGames);
		dbBtnGames[4].setBackground(Color.black);
		dbBtnGames[4].setForeground(Color.white);
		frame.add(dbBtnGames[4]);

		//**Top 5 Game Wishlist**//
		dbBtnWishlist[0] = new JButton("+");
		dbBtnWishlist[0].setBounds(900, 300, 50, 50);
		dbBtnWishlist[0].setFont(fontSmallButtons);
		dbBtnWishlist[0].setBackground(Color.green);
		dbBtnWishlist[0].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnWishlist(0);
		    }
		});
		frame.add(dbBtnWishlist[0]);

		dbBtnWishlist[1] = new JButton("+");
		dbBtnWishlist[1].setBounds(900, 350, 50, 50);
		dbBtnWishlist[1].setFont(fontSmallButtons);
		dbBtnWishlist[1].setBackground(Color.green);
		dbBtnWishlist[1].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnWishlist(1);
		    }
		});
		frame.add(dbBtnWishlist[1]);
		
		dbBtnWishlist[2] = new JButton("+");
		dbBtnWishlist[2].setBounds(900, 400, 50, 50);
		dbBtnWishlist[2].setFont(fontSmallButtons);
		dbBtnWishlist[2].setBackground(Color.green);
		dbBtnWishlist[2].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnWishlist(2);
		    }
		});
		frame.add(dbBtnWishlist[2]);
		
		dbBtnWishlist[3] = new JButton("+");
		dbBtnWishlist[3].setBounds(900, 450, 50, 50);
		dbBtnWishlist[3].setFont(fontSmallButtons);
		dbBtnWishlist[3].setBackground(Color.green);
		dbBtnWishlist[3].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnWishlist(3);
		    }
		});
		frame.add(dbBtnWishlist[3]);
		
		dbBtnWishlist[4] = new JButton("+");
		dbBtnWishlist[4].setBounds(900, 500, 50, 50);
		dbBtnWishlist[4].setFont(fontSmallButtons);
		dbBtnWishlist[4].setBackground(Color.green);
		dbBtnWishlist[4].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnWishlist(4);
		    }
		});
		frame.add(dbBtnWishlist[4]);


		//**Top 5 Game Remove**//
		dbBtnRemove[0] = new JButton("-");
		dbBtnRemove[0].setBounds(950, 300, 50, 50);
		dbBtnRemove[0].setFont(fontSmallButtons);
		dbBtnRemove[0].setBackground(Color.red);
		dbBtnRemove[0].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnRemove(0);
		    }
		});
		frame.add(dbBtnRemove[0]);

		dbBtnRemove[1] = new JButton("-");
		dbBtnRemove[1].setBounds(950, 350, 50, 50);
		dbBtnRemove[1].setFont(fontSmallButtons);
		dbBtnRemove[1].setBackground(Color.red);
		dbBtnRemove[1].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnRemove(1);
		    }
		});
		frame.add(dbBtnRemove[1]);
		
		dbBtnRemove[2] = new JButton("-");
		dbBtnRemove[2].setBounds(950, 400, 50, 50);
		dbBtnRemove[2].setFont(fontSmallButtons);
		dbBtnRemove[2].setBackground(Color.red);
		dbBtnRemove[2].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnRemove(2);
		    }
		});
		frame.add(dbBtnRemove[2]);
		
		dbBtnRemove[3] = new JButton("-");
		dbBtnRemove[3].setBounds(950, 450, 50, 50);
		dbBtnRemove[3].setFont(fontSmallButtons);
		dbBtnRemove[3].setBackground(Color.red);
		dbBtnRemove[3].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnRemove(3);
		    }
		});
		frame.add(dbBtnRemove[3]);
		
		dbBtnRemove[4] = new JButton("-");
		dbBtnRemove[4].setBounds(950, 500, 50, 50);
		dbBtnRemove[4].setFont(fontSmallButtons);
		dbBtnRemove[4].setBackground(Color.red);
		dbBtnRemove[4].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        //your actions
		    	btnRemove(4);
		    }
		});
		frame.add(dbBtnRemove[4]);

		
		//**Background Label for Color**//
    	bg = new JLabel();
    	bg.setBounds(0, 0, 1920, 1080);
    	bg.setBackground(teal);
    	bg.setOpaque(true);
		frame.add(bg);
		

		//**Frame Stuff**//
		frame.setSize(1920,1080);//400 width and 500 height
		frame.setLayout(null);//using no layout managers
		frame.setVisible(true);//making the frame visible
    }
    
    
    public void btnProfile()
    {
    	//**Make everything invisible**//
    	bg.setVisible(false);
		txtQuestion.setVisible(false);
    	for (int i = 0; i < dbBtnAnswers.length; i++)
    		dbBtnAnswers[i].setVisible(false);
    	for (int i = 0; i < dbBtnWishlist.length; i++)
    		dbBtnWishlist[i].setVisible(false);
    	for (int i = 0; i < dbBtnRemove.length; i++)
    		dbBtnRemove[i].setVisible(false);
    	for (int i = 0; i < dbBtnGames.length; i++)
    		dbBtnGames[i].setVisible(false);
    	
    	
    	//**Add buttons for wishlist games**//
    	for (int i = 0; i < user.numWishlist; i++)
    	{
    		Font fontGames = new Font("serif", Font.BOLD, 20);

    		//**List Of Games In Wishlist**//
    		dbBtnGamesWishlist[i] = new JButton(user.dbGamesWishlist[i].nam); //creating instance of JButton
    		int height = 100 + (i * 50);
    		//System.out.println(height);
    		dbBtnGamesWishlist[i].setBounds(700, height, 400, 50);
    		dbBtnGamesWishlist[i].setFont(fontGames);
    		dbBtnGamesWishlist[i].setBackground(Color.black);
    		dbBtnGamesWishlist[i].setForeground(Color.white);
    		dbBtnGamesWishlist[i].setOpaque(true);
    		/*dbBtnGamesWishlist[i].addActionListener(new ActionListener()
    		{
    		    @Override
    		    public void actionPerformed(ActionEvent e)
    		    {
    		        //your actions
    		    	btnWishlistGame(0); //This needs to be be assigned to the button somehow//
    		    }
    		});
    		*/
    		frame.add(dbBtnGamesWishlist[i]); //adding button in JFrame
    	}

    	//frame.setSize(1920,1080);//400 width and 500 height
		frame.setLayout(null);//using no layout managers
		frame.setVisible(true);//making the frame visible
    }
    
    
    public void btnWishlistGame(int num)
    {
    	//We clicked on a game in the wishlist//
    }
    
    
    public void btnAnswer(int num)
    {
    	guiChoiceSelected(dbGuiChoices[num]);
    }
    
    
    public void btnWishlist(int num)
    {
    	user.dbGamesWishlist[user.numWishlist] = dbGuiResults[num].game;
    	//System.out.println(user.dbGamesWishlist[user.numWishlist]);
    	user.numWishlist++;
        showResults();
        setQuestion();
    }
    
    
    public void btnRemove(int num)
    {
    	user.dbGamesRemoved[user.numRemoved] = dbGuiResults[num].game;
    	user.numRemoved++;
        showResults();
        setQuestion();
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
            	for (int i = 0; i < user.dbGamesRemoved.length; i++)
            	{
            		if (user.dbGamesRemoved[i] == api.dbGames[y])
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
        	dbBtnGames[x].setText(dbTopGames[x].nam);
        	dbGuiResults[x].game = dbTopGames[x];
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
    	txtQuestion.setText(dbQuestions[qnum].quest);

    	for (int x = 0; x < dbQuestions[qnum].dbChoices.length; x++)
        {
			//dbGuiChoices[x].txt_choice.text = dbQuestions[qnum].dbChoices[x].choice;
        	dbBtnAnswers[x].setText(dbQuestions[qnum].dbChoices[x].choice);

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
        user.dbGamesRemoved[user.numRemoved] = tgresult.game;
        user.numRemoved++;
        //tgresult.state = "deleting"; //Animation game being deleted//
        //StartCoroutine(delayed_show_results(1));
        showResults();
    }
}
