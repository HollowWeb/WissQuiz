package org.example.ui;

import org.example.logics.Person;
import org.example.logics.Quiz;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;


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
                {"Modul 114", "Modul114"}, // Button Text, Button Modul
                {"Modul 347", "Modul347"}, // <- wird gemacht
                {"Modul 117", "Modul117"}, // noch zu machen
                {"Modul 293", "Modul293"},
                {"Modul 320", "Modul320"},
                {"Modul 106", "Modul106"}
        };

        for (String[] module : modules) {
            JButton button = new JButton(module[0]);
            button.setFocusPainted(false);
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
}
