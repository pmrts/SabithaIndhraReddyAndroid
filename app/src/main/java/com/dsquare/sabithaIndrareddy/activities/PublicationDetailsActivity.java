package com.dsquare.sabithaIndrareddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.adapter.PublicationDetailsAdapter;
import com.dsquare.sabithaIndrareddy.adapter.PublicationsAdapter;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.publicationtypeResponse.PublicationResponse;
import com.dsquare.sabithaIndrareddy.pojos.publicationtypeResponse.Result;
import com.dsquare.sabithaIndrareddy.utils.EndlessRecyclerViewScrollListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class  PublicationDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout refreshLayout;
    private List<Result> publicationtype =new ArrayList<>();
    private PublicationDetailsAdapter adapter;
    private int maxId=1;
    private String id,name,image;
    private ImageView backbutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications);
        initializeViews();
    }

    private void initializeViews()
    {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        image = intent.getStringExtra("image");

        backbutton = (ImageView) findViewById(R.id.back);

        backbutton.setOnClickListener(this);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PublicationDetailsAdapter(this,publicationtype);
        recyclerView.setAdapter(adapter);


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {

                refreshLayout.setRefreshing(false);
            }
        });

        refreshLayout.setRefreshing(true);

        getPublications();

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Log.d("Recycler Bottom", "Reached Page :" + page + ", Total Count " + totalItemsCount);
                try {
                    maxId++;
                    getPublications();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getPublications()
    {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<PublicationResponse> call = apiInterface.getPublicationTypes(id,maxId+"");

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
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onClick(View view)
    {
        finish();
    }
}
