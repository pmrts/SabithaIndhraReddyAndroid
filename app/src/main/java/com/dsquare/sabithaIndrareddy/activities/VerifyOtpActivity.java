package com.dsquare.sabithaIndrareddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dsquare.sabithaIndrareddy.R;



public class VerifyOtpActivity extends AppCompatActivity implements View.OnClickListener {
   private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varification);
        initializeViews();
    }

    private void initializeViews()
    {
        btn = (Button)findViewById(R.id.continue_button);
        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);

    }


}
