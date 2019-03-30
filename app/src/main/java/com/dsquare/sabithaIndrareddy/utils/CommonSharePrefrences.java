package com.dsquare.sabithaIndrareddy.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class CommonSharePrefrences
{
    private static CommonSharePrefrences mInstance;  // class instance
    private static Context context;
    private SharedPreferences sharedPreferences; // for shared preferences
    private SharedPreferences sharedPreferences1; // for shared preferences
    private SharedPreferences.Editor editor;  // preferences editor
    private SharedPreferences.Editor editor1;  // preferences editor

    public static final String PREFERENCE_FILE_NAME = "Kirnabedi";
    public static final String PREFERENCE_FILE_NAME1 = "Kirnabedi1";
    public static final String PREFERENCE_TOKEN = "token";
    public static final String PREFERENCE_TOKEN1 = "token1";
    public static final String PREFERENCE_USER_ID = "userId";
    public static final String PREFERENCE_USER_NAME = "userName";
    public static final String PREFERENCE_USER_LNAME = "userLName";
    public static final String PREFERENCE_DOB = "dob";
    public static final String PREFERENCE_ADDRESS = "address";
    public static final String PREFERENCE_GENDER = "gender";
    public static final String PREFERENCE_USER_Emali = "useremail";
    public static final String PREFERENCE_USER_MOBILE = "usermobile";
    public static final String PREFERENCE_USER_Pincode = "pincode";
    public static final String PREFERENCE_GCM = "gcm";
    public static final String PREFERENCE_Image = "image";
    public static final String PREFERENCE_LANGUAGE = "langaue";

    /*set context*/
    public CommonSharePrefrences(Context context)
    {
        this.context  = context;
    }

    /* initialize Acharate if not initialized*/
    public static synchronized CommonSharePrefrences getInstance(Context context){
        if(mInstance==null){
            mInstance = new CommonSharePrefrences(context);
        }
        return mInstance;

    }


    /*initialize shared preferences if not already initialized*/
    public void initializeSharedPrefs()
    {
        if(sharedPreferences==null)
        {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME
                    ,Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    /*initialize shared preferences if not already initialized*/
    public void initializeSharedPrefs1(){
        if(sharedPreferences1==null){
            sharedPreferences1 = context.getSharedPreferences(PREFERENCE_FILE_NAME1
                    ,Context.MODE_PRIVATE);
            editor1 = sharedPreferences1.edit();
        }
    }

    public void setGcm(String token)
    {
        initializeSharedPrefs1();
        editor1.putString(PREFERENCE_GCM,token);
        editor1.commit();
    }

    public String getGcm()
    {
        initializeSharedPrefs1();
        return sharedPreferences1.getString(PREFERENCE_GCM, "");
    }


    public void setToken(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_TOKEN,token);
        editor.commit();
    }

    public String getToken()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_TOKEN, "");
    }

    public void setToken1(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_TOKEN1,token);
        editor.commit();
    }

    public String getToken1()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_TOKEN1, "");
    }

    public void setUserId(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_USER_ID,token);
        editor.commit();
    }

    public String getUserId()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_USER_ID, "");
    }

    public void setUserName(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_USER_NAME,token);
        editor.commit();
    }

    public String getUserName()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_USER_NAME, "");
    }

    public void setUserDob(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_DOB,token);
        editor.commit();
    }

    public String getUserDob()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_DOB, "");
    }


    public void setUserGender(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_GENDER,token);
        editor.commit();
    }

    public String getUserGender()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_GENDER, "");
    }

    public void setUserLname(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_USER_LNAME,token);
        editor.commit();
    }

    public String getUserLname()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_USER_LNAME, "");
    }

    public void setUserAddress(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_ADDRESS,token);
        editor.commit();
    }

    public String getUserAddress()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_ADDRESS, "");
    }

    public void setUserEmail(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_USER_Emali,token);
        editor.commit();
    }

    public String getUserEmail()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_USER_Emali, "");
    }

    public void setUserMobile(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_USER_MOBILE,token);
        editor.commit();
    }

    public String getUserMobile()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_USER_MOBILE, "");
    }

    public void setUserPincode(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_USER_Pincode,token);
        editor.commit();
    }

    public String getUserPincode()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_USER_Pincode, "");
    }

    public void setUserImage(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_Image,token);
        editor.commit();
    }

    public String getUserImage()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_Image, "");
    }

    public void setUserLanguage(String token)
    {
        initializeSharedPrefs();
        editor.putString(PREFERENCE_LANGUAGE,token);
        editor.commit();
    }

    public String getUserLanguage()
    {
        initializeSharedPrefs();
        return sharedPreferences.getString(PREFERENCE_LANGUAGE, "");
    }

    public void clearData()
    {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
