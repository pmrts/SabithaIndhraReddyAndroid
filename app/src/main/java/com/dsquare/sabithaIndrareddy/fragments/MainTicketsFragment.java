package com.dsquare.sabithaIndrareddy.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.LoginMobileActivity;
import com.dsquare.sabithaIndrareddy.activities.LoginRegisterActivity;


public class MainTicketsFragment extends Fragment implements View.OnClickListener
{

    public Button ticket, ticketcreate;
    public FragmentManager fragmentManager;
    private TextView ok,cancel;
    private EditText code;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ticket, container, false);
        intializeViews(view);
        return view;

    }


    private void intializeViews(View view)
    {
        ticket = (Button) view.findViewById(R.id.ticket_button);
        ticketcreate = (Button) view.findViewById(R.id.createticket_button);
//        ticket.setTextColor(Color.parseColor("#ffffff"));
//        ticketcreate.setTextColor(Color.parseColor("#162A47"));
          ticket.setOnClickListener(this);
          ticketcreate.setOnClickListener(this);


                fragmentManager = getFragmentManager();
                ticketcreate.setBackgroundResource(R.drawable.login_radius_fill);
                ticket.setBackgroundColor(Color.TRANSPARENT);
                ticket.setTextColor(Color.parseColor("#162A47"));
                ticketcreate.setTextColor(Color.parseColor("#ffffff"));

                Fragment fragment = new CreateTicketFragment();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

    }

    public void showPopUp()
    {
        final Dialog dialog = new Dialog(getContext());
        try {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.activity_petition_dilog);
            dialog.getWindow().setLayout(getWidth() - 40, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            ok = (TextView) dialog.findViewById(R.id.ok);
            cancel = (TextView) dialog.findViewById(R.id.cancel);
            code = (EditText) dialog.findViewById(R.id.et_code);

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!code.getText().toString().equals(""))
                    {
                        dialog.dismiss();
                        ticket.setBackgroundResource(R.drawable.login_radius_fill);
                        ticketcreate.setBackgroundColor(Color.TRANSPARENT);
                        ticket.setTextColor(Color.parseColor("#ffffff"));
                        ticketcreate.setTextColor(Color.parseColor("#162A47"));
                        Bundle args = new Bundle();
                        Fragment fragment = new TicketDetailsFragment();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        args.putString("code",code.getText().toString());
                        fragment.setArguments(args);
                        ft.replace(R.id.frame_layout, fragment);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.commit();
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Please add Petition code", Toast.LENGTH_SHORT).show();
                    }

                }

            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    dialog.dismiss();
                }
            });


            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

    public int getWidth() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screen_width = size.x;
        return screen_width;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ticket_button:
               // showPopUp();
                ticket.setBackgroundResource(R.drawable.login_radius_fill);
                ticketcreate.setBackgroundColor(Color.TRANSPARENT);
                ticketcreate.setTextColor(Color.parseColor("#162A47"));
                ticket.setTextColor(Color.parseColor("#ffffff"));

                Fragment fragment = new TicketDetailsFragment();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
            case R.id.createticket_button:
                ticketcreate.setBackgroundResource(R.drawable.login_radius_fill);
                ticket.setBackgroundColor(Color.TRANSPARENT);
                ticket.setTextColor(Color.parseColor("#162A47"));
                ticketcreate.setTextColor(Color.parseColor("#ffffff"));

                Fragment fragment1 = new CreateTicketFragment();
                FragmentTransaction ft1 = fragmentManager.beginTransaction();
                ft1.replace(R.id.frame_layout, fragment1);
                ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft1.commit();
                break;
        }

    }

    public void changeFragment() {
        ticket.setBackgroundResource(R.drawable.login_radius_fill);
        ticketcreate.setBackgroundColor(Color.TRANSPARENT);
        ticketcreate.setTextColor(Color.parseColor("#162A47"));
        ticket.setTextColor(Color.parseColor("#ffffff"));

        Fragment fragment = new TicketDetailsFragment();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }
}
