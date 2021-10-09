package edu.metrostate.ics370.grm.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class grmGUI extends JFrame{
    /**
     Declare all components of the GUI or each button.
     */
    private JPanel mainPanel;
    private JButton exit;


    /**
     Declare each listener. What each button is doing when clicked.
     */
    public grmGUI() {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     Main method. Define the window.
     */
    public static void main(String[] args) {
        grmGUI frame = new grmGUI();
        frame.setContentPane(new grmGUI().mainPanel);
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
