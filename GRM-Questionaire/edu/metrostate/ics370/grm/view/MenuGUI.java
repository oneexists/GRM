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

/**
 * @author skylar
 *
 */
public class MenuGUI implements ActionListener {

	private JPanel menuPanel;
	private JFrame frame;
	private JButton editProfile;
	private JButton takeQuiz;
	
	/**
	 * 
	 */
	public MenuGUI() {
		frame = new JFrame("Game Recommendation Manager Menu");
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initialize() {
		buildMenuPanel();
		frame.setVisible(true);
	}
	
	private void buildMenuPanel() {
		// panel and layout
		FlowLayout menuLayout = new FlowLayout();
		menuPanel = new JPanel();
		menuPanel.setLayout(menuLayout);
		
		// edit profile button
		editProfile = new JButton("Edit Profile");
		editProfile.addActionListener(this);
		menuPanel.add(editProfile);
		// take quiz button
		takeQuiz = new JButton("Take Quiz");
		takeQuiz.addActionListener(this);
		menuPanel.add(takeQuiz);
		
		// add to frame
		frame.add(menuPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == "Edit Profile") {
			// TODO implement edit profile
		} else {
			// TODO implment take quiz
		}
		
	}

}
