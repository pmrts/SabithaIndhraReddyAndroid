package com.dsquare.sabithaIndrareddy.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.publicationtypeResponse.PublicationResponse;
import com.dsquare.sabithaIndrareddy.pojos.publicationtypeResponse.Result;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 06-09-2018.
 */

public class LiveActivity extends AppCompatActivity  {
    private ImageView backbutton;
    private YouTubePlayer youTubeView;
    YouTubePlayerSupportFragment youTubePlayerFragment;
    private List<Result> publicationtype =new ArrayList<>();
    private SwipeRefreshLayout refreshLayout;
    private CommonFunction com;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_activity);
        youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_layout, youTubePlayerFragment).commit();

        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe);
        getLiveStreming();
        //backbutton = (ImageView) findViewById(R.id.back);
        //backbutton.setOnClickListener(this);
//        youTubePlayerFragment.initialize(com.DEVELOPER_KEY,new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b)
//            {
//
//                    youTubeView = youTubePlayer;
//                    youTubeView.setFullscreenControlFlags(1);
//                    youTubeView.setShowFullscreenButton(false);
//                    youTubeView.loadVideo("HoWJeHL3AEk");
//                    youTubeView.play();
//
//
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
//            {
//
//
//            }
//            // youTubeView = YouTubePlayer;
//      });
       }



//    @Override
//    public void onClick(View v)
//    {
//        onBackPressed();
//
//    }

    private void getLiveStreming()
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

       retrofit2.Call<ResponseBody> call = apiInterface.getLiveStrems();

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response)
            {
                if(response.code() == 200)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        parseResponse(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t)
            {


            }


        });
    }
    private void parseResponse(JSONObject object)
    {
        try {
            if(object.getInt("status")==1)
            {
                youTubePlayerFragment.initialize(com.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b)
                    {
                        if (!b) {
                            youTubeView = youTubePlayer;
                            youTubeView.setFullscreenControlFlags(1);
                            youTubeView.setShowFullscreenButton(false);
                            youTubeView.loadVideo("HoWJeHL3AEk");
                            youTubeView.play();
                        }


                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
                    {


                    }
                });

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
