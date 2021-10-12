package edu.metrostate.ics370.grm.gui;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class register {
    private JTextField username;
    private JPasswordField password;
    private JPasswordField passwordConfirm;
    private JButton submitButton;
    private JPanel registerPanel;
    private JLabel picture;
    private JButton cancel;

    public register() {
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    static void callWindow() {
        FlatDarkLaf.setup();
        UIManager.put( "Button.arc", 999 );
        JFrame frame = new JFrame();
        frame.setContentPane(new register().registerPanel);
        frame.setTitle("Game Recommendation Manager");
        frame.setPreferredSize(new Dimension(800, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        ImageIcon img = new ImageIcon("edu/metrostate/ics370/grm/gui/images/logo.png");
        frame.setIconImage(img.getImage());
    }


    public static void main(String[] args) {
        register.callWindow();
    }
}
