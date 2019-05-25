package com.example.thousandaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thousandaire.models.Game;
import com.example.thousandaire.models.Question;

public class MainActivity extends AppCompatActivity {

    private TextView mQuestion;
    private Button mAnswerA;
    private Button mAnswerB;
    private Button mAnswerC;
    private Button mAnswerD;

    private Game mGame;
    private Question mCurrentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGame = new Game();
        mGame.addQuestion(new Question(R.string.hundred_question,
                R.string.hundred_question_a,
                new int[] {R.string.hundred_question_a,
                        R.string.hundred_question_b,
                        R.string.hundred_question_c,
                        R.string.hundred_question_d}));
        mGame.addQuestion(new Question(R.string.two_hundred_question,
                R.string.two_hundred_question_c,
                new int[] {R.string.two_hundred_question_a,
                        R.string.two_hundred_question_b,
                        R.string.two_hundred_question_c,
                        R.string.two_hundred_question_d}));
        mGame.addQuestion(new Question(R.string.three_hundred_question,
                R.string.three_hundred_question_c,
                new int[] {R.string.three_hundred_question_a,
                        R.string.three_hundred_question_b,
                        R.string.three_hundred_question_c,
                        R.string.three_hundred_question_d}));
        mGame.addQuestion(new Question(R.string.four_hundred_question,
                R.string.four_hundred_question_d,
                new int[] {R.string.four_hundred_question_a,
                        R.string.four_hundred_question_b,
                        R.string.four_hundred_question_c,
                        R.string.four_hundred_question_d}));
        mGame.addQuestion(new Question(R.string.five_hundred_question,
                R.string.five_hundred_question_d,
                new int[] {R.string.five_hundred_question_a,
                        R.string.five_hundred_question_b,
                        R.string.five_hundred_question_c,
                        R.string.five_hundred_question_d}));
        mGame.addQuestion(new Question(R.string.thousand_question,
                R.string.thousand_question_c,
                new int[] {R.string.thousand_question_a,
                        R.string.thousand_question_b,
                        R.string.thousand_question_c,
                        R.string.thousand_question_d}));

        mCurrentQuestion = mGame.getNextQuestion();

        mQuestion = (TextView) this.findViewById(R.id.question_text);

        mAnswerA = (Button) this.findViewById(R.id.answer_a);
        mAnswerA.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                checkAnswer(mCurrentQuestion.getChoiceIds()[0]);
                mCurrentQuestion = mGame.getNextQuestion();
                updateQuestion();
            }
        });
        mAnswerB = (Button) this.findViewById(R.id.answer_b);
        mAnswerB.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                checkAnswer(mCurrentQuestion.getChoiceIds()[1]);
                mCurrentQuestion = mGame.getNextQuestion();
                updateQuestion();
            }
        });
        mAnswerC = (Button) this.findViewById(R.id.answer_c);
        mAnswerC.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                checkAnswer(mCurrentQuestion.getChoiceIds()[2]);
                mCurrentQuestion = mGame.getNextQuestion();
                updateQuestion();
            }
        });
        mAnswerD = (Button) this.findViewById(R.id.answer_d);
        mAnswerD.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                checkAnswer(mCurrentQuestion.getChoiceIds()[3]);
                mCurrentQuestion = mGame.getNextQuestion();
                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void checkAnswer(int choiceId)
    {
        int answerId = mCurrentQuestion.getAnswerId();
        if(choiceId == answerId)
        {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateQuestion()
    {
        if(mCurrentQuestion == null)
            return;
        int question = mCurrentQuestion.getQuestionTextId();
        mQuestion.setText(question);
        int[] choices = mCurrentQuestion.getChoiceIds();

        mAnswerA.setText(choices[0]);
        mAnswerB.setText(choices[1]);
        mAnswerC.setText(choices[2]);
        mAnswerD.setText(choices[3]);
    }
}
