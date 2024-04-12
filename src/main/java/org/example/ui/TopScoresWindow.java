package org.example.ui;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import org.example.logics.TopScorer;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class TopScoresWindow extends JFrame {
    public TopScoresWindow() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        List<TopScorer> topScorers = getTopScorers();

        // Display top scorers
        assert topScorers != null;
        JPanel scoresPanel = new JPanel(new GridLayout(topScorers.size(), 1));
        for (TopScorer person : topScorers) {
            scoresPanel.add(new JLabel(person.getUserName() + " - Total Score: " + person.getTotalScore()));
        }

        JScrollPane scrollPane = new JScrollPane(scoresPanel);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private List<TopScorer> getTopScorers() {

        List<TopScorer> users = new ArrayList<>();
        try {
            // Read the JSON file into a String
            String content = new String(Files.readAllBytes(Paths.get("users.json")));
            JSONArray jsonArray = new JSONArray(content);

            // Process each user in the array
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userJson = jsonArray.getJSONObject(i);
                String userName = userJson.getString("userName");
                JSONObject scoresJson = userJson.getJSONObject("scores");

                // Calculate the total score
                int totalScore = 0;
                for (String key : scoresJson.keySet()) {
                    totalScore += scoresJson.getInt(key);
                }

                // Add the user and their total score to the list
                users.add(new TopScorer(userName, totalScore));
            }

            // Sort the list by total score in descending order
            users.sort(Comparator.comparingInt(TopScorer::getTotalScore).reversed());

            // Return the top 10 scorers
            return users.subList(0, Math.min(users.size(), 10));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}




