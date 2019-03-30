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
import com.dsquare.sabithaIndrareddy.adapter.GovernorsAdapter;
import com.dsquare.sabithaIndrareddy.adapter.TeamRajNivasAdapter;
import com.dsquare.sabithaIndrareddy.pojos.governorsResponse.Datum;
import com.dsquare.sabithaIndrareddy.pojos.governorsResponse.GovernorsResponse;
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
 * Created by D square on 18-05-2018.
 */

public class GovernorsFragment extends Fragment
{
    private RecyclerView govrnorrecycler;
    private LinearLayoutManager linearLayoutManager;
    private GovernorsAdapter governorsAdapter;
    private List<Datum> governorslist = new ArrayList<>();
    private Gson gson;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_governors,container,false);
        intializeViews(view);
        return view;

    }

    private void intializeViews(View view)
    {
        govrnorrecycler = (RecyclerView)view.findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        govrnorrecycler.setLayoutManager(linearLayoutManager);


         gson = new Gson();

        GovernorsResponse pojo = (gson.fromJson(readDummyResponse(), GovernorsResponse.class));

        governorslist.addAll(pojo.getData());

        governorsAdapter = new GovernorsAdapter(getContext(),governorslist);
        govrnorrecycler.setAdapter(governorsAdapter);

    }
    public String readDummyResponse()
    {
        InputStream inputStream = null;
        try {
            inputStream = getActivity().getAssets().open("governor.json");
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
