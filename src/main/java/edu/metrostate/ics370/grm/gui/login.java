package edu.metrostate.ics370.grm.gui;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class login extends JFrame{

    //Define every component
    private JButton submitButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel loginPanel;
    private JLabel logoImage;
    private JLabel registerButton;

    //Define each listener. What each button or component does.
    public login() {
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                register register = new register();
                edu.metrostate.ics370.grm.gui.register.callWindow();
            }
        });
    }

    //Main Method
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        UIManager.put( "Button.arc", 999 );
        JFrame frame = new JFrame();
        frame.setContentPane(new login().loginPanel);
        frame.setTitle("Game Recommendation Manager");
        frame.setPreferredSize(new Dimension(800, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
