package com.dsquare.sabithaIndrareddy.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.HomeActivity;
import com.dsquare.sabithaIndrareddy.activities.UpdateMobileNumberActivity;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 06-06-2018.
 */

public class UpdateProfileFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText name,email,mobile,adhar_no,voter_id,address1,address2;
    private TextView name_tv,email_tv,mobile_tv,address1_tv,address2_tv,dob_tv,gender_tv;
    private Button update;
    private RadioGroup radioGroup1;
    private TextView dob,edit;
    private String dobString = "",genderString = "";
    private FixedHoloDatePickerDialog newFragment;
    private CommonSharePrefrences comshare;
    private CommonFunction com;
    private Spinner spinner1,spinner2;
    private List<String> mandalList = new ArrayList<>();
    private List<String> villageList =new ArrayList<>();
    private String MandalID="",villageListID="";
    private RadioButton male,female,other;
    private CallBackMethod mCallback;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_update_profile_one,container,false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view)
    {
        comshare = CommonSharePrefrences.getInstance(getContext());
        com = new CommonFunction(getContext());
        name = (EditText)view.findViewById(R.id.fname);
        email = (EditText)view.findViewById(R.id.registration_email);
        mobile = (EditText) view.findViewById(R.id.Mobile_number);
        update = (Button)view.findViewById(R.id.profile_button);
        dob = (TextView)view.findViewById(R.id.registration_dob);
        radioGroup1 = (RadioGroup)view.findViewById(R.id.radio_sex);
        adhar_no = (EditText)view.findViewById(R.id.registration_aadhar_no);
        voter_id = (EditText)view.findViewById(R.id.registration_voterid);
        address1 = (EditText)view.findViewById(R.id.address1);
        address2 = (EditText)view.findViewById(R.id.address2);
        edit =(TextView) view.findViewById(R.id.edit);

        //text views

        name_tv = (TextView)     view.findViewById(R.id.name_tv);
        email_tv = (TextView)    view.findViewById(R.id.email_tv);
        mobile_tv = (TextView)   view.findViewById(R.id.mobile_tv);
        dob_tv = (TextView)      view.findViewById(R.id.dob_tv);
        gender_tv = (TextView)   view.findViewById(R.id.gender_tv);
        address1_tv = (TextView) view.findViewById(R.id.address1_tv);
        address2_tv = (TextView) view.findViewById(R.id.address2_tv);



        male  =(RadioButton)view.findViewById(R.id.radio_male);
        female = (RadioButton)view.findViewById(R.id.radio_female);
        other = (RadioButton)view.findViewById(R.id.radio_other);


        spinner1 = (Spinner)view.findViewById(R.id.spinner1);
        spinner2 = (Spinner)view.findViewById(R.id.spinner2);
        dob.setOnClickListener(this);
        edit.setOnClickListener(this);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radio_male) {
                    genderString = "male";
                }
                else if (i == R.id.radio_female) {
                    genderString = "female";
                }
                else
                {
                    genderString = "others";
                }


            }
        });

        name_tv.setText ( Html.fromHtml ( "Name "+"<font color=\"#ff0000\">" + "* " + "</font>" ));
        email_tv.setText ( Html.fromHtml ( "Email"+"<font color=\"#ff0000\">" + "* " + "</font>" ));
        mobile_tv.setText ( Html.fromHtml ( "Mobile Number "+"<font color=\"#ff0000\">" + "* " + "</font>" ));
        dob_tv.setText ( Html.fromHtml ( "Date of Birth "+"<font color=\"#ff0000\">" + "* " + "</font>" ));
        gender_tv.setText ( Html.fromHtml ( "Gender"+"<font color=\"#ff0000\">" + "* " + "</font>" ));
        address1_tv.setText ( Html.fromHtml ( "Address Line1 "+"<font color=\"#ff0000\">" + "* " + "</font>" ));
        address2_tv.setText ( Html.fromHtml ( "Address Line2 "+"<font color=\"#ff0000\">" + "* " + "</font>" ));

