package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Objects;

public class QuizSelectionWindow extends JFrame {
    public QuizSelectionWindow(Person person) {
        setTitle("Quiz Module Selection");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 3)); // Layout to organize buttons

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeButtons(person);
    }

    private void initializeButtons(Person person) {
        // Define module names and corresponding file names
        String[][] modules = {
                {"Modul 231", "Modul122"}, // Button Text, Button Modul
                {"Modul 232", "modul12"},
                {"Modul 233", "modul233"},
                {"Modul 234", "modul234"},
                {"Modul 235", "modul235"},
                {"Modul 236", "modul236"}
        };

        for (String[] module : modules) {
            JButton button = new JButton(module[0]);

            button.addActionListener(e -> openQuiz(module[1], person));
            add(button);
        }
    }

    private void openQuiz(String quizFile, Person person) {
        try {
            Quiz quiz = new Quiz(quizFile, person); // Assuming Quiz constructor accepts the file name
            QuizWindow quizWindow = new QuizWindow(quiz, this);
            quizWindow.setVisible(true);
            setVisible(false);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Quiz file not found: " + quizFile, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> new QuizSelectionWindow().setVisible(true));
    }
}
