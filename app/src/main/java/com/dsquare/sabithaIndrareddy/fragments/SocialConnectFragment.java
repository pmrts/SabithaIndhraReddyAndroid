package com.dsquare.sabithaIndrareddy.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dsquare.sabithaIndrareddy.R;

/**
 * Created by D square on 13-04-2018.
 */

public class SocialConnectFragment extends Fragment {
    private Button facebook, twitter;
    public FragmentManager fragmentManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SampleViewPagerAdapter sampleViewPagerAdapter;
    private int[] tabIcons = {
            R.drawable.facebook_img,
            R.drawable.twitter_img,
            R.drawable.tumblr_img,
            R.drawable.youtube_img,
            R.drawable.instagram_img
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.social_connect_viewpager, container, false);
        intializeViews(view);
        return view;
    }

    private void intializeViews(View view) {

        fragmentManager = getFragmentManager();
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        sampleViewPagerAdapter = new SampleViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(sampleViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    public class SampleViewPagerAdapter extends FragmentPagerAdapter {

        public SampleViewPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Bundle args = new Bundle();
            if (position == 0) {
                Fragment fg;
                fg = new SocialFacebookFragment();
                args.putString("name","FaceBook");
                args.putString("url","https://www.facebook.com/SabithaIndraReddyOfficial/");
                fg.setArguments(args);
                return fg;
            } else if (position == 1) {
                Fragment fg;
                fg = new SocialFacebookFragment();
                args.putString("name","twitter");
                args.putString("url","https://twitter.com/sabithaindrare1");
                fg.setArguments(args);
                return fg;
            } else if(position == 2) {
                Fragment fg;
                args.putString("name","youtube");
                fg = new SocialFacebookFragment();
                args.putString("url", "https://www.youtube.com/channel/UCrlli0WqTtiaE5v7LIEKbNA");
                fg.setArguments(args);
                return fg;
            } else {
                Fragment fg;
                fg = new SocialFacebookFragment();
                args.putString("name","Instagram");
                args.putString("url", "https://www.instagram.com/sabithaindrareddypatlolla/");
                fg.setArguments(args);
                return fg;
            }

        }
        @Override
        public int getCount()
        {

            return 4;
        }
        @Override
        public CharSequence getPageTitle(int position)
        {
            String[] tabNames = {"Facebook","Twitter","Youtube","Instagram"};
            return tabNames[position];
        }

        public Fragment getFragment(int selectedTabPosition)
        {
            return null;
        }
    }
    }

