package org.example.logics;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private final List<String> fragen;
    private final List<String> antworten;
    private final List<String> richtigeAntworten;
    private final Modul jsonArray;
    private final Person person;
    private final String modul;
    private int score;
    private final int numberOfQuestions;
    private int questionNumber;

    public Quiz(String modul, Person person) throws FileNotFoundException {
        Gson gson = new Gson();
        this.modul = modul;
        this.jsonArray = gson.fromJson(new FileReader("src/main/java/org/example/module/"+modul+".json"), Modul.class);
        this.fragen = initFragen();
        this.antworten = initAntworten();
        this.richtigeAntworten = initRichtigeAntworten();
        this.score = 0;
        this.numberOfQuestions = initNumberOfQuestions();
        this.questionNumber = 0;
        this.person = person;

    }

    private List<String> initFragen(){
        return jsonArray.getFragen();
    }

    private List<String> initAntworten(){
        return jsonArray.getAntworten();
    }

    private List<String> initRichtigeAntworten(){
        return jsonArray.getRichtigeAntworten();
    }

    private int initNumberOfQuestions(){
        return jsonArray.getNumberOfQuestions();
    }

    public String getFrage(int i) {
        return fragen.get(i);
    }

    public List<String> getAntwort(int i){
        if (!QuizBrain.hasQuestions(this)){
           return null;
        }

        List<String> antwortenListe  = new ArrayList<>(List.of(antworten.get(i).split("\\|")));

        Collections.shuffle(antwortenListe);
        return antwortenListe;

    }

    public String getRichtigeAntwort(int i){
        return richtigeAntworten.get(i);
    }

    public int getScore() {
        return score;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void addScore(){
        score++;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void nextQuestion() {
        if (questionNumber <= numberOfQuestions) {
            questionNumber++;
        }
    }

    public Person getPerson() {
        return person;
    }

    public String getModul() {
        return modul;
    }
}
