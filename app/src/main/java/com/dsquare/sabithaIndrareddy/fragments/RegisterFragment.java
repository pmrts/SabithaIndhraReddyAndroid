package com.dsquare.sabithaIndrareddy.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.VerifyOtpActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Calendar;


public class RegisterFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private View view;
    private TextView dob;
    private Button signUp;
    private String addressString;
    private String gender="1";
    private static String dobString="";
    FixedHoloDatePickerDialog newFragment;
    private RadioGroup radioGroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_register,container,false);
        initializeViews();
        return view;
    }

    private void initializeViews() {
        dob = (TextView) view.findViewById(R.id.registration_dob);
        signUp = (Button) view.findViewById(R.id.signup_button);

        signUp.setOnClickListener(this);
        dob.setOnClickListener(this);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_sex);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int i)
            {
                if(i==R.id.radio_male)
                {
                    gender = "1";
                }
                else
                {
                    gender = "2";
                }

            }
        });

    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.signup_button:
                Intent intent = new Intent(getContext() , VerifyOtpActivity.class);
                startActivity(intent);
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
            dobString = year +"-"+"0"+(monthOfYear + 1)+"-"+dayOfMonth;
        }
        else if(dayOfMonth<10)
        {
            dob.setText((monthOfYear + 1)+"/0"+dayOfMonth + "/" + year);
            dobString = year +"-"+(monthOfYear + 1)+"-"+"0"+dayOfMonth;
        }
        else
        {
            dob.setText((monthOfYear + 1)+"/"+dayOfMonth + "/" + year);
            dobString = year +"-"+(monthOfYear + 1)+"-"+dayOfMonth;
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



