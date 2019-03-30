package com.dsquare.sabithaIndrareddy.activities;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.adapter.ExpandableListAdapter;
import com.dsquare.sabithaIndrareddy.fragments.AboutFragment;
import com.dsquare.sabithaIndrareddy.fragments.AllFragment;
import com.dsquare.sabithaIndrareddy.fragments.BestPractice;
import com.dsquare.sabithaIndrareddy.fragments.EventFragment;
import com.dsquare.sabithaIndrareddy.fragments.GovernorsFragment;
import com.dsquare.sabithaIndrareddy.fragments.IncFragment;
import com.dsquare.sabithaIndrareddy.fragments.MainEventFragment;
import com.dsquare.sabithaIndrareddy.fragments.MainTicketsFragment;
import com.dsquare.sabithaIndrareddy.fragments.PollingBoothFragment;
import com.dsquare.sabithaIndrareddy.fragments.PollsFragments;
import com.dsquare.sabithaIndrareddy.fragments.PublicationsFragment;
import com.dsquare.sabithaIndrareddy.fragments.RajNivasBookFragment;
import com.dsquare.sabithaIndrareddy.fragments.ShakthiEnrollmentFragment;
import com.dsquare.sabithaIndrareddy.fragments.ShowYourObservationsFragment;
import com.dsquare.sabithaIndrareddy.fragments.SocialConnectFragment;
import com.dsquare.sabithaIndrareddy.fragments.TeamRajNivas;
import com.dsquare.sabithaIndrareddy.fragments.UpdateProfileFragment;
import com.dsquare.sabithaIndrareddy.fragments.VolunteerFragment;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 11-04-2018.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener,UpdateProfileFragment.CallBackMethod
{
    private LinearLayout feedsLayout,tickets,polls,account;
    private ExpandableListAdapter mAdapter;
    private ExpandableListView expandableList;
    private List<String> listDataHeader;
    private NavigationView navigationView;
    HashMap<String, List<String>> listDataChild;
    private DrawerLayout mDrawerLayout;
    private List<String> langTypeList = new ArrayList<>();
    private Toolbar toolbar;
    private TextView langueText,feedimggray,ticketgray,pollsgray,eventsgray;
    private ImageView moveImage,feedimg,ticketimg,pollsimg,eventsimg;
    private Button profileupdate;
    private Fragment fragment;
    private ImageView web,call,menu,web1,live;
    private FragmentManager fragmentManager ;
    private TextView title;
    View view_Group;
    boolean doubleBackToExitPressedOnce = false;
    private boolean isHome = true;
    private String deviceId;
    private String type="";
    private CommonFunction com;


    private CommonSharePrefrences comshare;
    public static int[] icon ={ R.drawable.ic_move_icon,R.drawable.ic_move_icon,R.drawable.ic_move_icon,R.drawable.ic_move_icon,R.drawable.ic_move_icon,R.drawable.ic_move_icon,R.drawable.ic_move_icon,
            R.drawable.ic_move_icon,R.drawable.ic_move_icon,R.drawable.ic_move_icon,R.drawable.ic_move_icon,R.drawable.ic_move_icon};
    public static int[]  icon1 = {R.drawable.ic_about_kiranbedi_icon, R.drawable.ic_shakthi_enroll_icon,R.drawable.ic_share_your_ideas_icons,R.drawable.ic_congress_icon,R.drawable.ic_know_your_polling_booth, R.drawable.ic_volunteer_icon,
              R.drawable.ic_social_connect_icon,R.drawable.ic_volunteer_icon,
            R.drawable.ic_share_app_icon, R.drawable.ic_signout_icon,R.drawable.ic_signout_icon};
   // public static int[] icon2 = {R.drawable.ic_about_kiranbedi_icon,R.drawable.ic_past_governors_icon,R.drawable.ic_visit_raj_nivas_icon};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        com = new CommonFunction(this);

        feedsLayout = (LinearLayout)findViewById(R.id.feed_layout);
        tickets = (LinearLayout)findViewById(R.id.tickets_layout);
        polls = (LinearLayout)findViewById(R.id.polls_layout);
        account = (LinearLayout)findViewById(R.id.account_layout);
        feedimggray = (TextView)findViewById(R.id.tv_feeds);
        ticketgray = (TextView)findViewById(R.id.tv_tickets);
        pollsgray = (TextView)findViewById(R.id.tv_polls);
        eventsgray =(TextView)findViewById(R.id.tv_account);

        feedimg = (ImageView) findViewById(R.id.iv_feeds);
        ticketimg = (ImageView) findViewById(R.id.iv_tickets);
        pollsimg = (ImageView) findViewById(R.id.iv_polls);
        eventsimg =(ImageView) findViewById(R.id.iv_account);

        title =(TextView)findViewById(R.id.lang_text);
        web = (ImageView)findViewById(R.id.web_icon);
        web1 = (ImageView)findViewById(R.id.web_icon2);
        call = (ImageView)findViewById(R.id.call);
        menu = (ImageView)findViewById(R.id.menu);
        live = (ImageView)findViewById(R.id.live_icon);
        comshare = CommonSharePrefrences.getInstance(this);

        final Intent intent = getIntent();
        if(intent.getStringExtra("type")!=null)
        {
            type = intent.getStringExtra("type");
        }

        feedsLayout.setOnClickListener(this);
        tickets.setOnClickListener(this);
        polls.setOnClickListener(this);
        account.setOnClickListener(this);
        

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setItemIconTintList(null);

        expandableList = (ExpandableListView) findViewById(R.id.navigationmenu);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        View view = navigationView.inflateHeaderView(R.layout.nav_header_main);

        fragmentManager = getSupportFragmentManager();


        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        // getting device Id
        deviceId = telephonyManager.getDeviceId();



     //   profileupdate = (Button)view.findViewById(R.id.settings);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

//        profileupdate.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(getApplicationContext(),ProfileUpdateActivity.class);
//                startActivity(intent);
//
//            }
//        });

        web.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://rajnivas.py.gov.in/"));
                startActivity(browserIntent);
            }
        });

        web1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://lgpgrs.py.gov.in/grievanceregister.aspx"));
                startActivity(browserIntent);
            }
        });

        call.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                   Intent intent2 = new Intent(HomeActivity.this,NotificationActivity.class);
                   startActivity(intent2);
             //   isContinueasGuestAlert();
            }
        });
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent1 = new Intent(HomeActivity.this,LiveActivity.class);
                startActivity(intent1);

            }
        });

        getupdate();

        ValidateUser();

        initializeViews();
    }

    private void initializeViews()
    {

        prepareListData();
        mAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expandableList.setAdapter(mAdapter);

        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l)
            {

               switch (i)
               {
                   case 0:
                       mDrawerLayout.closeDrawers();

                       feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                       feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                       pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                       pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                       ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                       eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                       fragment = new AboutFragment();
                       Changing_Fragment(fragmentManager, fragment,"About Sabitha IndraReddy");
                       break;
                   case 1:
                       mDrawerLayout.closeDrawers();
                       feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                       feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                       pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                       pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                       ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                       eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                       fragment = new ShakthiEnrollmentFragment();
                       Changing_Fragment(fragmentManager, fragment,"Shakthi Enrollment");
                       break;
//                   case 1:
//                       mDrawerLayout.closeDrawers();
//
//                       feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon);
//                       feedimggray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                       pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
//                       pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
//                       ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
//                       ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
//                       eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
//                       eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));
//
//
//                       fragment = new AllFragment();
//                       Changing_Fragment(fragmentManager, fragment,"News Feeds");
//                       break;
//                   case 2:
//                       mDrawerLayout.closeDrawers();
//                       if(!comshare.getUserName().equals(""))
//                   {
//                      // if (!comshare.getUserEmail().equals("")) {
//
//                           feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
//                           feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
//                           pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
//                           pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
//                           ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon);
//                           ticketgray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                           eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
//                           eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));
//
//                           fragment = new MainTicketsFragment();
//                           Changing_Fragment(fragmentManager, fragment, "Grievances");
//                       }
//                       else
//                       {
//                           isContinueasGuestAlert1();
//
//                       }
//
//                       break;
//                   case 3:
//                       mDrawerLayout.closeDrawers();
//
//                       feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
//                       feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
//                       pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon);
//                       pollsgray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                       ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
//                       ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
//                       eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
//                       eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));
//
//
//
//                       fragment = new PollsFragments();
//                       Changing_Fragment(fragmentManager, fragment,"Survey");
//
//
//                       break;
//                   case 4:
//                       mDrawerLayout.closeDrawers();
//
//                       feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
//                       feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
//                       pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
//                       pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
//                       ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
//                       ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
//                       eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon);
//                       eventsgray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//
//                       fragment = new EventFragment();
//                       Changing_Fragment(fragmentManager, fragment,"Events");
//
//                       break;
//                   case 2:
//                       mDrawerLayout.closeDrawers();
//                       fragment = new ShakthiEnrollmentFragment();
//                       Changing_Fragment(fragmentManager, fragment,"Shakthi Enrollment");
//                       break;
                   case 2:
                       mDrawerLayout.closeDrawers();
                       if(!comshare.getUserName().equals("")) {
                           feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                           feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                           pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                           pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                           ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                           ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                           eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                           eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                           fragment = new ShowYourObservationsFragment();
                           Changing_Fragment(fragmentManager, fragment, "Share your Ideas");
                       }
                       else {
                           feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                           feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                           pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                           pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                           ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                           ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                           eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                           eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                           fragment = new ShowYourObservationsFragment();
                           Changing_Fragment(fragmentManager, fragment, "Share your Ideas");
                           com.type = "1";
                           isContinueasGuestAlert1();
                       }
                           break;
                   case 3:
                       mDrawerLayout.closeDrawers();

                       feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                       feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                       pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                       pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                       ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                       eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                       fragment = new IncFragment();
                       Changing_Fragment(fragmentManager, fragment,"History of Congress");
                       break;
                   case 4:
                       mDrawerLayout.closeDrawers();

                       feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                       feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                       pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                       pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                       ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                       eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                       fragment = new PollingBoothFragment();
                       Changing_Fragment(fragmentManager, fragment,"Know Your Polling Booth");
//                       Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://rajnivas.py.gov.in/"));
//                       startActivity(browserIntent);
                       break;

//                   case 5:
//                       mDrawerLayout.closeDrawers();
//
//                       feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
//                       feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
//                       pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
//                       pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
//                       ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
//                       ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
//                       eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
//                       eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));
//
//                       fragment= new RajNivasVisitorFormFragment();
//                       Changing_Fragment(fragmentManager,fragment,"Visit Raj Nivas");
//                       break;
                   case 5:
                       mDrawerLayout.closeDrawers();

                       if(!comshare.getUserName().equals(""))
                       {

                           feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                           feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                           pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                           pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                           ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                           ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                           eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                           eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                           fragment = new VolunteerFragment();
                           Changing_Fragment(fragmentManager, fragment, "Volunteer");
                       }
                       else
                       {
                           feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                           feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                           pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                           pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                           ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                           ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                           eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                           eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                           fragment = new VolunteerFragment();
                           com.type = "2";
                           Changing_Fragment(fragmentManager, fragment, "Volunteer");
                           isContinueasGuestAlert1();
                       }
                       break;



                   case 6:
                       mDrawerLayout.closeDrawers();

                       feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                       feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                       pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                       pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                       ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                       eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                       fragment = new SocialConnectFragment();
                       Changing_Fragment(fragmentManager, fragment,"Social Connect");

                       break;
                   case 7:

                       mDrawerLayout.closeDrawers();

                       feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                       feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                       pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                       pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                       ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                       eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                       com.type="0";
                       fragment = new UpdateProfileFragment();
                       Changing_Fragment(fragmentManager, fragment,"Update Profile");
                       break;

                   case 8:

                       mDrawerLayout.closeDrawers();
                       inviteFriends("https://play.google.com/store/apps/details?id=com.dsquare.sabithaIndrareddy");
                       break;
  //                 case 11:


   //                    mDrawerLayout.closeDrawers();
   //                    logoutAlert();
    //                   break;
//                  case 10:
//                      mDrawerLayout.closeDrawers();
//                      fragment = new PrivacyPolicyFragment();
//                       Changing_Fragment(fragmentManager, fragment,"PrivacyPolicy");
//                      break;
                case 9:
                   mDrawerLayout.closeDrawers();
                                  logoutAlert();
                                      break;


              }



                //Log.d("DEBUG", "heading clicked");
                return false;
            }
        });

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView,
                                        View view,
                                        int groupPosition,
                                        int childPosition, long id) {


                if (groupPosition == 7 && childPosition == 0)
                {
                    feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                    feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                    pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                    pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                    ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                    eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                    fragment = new AboutFragment();
                    Changing_Fragment(fragmentManager, fragment,"About Sabith Indra reddy");
                }
                else if(groupPosition == 7 && childPosition == 1)
                {
                    feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                    feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                    pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                    pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                    ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                    eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                    fragment = new GovernorsFragment();
                    Changing_Fragment(fragmentManager, fragment,"Past Governors");
                }
                else if(groupPosition == 7 && childPosition == 2)
                {
                    feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                    feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                    pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                    pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                    ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                    eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                    fragment = new TeamRajNivas();
                    Changing_Fragment(fragmentManager, fragment,"Team Raj Nivas");

                }
                else if(groupPosition == 8 && childPosition == 0)
                {
                    feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                    feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                    pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                    pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                    ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                    eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                    fragment = new RajNivasBookFragment();
                    Changing_Fragment(fragmentManager, fragment,"Raj Nivas Book");
                }
                else if(groupPosition == 8 && childPosition == 1)
                {
                    feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                    feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                    pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                    pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                    ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                    eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                    fragment = new BestPractice();
                    Changing_Fragment(fragmentManager, fragment,"Best Practice");
                }
                else if(groupPosition == 8 && childPosition == 2)
                {
                    feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                    feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                    pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                    pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                    ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                    eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                    fragment = new PublicationsFragment();
                    Changing_Fragment(fragmentManager, fragment,"Publications");
                }


                expandableListView.collapseGroup(7);

                view.setSelected(true);
                if (view_Group != null) {
                    view_Group.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                view_Group = view;
//                view_Group.setBackgroundColor(Color.parseColor("#DDDDDD"));
                mDrawerLayout.closeDrawers();
                return false;
            }
        });

        if(type.equals("2"))
        {
            feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
            feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
            pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon);
            pollsgray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
            ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
            eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
            eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));



            fragment = new PollsFragments();
            Changing_Fragment(fragmentManager, fragment,"Survey");
        }
        else if(type.equals("3"))
        {
            feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
            feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
            pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
            pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
            ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
            ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
            eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon);
            eventsgray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            fragment = new MainEventFragment();
            Changing_Fragment(fragmentManager, fragment,"Events");
        }
        else
        {
            feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon);
            feedimggray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
            pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
            ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
            ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
            eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
            eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

            fragment = new AllFragment();
            Changing_Fragment(fragmentManager, fragment,"News Feeds");
        }

    }




    public void drawerOpen()
    {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }


    /*invite friends*/
    private void inviteFriends(String invitationMessage)
    {
        List<Intent> targetShareIntents = new ArrayList<Intent>();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, invitationMessage);
        sendIntent.setType("text/plain");
        PackageManager pm = getPackageManager();
        Resources resources = getResources();
        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
        List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
        for (int i = 0; i < resInfo.size(); i++)
        {
            // Extract the label, append it, and repackage it in a LabeledIntent
            ResolveInfo ri = resInfo.get(i);
            String packageName = ri.activityInfo.packageName;
            Log.d("Package Name", packageName);

            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, invitationMessage);
            intent.setPackage(packageName);
            targetShareIntents.add(intent);

        }
        if (!targetShareIntents.isEmpty())
        {
            System.out.println("Have Intent");
            Intent chooserIntent = Intent.createChooser(targetShareIntents.remove(0), "Choose app to invite friends");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toArray(new Parcelable[]{}));
            startActivity(chooserIntent);
        }

    }

    private void getupdate()
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.updateDeviceToken(comshare.getGcm(), deviceId,comshare.getUserId(),"2");

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }



    @Override
    public void onClick(View v)
    {
        Fragment fragment;
        switch (v.getId())
        {
            case R.id.feed_layout:

                feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon);
                feedimggray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));


                fragment = new AllFragment();
                Changing_Fragment(fragmentManager, fragment,"News Feeds");
                break;
            case R.id.tickets_layout:
                if(!comshare.getUserName().equals("")) {

                    feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                    feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                    pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                    pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon);
                    ticketgray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                    eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                    fragment = new MainTicketsFragment();
                    Changing_Fragment(fragmentManager, fragment, "Grievances");
                }
                else
                {
                    feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                    feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                    pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                    pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon);
                    ticketgray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                    eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                    fragment = new MainTicketsFragment();
                    Changing_Fragment(fragmentManager, fragment, "Grievances");
                    com.type = "3";
                    isContinueasGuestAlert1();
                }
                break;
            case R.id.polls_layout:

                feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon);
                pollsgray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));



                fragment = new PollsFragments();
                Changing_Fragment(fragmentManager, fragment,"Survey");
                break;
            case R.id.account_layout:

                feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon);
                eventsgray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                fragment = new MainEventFragment();
                Changing_Fragment(fragmentManager, fragment,"Events");
                break;

        }

    }

    public boolean Changing_Fragment(FragmentManager fragmentManager, Fragment fragment,String name)
    {
        try {
            title.setText(name);
            String fragmentTagName = fragment.getClass().getName();
            System.out.println("Fragment Tag Name : " + fragmentTagName);

            if(fragmentTagName.equals("com.dsquare.sabithaIndrareddy.fragments.AllFragment")||fragmentTagName.equals("com.dsquare.sabithaIndrareddy.fragments.PollsFragments")||fragmentTagName.equals("com.dsquare.sabithaIndrareddy.fragments.EventFragment")||fragmentTagName.equals("com.dsquare.sabithaIndrareddy.fragments.MainTicketsFragment"))
            {

                live.setVisibility(View.VISIBLE);

            }
            else
            {
                live.setVisibility(View.GONE);
            }





            FragmentManager manager1 = fragmentManager;
            boolean fragmentPopped = manager1.popBackStackImmediate(
                    fragmentTagName, 0);

            FragmentTransaction ft = manager1.beginTransaction();
            ft.replace(R.id.fragment_main, fragment, fragmentTagName);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(null);
            ft.commit();

            return true;

        } catch (Exception e)
        {
//            shouldLoadFragment = true;
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public void onBackPressed() {
        // TODO BACK KEY PRESSED ACTION



        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            try {
                Fragment fragment = fragmentManager
                        .findFragmentById(R.id.fragment_main);
                System.out.println("Current FragmentName : "
                        + fragment.getClass().getName());
                String strCurrentFragment = fragment.getClass().getName().toString();



                if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                    Log.d("toeknn","3");
                    if (strCurrentFragment.equalsIgnoreCase(new AllFragment().getClass().getName())) {
                        if (isHome) {
                            if (doubleBackToExitPressedOnce) {
                                finishAffinity();
                                return;
                            }

                            this.doubleBackToExitPressedOnce = true;
                            Toast.makeText(this, "Please again to exit", Toast.LENGTH_SHORT).show();

                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    doubleBackToExitPressedOnce = false;
                                }
                            }, 2000);
                            return;
                        }
                    } else {
                        feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon);
                        feedimggray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                        pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                        ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                        ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                        eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                        eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                        Changing_Fragment(fragmentManager, new AllFragment() , "News Feeds");
                    }
                } else if (strCurrentFragment.equalsIgnoreCase(new AllFragment().getClass().getName())) {
                    Log.d("toeknn","1");
                    finishAffinity();
                }
                else
                {
                    feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon);
                    feedimggray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                    pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                    ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                    eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                    Changing_Fragment(fragmentManager, new AllFragment() , "News Feeds");
                   //  super.onBackPressed();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                super.onBackPressed();
            }
        }

    }

    private void prepareListData()
    {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding data header
        //listDataHeader.add("Home");
        //listDataHeader.add("Complaints");
        //listDataHeader.add("Polls");
        //  listDataHeader.add("DKS schedules");
       // listDataHeader.add("Events");
        listDataHeader.add("About Sabitha Indra Reddy");
//        listDataHeader.add("News Feeds");
//        listDataHeader.add("Grievances");
//        listDataHeader.add("Survey");
//        listDataHeader.add("Events");
        listDataHeader.add("Shakthi Enrollment");
        listDataHeader.add("Share your Ideas");
        listDataHeader.add("History of Congress");
        listDataHeader.add("Know your Polling booth");
        //listDataHeader.add("Visit Raj Nivas");
        listDataHeader.add("Volunteer");

       // listDataHeader.add("Books & Publications");
        listDataHeader.add("Social Connect");
        listDataHeader.add("Update Profile");
        listDataHeader.add("Share the App");
       // listDataHeader.add("PrivacyPolicy");
        if(!comshare.getToken().equals(""))
        {
            listDataHeader.add("Log Out");
        }




//        List<String> heading1 = new ArrayList<>();
//        heading1.add("About LT. Governor");
//        heading1.add("Past Governors");
//        heading1.add("Team Raj Nivas");
     //   listDataChild.put(listDataHeader.get(7),heading1);

        List<String> heading2 = new ArrayList<>();
      //  heading2.add("Raj Nivas Book");
        //heading2.add("Best Practice");
       // heading2.add("Publications");
       // listDataChild.put(listDataHeader.get(8),heading2);


    }

    public void isContinueasGuestAlert() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Call Us ");
       // builder.setMessage("Please call");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                callIntent.setData(Uri.parse("tel:" + "9703757222"));
                startActivity(callIntent);
            }
        });
         builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
         {
        @Override
        public void onClick(DialogInterface dialog, int id)
        {
         dialog.dismiss();
        }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }
    public void logoutAlert()

    {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                comshare.clearData();
                Intent intent = new Intent(getApplicationContext() , LoginMobileActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }


    private void ValidateUser()
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.getValidateUser(comshare.getUserId());

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {

                if(response.code() == 200 )
                {
                    JSONObject object = null;
                    try {
                        object = new JSONObject(response.body().string());
                        parseValidateUser(object);
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

            }
        });
    }

    private void parseValidateUser(JSONObject object)
    {
        try {
            String status = object.getString("status");
            if(status.equals("0"))
            {
                logoutAlert1();
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


    public void logoutAlert1()

    {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage("This account is not active Please Login");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                comshare.clearData();
                Intent intent = new Intent(getApplicationContext() , LoginMobileActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }
    public void isContinueasGuestAlert1() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
      //  builder.setTitle("Continue as Guest");
        builder.setMessage("Please  Update Profile");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
//                Intent intent = new Intent(this, .class);
//                startActivity(intent);
                fragment = new UpdateProfileFragment();
                Changing_Fragment(fragmentManager, fragment,"Update Profile");
            }
        });
//        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.dismiss();
//            }
//        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void changeMethod() {
        switch (com.type) {
            case "1":
                feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                fragment = new ShowYourObservationsFragment();
                Changing_Fragment(fragmentManager, fragment, "Share your Ideas");
                com.type = "0";
                break;
            case "2":
                feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                fragment = new VolunteerFragment();
                com.type = "0";
                Changing_Fragment(fragmentManager, fragment, "Volunteer");
                break;
            case "3":
                feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon_gray);
                feedimggray.setTextColor(getResources().getColor(R.color.gray_litee));
                pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon);
                ticketgray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));

                fragment = new MainTicketsFragment();
                Changing_Fragment(fragmentManager, fragment, "Grievances");
                com.type = "0";
                break;
            case "0":
                feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon);
                feedimggray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));


                fragment = new AllFragment();
                Changing_Fragment(fragmentManager, fragment,"News Feeds");
                break;
            default:
                feedimg.setImageResource(R.drawable.ic_bottom_bar_feeds_icon);
                feedimggray.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                pollsimg.setImageResource(R.drawable.ic_bottom_bar_polls_icon_gray);
                pollsgray.setTextColor(getResources().getColor(R.color.gray_litee));
                ticketimg.setImageResource(R.drawable.ic_bottom_bar_ticket_icon_gray);
                ticketgray.setTextColor(getResources().getColor(R.color.gray_litee));
                eventsimg.setImageResource(R.drawable.ic_bottom_bar_events_icon_gray);
                eventsgray.setTextColor(getResources().getColor(R.color.gray_litee));


                fragment = new AllFragment();
                Changing_Fragment(fragmentManager, fragment,"News Feeds");
                break;

        }
    }
}



