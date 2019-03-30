package com.dsquare.sabithaIndrareddy.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.pojos.volunteerResponse.Result;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;

import java.util.List;

/**
 * Created by D square on 12-04-2018.
 */

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Result> list;
    private CommonFunction com;

    public VolunteerAdapter(Context context, List<Result> list)
    {
        this.context=context;
        this.list=list;
        com = new CommonFunction(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_volunteer,parent,false);
       ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(holder);
       return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
//        switch (position)
//        {
//            case 0 :
//                holder.volunteerlist.setText("Swachh Pondicherry");
//                break;
//            case 1 :
//                holder.volunteerlist.setText("Media Support");
//                break;
//            case 2 :
//                holder.volunteerlist.setText("Education");
//                break;
//            case 3 :
//                holder.volunteerlist.setText("Awareness Program");
//                break;
//            case 4 :
//                holder.volunteerlist.setText("IT support");
//                break;
//            case 5 :
//                holder.volunteerlist.setText("Others");
//                break;
//
//
//        }

        Result result = list.get(position);

        holder.volunteerlist.setText(result.getSkillName());

        if(result.getStatus()==null)
        {
            holder.checkBox.setChecked(false);
            holder.volunteer_image.setImageResource(android.R.color.transparent);
            holder.volunteer_image.setBackgroundResource(R.drawable.ic_unchecked_icon);

        }
        else
        {
            holder.checkBox.setChecked(true);
            holder.volunteer_image.setImageResource(android.R.color.transparent);
            holder.volunteer_image.setBackgroundResource(R.drawable.ic_checked_icon);

        }

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    @Override
    public void onClick(View view)
    {
        ViewHolder holder = (ViewHolder)view.getTag();
        int position = holder.getAdapterPosition();
        if(holder.checkBox.isChecked())
        {
            com.volunterList.remove(position+"");
            list.get(position).setStatus(null);
            notifyDataSetChanged();
        }
        else
        {
            com.volunterList.add(position+"");
            holder.checkBox.setChecked(true);
            list.get(position).setStatus("1");
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView volunteerlist;
        private ImageView volunteer_image;
        private CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);

            volunteerlist= (TextView)itemView.findViewById(R.id.volunteer_text);
            volunteer_image = (ImageView)itemView.findViewById(R.id.volunteer_image);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }

    }

}
