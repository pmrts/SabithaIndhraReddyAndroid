package com.dsquare.sabithaIndrareddy.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.FeedDetailsActivity;
import com.dsquare.sabithaIndrareddy.pojos.feedslist.FeedData;
import com.dsquare.sabithaIndrareddy.pojos.feedslist.Result;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.iwf.photopicker.widget.TouchImageView;

/**
 * Created by D square on 12-04-2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private String s;
    String imagePath;
    private CommonFunction com;

    private List<Result> feedDataList;

    public HomeAdapter(Context context, List<Result> feedDataList)
    {
        this.context=context;
        this.feedDataList=feedDataList;
        com = new CommonFunction(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_feeds,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
       // viewHolder.itemView.setOnClickListener(this);
       // viewHolder.itemView.setTag(viewHolder);
        viewHolder.playButton.setOnClickListener(this);
        viewHolder.playButton.setTag(viewHolder);
        viewHolder.linear.setOnClickListener(this);
        viewHolder.linear.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Result feedData = feedDataList.get(position);

        holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.GONE);
        holder.title.setText(feedData.getFeedName());
        holder.description.setText(feedData.getFeedDesc().replaceAll("\\<.*?\\>", ""));
        holder.date.setText(feedData.getFeedDate());


        if(feedData.getFeedType().equals("1"))
        {
            holder.type.setText("Videos");
            holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            Picasso
                    .with(context)
                    .load("http://img.youtube.com/vi/"+feedData.getFeedVideolink().replace("&feature=youtu.be","").replace("https://www.youtube.com/watch?v=","")+"/hqdefault.jpg")
                    .placeholder(R.drawable.img_placeholder)
                    .into(holder.feed_img);
        }
        else if(feedData.getFeedType().equals("2"))
        {
            holder.type.setText("News");
            Picasso
                    .with(context)
                    .load(feedData.getFeedImages().get(0).getImage_name())
                    .placeholder(R.drawable.img_placeholder)
                    .resize(600,600)
                    .into(holder.feed_img);

        }

//        switch (position)
//        {
//            case 0:
//                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.GONE);
//                holder.title.setText("Note Books Distribution @ ZPHS, Khajaguda");
//                holder.description.setText("A few glimpses of free note books distribution from Sandaiah Memorial Trust @ ZPHS, Khajaguda");
//                holder.date.setText("JUN 26, 2018");
//                holder.type.setText("News");
//                Picasso
//                        .with(context)
//                        .load("https://scontent.fhyd2-1.fna.fbcdn.net/v/t1.0-9/36200231_1790237191046159_1606942841064390656_o.jpg?_nc_cat=0&oh=ce7ebd198fe04c169535d8f59b6e2ca7&oe=5BA7E04B")
//                        .placeholder(R.drawable.img_placeholder)
//                        .into(holder.feed_img);
//                break;
//            case 1:
//                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.GONE);
//                holder.title.setText("Note Books Distribution @ ZPHS, Kothaguda");
//                holder.description.setText("A few glimpses of free note books distribution from Sandaiah Memorial Trust @ ZPHS, Kothaguda. Heartfelt thanks to Sri Satyanarayana Garu ( 8th Battalion Commandant ) for being the Chief Guest at this social service event and making it grand.");
//                holder.date.setText("JUN 26, 2018");
//                holder.type.setText("News");
//                Picasso
//                        .with(context)
//                        .load("https://scontent.fhyd2-1.fna.fbcdn.net/v/t1.0-9/36189306_1789795477756997_8572266767286534144_o.jpg?_nc_cat=0&oh=2a305133d1244fa8618539da9b0f900f&oe=5BD61A58")
//                        .placeholder(R.drawable.img_placeholder)
//                        .into(holder.feed_img);
//                break;
//
//            case 2:
//                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.GONE);
//                holder.title.setText("Free Note Books and School Bags Distributions");
//                holder.description.setText("On the occasion of charismatic leader Rahul Gandhi Ji birthday today, took part in various programs, few being free note books and school bags distributions in ZPHS schools and other being cake cuttings in orphanages in various places. Because service is the highest form of worship, I have expressed my love and respect towards Rahul Gandhi Ji by helping poor kids who are also the citizens of tomorrow as told by Jawaharlal Nehru. Here are few glimpses of the celebrations.");
//                holder.date.setText("JUN 19, 2018");
//                holder.type.setText("News");
//                Picasso
//                        .with(context)
//                        .load("https://scontent.fhyd2-1.fna.fbcdn.net/v/t1.0-9/35671538_1779248945478317_7281830107897921536_o.jpg?_nc_cat=0&oh=99b38e92b8be439cb046920babd8cc04&oe=5BA224B5")
//                        .placeholder(R.drawable.img_placeholder)
//                        .into(holder.feed_img);
//                break;
//            case 3:
//                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.GONE);
//                holder.title.setText("Hosted iftaar party for Muslim brothers");
//                holder.description.setText("Hosted iftaar party for Muslim brothers at Maseedbanda. Thankful to Shabbir Ali garu whose presence made the event grand.");
//                holder.date.setText("JUN 10, 2018");
//                holder.type.setText("News");
//                Picasso
//                        .with(context)
//                        .load("https://scontent.fhyd2-1.fna.fbcdn.net/v/t1.0-9/34881826_1767381356665076_5063417505955971072_n.jpg?_nc_cat=0&oh=a9ee3df718dc2678721ef88da3bebf4f&oe=5BE06673")
//                        .placeholder(R.drawable.img_placeholder)
//                        .into(holder.feed_img);
//                break;
//
//
//        }

    }



    @Override
    public int getItemCount()
    {
        return feedDataList.size();
    }

    @Override
    public void onClick(View v)
    {
        ViewHolder holder = (ViewHolder)v.getTag();
        int position = holder.getAdapterPosition();

        switch (v.getId())
        {

            case R.id.linear:
                Intent intent1 = new Intent (context, FeedDetailsActivity.class);
                com.feedDataList = feedDataList.get(position);
                intent1.putExtra("pos",position);
                context.startActivity(intent1);
                break;
            case R.id.btnYoutube_player:
                Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) context, com.DEVELOPER_KEY, feedDataList.get(position).getFeedVideolink().replace("&feature=youtu.be","").replace("https://www.youtube.com/watch?v=",""),1,true,false);
                context.startActivity(intent);
                break;

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title,description,date,type;
        private Button detail;
        private ImageView feed_img;
        private LinearLayout linear;
        private RelativeLayout relativeLayoutOverYouTubeThumbnailView,relete;
        private ImageView playButton;
        public ViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            date =(TextView)itemView.findViewById(R.id.date);
            feed_img = (ImageView) itemView.findViewById(R.id.feed_image);
            linear = (LinearLayout)itemView.findViewById(R.id.linear);
            playButton=(ImageView)itemView.findViewById(R.id.btnYoutube_player);
            relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            type = (TextView)itemView.findViewById(R.id.tv_type);

        }
    }


}
