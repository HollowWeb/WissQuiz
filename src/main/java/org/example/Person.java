package org.example;

import java.util.HashMap;


public class Person {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final HashMap<String, Integer> scores;
    private final String userName;

    public Person(String firstName, String lastName, String email, String password, String userName, HashMap<String, Integer> scores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.scores = setDefaultScores(scores);

    }


    public HashMap<String, Integer> getScores() {return scores;}
    public String getEmail() {return email;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPassword() {return password;}
    public String getUserName() {return userName;}

    public void setScore(String module, int score){
        scores.put(module, score);
    }

    private HashMap<String, Integer> setDefaultScores(HashMap <String, Integer> scores){
        if (scores == null) {
            HashMap<String, Integer> defScores = new HashMap<>();
            defScores.put("Modul122", 0);
            defScores.put("Modul123", 0);
            defScores.put("Modul124", 0);
            defScores.put("Modul125", 0);
            defScores.put("Modul126", 0);
            defScores.put("Modul127", 0);
            defScores.put("Modul128", 0);
            return defScores;
        } else {return scores;}
    }

    public int getTotalScore() {
        int totalScore = 0;
        for (String module : scores.keySet()) {
            totalScore += scores.get(module);
        }
        return totalScore;
    }
}
