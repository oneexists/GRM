package edu.metrostate.ics370.grm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

import edu.metrostate.ics370.grm.controller.Login;

/**
 * @author skylar
 *
 */
public class NewUserGUI extends LoginGUI implements ActionListener {
	private final String INVALID_NEW_USER = "Could not create new user.";
	private final String INVALID_NEW_PASSWORD = "Passwords do not match.";
	private final String VALID_NEW_USER	= "New user created. Please sign in.";

	private JPanel userPanel;
	private JLabel userLabel;
	private JTextField userText;
	private JLabel passwordLabel;
	private JPasswordField passwordText;
	private JLabel confirmPasswordLabel;
	private JPasswordField confirmPasswordText;
	private JLabel dateOfBirthLabel;
	private JTextField dateOfBirthText;
	private JButton createUserButton;
	private JButton cancelButton;
	
	public NewUserGUI() throws UnsupportedLookAndFeelException {
		super();
	}

	@Override
	public void buildPanel() {
		userPanel = new JPanel();
		// user labels and text
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 220, 25);
		userPanel.add(userLabel);
		userText = new JTextField();
		userText.setBounds(220, 20, 165, 25);
		userPanel.add(userText);
		// password label and text
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 220, 25);
		userPanel.add(passwordLabel);
		passwordText = new JPasswordField();
		passwordText.setBounds(220, 50, 165, 25);
		passwordText.addActionListener(this);
		userPanel.add(passwordText);
		// confirm password label and text
		confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setBounds(10, 80, 220, 25);
		userPanel.add(confirmPasswordLabel);
		confirmPasswordText = new JPasswordField();
		confirmPasswordText.setBounds(220, 80, 165, 25);
		userPanel.add(confirmPasswordText);
		// date of birth
		dateOfBirthLabel = new JLabel("Date of birth (mm/dd/yyyy):");
		dateOfBirthLabel.setBounds(10, 110, 220, 25);
		userPanel.add(dateOfBirthLabel);
		dateOfBirthText = new JTextField();
		dateOfBirthText.setBounds(220, 110, 165, 25);
		userPanel.add(dateOfBirthText);
		
		// buttons
		createUserButton = new JButton("Create User");
		createUserButton.setBounds(10, 140, 130, 25);
		createUserButton.addActionListener(this);
		userPanel.add(createUserButton);
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(160, 140, 100, 25);
		cancelButton.addActionListener(this);
		userPanel.add(cancelButton);
		
		userPanel.setLayout(null);
		frame.add(userPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case "Create User":
			// create user
			boolean newUser;
			String user = userText.getText();
			@SuppressWarnings("deprecation")
			String password = passwordText.getText();
			@SuppressWarnings("deprecation")
			String confirmPassword = confirmPasswordText.getText();
			String dob = dateOfBirthText.getText();
			// matching password
			if (password.equals(confirmPassword)) {
				newUser = Login.newUser(user, password, dob);	
				if (newUser != true) {
					setSuccessMessage(INVALID_NEW_USER);
				} else {
					setSuccessMessage(VALID_NEW_USER);
				}
			} else {
				// passwords do not match
				setSuccessMessage(INVALID_NEW_PASSWORD);
			}
			frame.dispose();

		case "Cancel":
			frame.dispose();
		}
	}

}
