package com.example.thousandaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {

    private Button mGameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        mGameOver = (Button) this.findViewById(R.id.gameover);
        mGameOver.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                setResult(RESULT_OK,null);
                finish();
            }
        });
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        setResult(RESULT_CANCELED,null);
    }
}
