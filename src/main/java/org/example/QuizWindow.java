package org.example;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.List;

public class QuizWindow extends JFrame {
    private final Quiz quiz;
    private final Person person;
    private JLabel questionLabel;
    private JButton[] answerButtons = new JButton[4];
    private JLabel scoreLabel;
    private QuizSelectionWindow quizSelectionWindow;

    public QuizWindow(Quiz quiz, QuizSelectionWindow quizSelectionWindow) {
        this.quiz = quiz;
        this.quizSelectionWindow = quizSelectionWindow;
        this.person = quiz.getPerson();
        initializeUI(person);

    }

    private void initializeUI(Person person) {
        setTitle("Quiz App");
        setSize(600, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

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
        if (quiz.getQuestionNumber() < quiz.getNumberOfQuestions()){
            JButton clickedButton = (JButton) e.getSource();
            String selectedAnswer = clickedButton.getText();
            String correctAnswer = quiz.getRichtigeAntwort(quiz.getQuestionNumber());
            System.out.println(selectedAnswer);
            System.out.println(correctAnswer);

            if (selectedAnswer.equals(correctAnswer)) {
                quiz.addScore();
            }


            quiz.nextQuestion(); // This method should be implemented in Quiz class to update questionNumber
            loadQuestion();
            updateScoreLabel();
            if (!(quiz.getQuestionNumber() < quiz.getNumberOfQuestions())){
                JOptionPane.showMessageDialog(this, "Quiz finished! Your score: " + quiz.getScore());
                person.setScore(quiz.getModul(), quiz.getScore());
                JsonWriterReader.writeToJsonFile(person);
                setVisible(false);
                dispose();
                this.quizSelectionWindow.setVisible(true);
            }

        }

    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + quiz.getScore());
    }
/*
    public static void main(String[] args) throws FileNotFoundException {
        // Example usage

        Quiz quiz = new Quiz("Modul122.json"); // moduleName should be replaced with actual module name
        //SwingUtilities.invokeLater(() -> new QuizWindow(quiz, QuizSelectionWindow()).setVisible(true));
    }*/
}

