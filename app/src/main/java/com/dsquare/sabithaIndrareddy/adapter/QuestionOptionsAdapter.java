package com.dsquare.sabithaIndrareddy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.pojos.questionsResponse.OptionsResponse;
import com.dsquare.sabithaIndrareddy.pojos.questionsResponse.SubmitQuestionResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;

import java.util.List;

/**
 * Created by D square on 08-05-2018.
 */

public class QuestionOptionsAdapter extends RecyclerView.Adapter<QuestionOptionsAdapter.ViewHolder> implements View.OnClickListener
{
    Context mContext;
    List<OptionsResponse> list;
    private CommonFunction com;
    private String questionId;


    public QuestionOptionsAdapter(Context mContext, List<OptionsResponse> list, String questionId)
    {
        this.mContext=mContext;
        this.list=list;
        this.questionId = questionId;
        com = new CommonFunction(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_choice,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtChipName.setText(list.get(position).getOptionValue());
        holder.txtChoice.setText(list.get(position).getOptionName());


        if(list.get(position).getStatus()!=null)
        {
            for(int i = 0 ; i < com.submitQuestionList.size() ; i++)
            {
                if(com.submitQuestionList.get(i).getQuestionId().equals(questionId))
                {
                    com.submitQuestionList.remove(i);
                }
            }
            SubmitQuestionResponse pojo =  new SubmitQuestionResponse();
            pojo.setQuestionId(questionId);
            pojo.setOptionId(list.get(position).getOptionId());
            com.submitQuestionList.add(pojo);
            holder.verified.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.verified.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder)view.getTag();
        int position = holder.getAdapterPosition();
        for(int i = 0 ; i < list.size() ; i++)
        {
            list.get(i).setStatus(null);
        }
        list.get(position).setStatus("1");
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtChipName,txtChoice;
        private ImageView verified;
        public ViewHolder(View choiceView)
        {
            super(choiceView);
            txtChipName = (TextView) choiceView.findViewById(R.id.txtChipName);
            txtChoice = (TextView) choiceView.findViewById(R.id.txtChoice);
            verified = (ImageView) choiceView.findViewById(R.id.verified);
        }
    }
}
