package edu.metrostate.ics370.grm.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JFrame{

    //Define every component
    private JButton submitButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel loginPanel;
    private JLabel logoImage;
    private JLabel registerButton;

    //Define each listener. What each button or component does.
    public LoginGUI() {
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                RegisterGUI RegisterGUI = new RegisterGUI();
                RegisterGUI.callWindow();
            }
        });
    }

    //Main Method
    public static void main(String[] args) {
        UIManager.put( "Button.arc", 999 );
        JFrame frame = new JFrame();
        frame.setContentPane(new LoginGUI().loginPanel);
        frame.setTitle("Game Recommendation Manager");
        frame.setPreferredSize(new Dimension(800, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
