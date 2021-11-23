package edu.metrostate.ics370.grm.view;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDarkFuchsiaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonocaiIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsGUI{


    public SettingsGUI() {

        // JPanel + JFrame
        JPanel settingsPanel = new JPanel();
        JFrame frame = new JFrame("Settings");
        frame.add(settingsPanel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        GridLayout settingsLayout = new GridLayout(0,1);
        settingsPanel.setLayout(settingsLayout);

        // user labels and text
        JLabel userLabel = new JLabel("Change Theme");
        settingsPanel.add(userLabel);

        // ComboBox for Theme Choices
        String[] themes = {"FlatDarkLaf", "FlatLightLaf", "FlatDarculaLaf", "FlatIntelliJLaf", "FlatArcOrangeIJTheme", "FlatMonocaiIJTheme", "FlatGradiantoDarkFuchsiaIJTheme"};
        JComboBox cb = new JComboBox(themes);
        settingsPanel.add(cb);

        // Apply Button
        JButton settingsButton = new JButton("Apply");
        settingsPanel.add(settingsButton);

        // Change Font Size
        JLabel textSizeLabel = new JLabel("Font Size");
        settingsPanel.add(textSizeLabel);
        JSlider fontSize = new JSlider(8, 28, 14);
        fontSize.setPaintTrack(true);
        fontSize.setPaintTicks(true);
        fontSize.setPaintLabels(true);
        fontSize.setMajorTickSpacing(2);
        settingsPanel.add(fontSize);
        fontSize.setSnapToTicks(true);

        fontSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int x = ((JSlider) e.getSource()).getValue();
                UIManager.getLookAndFeelDefaults()
                        .put("defaultFont", new Font("TimesRoman", Font.BOLD, x));
            }
        });

        // Apply Button Action Listener
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userTheme = (String) cb.getItemAt(cb.getSelectedIndex());
                try {
                    switch (userTheme) {
                        case "FlatDarkLaf" -> UIManager.setLookAndFeel(new FlatDarkLaf());
                        case "FlatLightLaf" -> UIManager.setLookAndFeel(new FlatLightLaf());
                        case "FlatDarculaLaf" -> UIManager.setLookAndFeel(new FlatDarculaLaf());
                        case "FlatIntelliJLaf" -> UIManager.setLookAndFeel(new FlatIntelliJLaf());
                        case "FlatArcOrangeIJTheme" -> UIManager.setLookAndFeel(new FlatNordIJTheme());
                        case "FlatMonocaiIJTheme" -> UIManager.setLookAndFeel(new FlatMonocaiIJTheme());
                        case "FlatGradiantoDarkFuchsiaIJTheme" -> UIManager.setLookAndFeel(new FlatGradiantoDarkFuchsiaIJTheme());
                    }
                    FlatLaf.updateUI();
                } catch (UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Font Button Action Listener


        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        settingsPanel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGUI menu = new MenuGUI();
                menu.initialize();
                frame.dispose();
            }
        });
    }
}


