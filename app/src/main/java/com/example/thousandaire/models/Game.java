package com.example.thousandaire.models;

import java.util.ArrayList;

public class Game {
    private ArrayList<Question> mQuestions;
    private int mCurrentQuestionIndex;

    public Game()
    {
        this.mCurrentQuestionIndex = 0;
        this.mQuestions = new ArrayList<Question>();
    }

    public void addQuestion(Question newQuestion)
    {
        this.mQuestions.add(newQuestion);
    }

    public Question getNextQuestion()
    {
        if(mCurrentQuestionIndex == mQuestions.size())
        {
            return null;
        }
        return this.mQuestions.get(this.mCurrentQuestionIndex++);
    }

    public int getNumberQuestions()
    {
        return this.mQuestions.size();
    }
}
