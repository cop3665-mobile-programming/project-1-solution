package com.example.thousandaire.models;

public class Question {
    private int mTextResId;
    private int mAnswer;
    private int[] mChoiceIds;

    public Question(int questionTextId, int answerId, int[] choiceIds)
    {
        this.mTextResId = questionTextId;
        this.mAnswer = answerId;
        this.mChoiceIds = choiceIds;
    }

    public int getQuestionTextId()
    {
        return this.mTextResId;
    }

    public int getAnswerId()
    {
        return this.mAnswer;
    }

    public int[] getChoiceIds()
    {
        return this.mChoiceIds;
    }
}
