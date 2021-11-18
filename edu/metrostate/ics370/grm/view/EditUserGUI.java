package edu.metrostate.ics370.grm.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.metrostate.ics370.grm.controller.Login;
import edu.metrostate.ics370.grm.model.User;

public class EditUserGUI extends JPanel implements ActionListener {

	/**
	 * Version of the Edit User Panel
	 */
	private static final long serialVersionUID = 202111001L;

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
	private JComboBox genderComboBox = new JComboBox<User.Gender>(User.Gender.values());
	private JButton saveButton;
	private JButton cancelButton;
	/**
	 * No-arg constructor
	 */
	public EditUserGUI() {
		editPanel = new JPanel();
		GridLayout editUserLayout = new GridLayout(0, 2);
		editPanel.setLayout(editUserLayout);
	}
	
	public void initialize() {
		buildEditPanel();
	}
	private void buildEditPanel() {
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// "Save" and "Cancel" buttons
		String username = userText.getText();
		String firstName = firstNameText.getText();
		String lastName = lastNameText.getText();
		String dob = dateOfBirthText.getText();
		// get gender
		
	}

}
