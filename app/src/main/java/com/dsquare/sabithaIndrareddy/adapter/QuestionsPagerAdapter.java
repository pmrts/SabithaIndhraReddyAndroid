package com.dsquare.sabithaIndrareddy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.pojos.questionsResponse.QuestionResponse;
import com.dsquare.sabithaIndrareddy.pojos.questionsResponse.QuestionsListResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;

import java.util.List;

/**
 * Created by D square on 08-05-2018.
 */

public class QuestionsPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<QuestionsListResponse> mResources;
    private CommonFunction  com;

    public QuestionsPagerAdapter(Context mContext, List<QuestionsListResponse> mResources) {
        this.mContext = mContext;
        this.mResources = mResources;
        com = new CommonFunction(mContext);
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_questions, container, false);
        TextView question = (TextView) itemView.findViewById(R.id.question);
        TextView type = (TextView) itemView.findViewById(R.id.position);

        type.setText((position+1)+"/"+mResources.size());
        question.setText(mResources.get(position).getQuestionName());
        RecyclerView layout = (RecyclerView) itemView.findViewById(R.id.add_layout);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        layout.setLayoutManager(manager);
        QuestionOptionsAdapter adapter = new QuestionOptionsAdapter(mContext,mResources.get(position).getOptions(),mResources.get(position).getQuestionId());

        layout.setAdapter(adapter);
        container.addView(itemView);



        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
