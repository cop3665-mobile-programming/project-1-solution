package com.example.thousandaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thousandaire.models.Question;

public class MainActivity extends AppCompatActivity {

    private TextView mQuestion;
    private Button mAnswerA;
    private Button mAnswerB;
    private Button mAnswerC;
    private Button mAnswerD;
    private Question[] mQuestionBank = new Question[] {
      new Question(R.string.hundred_question, R.string.hundred_question_a, new int[] {R.string.hundred_question_a, R.string.hundred_question_b, R.string.hundred_question_c, R.string.hundred_question_d})
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestion = (TextView) this.findViewById(R.id.question_text);

        mAnswerA = (Button) this.findViewById(R.id.answer_a);
        mAnswerA.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                checkAnswer(mQuestionBank[0].getChoiceIds()[0]);
            }
        });
        mAnswerB = (Button) this.findViewById(R.id.answer_b);
        mAnswerB.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                checkAnswer(mQuestionBank[0].getChoiceIds()[1]);
            }
        });
        mAnswerC = (Button) this.findViewById(R.id.answer_c);
        mAnswerC.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                checkAnswer(mQuestionBank[0].getChoiceIds()[2]);
            }
        });
        mAnswerD = (Button) this.findViewById(R.id.answer_d);
        mAnswerD.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                checkAnswer(mQuestionBank[0].getChoiceIds()[3]);
            }
        });

        updateQuestion();
    }

    private void checkAnswer(int choiceId)
    {
        int answerId = mQuestionBank[0].getAnswerId();
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
        int question = mQuestionBank[0].getQuestionTextId();
        mQuestion.setText(question);
        int[] choices = mQuestionBank[0].getChoiceIds();

        mAnswerA.setText(choices[0]);
        mAnswerB.setText(choices[1]);
        mAnswerC.setText(choices[2]);
        mAnswerD.setText(choices[3]);
    }
}
