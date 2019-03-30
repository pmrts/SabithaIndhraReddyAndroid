package com.dsquare.sabithaIndrareddy.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;

import com.dsquare.sabithaIndrareddy.R;

import java.util.List;

public class ShakthiEnrollmentFragment extends Fragment {
    private static final int REQUEST_SMS = 0;
    private static final int REQ_PICK_CONTACT = 2;


    private Button sendButton;

    private String phoneNo, message;
    private EditText message_edit_text;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shakthi_enrolment, container, false);
        intializeViews(view);
        return view;
    }

    private void intializeViews(View view) {
        sendButton = (Button) view.findViewById(R.id.send_button);
        message_edit_text = (EditText) view.findViewById(R.id.message_edit_text);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendSMS("8328583750", "hello" + " " +"9703757222");
                if(!message_edit_text.getText().toString().equals(""))
                {
                    sendSMS();
                }
                else
                {
                    Toast.makeText(getContext(),"Please enter VoterId",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void sendSMS() {

//        String phoneNo = "8328583750";
//        String message = message_edit_text.getText().toString();
//
//
//        if (ContextCompat.checkSelfPermission(getContext(),
//                Manifest.permission.SEND_SMS)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
//                    Manifest.permission.SEND_SMS)) {
//            } else {
//                ActivityCompat.requestPermissions(getActivity(),
//                        new String[]{Manifest.permission.SEND_SMS},
//                        MY_PERMISSIONS_REQUEST_SEND_SMS);
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
//                    Toast.makeText(getContext(), "SMS sent.",
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getContext(),
//                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//        }
//
//    }
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", new String("7996179961"));
        smsIntent.putExtra("sms_body", message_edit_text.getText().toString()+" "+"9848033090");

        try {
            startActivity(smsIntent);
            //finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(),
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

  //  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getLayoutInflater().inflate(R.menu.menu, (ViewGroup) menu);
        return true;
    }



}


