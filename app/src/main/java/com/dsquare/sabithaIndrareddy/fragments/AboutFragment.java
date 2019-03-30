package com.dsquare.sabithaIndrareddy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 17-04-2018.
 */

public class AboutFragment extends Fragment
{
     private TextView about;
     private ImageView img;
     private WebView webView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_kiranbedi, container, false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view)
    {
        about = (TextView)view.findViewById(R.id.tv_about);
        img  = (ImageView)view.findViewById(R.id.iv_about_img);
        webView = (WebView)view.findViewById(R.id.textContent);


        getAbout();
    }

    private void getAbout()
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<ResponseBody> call = apiService.getaboutSabitha();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response)
            {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.body().string());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                parseAboutsabitha(jsonObject);

            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void parseAboutsabitha(JSONObject jsonObject)
    {
        try {
            if (jsonObject.getString("status").equals("1")) {
                JSONArray array = jsonObject.getJSONArray("result");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(0);
                   // about.setText(object.getString("about_desc"));
                    try {

                        String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";
                        String myData = object.getString("about_desc");


                        webView.loadData(String.format(htmlText, myData), "text/html", "utf-8");

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    Picasso
                            .with(getContext())
                            .load(String.valueOf(object.get("about_image")))
                            .placeholder(R.drawable.img_placeholder)
                            .into(img);
                }
            }
            } catch(JSONException e){
                e.printStackTrace();
            }

    }


}

