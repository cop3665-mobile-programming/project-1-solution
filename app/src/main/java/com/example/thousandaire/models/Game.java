package com.example.thousandaire.models;

import java.util.ArrayList;

public class Game {
    private ArrayList<Question> questions;

    public void addQuestion(Question newQuestion)
    {
        questions.add(newQuestion);
    }
}
