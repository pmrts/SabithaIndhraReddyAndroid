package com.dsquare.sabithaIndrareddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

/**
 * Created by D square on 07-05-2018.
 */

public class SplashSecondActivity extends AppCompatActivity
{
    private CommonSharePrefrences comshare;
    Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_two);


        handler = new Handler();
        comshare = CommonSharePrefrences.getInstance(this);

        handler.postDelayed(new Runnable()
        {

            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {
                if(comshare.getToken().equals(""))
                {
                    Intent i = new Intent(SplashSecondActivity.this, LoginMobileActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
                else
                {
                    Intent intent = new Intent(SplashSecondActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, 3*1000);

        Handler handler = new Handler();



    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
        handler=null;
        SplashSecondActivity.this.finishAffinity();
    }

}
