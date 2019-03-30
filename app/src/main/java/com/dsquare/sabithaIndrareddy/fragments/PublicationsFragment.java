package com.dsquare.sabithaIndrareddy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.adapter.PublicationsAdapter;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.publicationtypeResponse.PublicationResponse;
import com.dsquare.sabithaIndrareddy.pojos.publicationtypeResponse.Result;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 21-05-2018.
 */

public class PublicationsFragment extends Fragment
{

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PublicationsAdapter publicationsAdapter;
    private List<Result> publicationtype =new ArrayList<>();
    private SwipeRefreshLayout refreshLayout;

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
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        publicationsAdapter = new PublicationsAdapter(getActivity(),publicationtype);
        recyclerView.setAdapter(publicationsAdapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {

                refreshLayout.setRefreshing(false);
            }
        });

        refreshLayout.setRefreshing(true);

        getPublications();

    }

    private void getPublications()
    {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<PublicationResponse> call = apiInterface.getPublicationTypes();

        call.enqueue(new Callback<PublicationResponse>()
        {
            @Override
            public void onResponse(Call<PublicationResponse> call, Response<PublicationResponse> response)
            {
                refreshLayout.setRefreshing(false);
                if(response.code()==200)
                {
                    parsePublicType(response.body());
                }

            }

            @Override
            public void onFailure(Call<PublicationResponse> call, Throwable t)
            {
                refreshLayout.setRefreshing(false);
            }
        });

    }

    private void parsePublicType(PublicationResponse body)
    {
        if(body.getStatus()==1)
        {
            publicationtype.addAll(body.getResult());
            publicationsAdapter.notifyDataSetChanged();
        }
        else
        {

        }
    }

}
