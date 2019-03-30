package com.dsquare.sabithaIndrareddy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.dsquare.sabithaIndrareddy.R;

/**
 * Created by D square on 11-09-2018.
 */

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backbutton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        intilizeViews();
    }

    private void intilizeViews()
    {
        backbutton = (ImageView) findViewById(R.id.back);
        backbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        onBackPressed();

    }
}
