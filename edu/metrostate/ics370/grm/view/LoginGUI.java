package edu.metrostate.ics370.grm.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.metrostate.ics370.grm.controller.Connector;
import edu.metrostate.ics370.grm.controller.Login;

/**
 * @author skylar
 */
public class LoginGUI implements ActionListener {
	// login messages
	private final String INVALID_LOGIN = "Username or password is incorrect.";
	private final String INVALID_NEW_USER = "Could not create new user.";
	private final String INVALID_NEW_PASSWORD = "Passwords do not match.";
	private final String VALID_NEW_USER	= "New user created. Please sign in.";

	private static JFrame frame;
	private JPanel loginPanel;
	private JPanel newUserPanel;
	private JButton loginButton;
	private JButton newUserButton;
	private JButton createUserButton;
	private JButton cancelButton;
	private JLabel success;
	
	private JLabel userLabel;
	private JTextField userText;
	private JLabel passwordLabel;
	private JPasswordField passwordText;
	private JLabel confirmPasswordLabel;
	private JPasswordField confirmPasswordText;
	
	/**
	 * No-arg constructor
	 * 
	 * Initializes Login Menu
	 */
	public LoginGUI() {
		frame = new JFrame("Login");
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * Sets login frame as visible
	 */
	public static void openLogin() {
		frame.setVisible(true);
	}
	
	private void buildLoginPanel() {
		loginPanel = new JPanel();

		// user labels and text
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		loginPanel.add(userLabel);
		userText = new JTextField();
		userText.setBounds(100, 20, 165, 25);
		loginPanel.add(userText);
		// password label and text
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		loginPanel.add(passwordLabel);
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		passwordText.addActionListener(this);
		loginPanel.add(passwordText);

		// login button
		loginButton = new JButton("Login");
		loginButton.setBounds(10, 80, 80, 25);
		loginPanel.add(loginButton);
		loginButton.addActionListener(this);
		// new user button
		newUserButton = new JButton("Create New User");
		newUserButton.setBounds(100, 80, 175, 25);
		newUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newUser();
			}
			
		});
		loginPanel.add(newUserButton);

		// success label
		success = new JLabel();
		success.setBounds(10, 110, 300, 25);
		loginPanel.add(success);

		// add to frame
		frame.add(loginPanel);

		// set layout, visibility
		loginPanel.setLayout(null);
		frame.setVisible(true);
	}

	protected void newUser() {
		newUserPanel = new JPanel();
		// user labels and text
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 140, 25);
		newUserPanel.add(userLabel);
		userText = new JTextField();
		userText.setBounds(160, 20, 165, 25);
		newUserPanel.add(userText);
		// password label and text
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 140, 25);
		newUserPanel.add(passwordLabel);
		passwordText = new JPasswordField();
		passwordText.setBounds(160, 50, 165, 25);
		passwordText.addActionListener(this);
		newUserPanel.add(passwordText);
		// confirm password label and text
		confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setBounds(10, 80, 140, 25);
		newUserPanel.add(confirmPasswordLabel);
		confirmPasswordText = new JPasswordField();
		confirmPasswordText.setBounds(160, 80, 165, 25);
		newUserPanel.add(confirmPasswordText);
		
		// TODO add date of birth field
		
		// buttons
		createUserButton = new JButton("Create User");
		createUserButton.setBounds(10, 120, 130, 25);
		createUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// create user
				boolean newUser;
				String user = userText.getText();
				@SuppressWarnings("deprecation")
				String password = passwordText.getText();
				@SuppressWarnings("deprecation")
				String confirmPassword = confirmPasswordText.getText();
				// matching password
				if (password.equals(confirmPassword)) {
					newUser = Login.newUser(user, password);	
					if (newUser != true) {
						success.setText(INVALID_NEW_USER);
					} else {
						success.setText(VALID_NEW_USER);
					}
				} else {
					// passwords do not match
					success.setText(INVALID_NEW_PASSWORD);
				}
				// return to login
				newUserPanel.setVisible(false);
				loginPanel.setVisible(true);
			}	
		});
		newUserPanel.add(createUserButton);
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(160, 120, 100, 25);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// return to login menu
				newUserPanel.setVisible(false);
				loginPanel.setVisible(true);
			}	
		});
		newUserPanel.add(cancelButton);
		
		loginPanel.setVisible(false);
		newUserPanel.setLayout(null);
		frame.add(newUserPanel);

	}

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
				success.setText(INVALID_LOGIN);
			}
		} catch (SQLException err) {
			Connector.processException(err);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.buildLoginPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
