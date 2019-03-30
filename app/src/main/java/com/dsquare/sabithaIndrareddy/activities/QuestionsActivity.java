package com.dsquare.sabithaIndrareddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.adapter.QuestionsPagerAdapter;
import com.dsquare.sabithaIndrareddy.fragments.PollsFragments;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.questionsResponse.QuestionResponse;
import com.dsquare.sabithaIndrareddy.pojos.questionsResponse.QuestionsListResponse;
import com.dsquare.sabithaIndrareddy.pojos.questionsResponse.SubmitQuestionResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 08-05-2018.
 */

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener
{

    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ImageView back;
    private Button done;
    private String survey_id;
    private QuestionsPagerAdapter mAdapter;
    private CommonFunction com;
    private CommonSharePrefrences comShare;

    private List<QuestionsListResponse> questionlist = new ArrayList<>();
    private EditText remarks;
    private String s="";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        com = new CommonFunction(this);
        comShare = CommonSharePrefrences.getInstance(this);

        Intent intent = getIntent();
        survey_id = intent.getStringExtra("id");

        remarks = (EditText) findViewById(R.id.remarks);

        intro_images = (ViewPager) findViewById(R.id.pager_introduction);
        done = (Button) findViewById(R.id.done);
        back = (ImageView) findViewById(R.id.imgHeaderLeftBack);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        done.setOnClickListener(this);
        back.setOnClickListener(this);
        mAdapter = new QuestionsPagerAdapter(QuestionsActivity.this, questionlist);
        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(this);
        getQuestions();

        com.questionList.clear();
        com.submitQuestionList.clear();

        com.questionList.add("");
        com.questionList.add("");

        remarks.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("dataa",charSequence.toString());
                s = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });


    }

    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        if (1 == dotsCount)
        {
            done.setVisibility(View.VISIBLE);
            remarks.setVisibility(View.VISIBLE);
        } else {
            done.setVisibility(View.INVISIBLE);
            remarks.setVisibility(View.INVISIBLE);
        }


        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imgHeaderLeftBack:
                finish();
                break;
            case R.id.done:
                if(questionlist.size()==com.submitQuestionList.size())
                {
                    submitSurvey();
                    this.finish();
                }
                else
                {
                    Toast.makeText(this, "Should be submit all answer", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position)
    {
        for (int i = 0; i <= dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));


        if (position +1 == dotsCount)
        {
            done.setVisibility(View.VISIBLE);
            remarks.setVisibility(View.VISIBLE);
        } else {
            done.setVisibility(View.INVISIBLE);
            remarks.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void getQuestions()
    {
        com.showProgressDialogue();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<QuestionResponse> call = apiService.getQuestions(survey_id);
        call.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response)
            {
                com.dismissProgressDialogue();
                if(response.code()==200)
                {
                    parseQuestionsresponse(response);
                }

            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t)
            {
                com.dismissProgressDialogue();
            }
        });
    }

    private void parseQuestionsresponse(Response<QuestionResponse> response)
    {
        if(response.body().getStatus()==1)
        {
            questionlist.addAll(response.body().getResult());
            mAdapter.notifyDataSetChanged();
            setUiPageViewController();
        }
    }

    private void submitSurvey()
    {
        com.showProgressDialogue();

        JSONObject object = new JSONObject();
        try
        {
            object.put("poll_servey_id",survey_id);
            object.put("poll_user_id",comShare.getUserId());
            object.put("token",comShare.getToken());
            object.put("remarks",remarks.getText().toString());

            JSONArray jsonArray = new JSONArray();

            for(int i = 0 ; i < com.submitQuestionList.size() ; i++)
            {
                JSONObject object1 = new JSONObject();
                object1.put("poll_question_id",com.submitQuestionList.get(i).getQuestionId());
                object1.put("poll_option_id",com.submitQuestionList.get(i).getOptionId());

                jsonArray.put(object1);
            }

            object.put("poll_questions",jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();

        }

            Log.d("tokenn",object.toString());

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            RequestBody body =
                    RequestBody.create(MediaType.parse("application/json"), object.toString());


            Call<ResponseBody> call = apiService.submitSurvey(body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    com.dismissProgressDialogue();
                    try {

                        if (response.code() == 200) {
                            JSONObject object = null;
                            object = new JSONObject(response.body().string());

                            parseResponse(object);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    com.dismissProgressDialogue();
                }
            });
    }

    private void parseResponse(JSONObject jsonObject)
    {
        try {
            if(jsonObject.getInt("status")==1)
            {
                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

