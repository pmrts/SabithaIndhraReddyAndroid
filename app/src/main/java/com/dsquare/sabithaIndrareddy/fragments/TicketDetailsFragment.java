package com.dsquare.sabithaIndrareddy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.adapter.ViewPetitionTicketAdapter;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.createticketlist.Result;
import com.dsquare.sabithaIndrareddy.pojos.createticketlist.ViewPetitionResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 12-04-2018.
 */

public class TicketDetailsFragment extends Fragment
{
    private TextView grivence_code,status,comments,noData;
    private LinearLayout layout;
    private RecyclerView ticketrecycler;
    private SwipeRefreshLayout refreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private ViewPetitionTicketAdapter ticketDetailAdapter;
    public FragmentManager fragmentManager;
    private List<Result>viewpetitionList = new ArrayList();
    public ViewPetitionTicketAdapter viewPetitionTicketAdapter;
    private CommonFunction com;
    private CommonSharePrefrences comShare;
    private String petitioncode = "";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket_recyclerview,container,false);
        intializeViews(view);
        return view;

    }

    private void intializeViews(View view)
    {
        comShare = new CommonSharePrefrences(getActivity());

        refreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe);
        ticketrecycler = (RecyclerView)view.findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ticketrecycler.setLayoutManager(linearLayoutManager);
        ticketDetailAdapter = new ViewPetitionTicketAdapter(getActivity(),viewpetitionList);
        ticketrecycler.setAdapter(ticketDetailAdapter);
        com = new CommonFunction(getContext());

        refreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {

                refreshLayout.setRefreshing(false);
            }
        });

        refreshLayout.setRefreshing(true);
       // getEvents();
        ticketViewPetition();
    }


    private void ticketViewPetition()
    {


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ViewPetitionResponse> call = apiService.getViewPetitionList(comShare.getUserId());
        call.enqueue(new Callback<ViewPetitionResponse>()
        {
            @Override
            public void onResponse(Call<ViewPetitionResponse> call, Response<ViewPetitionResponse> response)
            {
                refreshLayout.setRefreshing(false);
                if (response.code() == 200) {
                    parseViewStatusResponse(response);
                    com.dismissProgressDialogue();
                }

            }

            @Override
            public void onFailure(Call<ViewPetitionResponse> call, Throwable t)
            {
                refreshLayout.setRefreshing(false);

            }
        });


    }

    private void parseViewStatusResponse(Response<ViewPetitionResponse> response)
    {
        if(response.body().getStatus()==1)
        {
           viewpetitionList.addAll(response.body().getResult());

            ticketDetailAdapter.notifyDataSetChanged();


        }
    }


//        petitioncode = getArguments().getString("code");
//
//        com = new CommonFunction(getContext());
//        fragmentManager = getFragmentManager();
//        grivence_code = (TextView)view.findViewById(R.id.details_grievance);
//        status = (TextView)view.findViewById(R.id.details_status);
//        comments = (TextView)view.findViewById(R.id.details_comments);
//        noData = (TextView)view.findViewById(R.id.tv_nodata);
//        layout = (LinearLayout) view.findViewById(R.id.layout);

//        viewPetitionCode();
//
//
//    }
//
//    private void viewPetitionCode()
//    {
//        com.showProgressDialogue();
//
//       ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//
//       Call<ResponseBody> call = apiInterface.getViewPetitionDetails(petitioncode);
//
//       call.enqueue(new Callback<ResponseBody>()
//       {
//           @Override
//           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
//           {
//               com.dismissProgressDialogue();
//               if(response.code() == 200 )
//               {
//                   JSONObject object = null;
//                   try {
//                       object = new JSONObject(response.body().string());
//                   } catch (JSONException e) {
//                       e.printStackTrace();
//                   } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//                   parseResponse(object);
//               }
//           }
//
//           @Override
//           public void onFailure(Call<ResponseBody> call, Throwable t)
//           {
//               com.dismissProgressDialogue();
//           }
//       });
//
//
//    }
//
//    private void parseResponse(JSONObject object)
//    {
//
//            try {
//
//                if(object.length()!=0)
//                {
//                    noData.setVisibility(View.GONE);
//                    layout.setVisibility(View.VISIBLE);
//                   // JSONObject object1 = object.getJSONObject("0");
//                  //  grivence_code.setText(object1.getString("Grievance_code"));
//                    status.setText(object.getString("petition_status"));
//                    if((object.getString("petition_status").equals("0")))
//                    {
//                        status.setText("Pending");
//                    }
//                    else if ((object.getString("petition_status").equals("1")))
//                    {
//                        status.setText("Inprogress");
//                    }
//                    else if((object.getString("petition_status").equals("2")))
//                    {
//                        status.setText("Completed");
//                }
//                else
//                    {
//                        status.setText("None");
//                    }
//                  //  comments.setText(object1.getString("Closing_comments"));
//                }
//                else
//                {
//                    layout.setVisibility(View.GONE);
//                    noData.setVisibility(View.VISIBLE);
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//    }


}
