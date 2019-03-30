package com.dsquare.sabithaIndrareddy.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.pojos.createticketlist.Result;

import java.util.List;

/**
 * Created by D square on 06-11-2018.
 */

public class ViewPetitionTicketAdapter extends RecyclerView.Adapter<ViewPetitionTicketAdapter.ViewHolder>
{
    private Context context;
    private List<Result> viewpetitionList;


    public ViewPetitionTicketAdapter(Context context, List<Result> viewpetitionList)
    {
        this.context = context;
        this.viewpetitionList =viewpetitionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ticket,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Result data = viewpetitionList.get(position);

        if((data.getStatus().equals("0")))
                    {
                        holder.details_status.setText("Pending");
                    }
                    else if((data.getStatus().equals("1")))
        {
            holder.details_status.setText("InProgress");
        }
        else if(data.getStatus().equals("2"))
        {
            holder.details_status.setText("Completed");
        }

        holder.details_grievance.setText(data.getId());
       // holder.details_status.setText(data.getStatus());

    }

    @Override
    public int getItemCount()
    {
        return viewpetitionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView details_grievance,details_status;

        public ViewHolder(View itemView) {
            super(itemView);
            details_grievance = (TextView)itemView.findViewById(R.id.grievanceId);
            details_status = (TextView)itemView.findViewById(R.id.grievanceStatus);

        }
    }

}
