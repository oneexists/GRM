package edu.metrostate.ics370.grm.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterGUI {
    private JTextField username;
    private JPasswordField password;
    private JPasswordField passwordConfirm;
    private JButton submitButton;
    private JPanel registerPanel;
    private JLabel picture;
    private JButton cancel;

    public RegisterGUI() {
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    static void callWindow() {
        UIManager.put( "Button.arc", 999 );
        JFrame frame = new JFrame();
        frame.setContentPane(new RegisterGUI().registerPanel);
        frame.setTitle("Game Recommendation Manager");
        frame.setPreferredSize(new Dimension(800, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        ImageIcon img = new ImageIcon("edu/metrostate/ics370/grm/gui/images/logo.png");
        frame.setIconImage(img.getImage());
    }


    public static void main(String[] args) {
        RegisterGUI.callWindow();
    }
}
