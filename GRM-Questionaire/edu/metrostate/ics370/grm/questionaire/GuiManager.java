package edu.metrostate.ics370.grm.questionaire;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GuiManager
{
	public RecommendationManager rm;
    public String menuSelected;
    public JFrame frame;
    public JLabel txtQuestion;
    public JLabel bg;
    public JButton btnQuestions;
    public JButton btnWishlist;
    public JButton btnHatelist;
    public GuiResult[] dbGuiResults = new GuiResult[5];
    public JButton[] dbBtnAnswers = new JButton[3];
    public JButton[] dbBtnGames = new JButton[5];
    public JButton[] dbBtnWishlist = new JButton[5];
    public JButton[] dbBtnRemove = new JButton[5];
    public JButton[] dbBtnGamesWishlist = new JButton[17];
    public JButton[] dbBtnGamesWishlistRemove = new JButton[17];

    
   public GuiManager(RecommendationManager trm) 
   {
	   rm = trm;
	   for (int i = 0; i < rm.dbGuiChoices.length; i++)
		   rm.dbGuiChoices[i] = new GuiChoice();
		
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
		
		
		//**Menu Button - Questions**//
		btnQuestions = new JButton("Questions"); //creating instance of JButton
		btnQuestions.setBounds(1400, 50, 150, 70); //x axis, y axis, width, height
		btnQuestions.setFont(fontQuestion);
		btnQuestions.setBackground(Color.green); //Green is for the selected menu button//
		btnQuestions.setForeground(Color.white);
		btnQuestions.addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
	    		btnQuestionsScreen();
		    }
		});
		frame.add(btnQuestions); //adding button in JFrame

		//**Menu Button - Wishlist**//
		btnWishlist = new JButton("Wishlist"); //creating instance of JButton
		btnWishlist.setBounds(1550, 50, 150, 70); //x axis, y axis, width, height
		btnWishlist.setFont(fontQuestion);
		btnWishlist.setBackground(Color.black);
		btnWishlist.setForeground(Color.white);
		btnWishlist.addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
	    		btnWishlistScreen();
		    }
		});
		frame.add(btnWishlist); //adding button in JFrame

		//**Menu Button - Hatelist**//
		btnHatelist = new JButton("Hatelist"); //creating instance of JButton
		btnHatelist.setBounds(1700, 50, 150, 70); //x axis, y axis, width, height
		btnHatelist.setFont(fontQuestion);
		btnHatelist.setBackground(Color.black);
		btnHatelist.setForeground(Color.white);
		btnHatelist.addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
	    		btnHatelistScreen();
		    }
		});
		frame.add(btnHatelist); //adding button in JFrame

		
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
		

		//**Adds the 25 buttons for the wishlist and makes them invisible by default**//
		setupGuiWishlist();
		
		
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
   
   
   public void setupGuiWishlist()
   {
	   Font fontGames = new Font("serif", Font.BOLD, 20);
	   
	   for (int i = 0; i < dbBtnGamesWishlist.length; i++)
	   {
		   int ti = i;
		   //**List Of Games In Wishlist & Hatelist**//
		   dbBtnGamesWishlist[ti] = new JButton(""); //creating instance of JButton
		   int height = 100 + (ti * 50);
		   dbBtnGamesWishlist[ti].setBounds(700, height, 400, 50);
		   dbBtnGamesWishlist[ti].setFont(fontGames);
		   dbBtnGamesWishlist[ti].setBackground(Color.black);
		   dbBtnGamesWishlist[ti].setForeground(Color.white);
		   dbBtnGamesWishlist[ti].setOpaque(true);
		   dbBtnGamesWishlist[ti].setVisible(false);
		   dbBtnGamesWishlist[ti].addActionListener(new ActionListener()
		   {
			   @Override
			   public void actionPerformed(ActionEvent e)
			   {
				   btnWishlistGame(ti); //This needs to be be assigned to the button somehow//
			   }
		   });
		   frame.add(dbBtnGamesWishlist[ti]); //adding button in JFrame

		   
		   dbBtnGamesWishlistRemove[ti] = new JButton("-"); //creating instance of JButton
		   //height = 100 + (ti * 50);
		   dbBtnGamesWishlistRemove[ti].setBounds(650, height, 50, 50);
		   dbBtnGamesWishlistRemove[ti].setFont(fontGames);
		   dbBtnGamesWishlistRemove[ti].setBackground(Color.red);
		   dbBtnGamesWishlistRemove[ti].setForeground(Color.white);
		   dbBtnGamesWishlistRemove[ti].setOpaque(true);
		   dbBtnGamesWishlistRemove[ti].setVisible(false);
		   dbBtnGamesWishlistRemove[ti].addActionListener(new ActionListener()
		   {
			   @Override
			   public void actionPerformed(ActionEvent e)
			   {
				   btnWishlistGameRemove(ti); //This needs to be be assigned to the button somehow//
			   }
		   });
		   frame.add(dbBtnGamesWishlistRemove[ti]); //adding button in JFrame
	   
	   }
   }
   
   
   public void btnWishlistScreen()
   {
	   menuSelected = "wishlist";
	   btnQuestions.setBackground(Color.black);
	   btnWishlist.setBackground(Color.green); //Green is for the selected menu button//
	   btnHatelist.setBackground(Color.black);

		//**Make everything invisible**//
	   //bg.setVisible(false);
	   txtQuestion.setVisible(false);
	   for (int i = 0; i < dbBtnAnswers.length; i++)
		   dbBtnAnswers[i].setVisible(false);
	   for (int i = 0; i < dbBtnWishlist.length; i++)
		   dbBtnWishlist[i].setVisible(false);
	   for (int i = 0; i < dbBtnRemove.length; i++)
		   dbBtnRemove[i].setVisible(false);
	   for (int i = 0; i < dbBtnGames.length; i++)
		   dbBtnGames[i].setVisible(false);
	   
	   btnWishlist.setText("Questions");
   	

	   //**Disable all buttons first**//
	   for (int i = 0; i < dbBtnGamesWishlist.length; i++) //rm.user.numWishlist; i++)
	   {
		   dbBtnGamesWishlist[i].setVisible(false);
		   dbBtnGamesWishlistRemove[i].setVisible(false);
	   }

   	
	   int count = dbBtnGamesWishlist.length;
	   if (rm.user.numWishlist < count) //If number of games in wishlist are less than 25, only make 25 buttons visible//
		   count = rm.user.numWishlist;

	   //**Add buttons for wishlist games**//
	   for (int i = 0; i < count; i++) //rm.user.numWishlist; i++)
	   {
		   dbBtnGamesWishlist[i].setVisible(true);
		   dbBtnGamesWishlist[i].setText(rm.user.dbGamesWishlist[i].nam);
		   dbBtnGamesWishlistRemove[i].setVisible(true);
	   }
	   

	   frame.setLayout(null);//using no layout managers
	   frame.setVisible(true);//making the frame visible
   }

   
   public void btnHatelistScreen()
   {
	   menuSelected = "hatelist";
	   btnQuestions.setBackground(Color.black);
	   btnWishlist.setBackground(Color.black);
	   btnHatelist.setBackground(Color.green); //Green is for the selected menu button//

		//**Make everything invisible**//
	   //bg.setVisible(false);
	   txtQuestion.setVisible(false);
	   for (int i = 0; i < dbBtnAnswers.length; i++)
		   dbBtnAnswers[i].setVisible(false);
	   for (int i = 0; i < dbBtnWishlist.length; i++)
		   dbBtnWishlist[i].setVisible(false);
	   for (int i = 0; i < dbBtnRemove.length; i++)
		   dbBtnRemove[i].setVisible(false);
	   for (int i = 0; i < dbBtnGames.length; i++)
		   dbBtnGames[i].setVisible(false);
	   
	   //btnHatelist.setText("Questions");
   	

	   //**Disable all buttons first**//
	   for (int i = 0; i < dbBtnGamesWishlist.length; i++) //rm.user.numWishlist; i++)
	   {
		   dbBtnGamesWishlist[i].setVisible(false);
		   dbBtnGamesWishlistRemove[i].setVisible(false);
	   }

   	
	   int count = dbBtnGamesWishlist.length;
	   if (rm.user.numHatelist < count) //If number of games in wishlist are less than 25, only make 25 buttons visible//
		   count = rm.user.numHatelist;

	   //**Add buttons for hatelist games**//
	   for (int i = 0; i < count; i++) //rm.user.numWishlist; i++)
	   {
		   dbBtnGamesWishlist[i].setVisible(true);
		   dbBtnGamesWishlist[i].setText(rm.user.dbGamesHatelist[i].nam);
		   dbBtnGamesWishlistRemove[i].setVisible(true);
	   }
	   
	   frame.setLayout(null);//using no layout managers
	   frame.setVisible(true);//making the frame visible
   }

   
   public void btnQuestionsScreen()
   {
	   menuSelected = "questions";
	   btnQuestions.setBackground(Color.green); //Green is for the selected menu button//
	   btnWishlist.setBackground(Color.black);
	   btnHatelist.setBackground(Color.black);

	   //**Make everything invisible**//
	   bg.setVisible(true);
	   txtQuestion.setVisible(true);
	   for (int i = 0; i < dbBtnAnswers.length; i++)
		   dbBtnAnswers[i].setVisible(true);
	   for (int i = 0; i < dbBtnWishlist.length; i++)
		   dbBtnWishlist[i].setVisible(true);
	   for (int i = 0; i < dbBtnRemove.length; i++)
		   dbBtnRemove[i].setVisible(true);
	   for (int i = 0; i < dbBtnGames.length; i++)
		   dbBtnGames[i].setVisible(true);
	   
	   btnWishlist.setText("Wishlist");
   	
   	
	   int count = dbBtnGamesWishlist.length;

	   //**Make Buttons Invisible**//
	   for (int i = 0; i < count; i++)
	   {
		   dbBtnGamesWishlist[i].setVisible(false);
		   dbBtnGamesWishlistRemove[i].setVisible(false);
	   }

	   frame.setLayout(null);//using no layout managers
	   frame.setVisible(true);//making the frame visible
	   rm.showResults();
   }

   
   public void btnWishlistGame(int num)
   {
	   //We clicked on a game in the wishlist, this can be a link to it's steam or similar (if links not avail, could do google search for game as generic take me to this game)//
   }
   
   
   public void btnWishlistGameRemove(int num)
   {
	   //We clicked to remove a game from the wishlist//
	   if (menuSelected == "wishlist")
	   {
		   rm.user.dbGamesWishlist[num] = rm.user.dbGamesWishlist[rm.user.numWishlist - 1]; //Takes the last on the list and moves it to this spot, then removes that last spot//
		   rm.user.dbGamesWishlist[rm.user.numWishlist - 1] = null;
		   rm.user.numWishlist--;
		   btnWishlistScreen();
	   }
	   else
	   //We clicked to remove a game from the hatelist//
	   if (menuSelected == "hatelist")
	   {
		   rm.user.dbGamesHatelist[num] = rm.user.dbGamesHatelist[rm.user.numHatelist - 1]; //Takes the last on the list and moves it to this spot, then removes that last spot//
		   rm.user.dbGamesHatelist[rm.user.numHatelist - 1] = null;
		   rm.user.numHatelist--;
		   btnHatelistScreen();
	   }
   }
   
   
   public void btnHatelistGameRemove(int num)
   {
	   //We clicked to remove a game from the wishlist//
	   rm.user.dbGamesHatelist[num] = rm.user.dbGamesHatelist[rm.user.numHatelist - 1]; //Takes the last on the list and moves it to this spot, then removes that last spot//
	   rm.user.dbGamesHatelist[rm.user.numHatelist - 1] = null;
	   rm.user.numHatelist--;
	   btnHatelistScreen();
   }
   
   
   public void btnAnswer(int num)
   {
	   rm.guiChoiceSelected(rm.dbGuiChoices[num]);
   }
   
   
   public void btnWishlist(int num)
   {
	   if (dbGuiResults[num].game != null && dbGuiResults[num].game.nam != null)
	   {
		   //System.out.println(dbGuiResults[num].game.nam);
		   rm.user.dbGamesWishlist[rm.user.numWishlist] = dbGuiResults[num].game;
		   rm.user.numWishlist++;
		   rm.showResults();
		   rm.setQuestion();
	   }
   }
   
   
   public void btnRemove(int num)
   {
	   if (dbGuiResults[num].game != null && dbGuiResults[num].game.nam != null)
	   {
		   rm.user.dbGamesHatelist[rm.user.numHatelist] = dbGuiResults[num].game;
		   rm.user.numHatelist++;
		   rm.showResults();
		   rm.setQuestion();
	   }
   }
}
