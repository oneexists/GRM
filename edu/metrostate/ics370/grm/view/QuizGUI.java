package edu.metrostate.ics370.grm.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.metrostate.ics370.grm.controller.QuestionnaireInterface;
import edu.metrostate.ics370.grm.controller.RecommendationManaager;
import edu.metrostate.ics370.grm.model.Game;


public class QuizGUI extends JFrame {
	/**
	 * Version of the Quiz Panel
	 */
	private static final long serialVersionUID = 202111002L;
	
	private JFrame frame;
	private JPanel quizPanel;
	private JButton btnQuestions;
	private JButton btnWishlist;
	private JButton btnHatelist;
	private JLabel promptLabel;
	private JLabel backgroundLabel;
	private JButton[] wishlistButtons = new JButton[5];
	private JButton[] wishlistAddButtons = new JButton[17];
	private JButton[] wishlistRemoveButtons = new JButton[17];
	private JButton[] removeGameButtons = new JButton[5];
	private JButton[] answerButtons = new JButton[3];
	private JButton[] topGameButtons = new JButton[5];
	private Game[] gameResults = new Game[5];
	//**Fonts**//
	Font fontQuestion = new Font("serif", Font.BOLD, 25);
	Font fontAnswers = new Font("serif", Font.BOLD, 20);
	Font fontGames = new Font("serif", Font.BOLD, 20);
	Font fontSmallButtons = new Font("serif", Font.BOLD, 25);
	//**Colors**//
	Color teal = new Color(0, 128, 128);
	
	/**
	 * No-arg constructor
	 */
	public QuizGUI() {
		   //**Initialize frame**//
		   frame = new JFrame("Questionnaire");
		   frame.setSize(1920,1080);					//400 width and 500 height
		   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   // set background
		   backgroundLabel = new JLabel();
		   backgroundLabel.setOpaque(true);
		   backgroundLabel.setBackground(teal);
		   frame.add(backgroundLabel);
		   frame.setVisible(true);						//making frame visible
	}
	
	/**
	 * Initialize Quiz
	 */
	public void initialize() {
		quizPanel = new JPanel();
		
		setupWishlist();
		setupMenu();
		setupQuestions();
		setupTopGames();
		setupTopWishlist();
		setupTopGamesRemove();

		frame.add(quizPanel);						//add panel to frame
	}

