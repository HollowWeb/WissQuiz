package org.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {

    public StartScreen() {
        super("Start Screen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // Set background
        setContentPane(new JLabel(new ImageIcon("src/main/resources/logo.png"))); // Update with your background path
        setLayout(null);


        // Start Game Button
        JButton startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        startGameButton.setBounds(75, 475, 200, 50);
        startGameButton.setBackground(Color.BLACK);
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setFocusPainted(false);
        startGameButton.setBorder(null);
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginWindow(); // Assuming LoginWindow() is your login window class
                dispose(); // Close the start screen
            }
        });

        // Show Top Scorers Button
        JButton showTopScorersButton = new JButton("Top Scorers");
        showTopScorersButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        showTopScorersButton.setBounds(525, 475, 200, 50);
        showTopScorersButton.setBackground(Color.BLACK);
        showTopScorersButton.setForeground(Color.WHITE);
        showTopScorersButton.setBorder(null);
        showTopScorersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TopScoresWindow(); // This will be the window displaying top scorers
            }
        });

        // Add buttons to the start screen
        add(startGameButton);
        add(showTopScorersButton);


        setVisible(true);
    }

    public static void main(String[] args) {
        new StartScreen();
    }
}

