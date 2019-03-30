package com.dsquare.sabithaIndrareddy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.adapter.TeamRajNivasAdapter;
import com.dsquare.sabithaIndrareddy.pojos.teamrajnivas.RajNivasListResponse;
import com.dsquare.sabithaIndrareddy.pojos.teamrajnivas.RajNivasResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by D square on 17-05-2018.
 */

public class TeamRajNivas extends Fragment
{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TeamRajNivasAdapter teamRajNivasAdapter;
    private Gson gson;
    private List<RajNivasListResponse> listResponses = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_teamrajnivas, container, false);
        intializeViews(view);
        return view;
    }

    private void intializeViews(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        gson = new Gson();

        RajNivasResponse pojo = (gson.fromJson(readDummyResponse(), RajNivasResponse.class));

        listResponses.addAll(pojo.getData());

        teamRajNivasAdapter = new TeamRajNivasAdapter(getContext(),listResponses);
        recyclerView.setAdapter(teamRajNivasAdapter);
    }

    public String readDummyResponse()
    {
        InputStream inputStream = null;
        try {
            inputStream = getActivity().getAssets().open("Teamrajnivas.json");
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
}


