package org.example;


import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Gson gson = new Gson();
        Modul myObject = gson.fromJson(new FileReader("src/main/java/org/example/modul.json"), Modul.class);
        System.out.println(myObject.getAntworten());
        System.out.println(myObject.getFragen().toArray().length);
        System.out.println(myObject.getName());
        System.out.println(myObject.getRichtigeAntworten());


        Modul myObject = gson.fromJson(new FileReader("src/main/java/org/example/modul.json"), Modul.class);

    }
}
