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
 *
 */
public class LoginGUI implements ActionListener {

	private static JFrame frame;
	private JPanel panel;
	private JButton loginButton;
	private JLabel success;
	
	private JLabel userLabel;
	private JTextField userText;
	private JLabel passwordLabel;
	private JPasswordField passwordText;	
	
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
		// instantiate elements
		panel = new JPanel();
		userLabel = new JLabel("User");
		userText = new JTextField();
		passwordLabel = new JLabel("Password");
		passwordText = new JPasswordField();
		loginButton = new JButton("Login");
		success = new JLabel();

		// set bounds
		userLabel.setBounds(10, 20, 80, 25);
		userText.setBounds(100, 20, 165, 25);
		passwordLabel.setBounds(10, 50, 80, 25);
		passwordText.setBounds(100, 50, 165, 25);
		loginButton.setBounds(10, 80, 80, 25);
		success.setBounds(10, 110, 300, 25);

		// add to panel
		panel.add(userLabel);
		panel.add(userText);
		panel.add(passwordLabel);
		panel.add(passwordText);
		frame.add(panel);
		panel.add(loginButton);
		panel.add(success);

		// set action listeners, layout, visibility
		loginButton.addActionListener(this);
		passwordText.addActionListener(this);
		panel.setLayout(null);
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
				success.setText("Invalid login.");
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
