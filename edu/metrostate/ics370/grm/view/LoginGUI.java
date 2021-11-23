package edu.metrostate.ics370.grm.view;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author skylar
 * @author nick
 */
public abstract class LoginGUI implements ActionListener {
	protected static JFrame frame;
	protected JPanel successPanel;
	protected JPanel userPanel;
	private JLabel success;
	
	public abstract void buildPanel();
	@Override
	public abstract void actionPerformed(ActionEvent e);
	
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
		frame.setVisible(true);
		buildSuccessPanel();
	}
	
	public void setSuccessMessage(String message) {
		success.setText(message);
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
					LoginGUI window = new SignInGUI();
					window.buildPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
