/**
 * 
 */
package edu.metrostate.ics370.grm.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.metrostate.ics370.grm.controller.Login;

/**
 * @author skylar
 */
public class MenuGUI extends JPanel {

	/**
	 * Version of the Menu Panel
	 */
	private static final long serialVersionUID = 202111002L;

	private JPanel menuPanel;
	private JFrame menuFrame;
	private JButton editProfile;
	private JButton takeQuiz;
	private JButton logout;
	
	/**
	 * No-arg constructor
	 */
	public MenuGUI() {
		menuFrame = new JFrame("Game Recommendation Manager Menu");
		menuFrame.setSize(350, 350);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Initialize Menu
	 */
	public void initialize() {
		buildMenuPanel();
		menuFrame.setVisible(true);
	}
	
	private void buildMenuPanel() {
		menuPanel = new JPanel();
		GridLayout menuLayout = new GridLayout(0,1);
		menuPanel.setLayout(menuLayout);
		
		// edit profile button
		editProfile = new JButton("Edit Profile");
		editProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO implement edit profile frame
			}
		});
		menuPanel.add(editProfile);
		// take quiz button
		takeQuiz = new JButton("Take Quiz");
		takeQuiz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QuizGUI quiz = new QuizGUI();
				quiz.initialize();
			}
		});
		menuPanel.add(takeQuiz);
		// logout button
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login.signOut();
				LoginGUI.openLogin();
				menuFrame.dispose();
			}
		});
		menuPanel.add(logout);
		
		// add to frame
		menuFrame.add(menuPanel, BorderLayout.WEST);
	}
}
