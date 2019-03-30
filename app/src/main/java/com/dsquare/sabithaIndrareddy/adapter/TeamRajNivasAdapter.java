package com.dsquare.sabithaIndrareddy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.pojos.teamrajnivas.RajNivasListResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by D square on 17-05-2018.
 */

public class TeamRajNivasAdapter extends RecyclerView.Adapter<TeamRajNivasAdapter.ViewHolder>
{
    private Context context;
    private List<RajNivasListResponse> listResponses;

    public TeamRajNivasAdapter(Context context, List<RajNivasListResponse> listResponses)
    {
        this.context = context;
        this.listResponses = listResponses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_teamrajnivas,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        RajNivasListResponse response = listResponses.get(position);
       holder.title.setText(response.getName());
       holder.subtile.setText(response.getDesignation());
       holder.email.setText(response.getEmail());
        Glide
                .with(context)
                .asBitmap()
                .apply(new RequestOptions().override(300, 300))
                .load(response.getImage())
                .into(new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition)
                    {
                        holder.image.setImageBitmap(resource);
                    }

                });

//       switch (position)
//       {
//           case 0:
//               Picasso
//                       .with(context)
//                       .load(R.drawable.team1)
//                       .placeholder(R.drawable.img_placeholder)
//                       .resize(300,300)
//                       .into(holder.image);
//               break;
//           case 1:
//               Picasso
//                       .with(context)
//                       .load(R.drawable.team5)
//                       .placeholder(R.drawable.img_placeholder)
//                       .resize(300,300)
//                       .into(holder.image);
//               break;
//           case 2:
//               Picasso
//                       .with(context)
//                       .load(R.drawable.team2)
//                       .placeholder(R.drawable.img_placeholder)
//                       .resize(300,300)
//                       .into(holder.image);
//               break;
//           case 3:
//               Picasso
//                       .with(context)
//                       .load(R.drawable.team6)
//                       .placeholder(R.drawable.img_placeholder)
//                       .resize(300,300)
//                       .into(holder.image);
//               break;
//           case 4:
//               Picasso
//                       .with(context)
//                       .load(R.drawable.team3)
//                       .placeholder(R.drawable.img_placeholder)
//                       .resize(300,300)
//                       .into(holder.image);
//               break;
//           case 5:
//               Picasso
//                       .with(context)
//                       .load(R.drawable.team7)
//                       .placeholder(R.drawable.img_placeholder)
//                       .resize(300,300)
//                       .into(holder.image);
//               break;
//           case 6:
//               Picasso
//                       .with(context)
//                       .load(R.drawable.team4)
//                       .placeholder(R.drawable.img_placeholder)
//                       .resize(300,300)
//                       .into(holder.image);
//               break;
   //    }


    }

    @Override
    public int getItemCount()
    {
        return listResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title,subtile,mobile,email;
        private ImageView image;

        public ViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            subtile = (TextView)itemView.findViewById(R.id.subtitle);
            mobile = (TextView)itemView.findViewById(R.id.mobile);
            email = (TextView)itemView.findViewById(R.id.email);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }

}



