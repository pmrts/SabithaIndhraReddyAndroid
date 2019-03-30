package com.dsquare.sabithaIndrareddy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.dsquare.sabithaIndrareddy.R;

import java.util.zip.Inflater;

/**
 * Created by D square on 11-04-2018.
 */

public class HomeFragment extends Fragment
{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SampleViewPagerAdapter sampleViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        intializeViews(view);

        return view;

    }

    private void intializeViews(View view)
    {
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        sampleViewPagerAdapter = new SampleViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(sampleViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    public class SampleViewPagerAdapter extends FragmentPagerAdapter
    {


        public SampleViewPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            Bundle args = new Bundle();
           if(position==0)
           {
               Fragment fg;
               fg = new AllFragment();
               args.putString("name", "ALL");
               fg.setArguments(args);
               return fg;
           }
           else if(position==1)
           {
               Fragment fg;
               fg = new AllFragment();
               args.putString("name", "ALL");
               fg.setArguments(args);
               return fg;
           }
           else if(position==2)
           {
               Fragment fg;
               fg = new VideoFragment();
               args.putString("name", "Video");
               fg.setArguments(args);
               return fg;
           }
           else if(position==3)
           {
               Fragment fg;
               fg = new LiveFragment();
               args.putString("name", "Live");
               fg.setArguments(args);
               return fg;

           }

              return null;
        }

        @Override
        public int getCount()
        {
            return 4;
        }
        @Override
        public CharSequence getPageTitle(int position)
        {
            String[] tabNames = {getResources().getString(R.string.all_tab), getResources().getString(R.string.news_tab),getResources().getString(R.string.videos_tab),getResources().getString(R.string.live_tab)};
            return tabNames[position];
        }

        public Fragment getFragment(int selectedTabPosition)
        {
            return null;
        }
    }
    }

