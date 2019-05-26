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

    public Question getCurrentQuestion()
    {
        if(mCurrentQuestionIndex == mQuestions.size())
        {
            return null;
        }
        return this.mQuestions.get(this.mCurrentQuestionIndex);
    }

    public Question getNextQuestion()
    {
        if((mCurrentQuestionIndex + 1) == mQuestions.size())
        {
            return null;
        }
        return this.mQuestions.get(this.mCurrentQuestionIndex + 1);
    }

    public boolean isFinalQuestion()
    {
        return (mCurrentQuestionIndex + 1) == this.mQuestions.size();
    }

    public void proceedToNextQuestion()
    {
        this.mCurrentQuestionIndex++;
    }
}
