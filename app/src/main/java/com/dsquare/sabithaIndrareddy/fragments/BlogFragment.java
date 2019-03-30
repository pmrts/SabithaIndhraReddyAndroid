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
import com.dsquare.sabithaIndrareddy.adapter.BlogAdapter;
import com.dsquare.sabithaIndrareddy.adapter.EventAdapter;

/**
 * Created by D square on 17-04-2018.
 */

public class BlogFragment extends Fragment
{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BlogAdapter blogAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bolg,container,false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view)
    {
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        blogAdapter = new BlogAdapter();
        recyclerView.setAdapter(blogAdapter);
    }
}
