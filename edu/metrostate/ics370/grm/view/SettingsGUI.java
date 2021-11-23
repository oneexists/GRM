package edu.metrostate.ics370.grm.view;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsGUI{

    public SettingsGUI() {

        // JPanel + JFrame
        JPanel settingsPanel = new JPanel();
        JFrame frame = new JFrame("Settings");
        frame.add(settingsPanel);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        GridLayout settingsLayout = new GridLayout(0,1);
        settingsLayout.setVgap(20);
        settingsPanel.setLayout(settingsLayout);

        // user labels and text
        JLabel userLabel = new JLabel("Change Theme");
        settingsPanel.add(userLabel);

        // ComboBox for Theme Choices
        String[] themes = {"Dark", "Light", "Darcula", "IntelliJ", "Arc Orange", "Arc Dark", "Carbon", "Cyan Light", "Nord"};
        JComboBox<String> cb = new JComboBox<String>(themes);
        settingsPanel.add(cb);

        // Apply Button for theme
        JButton themeApplyButton = new JButton("Apply");
        settingsPanel.add(themeApplyButton);

        // Change Font Size combo box
        JLabel textSizeLabel = new JLabel("Font Size");
        settingsPanel.add(textSizeLabel);
        JSlider fontSize = new JSlider(8, 28, 14);
        fontSize.setPaintTrack(true);
        fontSize.setPaintTicks(true);
        fontSize.setPaintLabels(true);
        fontSize.setMajorTickSpacing(2);
        settingsPanel.add(fontSize);
        fontSize.setSnapToTicks(true);

        // Apply Button Action Listener
        themeApplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userTheme = (String) cb.getItemAt(cb.getSelectedIndex());
                try {
                    switch (userTheme) {
                        case "Dark": UIManager.setLookAndFeel(new FlatDarkLaf());
                        case "Light": UIManager.setLookAndFeel(new FlatLightLaf());
                        case "Darcula": UIManager.setLookAndFeel(new FlatDarculaLaf());
                        case "IntelliJ": UIManager.setLookAndFeel(new FlatIntelliJLaf());
                        case "Arc Orange": UIManager.setLookAndFeel(new FlatArcOrangeIJTheme());
                        case "Arc Dark": UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
                        case "Carbon": UIManager.setLookAndFeel(new FlatCarbonIJTheme());
                        case "Cyan Light": UIManager.setLookAndFeel(new FlatCyanLightIJTheme());
                        case "Nord": UIManager.setLookAndFeel(new FlatNordIJTheme());
                    }
                    FlatLaf.updateUI();
                } catch (UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Apply Button for font size
        JButton textApplyButton = new JButton("Apply");
        settingsPanel.add(textApplyButton);

        // Font Size Apply Button Action Listener
        textApplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = fontSize.getValue();
                UIManager.getLookAndFeelDefaults()
                        .put("defaultFont", new Font("TimesRoman", Font.BOLD, x));
                FlatLaf.updateUI();
            }
        });

        // Cancel Button to main menu
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


