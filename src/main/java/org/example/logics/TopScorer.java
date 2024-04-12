package org.example.logics;

public class TopScorer {
    private String userName;
    private int totalScore;

    public TopScorer(String userName, int totalScore) {
        this.userName = userName;
        this.totalScore = totalScore;
    }

    public String getUserName() {
        return userName;
    }

    public int getTotalScore() {
        return totalScore;
    }
}