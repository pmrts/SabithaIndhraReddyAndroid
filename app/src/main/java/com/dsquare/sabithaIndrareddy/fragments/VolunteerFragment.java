package com.dsquare.sabithaIndrareddy.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.HomeActivity;
import com.dsquare.sabithaIndrareddy.activities.VolunteerActivity;
import com.dsquare.sabithaIndrareddy.adapter.VolunteerAdapter;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.volunteerResponse.Result;
import com.dsquare.sabithaIndrareddy.pojos.volunteerResponse.VolunteerResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VolunteerFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private VolunteerAdapter volunteerAdapter;
    private Button submit;
    private List<Result> list = new ArrayList<>();
    private EditText facebook,twitter,name,email,mobile,address,desc;
    private TextView tv_gender;
    private CommonFunction com;
    private CommonSharePrefrences comShare;
    private String gender;
    private RadioGroup radioGroup;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_volunteer, container, false);
        intializeViews(view);
        return view;
    }

    private void intializeViews(View view)
    {
        com = new CommonFunction(getContext());
        comShare = CommonSharePrefrences.getInstance(getContext());
        submit = (Button)view.findViewById(R.id.volunteer_submit);
        recyclerView = (RecyclerView)view.findViewById(R.id.volunteer_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        volunteerAdapter = new VolunteerAdapter(getActivity(),list);
        recyclerView.setAdapter(volunteerAdapter);


        facebook = (EditText)view.findViewById(R.id.volunteer_facebook_profile);
        twitter = (EditText)view.findViewById(R.id.volunteer_twitter_profile);
        name = (EditText)view.findViewById(R.id.name);
        email = (EditText)view.findViewById(R.id.email_id);
        mobile = (EditText)view.findViewById(R.id.mobile_no);
        address = (EditText)view.findViewById(R.id.address);
        desc = (EditText)view.findViewById(R.id.desc);
        tv_gender = (TextView) view.findViewById(R.id.tv_gender);


        name.setHint ( Html.fromHtml ( "Name "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        email.setHint ( Html.fromHtml ( "Email"+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        tv_gender.setHint ( Html.fromHtml ( "Gender"+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        address.setHint ( Html.fromHtml ( "Address "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        mobile.setHint ( Html.fromHtml ( "Mobile No "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        facebook.setHint ( Html.fromHtml ( "Your Facebook profile page link"+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        twitter.setHint ( Html.fromHtml ( "Your Twitter Profile Link"+"<font color=\"#ff0000\">" + "* " + "</font>" ) );


        submit.setOnClickListener(this);

        radioGroup = (RadioGroup) view.findViewById(R.id.radio_sex);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radio_male) {
                    gender = "1";
                }
                else if (i == R.id.radio_female) {
                    gender = "0";
                }
                else
                {
                    gender = "2";
                }


            }
        });

        getVolunteer();

    }



    @Override
    public void onClick(View v)
    {
//        if(!name.getText().toString().equals(""))
//        {
//            if(mobile.getText().toString().length()>=6)
//            {
//                if(isValidEmail(email.getText().toString()))
//                {
//                    if(!address.getText().toString().equals(""))
//                    {
                        if (!desc.getText().toString().equals(""))
                        {
//                            if(!facebook.getText().toString().equals(""))
//                            {
//                                if(!twitter.getText().toString().equals(""))
//                                {
                                    if(com.volunterList.size()!=0)
                                    {
                                        StringBuilder stringBuilder = new StringBuilder();
                                        for (int i = 0; i < com.volunterList.size(); i++) {
                                            if (stringBuilder.length() > 0) {
                                                stringBuilder.append(",");
                                                stringBuilder.append(com.volunterList.get(i).toString());
                                            }

                                            else
                                            {
                                                stringBuilder.append(com.volunterList.get(i).toString());
                                            }
                                        }
                                        updateOptions(stringBuilder.toString());


                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(),"please select atleast one Option",Toast.LENGTH_SHORT).show();
                                    }
                                }
//                                else
//                                {
//                                    Toast.makeText(getContext(), "Please add Twitter link", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                            else
//                            {
//                                Toast.makeText(getContext(), "Please add Facebook link", Toast.LENGTH_SHORT).show();
//                            }
//                        }

                        else {
                            Toast.makeText(getContext(), "Please add Description", Toast.LENGTH_SHORT).show();
                        }
                    }
//                    else {
//                        Toast.makeText(getContext(), "Please add Address", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//                else {
//                    Toast.makeText(getContext(), "Please add Email", Toast.LENGTH_SHORT).show();
//                }
//            }
//            else
//            {
//                Toast.makeText(getContext(), "Please add Mobile", Toast.LENGTH_SHORT).show();
//            }
//        }
//        else
//        {
//            Toast.makeText(getContext(), "Please add Name", Toast.LENGTH_SHORT).show();
//        }
//
//    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private boolean includeEdge;
        private int spacing;
        private int spanCount;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % this.spanCount;
            if (this.includeEdge) {
                outRect.left = this.spacing - ((this.spacing * column) / this.spanCount);
                outRect.right = ((column + 1) * this.spacing) / this.spanCount;
                if (position < this.spanCount) {
                    outRect.top = this.spacing;
                }
                outRect.bottom = this.spacing;
                return;
            }
            outRect.left = (this.spacing * column) / this.spanCount;
            outRect.right = this.spacing - (((column + 1) * this.spacing) / this.spanCount);
            if (position >= this.spanCount) {
                outRect.top = this.spacing;
            }
        }
    }

    private int dpToPx(int dp) {
        return Math.round(TypedValue.applyDimension(1, (float) dp, getResources().getDisplayMetrics()));
    }

    private void getVolunteer()
    {
        com.showProgressDialogue();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<VolunteerResponse> call = apiService.getVolunteerResponse();
        call.enqueue(new Callback<VolunteerResponse>() {
            @Override
            public void onResponse(Call<VolunteerResponse> call, Response<VolunteerResponse> response)
            {
                com.dismissProgressDialogue();
                if(response.code()==200)
                {
                    parseVolunteerResponse(response);
                }

            }

            @Override
            public void onFailure(Call<VolunteerResponse> call, Throwable t)
            {
                com.dismissProgressDialogue();
            }
        });
    }

    private void parseVolunteerResponse(Response<VolunteerResponse> response)
    {
        if(response.body().getStatus()==1)
        {
            list.addAll(response.body().getResult());
            volunteerAdapter.notifyDataSetChanged();
        }
    }


    private void updateOptions(String s)
    {
        com.showProgressDialogue();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.addVoluteer(facebook.getText().toString(),twitter.getText().toString(),mobile.getText().toString(),
               name.getText().toString(),address.getText().toString(),desc.getText().toString(),gender,email.getText().toString(),s,comShare.getUserId(),comShare.getToken() );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                try {
                    com.dismissProgressDialogue();

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
                facebook.setText("");
                twitter.setText("");
                name.setText("");
                email.setText("");
                mobile.setText("");
                address.setText("");
                desc.setText("");
                volunteerAdapter.notifyDataSetChanged();
                radioGroup.clearCheck();
                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
            else
            {
                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /* Check Whether Entered Email is Valid or Not */
    public boolean isValidEmail(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


}
