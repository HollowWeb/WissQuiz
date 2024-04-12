package org.example.logics;


public class QuizBrain {


    public static boolean hasQuestions(Quiz quiz){
        return quiz.getNumberOfQuestions() >= quiz.getQuestionNumber();
    }
}

