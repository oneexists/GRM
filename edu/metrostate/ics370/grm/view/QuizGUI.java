package edu.metrostate.ics370.grm.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import edu.metrostate.ics370.grm.controller.Login;
import edu.metrostate.ics370.grm.controller.QuestionnaireInterface;
import edu.metrostate.ics370.grm.model.Game;


/**
 * @author skylar
 * @author christian
 */
public class QuizGUI extends JFrame {
	/**
	 * Version of the Quiz Panel
	 */
	private static final long serialVersionUID = 202111002L;
	
	private JFrame frame;
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

	}
	
	/**
	 * Initialize Quiz
	 */
	public void initialize() {
		setupWishlist();
		setupMenu();
		setupQuestions();
		setupTopGames();
		setupTopWishlist();
		setupTopGamesRemove();
		// set background
		backgroundLabel = new JLabel();
		backgroundLabel.setOpaque(true);
		backgroundLabel.setBackground(teal);
		frame.add(backgroundLabel);

		frame.setLayout(null);
		frame.setVisible(true);
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
		        QuestionnaireInterface.addHatelist(0);
		    }
		});
		frame.add(removeGameButtons[0]);
		
		removeGameButtons[1] = new JButton("-");
		removeGameButtons[1].setBounds(950, 350, 50, 50);
		removeGameButtons[1].setFont(fontSmallButtons);
		removeGameButtons[1].setBackground(Color.red);
		removeGameButtons[1].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        QuestionnaireInterface.addHatelist(1);
		    }
		});
		frame.add(removeGameButtons[1]);
		
		removeGameButtons[2] = new JButton("-");
		removeGameButtons[2].setBounds(950, 400, 50, 50);
		removeGameButtons[2].setFont(fontSmallButtons);
		removeGameButtons[2].setBackground(Color.red);
		removeGameButtons[2].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        QuestionnaireInterface.addHatelist(0);
		    }
		});
		frame.add(removeGameButtons[2]);
		
		removeGameButtons[3] = new JButton("-");
		removeGameButtons[3].setBounds(950, 450, 50, 50);
		removeGameButtons[3].setFont(fontSmallButtons);
		removeGameButtons[3].setBackground(Color.red);
		removeGameButtons[3].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        QuestionnaireInterface.addHatelist(3);
		    }
		});
		frame.add(removeGameButtons[3]);
		
		removeGameButtons[4] = new JButton("-");
		removeGameButtons[4].setBounds(950, 500, 50, 50);
		removeGameButtons[4].setFont(fontSmallButtons);
		removeGameButtons[4].setBackground(Color.red);
		removeGameButtons[4].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        QuestionnaireInterface.addHatelist(4);
		    }
		});
		frame.add(removeGameButtons[4]);
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
		    	QuestionnaireInterface.addWishlist(0);
		    }
		});
		frame.add(wishlistButtons[0]);
		
		wishlistButtons[1] = new JButton("+");
		wishlistButtons[1].setBounds(900, 350, 50, 50);
		wishlistButtons[1].setFont(fontSmallButtons);
		wishlistButtons[1].setBackground(Color.green);
		wishlistButtons[1].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	QuestionnaireInterface.addWishlist(1);
		    }
		});
		frame.add(wishlistButtons[1]);
		
		wishlistButtons[2] = new JButton("+");
		wishlistButtons[2].setBounds(900, 400, 50, 50);
		wishlistButtons[2].setFont(fontSmallButtons);
		wishlistButtons[2].setBackground(Color.green);
		wishlistButtons[2].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	QuestionnaireInterface.addWishlist(2);
		    }
		});
		frame.add(wishlistButtons[2]);
		
		wishlistButtons[3] = new JButton("+");
		wishlistButtons[3].setBounds(900, 450, 50, 50);
		wishlistButtons[3].setFont(fontSmallButtons);
		wishlistButtons[3].setBackground(Color.green);
		wishlistButtons[3].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	QuestionnaireInterface.addWishlist(3);
		    }
		});
		frame.add(wishlistButtons[3]);
		
		wishlistButtons[4] = new JButton("+");
		wishlistButtons[4].setBounds(900, 500, 50, 50);
		wishlistButtons[4].setFont(fontSmallButtons);
		wishlistButtons[4].setBackground(Color.green);
		wishlistButtons[4].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	QuestionnaireInterface.addWishlist(4);
		    }
		});
		frame.add(wishlistButtons[4]);
	}

	private void setupTopGames() {
		if (QuestionnaireInterface.games != null) {
			//**Top 5 Game Recommendations**//
			topGameButtons[0] = new JButton(QuestionnaireInterface.games[0].getName());
			topGameButtons[0].setBounds(1000, 300, 400, 50);
			topGameButtons[0].setFont(fontGames);
			topGameButtons[0].setBackground(Color.black);
			topGameButtons[0].setForeground(Color.white);
			frame.add(topGameButtons[0]);
			topGameButtons[1] = new JButton(QuestionnaireInterface.games[1].getName());
			topGameButtons[1].setBounds(1000, 350, 400, 50);
			topGameButtons[1].setFont(fontGames);
			topGameButtons[1].setBackground(Color.black);
			topGameButtons[1].setForeground(Color.white);
			frame.add(topGameButtons[1]);
			topGameButtons[2] = new JButton(QuestionnaireInterface.games[2].getName());
			topGameButtons[2].setBounds(1000, 400, 400, 50);
			topGameButtons[2].setFont(fontGames);
			topGameButtons[2].setBackground(Color.black);
			topGameButtons[2].setForeground(Color.white);
			frame.add(topGameButtons[2]);
			topGameButtons[3] = new JButton(QuestionnaireInterface.games[3].getName());
			topGameButtons[3].setBounds(1000, 450, 400, 50);
			topGameButtons[3].setFont(fontGames);
			topGameButtons[3].setBackground(Color.black);
			topGameButtons[3].setForeground(Color.white);
			frame.add(topGameButtons[3]);
			topGameButtons[4] = new JButton(QuestionnaireInterface.games[4].getName());
			topGameButtons[4].setBounds(1000, 500, 400, 50);
			topGameButtons[4].setFont(fontGames);
			topGameButtons[4].setBackground(Color.black);
			topGameButtons[4].setForeground(Color.white);
			frame.add(topGameButtons[4]);
		}
	}

	private void setupQuestions() {
		//**Question Text**//
		promptLabel = new JLabel(QuestionnaireInterface.getQuestion().getPrompt());
		promptLabel.setBounds(200, 100, 400, 200);
		promptLabel.setHorizontalAlignment(JLabel.CENTER);
		promptLabel.setBackground(Color.black);
		promptLabel.setForeground(Color.white);
		promptLabel.setOpaque(true);
		promptLabel.setFont(fontQuestion);
		promptLabel.setVisible(true);
		frame.add(promptLabel);
		
		//**Answers**//
		answerButtons[0] = new JButton(QuestionnaireInterface.getQuestion().getChoices()[0].getText());
		answerButtons[0].setBounds(200, 320, 400, 120);
		answerButtons[0].setFont(fontAnswers);
		answerButtons[0].setBackground(Color.white);
		answerButtons[0].setVisible(true);
		answerButtons[0].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	QuestionnaireInterface.selectChoice(0);
		    }
		});
		frame.add(answerButtons[0]);
		
		answerButtons[1] = new JButton(QuestionnaireInterface.getQuestion().getChoices()[1].getText());
		answerButtons[1].setBounds(200, 460, 400, 120);
		answerButtons[1].setFont(fontAnswers);
		answerButtons[1].setBackground(Color.white);
		answerButtons[1].setVisible(true);
		answerButtons[1].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	QuestionnaireInterface.selectChoice(1);
		    }
		});
		frame.add(answerButtons[1]);
		
		answerButtons[2] = new JButton(QuestionnaireInterface.getQuestion().getChoices()[2].getText());
		answerButtons[2].setBounds(200, 600, 400, 120);
		answerButtons[2].setFont(fontAnswers);
		answerButtons[2].setBackground(Color.white);
		answerButtons[2].setVisible(true);
		answerButtons[2].addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	QuestionnaireInterface.selectChoice(2);
		    }
		});
		frame.add(answerButtons[2]);
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
		frame.add(btnQuestions); 						//adding button in JFrame

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
		frame.add(btnWishlist);

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
		frame.add(btnHatelist);		
	}

	private void setupWishlist() {
		int i = 0;
		int height = 100 + (i * 50);
		if (Login.user.getWishlist() != null) {
			for (Game game : Login.user.getWishlist()) {
				wishlistAddButtons[i] = new JButton("+");
				wishlistAddButtons[i].setBounds(700, height, 400, 50);
				wishlistAddButtons[i].setBackground(Color.black);
				wishlistAddButtons[i].setForeground(Color.white);
				wishlistAddButtons[i].setFont(fontGames);
				wishlistAddButtons[i].setOpaque(true);
				wishlistAddButtons[i].setVisible(false);
				wishlistAddButtons[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Login.user.removeWishlist(game);
					}
				});
				frame.add(wishlistAddButtons[i]);
			} 
		}
		i = 0;
		if (Login.user.getHatelist() != null) {
			for (Game game : Login.user.getHatelist()) {
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
						Login.user.addHatelist(game);
					}
				});
				frame.add(wishlistRemoveButtons[i]);
			} 
		}
	}	
}