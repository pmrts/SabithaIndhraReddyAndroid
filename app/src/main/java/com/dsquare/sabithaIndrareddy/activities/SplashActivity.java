package com.dsquare.sabithaIndrareddy.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.dsquare.sabithaIndrareddy.BuildConfig;
import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import io.fabric.sdk.android.Fabric;
import okhttp3.ResponseBody;
import retrofit2.Call;


public class SplashActivity extends AppCompatActivity
{
    private CommonSharePrefrences comshare;
    private CommonFunction com;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    int currentIndex = 0;
    Handler handler;

    private void checkPermissions()
    {
        try {
            for (int i = currentIndex; i < permissions.length; i++)
            {
                currentIndex = currentIndex + 1;
                int result = ContextCompat.checkSelfPermission(this, permissions[i]);
                if (result == PackageManager.PERMISSION_GRANTED) {

                } else
                    {
                    requestPermission(permissions[i]);
                    return;
                }
            }
            activatingTimer();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String[] permissions = new String[]{
            Manifest.permission.CALL_PHONE,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS};

    private void requestPermission(String permission)
    {
        ActivityCompat.requestPermissions(this, new String[]
                {permission}, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // Snackbar.make(view, "Permission Granted, Now you can access
                    // location data.", Snackbar.LENGTH_LONG)
                    // .show();
                    checkPermissions();
                } else {
                    try {
                        Toast.makeText(this, "Denied!!!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.splash_activity);
        checkPermissions();

        comshare = CommonSharePrefrences.getInstance(this);

    }

    private void activatingTimer()
    {
        setUpConfig();
        fetchConfigData();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onStop() {
        super.onStop();
//        handler.removeCallbacksAndMessages(null);
//        handler=null;
        SplashActivity.this.finishAffinity();
    }

    private void setUpConfig() {


        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

    }

    private void fetchConfigData() {

        long cacheExpiration = 0; // 1 hour in seconds.
        // If your app is using developer mode, cacheExpiration is set to 0, so each fetch will
        // retrieve values from the service.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Log.d("Remote Config","Fetch Succeeded");

                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {

                            Log.d("Remote Config","Fetch Failed");
                        }

                        long productionAppVersionCode = mFirebaseRemoteConfig.getLong(com.PRODUCTION_APP_VERSIONCODE_KEY);
                        checkUpdate(productionAppVersionCode);

                    }
                });

    }

    private void checkUpdate(long productionAppVersionCode) {

        int currentVersionCode = BuildConfig.VERSION_CODE;

        if(currentVersionCode < (int)productionAppVersionCode){

            getalert();

        }else{

            if(comshare.getToken().equals(""))
            {
                Intent i = new Intent(SplashActivity.this, SplashVideoActivity.class);
                startActivity(i);
                finish();
            }
            else
            {
                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        }

    }


    void getalert(){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(SplashActivity.this);
        } else {
            builder = new AlertDialog.Builder(SplashActivity.this);
        }
        builder.setTitle("Update")
                .setMessage("New version is available in playstore please update")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                    }
                })
//                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .show();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        handler.removeCallbacksAndMessages(null);
//        handler=null;
//        SplashActivity.this.finishAffinity();
//    }



}
