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

import edu.metrostate.ics370.grm.controller.Login;

/**
 * @author skylar
 *
 */
public class LoginGUI implements ActionListener {

	private JFrame frame;
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
	
	private void buildLoginPanel() {
		panel = new JPanel();
		userLabel = new JLabel("User");
		userText = new JTextField();
		passwordLabel = new JLabel("Password");
		passwordText = new JPasswordField();
		loginButton = new JButton("Login");
		success = new JLabel();

		userLabel.setBounds(10, 20, 80, 25);
		userText.setBounds(100, 20, 165, 25);
		passwordLabel.setBounds(10, 50, 80, 25);
		passwordText.setBounds(100, 50, 165, 25);
		loginButton.setBounds(10, 80, 80, 25);
		success.setBounds(10, 110, 300, 25);

		panel.add(userLabel);
		panel.add(userText);
		panel.add(passwordLabel);
		panel.add(passwordText);
		frame.add(panel);
		panel.add(loginButton);
		panel.add(success);

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
			login = Login.signIn(user, password);
			if (login == true) {
				MenuGUI menu = new MenuGUI();
				menu.initialize();
			} else {
				// display login error
				success.setText("Invalid login.");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