//        mandalList.add("Nandikotkur");
//        mandalList.add("Atmakur");
//        mandalList.add("velugodu");
//        //mandalList.add("");
//
//        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_items, mandalList);
//        countriesAdapter.setDropDownViewResource(R.layout.spinner_items);
//        spinner1.setAdapter(countriesAdapter);
//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
//            {
//                ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getContext(), R.color.dark_gray));
//                // licenceId = String.valueOf(i+1);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView)
//            {
//
//            }
//        });
//         villageList.add("hello");
//         villageList.add("hi");
//         villageList.add("hwwww");
//
//        ArrayAdapter<String> countriesAdapter1 = new ArrayAdapter<String>(getContext(), R.layout.spinner_items, villageList);
//        countriesAdapter1.setDropDownViewResource(R.layout.spinner_items);
//        spinner2.setAdapter(countriesAdapter1);
//        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
//            {
//                ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getContext(), R.color.dark_gray));
//                // licenceId = String.valueOf(i+1);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView)
//            {
//
//            }
//        });

        update.setOnClickListener(this);


    }

    @Override
    public void onResume() {
        super.onResume();
        getprofile();
    }

    private void getprofile()
    {
        com.showProgressDialogue();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.getuserProfile(comshare.getUserId());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                com.dismissProgressDialogue();
                if(response.code() == 200 )
            {
                JSONObject object = null;
                try {
                    object = new JSONObject(response.body().string());
                    parseGetProfile(object);
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
                com.dismissProgressDialogue();

            }
        });

    }

    private void parseGetProfile(JSONObject object)
    {
        try
        {
            JSONObject object1 = object.getJSONObject("result");
            name.setText(object1.getString(("user_name")));
            email.setText(object1.getString("user_email"));
            mobile.setText(object1.getString("user_mobile"));
         //   dob.setText(object1.getString("user_dob"));
            if(object1.getString("user_dob").equals("0000-00-00")||object1.getString("user_dob")==null||object1.getString("user_dob").equals("null"))
            {
                dob.setText("Date of Birth");

            }
            else
            {
                dob.setText(object1.getString("user_dob"));



            }
            genderString = (object1.getString("user_gender"));
            adhar_no.setText(object1.getString("user_aadhaar_card"));
            voter_id.setText(object1.getString("user_voter_id"));
            address1.setText(object1.getString("user_address1"));
            address2.setText(object1.getString("user_address2"));

            if(genderString.equals("male"))
            {
                male.toggle();

            }
            else if(genderString.equals("female"))
            {
               female.toggle();
            }
            else if(genderString.equals("other"))
            {
                other.toggle();
            }
            else
            {
                radioGroup1.clearCheck();
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


    private void updateprofile()
    {
        com.showProgressDialogue();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.getUpdateProfile(comshare.getUserId(),name.getText().toString(),mobile.getText().toString(),email.getText().toString(),dobString,genderString,
                                   adhar_no.getText().toString(),voter_id.getText().toString(),address1.getText().toString(),address2.getText().toString());
        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                if(response.code() == 200 )
                {
                    com.dismissProgressDialogue();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(response.body().string());
                        parseUpdateProfile(object);
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
                com.dismissProgressDialogue();
            }
        });
    }

    private void parseUpdateProfile(JSONObject object)
    {
        try {
            if(object.getString("status").equals("1"))
            {
                comshare.setUserName(name.getText().toString());
                comshare.setUserEmail(email.getText().toString());
                Toast.makeText(getContext(),object.getString("message"),Toast.LENGTH_SHORT).show();
                mCallback.changeMethod();
            }
            else
            {
                Toast.makeText(getContext(),object.getString("message"),Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public interface CallBackMethod
    {
        void changeMethod();
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (CallBackMethod) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement TextClicked");
        }
    }

//    private void changeFragemts()
//    {
//        switch (com.type)
//        {
//            case "1":
//                break;
//            case "2":
//                break;
//            case "3":
//                break;
//            case "0":
//                break;
//                default:
//                    break;
//        }
 //   }



    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.profile_button:

                com.HidingSoftKeyBoard(v);
                updateprofile();


                break;
            case R.id.registration_dob:
                if(dobString.equals(""))
                {
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    newFragment = new FixedHoloDatePickerDialog(getContext() , this ,year , month , day);
                    newFragment.setTitle("");
                    newFragment.show();
                }
                else
                {
                    int year = Integer.valueOf(dobString.substring(0,4));
                    int month = Integer.valueOf(dobString.substring(5,7));
                    int day = Integer.valueOf(dobString.substring(8,10));
                    newFragment = new FixedHoloDatePickerDialog(getContext() , this ,year , month-1 , day);
                    newFragment.setTitle("");
                    newFragment.show();
                }
                break;
            case R.id.edit:
             Intent intent = new Intent(getContext(),UpdateMobileNumberActivity.class);
             startActivity(intent);
             break;
        }


    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        if(monthOfYear<9&&dayOfMonth<10)
        {

            dob.setText("0"+(monthOfYear + 1)+"/0"+dayOfMonth + "/" + year);
            dobString = year +"-"+"0"+(monthOfYear + 1)+"-"+"0"+dayOfMonth;
        }
        else if(monthOfYear<9)
        {
            dob.setText("0"+(monthOfYear + 1)+"/"+dayOfMonth + "/" + year);
            //         date2.setText("0"+(monthOfYear + 1)+"/"+dayOfMonth + "/" + year);
            dobString = year +"-"+"0"+(monthOfYear + 1)+"-"+dayOfMonth;
            //       dateString1 = year +"-"+"0"+(monthOfYear + 1)+"-"+dayOfMonth;


        }
        else if(dayOfMonth<10)
        {

            dob.setText((monthOfYear + 1)+"/0"+dayOfMonth + "/" + year);
            //    date2.setText((monthOfYear + 1)+"/0"+dayOfMonth + "/" + year);
            dobString = year +"-"+(monthOfYear + 1)+"-"+"0"+dayOfMonth;
            //  dateString1 =  year +"-"+(monthOfYear + 1)+"-"+"0"+dayOfMonth;


        }
        else
        {

            dob.setText((monthOfYear + 1)+"/"+dayOfMonth + "/" + year);
            //date2.setText((monthOfYear + 1)+"/"+dayOfMonth + "/" + year);
            dobString = year +"-"+(monthOfYear + 1)+"-"+dayOfMonth;
            //dateString1 = year +"-"+(monthOfYear + 1)+"-"+"0"+dayOfMonth;
        }

    }

    private static final class FixedHoloDatePickerDialog extends DatePickerDialog {
        private FixedHoloDatePickerDialog(Context context, OnDateSetListener callBack,
                                          int year, int monthOfYear, int dayOfMonth) {
            super(context, callBack, year, monthOfYear, dayOfMonth);

            // Force spinners on Android 7.0 only (SDK 24).
            // Note: I'm using a naked SDK value of 24 here, because I'm
            // targeting SDK 23, and Build.VERSION_CODES.N is not available yet.
            // But if you target SDK >= 24, you should have it.
//            if (Build.VERSION.SDK_INT == 24) {
//                try {
//                    final Field field = this.findField(
//                            DatePickerDialog.class,
//                            DatePicker.class,
//                            "mDatePicker"
//                    );
//
//                    final DatePicker datePicker = (DatePicker) field.get(this);
//                    final Class<?> delegateClass = Class.forName(
//                            "android.widget.DatePicker$DatePickerDelegate"
//                    );
//                    final Field delegateField = this.findField(
//                            DatePicker.class,
//                            delegateClass,
//                            "mDelegate"
//                    );
//
//                    final Object delegate = delegateField.get(datePicker);
//                    final Class<?> spinnerDelegateClass = Class.forName(
//                            "android.widget.DatePickerSpinnerDelegate"
//                    );
//
//                    if (delegate.getClass() != spinnerDelegateClass) {
//
//                        delegateField.set(datePicker, null);
//
//
//
//                        final Constructor spinnerDelegateConstructor =
//                                spinnerDelegateClass.getDeclaredConstructor(
//                                        DatePicker.class,
//                                        Context.class,
//                                        AttributeSet.class,
//                                        int.class,
//                                        int.class
//                                );
//                        spinnerDelegateConstructor.setAccessible(true);
//
//                        final Object spinnerDelegate = spinnerDelegateConstructor.newInstance(
//                                datePicker,
//                                context,
//                                null,
//                                android.R.attr.datePickerStyle,
//                                0
//                        );
//                        delegateField.set(datePicker, spinnerDelegate);
//
//                        datePicker.init(year, monthOfYear, dayOfMonth, this);
//                        datePicker.setCalendarViewShown(false);
//                        datePicker.setSpinnersShown(true);
//                    }
//                } catch (Exception e) { /* Do nothing */ }
//            }
        }

        /**
         * Find Field with expectedName in objectClass. If not found, find first occurrence of
         * target fieldClass in objectClass.
         */
        private Field findField(Class objectClass, Class fieldClass, String expectedName) {
            try {
                final Field field = objectClass.getDeclaredField(expectedName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) { /* Ignore */ }

            // Search for it if it wasn't found under the expectedName.
            for (final Field field : objectClass.getDeclaredFields()) {
                if (field.getType() == fieldClass) {
                    field.setAccessible(true);
                    return field;
                }
            }

            return null;
        }
    }
}
