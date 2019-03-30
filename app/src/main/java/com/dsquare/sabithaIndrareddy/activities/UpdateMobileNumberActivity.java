package com.dsquare.sabithaIndrareddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.fragments.UpdateProfileFragment;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.updatemobileotpResponse.UpdateMobileNoOtpResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 27-09-2018.
 */

public class UpdateMobileNumberActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText login_mobile_no;
    private Button otp_button;
    private String mobile,otp;
    private ImageView back;
    private CommonSharePrefrences comshare;
    private CommonFunction com;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mobile_no);
        intializeViews();

    }

    private void intializeViews()
    {
        login_mobile_no = (EditText)findViewById(R.id.login_mobile_no);
        otp_button      = (Button)findViewById(R.id.otp_button);
        back     = (ImageView)findViewById(R.id.back);
        comshare = CommonSharePrefrences.getInstance(this);
        com = new CommonFunction(this);

        login_mobile_no.setOnClickListener(this);
        otp_button.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.otp_button:

            if (login_mobile_no.getText().toString().length() != 0) {
                if (login_mobile_no.getText().toString().length() == 10) {
                    com.HidingSoftKeyBoard(v);
                    getupdateMobileOtp();
                } else {
                    Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.back:
                onBackPressed();
                break;
        }
    }
    private void getupdateMobileOtp()
    {
        com.showProgressDialogue();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<UpdateMobileNoOtpResponse>  call = apiInterface.getOtp(login_mobile_no.getText().toString());

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
            Intent intent = new Intent(this,UpdatedOtp.class);
            intent.putExtra("updateotp",body.getMobileVerifiedOtp());
            intent.putExtra("updatemobile",login_mobile_no.getText().toString());
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(this,body.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }



    private  void updateMobileNumber()
    {
        ApiInterface apiInterface =ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.getupdateMobilenumber(comshare.getUserId(),login_mobile_no.getText().toString());

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
                comshare.setToken(object.getString("token"));
                comshare.setUserId(object.getString("user_id"));
                Toast.makeText(this,object.getString("message"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,UpdateProfileFragment.class);
                startActivity(intent);
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


