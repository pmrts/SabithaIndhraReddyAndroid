package com.dsquare.sabithaIndrareddy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dsquare.sabithaIndrareddy.adapter.PollsAdapter;
import com.dsquare.sabithaIndrareddy.R;

/**
 * Created by D square on 12-04-2018.
 */

public class PollsActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PollsAdapter pollsAdapter;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState)
   {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_polls);
       initializeViews();
   }

    private void initializeViews()
    {
        recyclerView = (RecyclerView)findViewById(R.id.recyler);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(pollsAdapter);

    }

}