	private void setupTopGamesRemove() {
		//**Top 5 Game Remove**//
		removeGameButtons[0] = new JButton("-");
		removeGameButtons[0].setBounds(950, 300, 50, 50);
		removeGameButtons[0].setFont(fontSmallButtons);
		removeGameButtons[0].setBackground(Color.red);
		removeGameButtons[0].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        QuestionnaireInterface.addHatelist(gameResults[0]);
		    }
		});
		quizPanel.add(removeGameButtons[0]);
		
		removeGameButtons[1] = new JButton("-");
		removeGameButtons[1].setBounds(950, 350, 50, 50);
		removeGameButtons[1].setFont(fontSmallButtons);
		removeGameButtons[1].setBackground(Color.red);
		removeGameButtons[1].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        QuestionnaireInterface.addHatelist(gameResults[1]);
		    }
		});
		quizPanel.add(removeGameButtons[1]);
		
		removeGameButtons[2] = new JButton("-");
		removeGameButtons[2].setBounds(950, 400, 50, 50);
		removeGameButtons[2].setFont(fontSmallButtons);
		removeGameButtons[2].setBackground(Color.red);
		removeGameButtons[2].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        QuestionnaireInterface.addHatelist(gameResults[0]);
		    }
		});
		quizPanel.add(removeGameButtons[2]);
		
		removeGameButtons[3] = new JButton("-");
		removeGameButtons[3].setBounds(950, 450, 50, 50);
		removeGameButtons[3].setFont(fontSmallButtons);
		removeGameButtons[3].setBackground(Color.red);
		removeGameButtons[3].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        QuestionnaireInterface.addHatelist(gameResults[3]);
		    }
		});
		quizPanel.add(removeGameButtons[3]);
		
		removeGameButtons[4] = new JButton("-");
		removeGameButtons[4].setBounds(950, 500, 50, 50);
		removeGameButtons[4].setFont(fontSmallButtons);
		removeGameButtons[4].setBackground(Color.red);
		removeGameButtons[4].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        QuestionnaireInterface.addHatelist(gameResults[4]);
		    }
		});
		quizPanel.add(removeGameButtons[4]);
	}

	private void setupTopWishlist() {
		//**Top 5 Game Wishlist**//
		wishlistButtons[0] = new JButton("+");
		wishlistButtons[0].setBounds(900, 300, 50, 50);
		wishlistButtons[0].setFont(fontSmallButtons);
		wishlistButtons[0].setBackground(Color.green);
		wishlistButtons[0].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	QuestionnaireInterface.addWishlist(gameResults[0]);
		    }
		});
		quizPanel.add(wishlistButtons[0]);
		
		wishlistButtons[1] = new JButton("+");
		wishlistButtons[1].setBounds(900, 350, 50, 50);
		wishlistButtons[1].setFont(fontSmallButtons);
		wishlistButtons[1].setBackground(Color.green);
		wishlistButtons[1].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	QuestionnaireInterface.addWishlist(gameResults[1]);
		    }
		});
		quizPanel.add(wishlistButtons[1]);
		
		wishlistButtons[2] = new JButton("+");
		wishlistButtons[2].setBounds(900, 400, 50, 50);
		wishlistButtons[2].setFont(fontSmallButtons);
		wishlistButtons[2].setBackground(Color.green);
		wishlistButtons[2].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	QuestionnaireInterface.addWishlist(gameResults[2]);
		    }
		});
		quizPanel.add(wishlistButtons[2]);
		
		wishlistButtons[3] = new JButton("+");
		wishlistButtons[3].setBounds(900, 450, 50, 50);
		wishlistButtons[3].setFont(fontSmallButtons);
		wishlistButtons[3].setBackground(Color.green);
		wishlistButtons[3].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	QuestionnaireInterface.addWishlist(gameResults[3]);
		    }
		});
		quizPanel.add(wishlistButtons[3]);
		
		wishlistButtons[4] = new JButton("+");
		wishlistButtons[4].setBounds(900, 500, 50, 50);
		wishlistButtons[4].setFont(fontSmallButtons);
		wishlistButtons[4].setBackground(Color.green);
		wishlistButtons[4].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	QuestionnaireInterface.addWishlist(gameResults[4]);
		    }
		});
		quizPanel.add(wishlistButtons[4]);
	}

	private void setupTopGames() {
		//**Top 5 Game Recommendations**//
		topGameButtons[0] = new JButton("");
		topGameButtons[0].setBounds(1000, 300, 400, 50);
		topGameButtons[0].setFont(fontGames);
		topGameButtons[0].setBackground(Color.black);
		topGameButtons[0].setForeground(Color.white);
		quizPanel.add(topGameButtons[0]);
		
		topGameButtons[1] = new JButton("");
		topGameButtons[1].setBounds(1000, 350, 400, 50);
		topGameButtons[1].setFont(fontGames);
		topGameButtons[1].setBackground(Color.black);
		topGameButtons[1].setForeground(Color.white);
		quizPanel.add(topGameButtons[1]);
		
		topGameButtons[2] = new JButton("");
		topGameButtons[2].setBounds(1000, 400, 400, 50);
		topGameButtons[2].setFont(fontGames);
		topGameButtons[2].setBackground(Color.black);
		topGameButtons[2].setForeground(Color.white);
		quizPanel.add(topGameButtons[2]);
		
		topGameButtons[3] = new JButton("");
		topGameButtons[3].setBounds(1000, 450, 400, 50);
		topGameButtons[3].setFont(fontGames);
		topGameButtons[3].setBackground(Color.black);
		topGameButtons[3].setForeground(Color.white);
		quizPanel.add(topGameButtons[3]);
		
		topGameButtons[4] = new JButton("");
		topGameButtons[4].setBounds(1000, 500, 400, 50);
		topGameButtons[4].setFont(fontGames);
		topGameButtons[4].setBackground(Color.black);
		topGameButtons[4].setForeground(Color.white);
		quizPanel.add(topGameButtons[4]);
	}

	private void setupQuestions() {
		//**Question Text**//
		promptLabel = new JLabel();
		promptLabel.setBounds(200, 100, 400, 200);
		// TODO txtQuestion.setText(getQuestion());
		promptLabel.setHorizontalAlignment(JLabel.CENTER);
		promptLabel.setBackground(Color.black);
		promptLabel.setForeground(Color.white);
		promptLabel.setOpaque(true);
		promptLabel.setFont(fontQuestion);
		//txtQuest = quest;
		quizPanel.add(promptLabel);
		
		//**Answers**//
		answerButtons[0] = new JButton("Answer 0");
		answerButtons[0].setBounds(200, 320, 400, 120);
		answerButtons[0].setFont(fontAnswers);
		answerButtons[0].setBackground(Color.white);
		answerButtons[0].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	RecommendationManaager.selectChoice(0);
		    }
		});
		quizPanel.add(answerButtons[0]);
		
		answerButtons[1] = new JButton("Answer 1");
		answerButtons[1].setBounds(200, 460, 400, 120);
		answerButtons[1].setFont(fontAnswers);
		answerButtons[1].setBackground(Color.white);
		answerButtons[1].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	// TODO select choice
		    	//		replace rm.guiChoiceSelected(rm.getQuestionChoices()[num])
		    }
		});
		quizPanel.add(answerButtons[1]);
		
		answerButtons[2] = new JButton("");
		answerButtons[2].setBounds(200, 600, 400, 120);
		answerButtons[2].setFont(fontAnswers);
		answerButtons[2].setBackground(Color.white);
		answerButtons[2].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	// TODO select choice
		    	//		replace rm.guiChoiceSelected(rm.getQuestionChoices()[num])
		    }
		});
		quizPanel.add(answerButtons[2]);
	}
	    

	private void setupMenu() {
		//**Menu Button - Questions**//
		btnQuestions = new JButton("Questions"); 		//creating instance of JButton
		btnQuestions.setBounds(1400, 50, 150, 70); 		//x axis, y axis, width, height
		btnQuestions.setFont(fontQuestion);
		btnQuestions.setBackground(Color.green); 		//Green is for the selected menu button
		btnQuestions.setForeground(Color.white);
		
		btnQuestions.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
	    		// TODO btnQuestions button action
		    	//		replace btnQuestionsScreen()
		    }
		});
		quizPanel.add(btnQuestions); 						//adding button in JFrame

		//**Menu Button - Wishlist**//
		btnWishlist = new JButton("Wishlist");
		btnWishlist.setBounds(1550, 50, 150, 70);
		btnWishlist.setFont(fontQuestion);
		btnWishlist.setBackground(Color.black);
		btnWishlist.setForeground(Color.white);
		
		btnWishlist.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
	    		// TODO btnWishlist button action
		    	//		replace btnWishlistScreen()
		    }
		});
		quizPanel.add(btnWishlist);

		//**Menu Button - Hatelist**//
		btnHatelist = new JButton("Hatelist");
		btnHatelist.setBounds(1700, 50, 150, 70);
		btnHatelist.setFont(fontQuestion);
		btnHatelist.setBackground(Color.black);
		btnHatelist.setForeground(Color.white);
		
		btnHatelist.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	// TODO btnHatelist button action
	    		//		replace btnHatelistScreen()
		    }
		});
		quizPanel.add(btnHatelist);		
	}

	private void setupWishlist() {
		for (int i=0; i<17; i++) {
			wishlistAddButtons[i] = new JButton("");
			int height = 100 + (i * 50);
			wishlistAddButtons[i].setBounds(700, height, 400, 50);
			wishlistAddButtons[i].setFont(fontGames);
			wishlistAddButtons[i].setBackground(Color.black);
			wishlistAddButtons[i].setForeground(Color.white);
			wishlistAddButtons[i].setOpaque(true);
			wishlistAddButtons[i].setVisible(false);
			wishlistAddButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO add wishlist button action
				}
			});
			quizPanel.add(wishlistAddButtons[i]);
			
			wishlistRemoveButtons[i] = new JButton("-");
			wishlistRemoveButtons[i].setBounds(650, height, 50, 50);
			wishlistRemoveButtons[i].setFont(fontGames);
			wishlistRemoveButtons[i].setBackground(Color.red);
			wishlistRemoveButtons[i].setForeground(Color.white);
			wishlistRemoveButtons[i].setOpaque(true);
			wishlistRemoveButtons[i].setVisible(false);
			wishlistRemoveButtons[i].addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
				   // TODO add wishlist remove button action
			   }
		   });
		   quizPanel.add(wishlistRemoveButtons[i]);
		   }
	}	
}