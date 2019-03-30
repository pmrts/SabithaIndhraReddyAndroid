package com.dsquare.sabithaIndrareddy.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.LoginMobileActivity;
import com.dsquare.sabithaIndrareddy.adapter.EventAdapter;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.EventsListResponse.EventResponse;
import com.dsquare.sabithaIndrareddy.pojos.EventsListResponse.Result;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeatureEvents extends Fragment
{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout refreshLayout;
    private EventAdapter eventAdapter;
    private List<Result> eventlist = new ArrayList<>();
    private int maxId=1;
    private String event_type="3";
    private CommonFunction com;
    private TextView nodata;

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
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        eventAdapter = new EventAdapter(getActivity(),eventlist);
        recyclerView.setAdapter(eventAdapter);
        com = new CommonFunction(getContext());

        nodata = (TextView) view.findViewById(R.id.no_data);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {

                refreshLayout.setRefreshing(false);
            }
        });

        refreshLayout.setRefreshing(true);
        getEvents();

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
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


    private void getEvents() {


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<EventResponse> call = apiService.eventResponse(maxId + "", event_type);

        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                refreshLayout.setRefreshing(false);
                if (response.code() == 200) {
                    parseEventResponse(response);
                }

            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                refreshLayout.setRefreshing(false);
            }
        });

    }



    private void parseEventResponse(Response<EventResponse> response) {

        if (response.body().getStatus() == 1) {
            eventlist.addAll(response.body().getResult());
            eventAdapter.notifyDataSetChanged();
        }

        if(eventlist.size()==0)
        {
            nodata.setText(response.body().getMessage());
            nodata.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else
        {
            recyclerView.setVisibility(View.VISIBLE);
            nodata.setVisibility(View.GONE);
        }

    }



    public void isContinueasGuestAlert() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Continue as Guest");
        builder.setMessage("Please Login");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                Intent intent = new Intent(getContext(), LoginMobileActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }

}
