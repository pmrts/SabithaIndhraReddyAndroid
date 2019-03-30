package com.dsquare.sabithaIndrareddy.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Switch;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.PublicationDetailsActivity;
import com.dsquare.sabithaIndrareddy.pojos.publicationtypeResponse.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.iwf.photopicker.widget.TouchImageView;


public class PublicationDetailsAdapter extends RecyclerView.Adapter<PublicationDetailsAdapter.ViewHolder> implements View.OnClickListener {

    Context context;
    List<Result> publicationtype;

    public PublicationDetailsAdapter(Context context, List<Result> publicationtype)
    {
        this.context = context;
        this.publicationtype = publicationtype;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_publication_details,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.image.setOnClickListener(this);
        holder.image.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Picasso
                .with(context)
                .load(publicationtype.get(position).getTypeimage1())
                .placeholder(R.drawable.img_placeholder)
                .resize(800,400)
                .into(holder.image);
    }

    @Override
    public int getItemCount()
    {

        return publicationtype.size();
    }

    @Override
    public void onClick(View v)
    {
        ViewHolder holder = (ViewHolder)v.getTag();
        int position = holder.getAdapterPosition();

        switch(v.getId())
        {
            case R.id.iv_image:
                showImage(publicationtype.get(position).getTypeimage1());
                 break;

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        public ViewHolder(View itemView)
        {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_image);
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
                .resize(800,400)
                .into(imageView);

        builder.setCancelable(true);
        builder.show();
    }
}
