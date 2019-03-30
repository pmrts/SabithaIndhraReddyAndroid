package com.dsquare.sabithaIndrareddy.fcm;

import android.util.Log;


import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private CommonSharePrefrences com;
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //We can Save it and send to server.
        Log.d("token","Refresh token "+refreshedToken);

        com = CommonSharePrefrences.getInstance(getApplicationContext());
        com.setGcm(refreshedToken);
    }

}


