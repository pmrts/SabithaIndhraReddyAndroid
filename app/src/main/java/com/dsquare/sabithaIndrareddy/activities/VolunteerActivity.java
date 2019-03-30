package com.dsquare.sabithaIndrareddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.adapter.VolunteerAdapter;
import com.dsquare.sabithaIndrareddy.R;

/**
 * Created by D square on 12-04-2018.
 */

public class VolunteerActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private VolunteerAdapter volunteerAdapter;
    private Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        intializeViews();

    }

    private void intializeViews()
    {
        submit = (Button)findViewById(R.id.volunteer_submit);
        recyclerView = (RecyclerView)findViewById(R.id.volunteer_recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        //volunteerAdapter = new VolunteerAdapter();
        recyclerView.setAdapter(volunteerAdapter);


        submit.setOnClickListener(this);
    }

    private int dpToPx(int dp)


    {
        return Math.round(TypedValue.applyDimension(1, (float) dp, getResources().getDisplayMetrics()));
    }

    @Override
    public void onClick(View v)
    {
       // Toast.makeText(this,"Your sucessfully aplied",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);

    }

    private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        public GridSpacingItemDecoration(int i, int i1, boolean b)
        {

        }
    }

}
