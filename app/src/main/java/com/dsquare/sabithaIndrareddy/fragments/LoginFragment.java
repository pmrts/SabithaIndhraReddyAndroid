package com.dsquare.sabithaIndrareddy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.HomeActivity;
import com.dsquare.sabithaIndrareddy.activities.VerifyOtpActivity;



public class LoginFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button signUp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_login,container,false);
        initializeViews();
        return view;
    }

    private void initializeViews()
    {
        signUp = (Button) view.findViewById(R.id.signup_button);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent intent = new Intent(getContext() , HomeActivity.class);
        startActivity(intent);
    }
}
