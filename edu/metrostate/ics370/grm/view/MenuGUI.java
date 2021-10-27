/**
 * 
 */
package edu.metrostate.ics370.grm.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.metrostate.ics370.grm.controller.Login;

/**
 * @author skylar
 *
 */
public class MenuGUI extends JPanel {

	/**
	 * Version of the Menu Panel
	 */
	private static final long serialVersionUID = 202110001L;

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
		menuFrame.setSize(350, 200);
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
		// panel and layout
		FlowLayout menuLayout = new FlowLayout();
		menuPanel = new JPanel();
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
				// TODO implement take quiz frame
			}
		});
		menuPanel.add(takeQuiz);
		// logout button
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO logout user and return to login menu
				Login.signOut();
				menuFrame.dispose();
			}
		});
		menuPanel.add(logout);
		
		// add to frame
		menuFrame.add(menuPanel, BorderLayout.CENTER);
	}
}
