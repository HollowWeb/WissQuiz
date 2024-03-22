package org.example;

import java.util.List;

public class Modul {
    private String name;
    private List<String> fragen;
    private List<String> antworten;
    private List<String> richtigeAntworten;

    public Modul(){

    }

    public String getName() {
        return name;
    }

    public List<String> getFragen() {
        return fragen;
    }

    public List<String> getAntworten() {
        return antworten;
    }

    public List<String> getRichtigeAntworten() {
        return richtigeAntworten;
    }
}
