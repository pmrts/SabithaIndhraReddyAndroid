package com.dsquare.sabithaIndrareddy.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.dsquare.sabithaIndrareddy.R;

/**
 * Created by D square on 16-04-2018.
 */

public class PollsDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls_detail);
        intializeViews();
        }

    private void intializeViews()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(this);
        }

    @Override
    public void onClick(View v)
    {
        onBackPressed();

    }
}
