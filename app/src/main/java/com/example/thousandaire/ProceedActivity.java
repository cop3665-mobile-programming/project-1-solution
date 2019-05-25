package com.example.thousandaire;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProceedActivity extends AppCompatActivity {

    private static final String  EXTRA_CURRENT_AMOUNT = "current";
    private static final String EXTRA_NEXT_AMOUNT = "next";
    private static final String EXTRA_CONTINUE_SELECTED = "cont";


    private TextView mProceed;
    private Button mContinue;
    private Button mQuit;

    private int currentAmount;
    private int nextAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed);

        currentAmount = getIntent().getIntExtra(EXTRA_CURRENT_AMOUNT, 0);
        nextAmount = getIntent().getIntExtra(EXTRA_NEXT_AMOUNT, 0);

        String proceedText = "Correct! You have earned $" + currentAmount
                + ". Would you care to try for $" + nextAmount + "?";
        mProceed = this.findViewById(R.id.proceed_text);
        mProceed.setText(proceedText);

        mContinue = (Button) this.findViewById(R.id.cont);
        mContinue.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                setContinueOn(true);
            }
        });
        mQuit = (Button) this.findViewById(R.id.quit);
        mQuit.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                setContinueOn(false);
            }
        });
    }

    private void setContinueOn(boolean continueClicked)
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_CONTINUE_SELECTED, continueClicked);
        setResult(RESULT_OK,data);
        finish();
    }

    public static boolean wasContinueSelected(Intent result)
    {
        return result.getBooleanExtra(EXTRA_CONTINUE_SELECTED, false);
    }

    public static Intent newIntent(Context packageContext, int currentAmount, int nextAmount)
    {
        Intent intent = new Intent(packageContext, ProceedActivity.class);
        intent.putExtra(EXTRA_CURRENT_AMOUNT, currentAmount);
        intent.putExtra(EXTRA_NEXT_AMOUNT, nextAmount);
        return intent;
    }
}
