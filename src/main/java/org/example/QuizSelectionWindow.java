package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Objects;

public class QuizSelectionWindow extends JFrame {

    public QuizSelectionWindow() {

        setTitle("Quiz Module Selection");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 3)); // Layout to organize buttons

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeButtons();
    }

    private void initializeButtons() {
        // Define module names and corresponding file names
        String[][] modules = {
                {"Modul 231", "modul.json"}, // Button Text, Button Modul.json
                {"Modul 232", "modul12.json"},
                {"Modul 233", "modul233.json"},
                {"Modul 234", "modul234.json"},
                {"Modul 235", "modul235.json"},
                {"Modul 236", "modul236.json"}
        };

        for (String[] module : modules) {
            JButton button = new JButton(module[0]);

            button.addActionListener(e -> openQuiz(module[1]));
            add(button);
        }
    }

    private void openQuiz(String quizFile) {
        try {
            Quiz quiz = new Quiz(quizFile); // Assuming Quiz constructor accepts the file name
            QuizWindow quizWindow = new QuizWindow(quiz, this);
            quizWindow.setVisible(true);
            setVisible(false);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Quiz file not found: " + quizFile, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizSelectionWindow().setVisible(true));
    }
}
