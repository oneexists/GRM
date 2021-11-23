/**
 * 
 */
package edu.metrostate.ics370.grm.view;

import edu.metrostate.ics370.grm.controller.Login;
import edu.metrostate.ics370.grm.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JButton editProfile;
	private JButton takeQuiz;
	private JButton logout;
	private JButton settings;

	private JPanel profilePanel;
	private JLabel username;
	private JLabel firstName;
	private JLabel dateOfBirth;
	private JLabel gender;
	
	private JPanel editPanel;
	private JLabel userLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel dateOfBirthLabel;
	private JLabel genderLabel;
	private JTextField userText;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField dateOfBirthText;
	private JComboBox<User.Gender> genderComboBox;
	private JButton saveButton;
	private JButton cancelButton;
	
	/**
	 * No-arg constructor
	 */
	public MenuGUI() {
		menuFrame = new JFrame("Game Recommendation Manager Menu");
		menuFrame.setSize(700, 600);
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
		menuLayout.setVgap(30);

		// Add logo
		menuPanel.add(new JLabel(new ImageIcon("lib/images/Logo_small.png")));

		// edit profile button
		editProfile = new JButton("Edit Profile");
		editProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO implement edit profile frame
				profilePanel.setVisible(false);
				buildEditUserPanel();
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

		// settings button
		settings = new JButton("Settings");
		settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SettingsGUI();
				menuFrame.dispose();
			}
		});
		menuPanel.add(settings);

		// logout button
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login.signOut();
				LoginGUI window;
				try {
					window = new SignInGUI();
					window.buildPanel();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				menuFrame.dispose();
			}
		});
		menuPanel.add(logout);
		
		// add to frame
		menuFrame.add(menuPanel, BorderLayout.WEST);
	}
	
	private void buildEditUserPanel() {
		editPanel = new JPanel();
		GridLayout editUserLayout = new GridLayout(0, 2);
		editPanel.setLayout(editUserLayout);	
		// labels
		userLabel = new JLabel("Username:");
		firstNameLabel = new JLabel("First name:");
		lastNameLabel = new JLabel("Last name:");
		dateOfBirthLabel = new JLabel("Date of birth:");
		genderLabel = new JLabel("Gender:");
		// text fields
		userText = new JTextField(Login.user.getUsername());
		firstNameText = new JTextField(Login.user.getFirstName());
		lastNameText = new JTextField(Login.user.getLastName());
		dateOfBirthText = new JTextField(Login.user.getDateOfBirth().toString());
		// combo box
		genderComboBox = new JComboBox<User.Gender>(User.Gender.values());
		// buttons
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		// add to panel
		editPanel.add(userLabel);
		editPanel.add(userText);
		editPanel.add(firstNameLabel);
		editPanel.add(firstNameText);
		editPanel.add(lastNameLabel);
		editPanel.add(lastNameText);
		editPanel.add(dateOfBirthLabel);
		editPanel.add(dateOfBirthText);
		editPanel.add(genderLabel);
		editPanel.add(genderComboBox);
		editPanel.add(saveButton);
		editPanel.add(cancelButton);
		
		profilePanel.setVisible(false);
		editPanel.setVisible(true);
		menuFrame.add(editPanel);
	}
	
	private void buildProfilePanel() {
		profilePanel = new JPanel();
		GridLayout profileLayout = new GridLayout(0,1);
		profilePanel.setLayout(profileLayout);
		// username
		username = new JLabel(Login.user.getUsername());
		profilePanel.add(username);
		// first name
		firstName = new JLabel(Login.user.getFirstName());
		profilePanel.add(firstName);
		// date of birth
		dateOfBirth = new JLabel(Login.user.getDateOfBirth().toString());
		profilePanel.add(dateOfBirth);
		// gender
		if (Login.user.getGender() != null) {
			gender = new JLabel(Login.user.getGender().toString());			
			profilePanel.add(gender);
		}
		
		// add to frame
		menuFrame.add(profilePanel);
	}
}
