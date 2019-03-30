package com.dsquare.sabithaIndrareddy.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.pojos.EventsListResponse.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.iwf.photopicker.widget.TouchImageView;


/**
 * Created by D square on 13-04-2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Result> eventlist;

    public EventAdapter(Context context, List<Result> eventlist)
    {
        this.context=context;
        this.eventlist = eventlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_events,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.event_img.setOnClickListener(this);
        viewHolder.event_img.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Result data = eventlist.get(position);
        holder.title.setText(data.getEventName());
        holder.time.setText(data.getEventTime());
        holder.address.setText(data.getEventAddress());
        holder.date.setText(data.getEventDate());
        Picasso
                .with(context)
                .load(data.getEventImage())
                .placeholder(R.drawable.img_placeholder)
                .resize(300,300)
                .into(holder.event_img);

//
//        switch (position)
//        {
//            case 0:
//                holder.title.setText("Unique initiative of team GHMC");
//               // holder.description.setText("Earth Day - Shramdaan at Botanical Garden");
//                holder.time.setText("05:55 PM");
//                holder.address.setText("Unique initiative of team GHMC Online led by Mayor bonthurammohan Garu Request Hyderabad youngsters to avail");
//                holder.date.setText("30-06-2018");
//                Picasso
//                        .with(context)
//                        .load("https://pbs.twimg.com/media/Df39hnQWAAINkbu.jpg")
//                        .placeholder(R.drawable.img_placeholder)
//                        .resize(300,300)
//                        .into(holder.event_img);
//                break;
//            case 1:
//                holder.title.setText("Test9");
//               // holder.description.setText("\"Ask the HLG  Session-1 - Get answers about Puducherry using #LGOVPUDUCHERRY");
//                holder.time.setText("05:55 PM");
//                holder.address.setText("ARK chamberss,plot no madhapur,kavurihills,hyderabad");
//                holder.date.setText("21-04-2018");
//                Picasso
//                        .with(context)
//                        .load("http:\\/\\/ifuture.us\\/lt\\/images\\/eventimages\\/380707feed_img4.jpg")
//                        .placeholder(R.drawable.img_placeholder)
//                        .resize(300,300)
//                        .into(holder.event_img);
//                break;
//            case 2:
//                holder.title.setText("Test9");
//                //holder.description.setText("Rajnivas Leadership Series - A session with Dr. V. Candavelou, IAS, Commissioner-cum-Secretary and Chief Electoral Officer");
//                holder.time.setText("6PM");
//                holder.address.setText("ARK chamberss,plot no madhapur,kavurihills,hyderabad");
//                holder.date.setText("13-04-2018");
//                Picasso
//                        .with(context)
//                        .load("http:\\/\\/ifuture.us\\/lt\\/images\\/eventimages\\/380707feed_img4.jpg")
//                        .placeholder(R.drawable.img_placeholder)
//                        .resize(300,300)
//                        .into(holder.event_img);
//                break;
//            case 3:
//                holder.title.setText("Test9");
//               // holder.description.setText("Rajnivas Leadership Series - A session with Dr. V. Candavelou, IAS, Commissioner-cum-Secretary and Chief Electoral Officer");
//                holder.time.setText("6PM");
//                holder.address.setText("ARK chamberss,plot no madhapur,kavurihills,hyderabad");
//                holder.date.setText("13-04-2018");
//                Picasso
//                        .with(context)
//                        .load("http:\\/\\/ifuture.us\\/lt\\/images\\/eventimages\\/380707feed_img4.jpg")
//                        .placeholder(R.drawable.img_placeholder)
//                        .resize(300,300)
//                        .into(holder.event_img);
//                break;
//            case 4:
//                holder.title.setText("Test9");
//               // holder.description.setText("Revitalizing the Water bodies in Puducherry 22nd March 2018");
//                holder.time.setText("6PM");
//                holder.address.setText("ARK chamberss,plot no madhapur,kavurihills,hyderabad");
//                holder.date.setText("22-03-2018");
//                Picasso
//                        .with(context)
//                        .load("\"http:\\\\/\\\\/ifuture.us\\\\/lt\\\\/images\\\\/eventimages\\\\/380707feed_img4.jpg\" ")
//                        .placeholder(R.drawable.img_placeholder)
//                        .resize(300,300)
//                        .into(holder.event_img);
//                break;
//        }


    }

    @Override
    public int getItemCount()
    {
        return eventlist.size() ;
    }

    @Override
    public void onClick(View v)
    {
        ViewHolder holder = (ViewHolder)v.getTag();
        int position = holder.getAdapterPosition();

        switch (v.getId())
        {
            case R.id.event_img:
               showImage(eventlist.get(position).getEventImage());
                break;
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title,description,time,date,address;
        private ImageView event_img;
        public ViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            time = (TextView)itemView.findViewById(R.id.time);
            date = (TextView)itemView.findViewById(R.id.date);
            address = (TextView)itemView.findViewById(R.id.location);
            event_img = (ImageView)itemView.findViewById(R.id.event_img);
        }
    }


    public void showImage(String imageUri) {
        Dialog builder = new Dialog(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(R.layout.adapter_congress_imageview);


        TouchImageView imageView = (TouchImageView) builder.findViewById(R.id.image);


        Picasso
                .with(context)
                .load(imageUri)
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);

        builder.setCancelable(true);
        builder.show();
    }
}
