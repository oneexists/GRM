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
import javax.swing.JTextField;

import edu.metrostate.ics370.grm.controller.Login;

/**
 * Menu panel: edit profile, take quiz, logout
 * Profile panel: username, firstName, dateOfBirth, gender
 * 
 * @author skylar
 *
 */
public class MenuGUI extends JFrame {

	/**
	 * Version of the Menu Panel
	 */
	private static final long serialVersionUID = 202111002L;

	private JFrame menuFrame;
	private JPanel menuPanel;
	private JPanel profilePanel;
	private JButton editProfile;
	private JButton takeQuiz;
	private JButton logout;
	private JTextField username;
	private JTextField firstName;
	private JTextField dateOfBirth;
	private JTextField gender;
	
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
		buildProfilePanel();
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
				// TODO implement take quiz frame
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
	
	private void buildProfilePanel() {
		profilePanel = new JPanel();
		GridLayout profileLayout = new GridLayout(0,1);
		profilePanel.setLayout(profileLayout);
		// username
		username = new JTextField(Login.user.getUsername());
		profilePanel.add(username);
		// first name
		firstName = new JTextField(Login.user.getFirstName());
		profilePanel.add(firstName);
		// date of birth
		dateOfBirth = new JTextField(Login.user.getDateOfBirth().toString());
		profilePanel.add(dateOfBirth);
		// gender
		if (Login.user.getGender() != null) {
			gender = new JTextField(Login.user.getGender().toString());			
			profilePanel.add(gender);
		}
		
		// add to frame
		menuFrame.add(profilePanel);
	}
}
