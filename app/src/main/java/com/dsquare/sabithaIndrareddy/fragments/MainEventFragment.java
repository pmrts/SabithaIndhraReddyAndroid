package com.dsquare.sabithaIndrareddy.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dsquare.sabithaIndrareddy.R;

/**
 * Created by D square on 14-06-2018.
 */

public class MainEventFragment extends Fragment implements View.OnClickListener {
    private Button upcomming,past,feature;
    private FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_event_tabs, container, false);
        intializeViews(view);
        return view;
        }

    private void intializeViews(View view)
    {
         past = (Button) view.findViewById(R.id.ticket_button);
        upcomming  = (Button) view.findViewById(R.id.createticket_button);
        feature = (Button)view.findViewById(R.id.ticket_two_button);
        upcomming.setOnClickListener(this);
        past.setOnClickListener(this);
        feature.setOnClickListener(this);

        fragmentManager = getFragmentManager();
        upcomming.setBackgroundResource(R.drawable.login_radius_fill);
        past.setBackgroundColor(Color.TRANSPARENT);
        past.setTextColor(Color.parseColor("#00aeef"));
        feature.setTextColor(Color.parseColor("#00aeef"));
        upcomming.setTextColor(Color.parseColor("#ffffff"));

        Fragment fragment = new EventFragment();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout,fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        }

    @Override
    public void onClick(View v)
    {
      switch (v.getId()) {
          case R.id.createticket_button:

              upcomming.setBackgroundResource(R.drawable.login_radius_fill);
              past.setBackgroundColor(Color.parseColor("#FFFFFF"));
              feature.setBackgroundColor(Color.parseColor("#ffffff"));
              past.setBackgroundColor(Color.TRANSPARENT);
              feature.setBackgroundColor(Color.TRANSPARENT);
              past.setTextColor(Color.parseColor("#00aeef"));
              feature.setTextColor(Color.parseColor("#00aeef"));
              upcomming.setTextColor(Color.parseColor("#ffffff"));

              Fragment fragment = new EventFragment();
              FragmentTransaction ft = fragmentManager.beginTransaction();
              ft.replace(R.id.frame_layout, fragment);
              ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
              ft.commit();
               break;

          case R.id.ticket_button:
              past.setBackgroundResource(R.drawable.login_radius_fill);
              upcomming.setBackgroundColor(Color.parseColor("#FFFFFF"));
              feature.setBackgroundColor(Color.parseColor("#ffffff"));
              upcomming.setBackgroundColor(Color.TRANSPARENT);
              feature.setBackgroundColor(Color.TRANSPARENT);
              upcomming.setTextColor(Color.parseColor("#00aeef"));
              feature.setTextColor(Color.parseColor("#00aeef"));
              past.setTextColor(Color.parseColor("#ffffff"));

              Fragment fragment1 = new PastEventsFragment();
              FragmentTransaction ft1 = fragmentManager.beginTransaction();
              ft1.replace(R.id.frame_layout,fragment1);
              ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
              ft1.commit();
              break;

          case R.id.ticket_two_button:

              feature.setBackgroundResource(R.drawable.login_radius_fill);
              past.setBackgroundColor(Color.TRANSPARENT);
              upcomming.setBackgroundColor(Color.TRANSPARENT);
              upcomming.setTextColor(Color.parseColor("#00aeef"));
              past.setTextColor(Color.parseColor("#00aeef"));
              feature.setTextColor(Color.parseColor("#ffffff"));

              Fragment fragment2 = new FeatureEvents();
              FragmentTransaction ft2 = fragmentManager.beginTransaction();
              ft2.replace(R.id.frame_layout,fragment2);
              ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
              ft2.commit();
              break;
      }
    }
}
