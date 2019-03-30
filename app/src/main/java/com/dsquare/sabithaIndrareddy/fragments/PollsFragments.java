package com.dsquare.sabithaIndrareddy.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.LoginMobileActivity;
import com.dsquare.sabithaIndrareddy.activities.LoginRegisterActivity;
import com.dsquare.sabithaIndrareddy.adapter.PollsAdapter;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.suveryResponse.SurveyListResponse;
import com.dsquare.sabithaIndrareddy.pojos.suveryResponse.SurveyResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;
import com.dsquare.sabithaIndrareddy.utils.EndlessRecyclerViewScrollListener;
import com.google.gson.Gson;

import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PollsFragments extends Fragment
{

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PollsAdapter pollsAdapter;
    private Toolbar toolbar;
    private Gson gson;
    private SwipeRefreshLayout refreshLayout;
    public List<SurveyListResponse> surveyList = new ArrayList<>();
    private int maxId=1;
    NavigationView navigationView;
    private CommonFunction com;
    private CommonSharePrefrences comShare;
    private TextView nodata;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_polls, container, false);
        intializeViews(view);
        initializeOnclickListener();
        return view;
    }


    private void intializeViews(View view)
    {
        refreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        com = new CommonFunction(getContext());
        comShare = CommonSharePrefrences.getInstance(getContext());
        pollsAdapter = new PollsAdapter(getContext(),surveyList);
        recyclerView.setAdapter(pollsAdapter);
        nodata = (TextView) view.findViewById(R.id.no_data);
        //navigationView = (NavigationView)view.findViewById(R.id.nav_view);
//        navigationView.setItemIconTintList(null);
      //  getSurveyList();

    //    gson = new Gson();
//        SurveyResponse pojo = (gson.fromJson(readDummyResponse(), SurveyResponse.class));
//
//        surveyList.addAll(pojo.getResult());
//
//        pollsAdapter.notifyDataSetChanged();

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
//        {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem)
//            {
//              //  displaySelectedScreen(menuItem.getItemId());
//                return true;
//            }
//        });
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh()
//            {
//
//                refreshLayout.setRefreshing(false);
//            }
//        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {

                refreshLayout.setRefreshing(false);
            }
        });

        refreshLayout.setRefreshing(true);
       // getSurveyList();


        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Log.d("Recycler Bottom", "Reached Page :" + page + ", Total Count " + totalItemsCount);
                try {
                   maxId++;
                  getSurveyList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    private void initializeOnclickListener()
    {

    }
    public void isContinueasGuestAlert()
    {

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

    public String readDummyResponse() {
        InputStream inputStream = null;
        try {
            inputStream = getActivity().getAssets().open("RaviKumarSurvey.json");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        JSONObject jsonObject = null;
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inputStream));
            StringBuffer buffer = new StringBuffer();
            String statement;
            try {
                while ((statement = reader.readLine()) != null) {
                    if (statement.trim().length() > 0) {
                        buffer.append(statement);
                    }
                }
            } catch (IOException e1) { // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return buffer.toString();
        }
        return null;
    }

    private void getSurveyList()
    {
        ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);

        Call<SurveyResponse> call = apiservice.getSurveys(maxId+"",comShare.getUserId());

        call.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call, Response<SurveyResponse> response)
            {
                refreshLayout.setRefreshing(false);
                if(response.code()==200)
                {
                    parseSurveyList(response);
                }

            }

            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t)
            {
                refreshLayout.setRefreshing(false);

            }
        });

    }

    private void parseSurveyList(Response<SurveyResponse> response)
    {
      //  surveyList.clear();

        if(response.body().getStatus()==1)
        {
            surveyList.addAll(response.body().getResult());
            pollsAdapter.notifyDataSetChanged();
        }
        if(surveyList.size()==0)
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



    @Override
    public void onResume() {
        super.onResume();
        surveyList.clear();
        maxId=1;
        refreshLayout.setRefreshing(true);
        getSurveyList();
        pollsAdapter.notifyDataSetChanged();
    }


}
