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
import com.dsquare.sabithaIndrareddy.adapter.HomeAdapter;
import com.dsquare.sabithaIndrareddy.adapter.VideoAdapter;

/**
 * Created by D square on 24-04-2018.
 */

public class VideoFragment extends Fragment
{
    private RecyclerView recycler;
    private LinearLayoutManager linearLayoutManager;
    private VideoAdapter videoAdapter;

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
        recycler=(RecyclerView)view.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
        videoAdapter = new VideoAdapter(getActivity());
        recycler.setAdapter(videoAdapter);
    }
}
