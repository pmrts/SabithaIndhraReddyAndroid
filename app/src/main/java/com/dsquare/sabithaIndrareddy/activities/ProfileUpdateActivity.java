package com.dsquare.sabithaIndrareddy.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.fragments.RegisterFragment;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Calendar;

/**
 * Created by D square on 17-04-2018.
 */

public class ProfileUpdateActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener  {
    private TextView dob;
    private String dobString="";
    private FixedHoloDatePickerDialog newFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        intilizeViews();

    }

    private void intilizeViews()
    {
       // isContinueasGuestAlert();
        dob = (TextView)findViewById(R.id.registration_dob);

        dob.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        if(dobString.equals(""))
        {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            newFragment = new FixedHoloDatePickerDialog(this , this ,year , month , day);
            newFragment.setTitle("");
            newFragment.show();
        }
        else
        {
            int year = Integer.valueOf(dobString.substring(0,4));
            int month = Integer.valueOf(dobString.substring(5,7));
            int day = Integer.valueOf(dobString.substring(8,10));
            newFragment = new FixedHoloDatePickerDialog(this , this ,year , month-1 , day);
            newFragment.setTitle("");
            newFragment.show();
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

    public void isContinueasGuestAlert() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Continue as Guest");
        builder.setMessage("Please Login");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                Intent intent = new Intent(ProfileUpdateActivity.this,LoginMobileActivity.class);
                startActivity(intent);
            }
        });
      //  builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
           // @Override
           // public void onClick(DialogInterface dialog, int id) {
              //  dialog.dismiss();
           // }
        //});
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }
}

