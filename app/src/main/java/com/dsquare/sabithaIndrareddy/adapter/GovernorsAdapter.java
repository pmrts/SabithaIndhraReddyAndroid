package com.dsquare.sabithaIndrareddy.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.pojos.governorsResponse.Datum;
import com.dsquare.sabithaIndrareddy.pojos.governorsResponse.GovernorsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by D square on 18-05-2018.
 */

public class GovernorsAdapter extends RecyclerView.Adapter<GovernorsAdapter.ViewHolder>
{
    private Context context;
    private List<Datum> governorslist;

    public GovernorsAdapter(Context context, List<Datum> governorslist)
    {
        this.context = context;
        this.governorslist = governorslist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpater_governors,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Datum response = governorslist.get(position);
        holder.title.setText(response.getName());
        holder.date.setText(response.getYear());


        if(position!=0)
        {
            holder.text.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return governorslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView title,date,text;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.image);
            title = (TextView)itemView.findViewById(R.id.title);
            date = (TextView)itemView.findViewById(R.id.date);
            text = (TextView)itemView.findViewById(R.id.text);

        }
    }
}
