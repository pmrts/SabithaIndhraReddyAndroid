package com.dsquare.sabithaIndrareddy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.adapter.CongressAdapter;
import com.dsquare.sabithaIndrareddy.pojos.congress.CongressListResponse;
import com.dsquare.sabithaIndrareddy.pojos.feedList.JouneryListPojo;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryofcongressFragment extends Fragment
{

    private LinearLayout layout;
    private List<String> mainList = new ArrayList<>();
    private RecyclerView journeyofdksrecycler;
    private CongressAdapter achievementsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private HashMap<Integer, List<JouneryListPojo>> hashMap = new HashMap<>();

    private View view;
    private CommonFunction com;
    private CommonSharePrefrences comshare;
    private String type;
    List<CongressListResponse> jouneryListPojos = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_energy, container, false);
        comshare = CommonSharePrefrences.getInstance(getContext());
        com = new CommonFunction(getContext());
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view)
    {
        type = getArguments().getString("type");

        journeyofdksrecycler = (RecyclerView) view.findViewById(R.id.recyler);

        linearLayoutManager = new LinearLayoutManager(getContext());
        journeyofdksrecycler.setLayoutManager(linearLayoutManager);
        achievementsAdapter = new CongressAdapter(getContext(), jouneryListPojos);
        journeyofdksrecycler.setAdapter(achievementsAdapter);

        journeyofdksrecycler.scrollToPosition(jouneryListPojos.size() - 1);

        jouneryListPojos = com.listHashMap.get(type);
        achievementsAdapter.refresh(jouneryListPojos);
    }

}
