package com.example.thousandaire;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private static final String EXTRA_FINAL_AMOUNT = "final";
    private static final String EXTRA_PLAYOVER_SELECTED = "play";

    private TextView mScore;
    private Button mPlay;
    private Button mQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        int finalAmount = getIntent().getIntExtra(EXTRA_FINAL_AMOUNT, 0);

        String scoreText = "Congratulations!\nYou earned $" + finalAmount +".";
        mScore = this.findViewById(R.id.score);
        mScore.setText(scoreText);

        mPlay = (Button) this.findViewById(R.id.playagain);
        mPlay.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                setPlayAgain(true);
            }
        });
        mQuit = (Button) this.findViewById(R.id.quitgame);
        mQuit.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                setPlayAgain(false);
            }
        });
    }

    private void setPlayAgain(boolean playAgainClicked)
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_PLAYOVER_SELECTED, playAgainClicked);
        setResult(RESULT_OK,data);
        finish();
    }

    public static boolean wasPlayAgainSelected(Intent result)
    {
        return result.getBooleanExtra(EXTRA_PLAYOVER_SELECTED, false);
    }

    public static Intent newIntent(Context packageContext, int finalAmount)
    {
        Intent intent = new Intent(packageContext, ScoreActivity.class);
        intent.putExtra(EXTRA_FINAL_AMOUNT, finalAmount);
        return intent;
    }
}
