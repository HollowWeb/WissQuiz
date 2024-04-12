package org.example.logics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

public class JsonWriterReader {

    public static void writeToJsonFile(Person person) {
        try {
            File file = new File("src/main/resources/users.json");
            JSONArray jsonArray;

            // Check if the file exists
            if (file.exists() && !file.isDirectory()) {
                // Read the existing content
                String content = new String(Files.readAllBytes(Paths.get("src/main/resources/users.json")));
                jsonArray = new JSONArray(content);
            } else {
                // Create a new JSON array if the file doesn't exist
                jsonArray = new JSONArray();
            }

            // Prepare the JSON object to be added or updated
            JSONObject newUser = getJsonObject(person);

            // Check if the user already exists in the array
            boolean userExists = false;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject existingUser = jsonArray.getJSONObject(i);
                if (existingUser.getString("userName").equals(person.getUserName())) {
                    // User found, replace the existing user with the new data
                    jsonArray.put(i, newUser);
                    userExists = true;
                    break;
                }
            }

            // If the user does not exist, add new user
            if (!userExists) {
                jsonArray.put(newUser);
            }

            // Write the updated JSON back to the file
            try (FileWriter fileWriter = new FileWriter("src/main/resources/users.json")) {
                fileWriter.write(jsonArray.toString(4)); // Indentation for readability
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static JSONObject getJsonObject(Person person) {
        JSONObject newUser = new JSONObject();
        newUser.put("firstName", person.getFirstName());
        newUser.put("lastName", person.getLastName());
        newUser.put("email", person.getEmail());
        newUser.put("password", person.getPassword());
        // Convert scores HashMap to JSONObject
        JSONObject scoresJson = new JSONObject(person.getScores());
        newUser.put("scores", scoresJson);
        newUser.put("userName", person.getUserName());
        return newUser;
    }

    public static Person lookUpPerson(String password, String userName) throws IOException {
        try {
            File file = new File("src/main/resources/users.json");

            if (!file.exists() || file.isDirectory()) {
                return null;
            }

            String fileContent = new String(Files.readAllBytes(Paths.get("src/main/resources/users.json")));

            JSONArray jsonArray = new JSONArray(fileContent);

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject user = jsonArray.getJSONObject(i);

                if (Objects.equals(user.getString("userName"), userName) && Objects.equals(user.getString("password"), password)){

                    HashMap<String, Integer> scores = new HashMap<>();
                    JSONObject userScores = user.getJSONObject("scores");

                    for (String key : userScores.keySet()){
                        scores.put(key, userScores.getInt(key));
                    }

                    return new Person(
                            user.getString("firstName"),
                            user.getString("lastName"),
                            user.getString("email"),
                            user.getString("password"),
                            user.getString("userName"),
                            scores);
                }
            }
        } catch (JSONException e) {e.printStackTrace();}

        return null;
    }

    public static boolean isUsernameTaken(String username) {
        String filePath = "src/main/resources/users.json";
        try {
            File file = new File(filePath);

            // Check if the file exists
            if (!file.exists() || file.isDirectory()) {

                return false;
            }

            // Read the file content
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);

            // Iterate through the JSON array to find the user
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userJson = jsonArray.getJSONObject(i);
                if (userJson.getString("userName").equalsIgnoreCase(username)) {
                    // Username found, so it is taken
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Username not found
        return false;
    }


}
