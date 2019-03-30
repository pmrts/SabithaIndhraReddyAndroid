package com.dsquare.sabithaIndrareddy.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.creatTicketDistrictResponse.DistrictList;
import com.dsquare.sabithaIndrareddy.pojos.visitrajnivasidtypes.Result;
import com.dsquare.sabithaIndrareddy.pojos.visitrajnivasidtypes.VisitRajNivasIdTypesResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by D square on 17-05-2018.
 */

public class RajNivasVisitorFormFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText num_childerns,num_adults,visitor_name,address,mobileno,email_id,state,id_number;
    private TextView gender,group_type,date,date_of_birth;
    private List<String> licenceList = new ArrayList<>();
    private List<String> idTypesList = new ArrayList<>();
    private Spinner spinner1,spinner2,spinnerGroup;
    private String dateString="",dobString="",type="";
    private FixedHoloDatePickerDialog newFragment;
    private String gendertypeString="",genderString="",nationalityString,licenceId;
    private RadioGroup radioGroup,radioGroup1,radioGroup2;
    private EditText question1,question2,question3,question4,question5;
    private Button send;
    private CommonFunction com;
    private CommonSharePrefrences comShare;
    private List<String> groupList = new ArrayList<>();
    private LinearLayout layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.rajnivas_visitor_form_fragment,container,false);
        initializeViews(view);
        return view;

    }

    private void initializeViews(View view)
    {
        com = new CommonFunction(getContext());
        comShare = CommonSharePrefrences.getInstance(getContext());

        groupList.add("Select Group type");
        groupList.add("Individual");
        groupList.add("SmallGroup(Max 5)");

        spinner1 = (Spinner)view.findViewById(R.id.spinner1);
        spinnerGroup = (Spinner)view.findViewById(R.id.spinner_group);
        date = (TextView)view.findViewById(R.id.et_date);
        num_adults = (EditText)view.findViewById(R.id.et_adults);
        num_childerns = (EditText)view.findViewById(R.id.et_childerns);
        visitor_name = (EditText)view.findViewById(R.id.et_visitors);
        gender = (TextView)view.findViewById(R.id.tv_gender);
        date_of_birth = (TextView)view.findViewById(R.id.et_date_of_birth);
        address = (EditText)view.findViewById(R.id.et_address);
        mobileno = (EditText)view.findViewById(R.id.et_mobile);
        email_id = (EditText)view.findViewById(R.id.et_email);
        state = (EditText)view.findViewById(R.id.et_state);
        id_number = (EditText)view.findViewById(R.id.et_number);
        group_type = (TextView)view.findViewById(R.id.tv_group_type);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_grop_individual);
        radioGroup1 = (RadioGroup) view.findViewById(R.id.radio_sex);
        radioGroup2 = (RadioGroup) view.findViewById(R.id.radio_nationality);
        question1 = (EditText)view.findViewById(R.id.et_question1);
        question2 = (EditText)view.findViewById(R.id.et_question2);
        question3 = (EditText)view.findViewById(R.id.et_question3);
        question4 = (EditText)view.findViewById(R.id.et_question4);
        question5 = (EditText)view.findViewById(R.id.et_question5);
        send = (Button) view.findViewById(R.id.bt_send);

        layout = (LinearLayout) view.findViewById(R.id.group_layout);

        date.setOnClickListener(this);
        date_of_birth.setOnClickListener(this);
        send.setOnClickListener(this);

        ArrayAdapter<String> idtypesAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_items, groupList);
        idtypesAdapter.setDropDownViewResource(R.layout.spinner_items);
        spinnerGroup.setAdapter(idtypesAdapter);

        spinnerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i!=0)
                {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getContext(), R.color.dark_gray));
                    if(i==1)
                    {
                        gendertypeString = "1";

                        question2.setVisibility(View.GONE);
                        question3.setVisibility(View.GONE);
                        question4.setVisibility(View.GONE);
                        question5.setVisibility(View.GONE);
                        layout.setVisibility(View.VISIBLE);
                        send.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        gendertypeString = "2";
                        layout.setVisibility(View.VISIBLE);
                        question2.setVisibility(View.VISIBLE);
                        question3.setVisibility(View.VISIBLE);
                        question4.setVisibility(View.VISIBLE);
                        question5.setVisibility(View.VISIBLE);
                        send.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radio_individual)
                {
                    gendertypeString = "1";

                    question2.setVisibility(View.GONE);
                    question3.setVisibility(View.GONE);
                    question4.setVisibility(View.GONE);
                    question5.setVisibility(View.GONE);
                }
                else
                {
                    gendertypeString = "2";

                    question2.setVisibility(View.VISIBLE);
                    question3.setVisibility(View.VISIBLE);
                    question4.setVisibility(View.VISIBLE);
                    question5.setVisibility(View.VISIBLE);
                }


            }
        });

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radio_male) {
                    genderString = "1";
                }
                else if (i == R.id.radio_female) {
                    genderString = "0";
                }
                else
                {
                    genderString = "2";
                }


            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radio_indian)
                {
                    nationalityString = "1";
                }
                else
                {
                    nationalityString = "2";
                }
            }
        });


        visitor_name.setHint ( Html.fromHtml ( "Visitor Name "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        address.setHint ( Html.fromHtml ( "Address "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        email_id.setHint ( Html.fromHtml ( "Email ID "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        mobileno.setHint ( Html.fromHtml ( "Mobile No "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        state.setHint ( Html.fromHtml ( "State "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        id_number.setHint ( Html.fromHtml ( "ID Number "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        group_type.setText ( Html.fromHtml ( "Group Type "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );

//        licenceList.add("ID Type");
//        licenceList.add("Driving Licence");
//        licenceList.add("passport");
//        licenceList.add("PAN card");
//        licenceList.add("Voter id_card");
//        licenceList.add("Gov issue ID_card");
//        licenceList.add("Student ID_card");
//        licenceList.add("Bank PassBook");
//        licenceList.add("Others");
        idTypes();
//        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_items, licenceList);
//        countriesAdapter.setDropDownViewResource(R.layout.spinner_items);
//        spinner1.setAdapter(countriesAdapter);
//
//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
//            {
//                ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getContext(), R.color.dark_gray));
//                licenceId = String.valueOf(i+1);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView)
//            {
//
//            }
//        });

    }


    private void idTypes()
    {
        com.showProgressDialogue();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<VisitRajNivasIdTypesResponse> call = apiService.getVisitorIdtypes();
        call.enqueue(new Callback<VisitRajNivasIdTypesResponse>() {
            @Override
            public void onResponse(Call<VisitRajNivasIdTypesResponse> call, Response<VisitRajNivasIdTypesResponse> response)
            {
                com.dismissProgressDialogue();
                if(response.code()==200)
                {
                    ParseIdtype(response.body());
                }

            }

            @Override
            public void onFailure(Call<VisitRajNivasIdTypesResponse> call, Throwable t)
            {
                com.dismissProgressDialogue();

            }
        });
    }

    private void ParseIdtype(VisitRajNivasIdTypesResponse body)
    {
        if(body.getStatus()==1) {
            licenceList.add("Please select Id type");
            for(int i = 0 ; i < body.getResult().size() ; i++ )
            {

                licenceList.add(body.getResult().get(i).getTypeName());
                idTypesList.add(body.getResult().get(i).getTypeId());
            }
            // idTypesList.clear();
            ArrayAdapter<String> idtypesAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_items, licenceList);
            idtypesAdapter.setDropDownViewResource(R.layout.spinner_items);
            spinner1.setAdapter(idtypesAdapter);

            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                {
                    if(i!=0)
                    {
                        ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getContext(), R.color.dark_gray));
                        licenceId = idTypesList.get(i);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView)
                {

                }
            });
        }
    }


    private void addVisitor()
    {

        com.showProgressDialogue();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.addVisitors(gendertypeString,dateString,num_adults.getText().toString(),
                num_childerns.getText().toString(),visitor_name.getText().toString(), nationalityString,dobString,
                genderString,address.getText().toString(),mobileno.getText().toString(),email_id.getText().toString(),
                state.getText().toString(),licenceId,id_number.getText().toString(),question1.getText().toString(),
                question2.getText().toString(),question3.getText().toString(),question4.getText().toString(),question5.getText().toString(),comShare.getToken(),comShare.getUserId());

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
                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.et_date:
                type = "1";
                if(dateString.equals(""))
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
                    int year = Integer.valueOf(dateString.substring(0,4));
                    int month = Integer.valueOf(dateString.substring(5,7));
                    int day = Integer.valueOf(dateString.substring(8,10));
                    newFragment = new FixedHoloDatePickerDialog(getContext() , this ,year , month-1 , day);
                    newFragment.setTitle("");
                    newFragment.show();
                }
                break;
            case R.id.et_date_of_birth:
                type = "2";
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
            case R.id.bt_send:
                addVisitor();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year,
                          int monthOfYear, int dayOfMonth)
    {
        if(monthOfYear<9&&dayOfMonth<10)
        {
            if(type.equals("1"))
            {
                date.setText("0"+(monthOfYear + 1)+"/0"+dayOfMonth + "/" + year);
                dateString = year +"-"+"0"+(monthOfYear + 1)+"-"+"0"+dayOfMonth;
            }
            else
            {
                date_of_birth.setText("0"+(monthOfYear + 1)+"/0"+dayOfMonth + "/" + year);
                dobString = year +"-"+"0"+(monthOfYear + 1)+"-"+"0"+dayOfMonth;
            }

        }
        else if(monthOfYear<9)
        {
            if(type.equals("1"))
            {
                date.setText("0"+(monthOfYear + 1)+"/"+dayOfMonth + "/" + year);
                dateString = year +"-"+"0"+(monthOfYear + 1)+"-"+dayOfMonth;
            }
            else
            {
                date_of_birth.setText("0"+(monthOfYear + 1)+"/"+dayOfMonth + "/" + year);
                dobString = year +"-"+"0"+(monthOfYear + 1)+"-"+dayOfMonth;
            }

        }
        else if(dayOfMonth<10)
        {
            if(type.equals("1"))
            {
                date.setText((monthOfYear + 1)+"/0"+dayOfMonth + "/" + year);
                dateString = year +"-"+(monthOfYear + 1)+"-"+"0"+dayOfMonth;
            }
            else
            {
                date_of_birth.setText((monthOfYear + 1)+"/0"+dayOfMonth + "/" + year);
                dobString = year +"-"+(monthOfYear + 1)+"-"+"0"+dayOfMonth;
            }

        }
        else
        {
            if(type.equals("1"))
            {
                date.setText((monthOfYear + 1)+"/"+dayOfMonth + "/" + year);
                dateString = year +"-"+(monthOfYear + 1)+"-"+dayOfMonth;
            }
            else
            {
                date_of_birth.setText((monthOfYear + 1)+"/"+dayOfMonth + "/" + year);
                dobString = year +"-"+(monthOfYear + 1)+"-"+dayOfMonth;
            }

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
            if (Build.VERSION.SDK_INT == 24) {
                try {
                    final Field field = this.findField(
                            DatePickerDialog.class,
                            DatePicker.class,
                            "mDatePicker"
                    );

                    final DatePicker datePicker = (DatePicker) field.get(this);
                    final Class<?> delegateClass = Class.forName(
                            "android.widget.DatePicker$DatePickerDelegate"
                    );
                    final Field delegateField = this.findField(
                            DatePicker.class,
                            delegateClass,
                            "mDelegate"
                    );

                    final Object delegate = delegateField.get(datePicker);
                    final Class<?> spinnerDelegateClass = Class.forName(
                            "android.widget.DatePickerSpinnerDelegate"
                    );

                    if (delegate.getClass() != spinnerDelegateClass) {
                        delegateField.set(datePicker, null);
                        datePicker.removeAllViews();

                        final Constructor spinnerDelegateConstructor =
                                spinnerDelegateClass.getDeclaredConstructor(
                                        DatePicker.class,
                                        Context.class,
                                        AttributeSet.class,
                                        int.class,
                                        int.class
                                );
                        spinnerDelegateConstructor.setAccessible(true);

                        final Object spinnerDelegate = spinnerDelegateConstructor.newInstance(
                                datePicker,
                                context,
                                null,
                                android.R.attr.datePickerStyle,
                                0
                        );
                        delegateField.set(datePicker, spinnerDelegate);

                        datePicker.init(year, monthOfYear, dayOfMonth, this);
                        datePicker.setCalendarViewShown(false);
                        datePicker.setSpinnersShown(true);
                    }
                } catch (Exception e) { /* Do nothing */ }
            }
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
