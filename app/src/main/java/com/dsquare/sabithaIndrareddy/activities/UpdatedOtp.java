package com.dsquare.sabithaIndrareddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.fragments.UpdateProfileFragment;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.updatemobileotpResponse.UpdateMobileNoOtpResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;
import com.dsquare.sabithaIndrareddy.utils.SmsListener;
import com.dsquare.sabithaIndrareddy.utils.SmsReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 01-10-2018.
 */

public class UpdatedOtp extends AppCompatActivity implements View.OnClickListener {
    private Button submit;
    private TextView two,three,four,resend,otp_msg;
    private EditText one;
    private String otp,mobile;
    private CommonFunction com;
    private CommonSharePrefrences comShare;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varification);
        initilizeViews();
    }

    private void initilizeViews()
    {
        com = new CommonFunction(this);
        comShare = CommonSharePrefrences.getInstance(this);


        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(this);

        Intent intent = getIntent();
        otp = intent.getStringExtra("updateotp");
        mobile = intent.getStringExtra("updatemobile");

        otp_msg = (TextView)findViewById(R.id.otp_msg);
        otp_msg.setText("OTP is sent to "+ mobile);


        one = (EditText) findViewById(R.id.otp);
//        two = (TextView) findViewById(R.id.otp_two);
//        three = (TextView) findViewById(R.id.otp_three);
//        four = (TextView) findViewById(R.id.otp_four);
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
                updateMobileNumber();
            }
        });

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.resend:
                getupdateMobileOtp();
                break;
            case R.id.continue_button:
                if(otp.equals(one.getText().toString())) {
                    com.HidingSoftKeyBoard(v);
                    updateMobileNumber();
                }
                else
                {
                    Toast.makeText(this,"Please check Your OTP",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back:
                Intent intent = new Intent(this,UpdateMobileNumberActivity.class);
                startActivity(intent);
                finish();
                break;
        }


    }

    private void getupdateMobileOtp()
    {
        com.showProgressDialogue();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<UpdateMobileNoOtpResponse>  call = apiInterface.getOtp(mobile);

        call.enqueue(new Callback<UpdateMobileNoOtpResponse>() {
            @Override
            public void onResponse(Call<UpdateMobileNoOtpResponse> call, Response<UpdateMobileNoOtpResponse> response)
            {
                com.dismissProgressDialogue();
                if(response.code()==200)
                {
                    parseUpdateMobileOtp(response.body());
                }

            }

            @Override
            public void onFailure(Call<UpdateMobileNoOtpResponse> call, Throwable t)
            {
                com.dismissProgressDialogue();
            }
        });

    }

    private void parseUpdateMobileOtp(UpdateMobileNoOtpResponse body)
    {
        if(body.getStatus()==1)
        {
            Toast.makeText(this,body.getMessage(),Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,body.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }


    private  void updateMobileNumber()
    {
        ApiInterface apiInterface =ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.getupdateMobilenumber(comShare.getUserId(),mobile);

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                if(response.code()==200)
                {
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        parseResponse1(object);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {

            }
        });


    }

    private void parseResponse1(JSONObject object)
    {
        try {
            if(object.getInt("status")==1)
            {
                Toast.makeText(this,object.getString("message"), Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this,object.getString("message"),Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
