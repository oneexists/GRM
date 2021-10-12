package edu.metrostate.ics370.grm.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{
    /**
     Declare all components of the GUI or each button.
     */
    private JPanel mainPanel;
    private JButton exit;
    private JButton homeButton;
    private JButton quizButton;
    private JButton settingsButton;


    /**
     Declare each listener. What each button is doing when clicked.
     */
    public MainGUI() {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    static void mainWindow() {
        UIManager.put( "Button.arc", 999 );
        MainGUI frame = new MainGUI();
        frame.setContentPane(new MainGUI().mainPanel);
        frame.setTitle("Game Recommendation Manager");
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        ImageIcon img = new ImageIcon("edu/metrostate/ics370/grm/gui/images/logo.png");
        frame.setIconImage(img.getImage());

    }
    /**
     Main method.
     */
    public static void main(String[] args) {
        mainWindow();
    }
}
