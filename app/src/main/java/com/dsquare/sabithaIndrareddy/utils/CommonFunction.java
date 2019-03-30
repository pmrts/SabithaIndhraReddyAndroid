package com.dsquare.sabithaIndrareddy.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.fragments.CreateTicketFragment;
import com.dsquare.sabithaIndrareddy.pojos.congress.CongressListResponse;
import com.dsquare.sabithaIndrareddy.pojos.feedslist.Result;
import com.dsquare.sabithaIndrareddy.pojos.questionsResponse.QuestionResponse;
import com.dsquare.sabithaIndrareddy.pojos.questionsResponse.SubmitQuestionResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by D square on 24-04-2018.
 */

public class CommonFunction
{
    Context context;
    MyProgressDialog Loading;


    /**
     * @param context
     */
    public CommonFunction(Context context) {
        this.context = context;
    }

    public static final String DEVELOPER_KEY = "AIzaSyDDTuwpR2aD4fZLKSXVBUJ6C1e14oNtSfA";

    public static List<QuestionResponse> quesListPojoList = new ArrayList<>();
    public static HashMap<String , List<CongressListResponse>> listHashMap = new HashMap<>();
    public static List<String> volunterList = new ArrayList<>();
    public static List<String> questionList = new ArrayList<>();
    public static List<SubmitQuestionResponse> submitQuestionList = new ArrayList<>();
    public static  Result feedDataList = new Result();
    public static String basePath;
    public static String baseUrl ;
    public static String type = "0" ;
    public static final String PRODUCTION_APP_VERSIONCODE_KEY = "version";

    public void showProgressDialogue()
    {
        Loading = new MyProgressDialog(context);

        try {
            Loading.show();
            Loading.setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissProgressDialogue()
    {
        if(Loading!=null)
        {
            if (Loading.isShowing()) {
                try {
                    Loading.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * To hide the SoftKeyboard when click the View
     *
     * @param view
     */
    public void HidingSoftKeyBoard(View view) {
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
