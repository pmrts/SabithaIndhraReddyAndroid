package com.dsquare.sabithaIndrareddy.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.squareup.picasso.Picasso;

/**
 * Created by D square on 24-04-2018.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private CommonFunction com;

    public VideoAdapter(Context context)
    {
        this.context=context;
        com = new CommonFunction(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_feeds,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.playButton.setOnClickListener(this);
        viewHolder.playButton.setTag(viewHolder);
        viewHolder.feed_img.setOnClickListener(this);
        viewHolder.feed_img.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);

        switch (position)
        {
            case 0:
                holder.title.setText("Dr. kiran Bedi inspires Skills to learn before 30 !!");
                holder.description.setText("Dr. kiran Bedi inspires Skills to learn before 30 !!");
                holder.date.setText("Feb 18, 2016");
                holder.type.setText("Video");
                try {
                    Picasso
                            .with(context)
                            .load("http://img.youtube.com/vi/NkOLkHlllrA/hqdefault.jpg")
                            .placeholder(R.drawable.img_placeholder)
                            .into(holder.feed_img);
                } catch(IllegalArgumentException ex)
                {
                    Log.wtf("Glide-tag", String.valueOf(holder.feed_img.getTag()));
                }
                break;
            case 1:
                holder.title.setText("Kiran Bedi: How I remade one of India's toughest prisons");
                holder.description.setText("Kiran Bedi: How I remade one of India's toughest prisons");
                holder.date.setText("Dec 13, 2010 ");
                holder.type.setText("Video");
                try {
                    Picasso
                            .with(context)
                            .load("http://img.youtube.com/vi/g_CSsL3it9Y/hqdefault.jpg")
                            .placeholder(R.drawable.img_placeholder)
                            .into(holder.feed_img);
                } catch(IllegalArgumentException ex)
                {
                    Log.wtf("Glide-tag", String.valueOf(holder.feed_img.getTag()));
                }
                break;
            case 2:
                holder.title.setText("Dr. Kiran Bedi, IPS - Exclusive Speech");
                holder.description.setText("Dr. Kiran Bedi, IPS - Exclusive Speech");
                holder.date.setText("Feb 18, 2016");
                holder.type.setText("Video");
                try {
                    Picasso
                            .with(context)
                            .load("http://img.youtube.com/vi/a3gTT--S_YQ/hqdefault.jpg")
                            .placeholder(R.drawable.img_placeholder)
                            .into(holder.feed_img);
                } catch(IllegalArgumentException ex)
                {
                    Log.wtf("Glide-tag", String.valueOf(holder.feed_img.getTag()));
                }
                break;
            case 3:
                holder.title.setText("Dr. kiran Bedi inspires Skills to learn before 30 !!");
                holder.description.setText("Dr. kiran Bedi inspires Skills to learn before 30 !!");
                holder.date.setText("Feb 18, 2016");
                try {
                    Picasso
                            .with(context)
                            .load("http://img.youtube.com/vi/NkOLkHlllrA/hqdefault.jpg")
                            .placeholder(R.drawable.img_placeholder)
                            .into(holder.feed_img);
                } catch(IllegalArgumentException ex)
                {
                    Log.wtf("Glide-tag", String.valueOf(holder.feed_img.getTag()));
                }
                break;


        }
    }

    @Override
    public int getItemCount()
    {
        return 3;
    }

    @Override
    public void onClick(View v)
    {
        ViewHolder holder = (ViewHolder)v.getTag();
        int position = holder.getAdapterPosition();
        switch (position)
        {
            case 0:
                Intent intent1 = YouTubeStandalonePlayer.createVideoIntent((Activity) context, com.DEVELOPER_KEY, "NkOLkHlllrA",1,true,false);
                context.startActivity(intent1);
                break;
            case 1:
                Intent intent2 = YouTubeStandalonePlayer.createVideoIntent((Activity) context, com.DEVELOPER_KEY, "g_CSsL3it9Y",1,true,false);
                context.startActivity(intent2);
                break;
            case 2:
                Intent intent3 = YouTubeStandalonePlayer.createVideoIntent((Activity) context, com.DEVELOPER_KEY, "a3gTT--S_YQ",1,true,false);
                context.startActivity(intent3);
                break;
            case 3:
                Intent intent4 = YouTubeStandalonePlayer.createVideoIntent((Activity) context, com.DEVELOPER_KEY, "NkOLkHlllrA",1,true,false);
                context.startActivity(intent4);
                break;
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        private TextView title,description,date,type;
        private Button detail;
        private ImageView feed_img;
        private RelativeLayout relativeLayoutOverYouTubeThumbnailView,relete;
        private ImageView playButton;
        public ViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            date =(TextView)itemView.findViewById(R.id.date);
            type = (TextView)itemView.findViewById(R.id.tv_type);
            feed_img = (ImageView) itemView.findViewById(R.id.feed_image);
            playButton=(ImageView)itemView.findViewById(R.id.btnYoutube_player);
            relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
        }

    }
}


