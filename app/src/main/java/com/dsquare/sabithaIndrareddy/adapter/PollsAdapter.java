package com.dsquare.sabithaIndrareddy.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.LoginMobileActivity;
import com.dsquare.sabithaIndrareddy.activities.PollsDetailActivity;
import com.dsquare.sabithaIndrareddy.activities.QuestionsActivity;
import com.dsquare.sabithaIndrareddy.pojos.suveryResponse.SurveyListResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import java.util.List;

/**
 * Created by D square on 12-04-2018.
 */

public class PollsAdapter extends RecyclerView.Adapter<PollsAdapter.ViewHolder> implements View.OnClickListener
{

    private Context context;
    private List<SurveyListResponse> surveyList;
    private CommonFunction com;
    private CommonSharePrefrences comShare;

    public PollsAdapter(Context context, List<SurveyListResponse> surveyList)
    {
        this.context=context;
        this.surveyList = surveyList;
        com = new CommonFunction(context);
        comShare = CommonSharePrefrences.getInstance(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_polls,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.takeSurvey.setOnClickListener(this);
        viewHolder.takeSurvey.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.title.setText(surveyList.get(position).getSurveyMainQuestion());
        holder.date.setText(surveyList.get(position).getSurveyDate());
        holder.id.setText(surveyList.get(position).getSurveyUniqueDisplayId());
    }

    @Override
    public int getItemCount() {
        return surveyList.size();
    }

    @Override
    public void onClick(View v)
    {
        ViewHolder holder = (ViewHolder)v.getTag();
        int position = holder.getAdapterPosition();

        if(comShare.getToken().equals(""))
        {
            isContinueasGuestAlert();
        }
        else
        {
            Intent intent = new Intent (context,QuestionsActivity.class);
            intent.putExtra("id",surveyList.get(position).getSurveyId());
            context.startActivity(intent);
        }




        //com.quesListPojoList=surveyList.get(position).getQuestions();

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
       private Button takeSurvey;
       private TextView title,date,id;

        public ViewHolder(View itemView)
        {
            super(itemView);
            takeSurvey = (Button)itemView.findViewById(R.id.take_survey);
            title = (TextView) itemView.findViewById(R.id.polls_title);
            date = (TextView) itemView.findViewById(R.id.tv_ticket_date);
            id = (TextView) itemView.findViewById(R.id.tv_ticket_id);
        }
    }

    public void isContinueasGuestAlert()
    {

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Please Login");
       // builder.setMessage("Please Login");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                Intent intent = new Intent(context, LoginMobileActivity.class);
                context.startActivity(intent);

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
}
