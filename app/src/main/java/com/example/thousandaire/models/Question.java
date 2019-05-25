package com.example.thousandaire.models;

public class Question {
    private int mTextResId;
    private int mAnswer;
    private int[] mChoiceIds;
    private int mAmount;

    public Question(int questionTextId, int answerId, int[] choiceIds, int amount)
    {
        this.mTextResId = questionTextId;
        this.mAnswer = answerId;
        this.mChoiceIds = choiceIds;
        this.mAmount = amount;
    }

    public int getAmount() { return this.mAmount; }

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
