package com.dsquare.sabithaIndrareddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.registrationResponse.RegistrationResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 07-05-2018.
 */

public class LoginMobileActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button signin;
    private EditText mobileno;
    private CommonFunction com;
    private CommonSharePrefrences comshare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_login);
        intilizeViews();
    }

    private void intilizeViews()
    {
        comshare = CommonSharePrefrences.getInstance(this);
        com = new CommonFunction(this);
        signin = (Button)findViewById(R.id.signup_button);
        mobileno = (EditText)findViewById(R.id.login_mobile_no);
        signin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        if(mobileno.getText().toString().length()!=0)
        {
            if(mobileno.getText().toString().length()==10)
            {
                getRegistration();
            }
            else
            {
                Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
        }

    }

    private void getRegistration()
    {
        com.showProgressDialogue();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<RegistrationResponse> call = apiService.getRegistratiionResponse(mobileno.getText().toString());

        call.enqueue(new Callback<RegistrationResponse>()
        {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response)
            {
                com.dismissProgressDialogue();
                if(response.code()==200)
                {
                    parseRegistration(response.body());
                }


            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t)
            {
                com.dismissProgressDialogue();
            }
        });
        }

    private void parseRegistration(RegistrationResponse response)
    {
        if(response.getStatus()==1)
        {
            if(response.getMobileVerifiedStatus()==0)
            {

                Intent intent = new Intent(this,OtpActivity.class);
                intent.putExtra("otp",response.getMobileVerifiedOtp());
                intent.putExtra("mobile",mobileno.getText().toString());
                startActivity(intent);
                //finish();
            }
            else
            {
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                LoginMobileActivity.this.finishAffinity();
            }

        }
        else
        {
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
