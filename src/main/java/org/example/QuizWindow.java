package org.example;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.List;

public class QuizWindow extends JFrame {
    private final Quiz quiz;
    private JLabel questionLabel;
    private JButton[] answerButtons = new JButton[4];
    private JLabel scoreLabel;

    public QuizWindow(Quiz quiz) {
        this.quiz = quiz;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Quiz App");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        questionLabel = new JLabel("Question", SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel();
        answersPanel.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < answerButtons.length; i++) {
            final int idx = i;
            answerButtons[i] = new JButton("Answer " + (i + 1));
            answerButtons[i].addActionListener(this::answerButtonClicked);
            answersPanel.add(answerButtons[i]);
        }
        add(answersPanel, BorderLayout.CENTER);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        add(scoreLabel, BorderLayout.SOUTH);

        loadQuestion();
    }

    private void loadQuestion() {
        if (QuizBrain.hasQuestions(quiz)) {
            System.out.println("load");
            int questionNumber = quiz.getQuestionNumber();
            String question = quiz.getFrage(questionNumber);
            List<String> answers = quiz.getAntwort(questionNumber);

            questionLabel.setText(question);
            for (int i = 0; i < answers.size(); i++) {
                answerButtons[i].setText(answers.get(i));
            }
        }
    }

    private void answerButtonClicked(ActionEvent e) {
        if (QuizBrain.hasQuestions(quiz)){
            JButton clickedButton = (JButton) e.getSource();
            String selectedAnswer = clickedButton.getText();
            String correctAnswer = quiz.getRichtigeAntwort(quiz.getQuestionNumber());


            if (selectedAnswer.equals(correctAnswer)) {
                quiz.addScore();
            }


            quiz.nextQuestion(); // This method should be implemented in Quiz class to update questionNumber
            loadQuestion();
            updateScoreLabel();

        }
        else {
            JOptionPane.showMessageDialog(this, "Quiz finished! Your score: " + quiz.getScore());
            System.exit(0);
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + quiz.getScore());
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Example usage

        Quiz quiz = new Quiz("modul.json"); // moduleName should be replaced with actual module name
        SwingUtilities.invokeLater(() -> new QuizWindow(quiz).setVisible(true));
    }
}

