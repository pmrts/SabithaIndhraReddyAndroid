package com.dsquare.sabithaIndrareddy.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.fragments.CreateTicketFragment;
import com.dsquare.sabithaIndrareddy.fragments.LoginFragment;
import com.dsquare.sabithaIndrareddy.fragments.TicketDetailsFragment;

/**
 * Created by D square on 12-04-2018.
 */

public class TicketActivity extends AppCompatActivity implements View.OnClickListener {
    private Button ticket,ticketcreate;
    public FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);


        ticket = (Button)findViewById(R.id.ticket_button);
        ticketcreate = (Button)findViewById(R.id.createticket_button);
        ticket.setTextColor(Color.parseColor("#ffffff"));
        ticketcreate.setTextColor(Color.parseColor("#162A47"));
        ticket.setOnClickListener(this);
        ticketcreate.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = new TicketDetailsFragment();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();


    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.ticket_button:
                ticket.setBackgroundResource(R.drawable.login_radius_fill);
                ticketcreate.setBackgroundColor(Color.TRANSPARENT);
                ticket.setTextColor(Color.parseColor("#ffffff"));
                ticketcreate.setTextColor(Color.parseColor("#162A47"));

                Fragment fragment = new TicketDetailsFragment();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
            case R.id.createticket_button:
                ticketcreate.setBackgroundResource(R.drawable.login_radius_fill);
                ticket.setBackgroundColor(Color.TRANSPARENT);
                ticket.setTextColor(Color.parseColor("#ffffff"));
                ticketcreate.setTextColor(Color.parseColor("#162A47"));

                Fragment fragment1 = new CreateTicketFragment();
                FragmentTransaction ft1 = fragmentManager.beginTransaction();
                ft1.replace(R.id.frame_layout, fragment1);
                ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft1.commit();
                break;
        }



    }
}
