package org.example.logics;

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
        if (this.scores.get(module) < score){
            this.scores.put(module, score);
        }
    }

    private HashMap<String, Integer> setDefaultScores(HashMap <String, Integer> scores){
        if (scores == null) {
            HashMap<String, Integer> defScores = new HashMap<>();
            defScores.put("Modul347", 0);
            defScores.put("Modul114", 0);
            defScores.put("Modul117", 0);
            defScores.put("Modul293", 0);
            defScores.put("Modul320", 0);
            defScores.put("Modul106", 0);
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
