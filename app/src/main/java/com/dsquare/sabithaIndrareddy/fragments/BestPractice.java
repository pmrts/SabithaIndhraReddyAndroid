package com.dsquare.sabithaIndrareddy.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by D square on 16-05-2018.
 */

public class BestPractice extends Fragment
{
    Integer pageNumber = 1;
    String pdfFileName;
    private TextView pagesCount;
    WebView webview;
    private CommonFunction com;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_best_practice,container,false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view)
    {

        com = new CommonFunction(getContext());

        webview = (WebView) view.findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        initWebView(); 
        webview.loadUrl("https://drive.google.com/gview?embedded=true&url=http://ifuture.us/LT_Uploads/BestPractices.pdf");
    }

    private void initWebView() {
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new MyWebChromeClient(getContext()));
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                view.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
                com.showProgressDialogue();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
                com.dismissProgressDialogue();

            }

            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getContext(), description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());

                Toast.makeText(getContext(), rerr.getDescription().toString(), Toast.LENGTH_SHORT).show();
            }


        });

    }

    private class MyWebChromeClient extends WebChromeClient {
        Context context;

        public MyWebChromeClient(Context context) {
            super();
            this.context = context;
        }


    }
}
