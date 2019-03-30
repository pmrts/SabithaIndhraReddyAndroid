package com.dsquare.sabithaIndrareddy.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.fragments.LoginFragment;
import com.dsquare.sabithaIndrareddy.fragments.RegisterFragment;


public class LoginRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login ,register;
    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
    }

    private void initializeViews()
    {
        login = (Button) findViewById(R.id.login_button);
        register = (Button) findViewById(R.id.register_button);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        login.setBackgroundResource(R.drawable.login_radius_fill);
        register.setBackgroundColor(Color.TRANSPARENT);
        login.setTextColor(Color.parseColor("#ffffff"));
        register.setTextColor(Color.parseColor("#162A47"));

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = new LoginFragment();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }



    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.login_button:
                login.setBackgroundResource(R.drawable.login_radius_fill);
                register.setBackgroundColor(Color.TRANSPARENT);
                login.setTextColor(Color.parseColor("#ffffff"));
                register.setTextColor(Color.parseColor("#162A47"));

                Fragment fragment = new LoginFragment();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
            case R.id.register_button:
                login.setBackgroundColor(Color.TRANSPARENT);
                register.setBackgroundResource(R.drawable.login_radius_fill);
                login.setTextColor(Color.parseColor("#162A47"));
                register.setTextColor(Color.parseColor("#ffffff"));

                Fragment fragment1 = new RegisterFragment();
                FragmentTransaction ft1 = fragmentManager.beginTransaction();
                ft1.replace(R.id.frame_layout, fragment1);
                ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft1.commit();
                break;
        }
    }
}
