package com.dsquare.sabithaIndrareddy.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.KnowYourPollingBoothEmptyActivity;
import com.dsquare.sabithaIndrareddy.activities.MapsActivity;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

public class PollingBoothFragment extends Fragment
{
    private WebView webView;
    private ProgressBar progressBar;
    private String url;
    private float m_downX;
    private Button submit;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pollingbooth, container, false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view)
    {
        webView = (WebView) view.findViewById(R.id.webView);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        url = "https://electoralsearch.in/";
        initWebView();
        webView.loadUrl(url);
    }

    private void initWebView() {
//        webView.setInitialScale(120);
        webView.setScrollContainer(true);
        webView.bringToFront();
        webView.setScrollbarFadingEnabled(true);
        webView.setVerticalScrollBarEnabled(true);
        webView.setHorizontalScrollBarEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.getSettings().setUseWideViewPort(true);
//        webView.setInitialScale(50);
        webView.setWebChromeClient(new MyWebChromeClient(getContext()));
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
//                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressBar.setVisibility(View.GONE);
//                getActivity().invalidateOptionsMenu();
            }
        });

        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getPointerCount() > 1) {
                    //Multi touch detected
                    return true;
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // save the x
                        m_downX = event.getX();
                    }
                    break;

                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP: {
                        // set x so that it doesn't move
                        event.setLocation(m_downX, event.getY());
                    }
                    break;
                }

                return false;
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



