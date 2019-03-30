package com.dsquare.sabithaIndrareddy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.networks.ApiClient1;
import com.dsquare.sabithaIndrareddy.networks.ApiClient2;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.congress.CongressPojo;
import com.dsquare.sabithaIndrareddy.pojos.congress.CongressResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncFragment extends Fragment
{

    private SampleViewPagerAdapter mViewPagerAdapter;
    private CommonFunction com;
    List<CongressResponse> responseList = new ArrayList<CongressResponse>();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeFragment.SampleViewPagerAdapter sampleViewPagerAdapter;

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
        com = new CommonFunction(getContext());
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);

        getInc();

    }

    private void getInc()
    {
        com.showProgressDialogue();
        ApiInterface apiService =
                ApiClient2.getClient().create(ApiInterface.class);

        Call<CongressPojo> call = apiService.getCongressResponse();
        call.enqueue(new Callback<CongressPojo>() {
            @Override
            public void onResponse(Call<CongressPojo> call, Response<CongressPojo> response)
            {
                com.dismissProgressDialogue();
                parsegetInc(response.body());

            }

            @Override
            public void onFailure(Call<CongressPojo> call, Throwable t)
            {
                com.dismissProgressDialogue();
            }
        });

    }

    private void parsegetInc(CongressPojo body)
    {
        responseList.addAll(body.getList());

        for(CongressResponse response : responseList)
        {
            com.listHashMap.put(response.getMainYear() , response.getList());
        }


        mViewPagerAdapter = new SampleViewPagerAdapter(getFragmentManager(),responseList);
        viewPager.setAdapter(mViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


    public class SampleViewPagerAdapter extends FragmentPagerAdapter
    {

        List<CongressResponse> yearsList = new ArrayList<>();

        public SampleViewPagerAdapter(FragmentManager fm, List<CongressResponse> yearsList) {
            super(fm);
            this.yearsList = yearsList;
        }

        @Override
        public Fragment getItem(int position)
        {
            Bundle args = new Bundle();
            Fragment fg;
            fg = new HistoryofcongressFragment();
            args.putString("type", yearsList.get(position).getMainYear());
            fg.setArguments(args);
            return fg;

        }

        @Override
        public int getCount()
        {
            return yearsList.size();
        }
        @Override
        public CharSequence getPageTitle(int position)
        {
            return yearsList.get(position).getMainYear();
        }

        public Fragment getFragment(int selectedTabPosition)
        {
            return null;
        }
    }

}
