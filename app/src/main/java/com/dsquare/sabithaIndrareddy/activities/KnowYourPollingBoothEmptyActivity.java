package com.dsquare.sabithaIndrareddy.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.dsquare.sabithaIndrareddy.R;

/**
 * Created by D square on 05-11-2018.
 */

public class KnowYourPollingBoothEmptyActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backbutton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_your_booth_empty);
        intilizeViews();

    }

    private void intilizeViews()
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://rajnivas.py.gov.in/"));
        startActivity(browserIntent);
        backbutton = (ImageView) findViewById(R.id.back);
        backbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        onBackPressed();

    }
}
