package edu.metrostate.ics370.grm.view;

import com.formdev.flatlaf.FlatDarkLaf;

import edu.metrostate.ics370.grm.controller.Connector;
import edu.metrostate.ics370.grm.controller.Login;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @author skylar
 * @author nick
 */
public class LoginGUI implements ActionListener {
	private final String INVALID_LOGIN = "Username or password is incorrect.";
	private final String INVALID_NEW_USER = "Could not create new user.";
	private final String INVALID_NEW_PASSWORD = "Passwords do not match.";
	private final String VALID_NEW_USER	= "New user created. Please sign in.";
	
	private static JFrame frame;
	// panel
	private JPanel successPanel;
	private JPanel userPanel;
	private JPanel newUserPanel;
	private JLabel success;
	
	// elements
	private JLabel userLabel;
	private JTextField userText;
	private JLabel passwordLabel;
	private JPasswordField passwordText;
	private JButton loginButton;
	private JButton newUserButton;
	
	private JLabel confirmPasswordLabel;
	private JPasswordField confirmPasswordText;
	private JLabel dateOfBirthLabel;
	private JTextField dateOfBirthText;
	private JButton createUserButton;
	private JButton cancelButton;

	/**
	 * No-arg constructor
	 * 
	 * Initializes Login Menu
	 */
	public LoginGUI() throws UnsupportedLookAndFeelException {
		// Set Frames to Default Dark Theme + Other Modifications
		UIManager.setLookAndFeel(new FlatDarkLaf());
		UIManager.put( "Button.arc", 999 );
		UIManager.getLookAndFeelDefaults()
				.put("defaultFont", new Font("TimesRoman", Font.BOLD, 14));

		frame = new JFrame("Login");
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initialize() {
		buildSuccessPanel();
		buildSignInPanel();
		frame.setVisible(true);		
	}
	
	private void setSuccessMessage(String message) {
		success.setText(message);
	}
	
	private void buildSignInPanel() {
		userPanel = new JPanel();
		userPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		userPanel.setLayout(new GridLayout(0,2));
		Border padding = BorderFactory.createEmptyBorder(50, 50, 50, 50);
		userPanel.setBorder(padding);
		
		// user labels and text
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		userPanel.add(userLabel);
		userText = new JTextField();
		userText.setBounds(100, 20, 165, 25);
		userPanel.add(userText);
		// password label and text
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		userPanel.add(passwordLabel);
		passwordText = new JPasswordField();
		passwordText.setActionCommand("Login");
		passwordText.setBounds(100, 50, 165, 25);
		passwordText.addActionListener(this);
		userPanel.add(passwordText);

		// login button
		loginButton = new JButton("Login");
		loginButton.setBounds(10, 80, 80, 25);
		userPanel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = userText.getText();
				@SuppressWarnings("deprecation")
				String password = passwordText.getText();
				boolean login;
				try {
					// validate login
					login = Login.signIn(user, password);
					if (login == true) {
						MenuGUI menu = new MenuGUI();
						menu.initialize();
						userText.setText("");
						passwordText.setText("");
						frame.setVisible(false);
					} else {
						// display login error
						setSuccessMessage(INVALID_LOGIN);
					}
				} catch (SQLException err) {
					Connector.processException(err);
				}
			}
		});
		// new user button
		newUserButton = new JButton("Create New User");
		newUserButton.setBounds(100, 80, 165, 25);
		newUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userPanel.setVisible(false);
				buildNewUserPanel();
			}
			
		});
		userPanel.add(newUserButton);

		// add to frame
		frame.add(userPanel);
	}
	
	private void buildNewUserPanel() {
		newUserPanel = new JPanel();
		// user labels and text
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 220, 25);
		newUserPanel.add(userLabel);
		userText = new JTextField();
		userText.setBounds(220, 20, 165, 25);
		newUserPanel.add(userText);
		// password label and text
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 220, 25);
		newUserPanel.add(passwordLabel);
		passwordText = new JPasswordField();
		passwordText.setBounds(220, 50, 165, 25);
		passwordText.addActionListener(this);
		newUserPanel.add(passwordText);
		// confirm password label and text
		confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setBounds(10, 80, 220, 25);
		newUserPanel.add(confirmPasswordLabel);
		confirmPasswordText = new JPasswordField();
		confirmPasswordText.setBounds(220, 80, 165, 25);
		newUserPanel.add(confirmPasswordText);
		// date of birth
		dateOfBirthLabel = new JLabel("Date of birth (mm/dd/yyyy):");
		dateOfBirthLabel.setBounds(10, 110, 220, 25);
		newUserPanel.add(dateOfBirthLabel);
		dateOfBirthText = new JTextField();
		dateOfBirthText.setBounds(220, 110, 165, 25);
		newUserPanel.add(dateOfBirthText);
		
		// buttons
		createUserButton = new JButton("Create User");
		createUserButton.setBounds(10, 140, 130, 25);
		createUserButton.addActionListener(this);
		newUserPanel.add(createUserButton);
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(160, 140, 100, 25);
		cancelButton.addActionListener(this);
		newUserPanel.add(cancelButton);
		
		newUserPanel.setLayout(null);
		frame.add(newUserPanel);
	}
	
	private void buildSuccessPanel() {
		successPanel = new JPanel();
		success = new JLabel();
		successPanel.add(success, new FlowLayout());
		frame.add(successPanel, BorderLayout.NORTH);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		String user = userText.getText();
		@SuppressWarnings("deprecation")
		String password = passwordText.getText();

		switch (action) {
		case "Login":
			boolean login;
			try {
				// validate login
				login = Login.signIn(user, password);
				if (login == true) {
					MenuGUI menu = new MenuGUI();
					menu.initialize();
					userText.setText("");
					passwordText.setText("");
					frame.setVisible(false);
				} else {
					// display login error
					setSuccessMessage(INVALID_LOGIN);
				}
			} catch (SQLException err) {
				Connector.processException(err);
			}
			break;
		case "Create User":
			// create user
			boolean newUser;
			@SuppressWarnings("deprecation")
			String confirmPassword = confirmPasswordText.getText();
			String dob = dateOfBirthText.getText();
			// matching password
			if (password.equals(confirmPassword)) {
				newUser = Login.newUser(user, password, dob);	
				if (newUser != true) {
					setSuccessMessage(INVALID_NEW_USER);
					newUserPanel.setVisible(false);
					userPanel.setVisible(true);
				} else {
					setSuccessMessage(VALID_NEW_USER);
					newUserPanel.setVisible(false);
					userPanel.setVisible(true);
				}
			} else {
				// passwords do not match
				setSuccessMessage(INVALID_NEW_PASSWORD);
			}

		case "Cancel":
			newUserPanel.setVisible(false);
			userPanel.setVisible(true);
		}
	}
}
