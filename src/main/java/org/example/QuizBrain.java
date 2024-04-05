package org.example;



public class QuizBrain {
/*
    public boolean checkAnswer(String userAnswer, String richtigeAntwort){
        return userAnswer.equals(richtigeAntwort);
    }*/

    public static boolean hasQuestions(Quiz quiz){
        return quiz.getNumberOfQuestions() > quiz.getQuestionNumber();
    }
}

