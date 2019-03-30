package com.dsquare.sabithaIndrareddy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.adapter.EventAdapter;
import com.dsquare.sabithaIndrareddy.adapter.GovernorsAdapter;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.EventsListResponse.EventResponse;
import com.dsquare.sabithaIndrareddy.pojos.EventsListResponse.Result;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;
import com.dsquare.sabithaIndrareddy.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 14-06-2018.
 */

public class PastEventsFragment extends Fragment
{
    private RecyclerView pastrecycler;
    private LinearLayoutManager linearLayoutManager;
    private EventAdapter eventAdapter;
    private List<Result> eventlist = new ArrayList<>();
    private SwipeRefreshLayout refreshLayout;
    private int maxId=1;
    private String event_type="2";
    private CommonFunction com;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_event, container, false);
        intializeViews(view);
        return view;
    }

    private void intializeViews(View view)
    {
        refreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe);
        com = new CommonFunction(getContext());
        pastrecycler = (RecyclerView)view.findViewById(R.id.recycler);
        linearLayoutManager= new LinearLayoutManager(getContext());
        pastrecycler.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventAdapter = new EventAdapter(getContext(),eventlist);
        pastrecycler.setAdapter(eventAdapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
            }
        });

        refreshLayout.setRefreshing(true);
        getEvents();
        pastrecycler.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Log.d("Recycler Bottom", "Reached Page :" + page + ", Total Count " + totalItemsCount);
                try {
                    maxId++;
                    getEvents();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void  getEvents()
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<EventResponse> call = apiInterface.eventResponse(maxId+"",event_type);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response)
            {
                refreshLayout.setRefreshing(false);
                if(response.code()==200) {
                    parseEvents(response.body());
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                refreshLayout.setRefreshing(false);
            }
        });

    }

    private void parseEvents(EventResponse body)
    {
        if(body.getStatus()==1) {
            eventlist.addAll(body.getResult());
            eventAdapter.notifyDataSetChanged();
        }
    }

}
