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

import edu.metrostate.ics370.grm.controller.Login;
import edu.metrostate.ics370.grm.questionaire.RecommendationManager;

/**
 * @author skylar
 *
 */
public class MenuGUI extends JPanel {

	/**
	 * Version of the Menu Panel
	 */
	private static final long serialVersionUID = 202110001L;

	private JPanel menuPanel;
	private JFrame frame;
	private JButton editProfile;
	private JButton takeQuiz;
	private JButton logout;
	
	/**
	 * No-arg constructor
	 */
	public MenuGUI() {
		frame = new JFrame("Game Recommendation Manager Menu");
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Initialize Menu
	 */
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
			public void actionPerformed(ActionEvent e)
			{
				// TODO implement take quiz frame
				RecommendationManager rms;
				try
				{
					rms = new RecommendationManager();
				}
				catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuPanel.add(takeQuiz);
		// logout button
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO logout user and return to login menu
				Login.signOut();
				frame.dispose();
			}
		});
		menuPanel.add(logout);
		
		// add to frame
		frame.add(menuPanel, BorderLayout.CENTER);
	}
}
