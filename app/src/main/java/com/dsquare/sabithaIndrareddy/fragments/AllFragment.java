package com.dsquare.sabithaIndrareddy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsquare.sabithaIndrareddy.adapter.HomeAdapter;
import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.feedslist.FeedData;
import com.dsquare.sabithaIndrareddy.pojos.feedslist.Result;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 11-04-2018.
 */

public class AllFragment extends Fragment
{
    private RecyclerView recycler;
    private LinearLayoutManager linearLayoutManager;
    private HomeAdapter homeAdapter;
    private String baseUrl;
    private SwipeRefreshLayout refreshLayout;
    private String s;
    private List<Result> feedDataList = new ArrayList<>();
    private int maxId=1;
    private CommonFunction com;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_all,container,false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view)
    {
        com = new CommonFunction(getContext());

        recycler=(RecyclerView)view.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
        homeAdapter = new HomeAdapter(getActivity(), feedDataList);
        recycler.setAdapter(homeAdapter);



        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {

                refreshLayout.setRefreshing(false);
            }
        });

        refreshLayout.setRefreshing(true);

        getFeeds();

        recycler.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Log.d("Recycler Bottom", "Reached Page :" + page + ", Total Count " + totalItemsCount);
                try {
                    maxId++;
                    getFeeds();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getFeeds()
    {
        ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<FeedData> call = apiservice.feedDataResponse(maxId+"");
        call.enqueue(new Callback<FeedData>() {
            @Override
            public void onResponse(retrofit2.Call<FeedData> call, Response<FeedData> response)
            {
                refreshLayout.setRefreshing(false);
                if(response.code()==200)
                {
                    parseFeedResponse(response);
                }


            }

            @Override
            public void onFailure(retrofit2.Call<FeedData> call, Throwable t)
            {
                refreshLayout.setRefreshing(false);
            }
        });


    }

    private void parseFeedResponse(Response<FeedData> response)
    {

      //  com.basePath = response.body().getBase_url();
        if(response.body().getStatus()==1)
        {
            feedDataList.addAll(response.body().getResult());
            homeAdapter.notifyDataSetChanged();
        }
    }

}


