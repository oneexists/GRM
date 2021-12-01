package edu.metrostate.ics370.grm.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import edu.metrostate.ics370.grm.controller.Connector;
import edu.metrostate.ics370.grm.controller.Login;

/**
 * @author skylar
 *
 */
public class SignInGUI extends LoginGUI implements ActionListener {
	// login messages
	private final String INVALID_LOGIN = "Username or password is incorrect.";
	// panel
	private JPanel userPanel;
	// elements
	private JLabel userLabel;
	private JTextField userText;
	private JLabel passwordLabel;
	private JPasswordField passwordText;
	private JButton loginButton;
	private JButton newUserButton;

	public SignInGUI() throws UnsupportedLookAndFeelException {
		super();
	}

	@Override
	public void buildPanel() {
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
		passwordText.setBounds(100, 50, 165, 25);
		passwordText.addActionListener(this);
		userPanel.add(passwordText);

		// login button
		loginButton = new JButton("Login");
		loginButton.setBounds(10, 80, 80, 25);
		userPanel.add(loginButton);
		loginButton.addActionListener(this);
		// new user button
		newUserButton = new JButton("Create New User");
		newUserButton.setBounds(100, 80, 165, 25);
		newUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					NewUserGUI newUser = new NewUserGUI();
					newUser.buildPanel();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		userPanel.add(newUserButton);

		// add to frame
		frame.add(userPanel);
		frame.setVisible(true);
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
				setSuccessMessage(INVALID_LOGIN);
			}
		} catch (SQLException err) {
			Connector.processException(err);
		}
	}
}
