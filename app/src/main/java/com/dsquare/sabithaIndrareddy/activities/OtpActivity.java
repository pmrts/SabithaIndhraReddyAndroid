package com.dsquare.sabithaIndrareddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.registrationResponse.RegistrationResponse;
import com.dsquare.sabithaIndrareddy.pojos.updatemobileotpResponse.UpdateMobileNoOtpResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;
import com.dsquare.sabithaIndrareddy.utils.SmsListener;
import com.dsquare.sabithaIndrareddy.utils.SmsReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 07-05-2018.
 */

public class OtpActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button submit;
    private EditText one,two,three,four;
    private TextView resend,otp_msg;
    private ImageView back;
    private String otpString = "";
    private String otp,mobile;
    private CommonFunction com;
    private CommonSharePrefrences comShare;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varification);
        intilizeViews();
    }

    private void intilizeViews()
    {
        com = new CommonFunction(this);
        comShare = CommonSharePrefrences.getInstance(this);

        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(this);

        otp_msg = (TextView)findViewById(R.id.otp_msg);


        Intent intent = getIntent();
        otp = intent.getStringExtra("otp");
        mobile = intent.getStringExtra("mobile");

        otp_msg.setText("OTP is sent to "+ mobile);


        one = (EditText) findViewById(R.id.otp);
//        two = (EditText) findViewById(R.id.otp_two);
//        three = (EditText) findViewById(R.id.otp_three);
//        four = (EditText) findViewById(R.id.otp_four);
        resend = (TextView) findViewById(R.id.resend);

//        one.setText(otp.substring(0,1));
//        two.setText(otp.substring(1,2));
//        three.setText(otp.substring(2,3));
//        four.setText(otp.substring(3,4));

        submit = (Button)findViewById(R.id.continue_button);
        submit.setOnClickListener(this);
        resend.setOnClickListener(this);

        SmsReceiver.bindListener(new SmsListener()
        {
            @Override
            public void messageReceived(String messageText)
            {
                one.setText(messageText);
                getOtp();
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.resend:
                getRegistration();
                break;
            case R.id.continue_button:

                Log.d("one", "one");
                if(otp.equals(one.getText().toString()))
                {
                    getOtp();
                }
                else
                {
                    Toast.makeText(this,"Please check Your OTP",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.back:
                onBackPressed();
                break;
        }

    }

    private void getOtp()
    {

        com.showProgressDialogue();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.verifyOtp(otp,mobile);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                com.dismissProgressDialogue();
                try {
                    if(response.code()==200)
                    {
                        JSONObject object = null;
                        object = new JSONObject(response.body().string());

                        parseResponse(object);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                com.dismissProgressDialogue();
            }
        });

    }




    private void parseResponse(JSONObject jsonObject)
    {
        try {
            if(jsonObject.getInt("status")==1)
            {
                if(jsonObject.getInt("mobile_verified_status")==1)
                {
                    comShare.setToken(jsonObject.getString("token"));
                    comShare.setUserId(jsonObject.getString("user_id"));
                    comShare.setUserName(jsonObject.getString("user_name"));
                    comShare.setUserEmail(jsonObject.getString("user_email"));
                    comShare.setUserMobile(jsonObject.getString("user_mobile"));
                    Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,HomeActivity.class);
                    startActivity(intent);
                    OtpActivity.this.finishAffinity();
                }
                else
                {
                    Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getRegistration()
    {
        com.showProgressDialogue();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<RegistrationResponse> call = apiService.getRegistratiionResponse(mobile);

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

    private void parseRegistration(RegistrationResponse body)
    {
        comShare.setUserMobile(body.getUserMobile());
        otp = body.getMobileVerifiedOtp();
        //one .setText();
//        one.setText(otp.substring(0,1));
//        two.setText(otp.substring(1,2));
//        three.setText(otp.substring(2,3));
//        four.setText(otp.substring(3,4));
    }




}
