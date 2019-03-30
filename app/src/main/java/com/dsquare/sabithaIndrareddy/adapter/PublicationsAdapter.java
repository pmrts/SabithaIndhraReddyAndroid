package com.dsquare.sabithaIndrareddy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.PublicationDetailsActivity;
import com.dsquare.sabithaIndrareddy.pojos.publicationtypeResponse.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by D square on 31-05-2018.
 */

public class PublicationsAdapter extends RecyclerView.Adapter<PublicationsAdapter.ViewHolder> implements View.OnClickListener {

    Context context;
    List<Result> publicationtype;

    public PublicationsAdapter(Context context, List<Result> publicationtype)
    {
        this.context = context;
        this.publicationtype = publicationtype;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_publications,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.itemView.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Result result = publicationtype.get(position);
        holder.title.setText(result.getTypeName());
        Picasso
                .with(context)
                .load(publicationtype.get(position).getTypeimage())
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

        Intent intent = new Intent(context, PublicationDetailsActivity.class);
        intent.putExtra("id",publicationtype.get(position).getTypeId());
        intent.putExtra("name",publicationtype.get(position).getTypeName());
        intent.putExtra("image",publicationtype.get(position).getTypeimage());
        context.startActivity(intent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        private ImageView image;
        public ViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            image = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }

}
