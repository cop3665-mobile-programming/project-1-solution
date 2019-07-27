package com.example.thousandaire;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thousandaire.models.Game;
import com.example.thousandaire.models.Question;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CONTINUE = 0;
    private static final int REQUEST_CODE_GAMEOVER = 1;
    private static final int REQUEST_CODE_PLAYOVER = 2;

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

        startNewGame();

        mQuestion = (TextView) this.findViewById(R.id.question_text);

        mAnswerA = (Button) this.findViewById(R.id.answer_a);
        mAnswerA.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                handleResponse(0);
            }
        });
        mAnswerB = (Button) this.findViewById(R.id.answer_b);
        mAnswerB.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                handleResponse(1);
            }
        });
        mAnswerC = (Button) this.findViewById(R.id.answer_c);
        mAnswerC.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                handleResponse(2);
            }
        });
        mAnswerD = (Button) this.findViewById(R.id.answer_d);
        mAnswerD.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                handleResponse(3);
            }
        });

        updateQuestion();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == Activity.RESULT_CANCELED)
        {
            finish();
        }
        if(resultCode != Activity.RESULT_OK)
        {
            return;
        }

        if(data!= null) {
            if (requestCode == REQUEST_CODE_CONTINUE) {
                if (ProceedActivity.wasContinueSelected(data)) {
                    mGame.proceedToNextQuestion();
                    updateQuestion();
                }
                else
                {
                    Intent intent = ScoreActivity.newIntent(MainActivity.this, mGame.getCurrentQuestion().getAmount());
                    startActivityForResult(intent, REQUEST_CODE_PLAYOVER);
                }
            }
            if (requestCode == REQUEST_CODE_PLAYOVER) {
                if (ScoreActivity.wasPlayAgainSelected(data)) {
                    startNewGame();
                    updateQuestion();
                }
                else
                {
                    finish();
                }
            }
        }


        if(requestCode == REQUEST_CODE_GAMEOVER)
        {
            finish();
        }
    }

    public void handleResponse(int selection)
    {
        boolean isCorrect = isCorrectAnswer(mCurrentQuestion.getChoiceIds()[selection]);
        showResults(isCorrect);
    }

    private boolean isCorrectAnswer(int choiceId)
    {
        int answerId = mCurrentQuestion.getAnswerId();
        if(choiceId == answerId)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void showResults(boolean isCorrect)
    {
        if(isCorrect)
        {
            if(!mGame.isFinalQuestion())
            {
                int currentAmount = mGame.getCurrentQuestion().getAmount();
                int nextAmount = mGame.getNextQuestion().getAmount();
                if ((currentAmount >= 0) && (nextAmount >= 0)) {
                    Intent intent = ProceedActivity.newIntent(this, currentAmount, nextAmount);
                    startActivityForResult(intent, REQUEST_CODE_CONTINUE);
                }
            }
            else
            {
                Intent intent = ScoreActivity.newIntent(MainActivity.this, mGame.getCurrentQuestion().getAmount());
                startActivityForResult(intent, REQUEST_CODE_PLAYOVER);
            }
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, GameOverActivity.class);
            startActivityForResult(intent, REQUEST_CODE_GAMEOVER);
        }
    }

    private void startNewGame()
    {
        mGame = new Game();
        mGame.addQuestion(new Question(R.string.hundred_question,
                R.string.hundred_question_a,
                new int[] {R.string.hundred_question_a,
                        R.string.hundred_question_b,
                        R.string.hundred_question_c,
                        R.string.hundred_question_d}, 100));
        mGame.addQuestion(new Question(R.string.two_hundred_question,
                R.string.two_hundred_question_c,
                new int[] {R.string.two_hundred_question_a,
                        R.string.two_hundred_question_b,
                        R.string.two_hundred_question_c,
                        R.string.two_hundred_question_d},200));
        mGame.addQuestion(new Question(R.string.three_hundred_question,
                R.string.three_hundred_question_c,
                new int[] {R.string.three_hundred_question_a,
                        R.string.three_hundred_question_b,
                        R.string.three_hundred_question_c,
                        R.string.three_hundred_question_d}, 300));
        mGame.addQuestion(new Question(R.string.four_hundred_question,
                R.string.four_hundred_question_d,
                new int[] {R.string.four_hundred_question_a,
                        R.string.four_hundred_question_b,
                        R.string.four_hundred_question_c,
                        R.string.four_hundred_question_d}, 400));
        mGame.addQuestion(new Question(R.string.five_hundred_question,
                R.string.five_hundred_question_d,
                new int[] {R.string.five_hundred_question_a,
                        R.string.five_hundred_question_b,
                        R.string.five_hundred_question_c,
                        R.string.five_hundred_question_d}, 500));
        mGame.addQuestion(new Question(R.string.thousand_question,
                R.string.thousand_question_c,
                new int[] {R.string.thousand_question_a,
                        R.string.thousand_question_b,
                        R.string.thousand_question_c,
                        R.string.thousand_question_d},1000));

    }

    private void updateQuestion()
    {
        mCurrentQuestion = mGame.getCurrentQuestion();
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
