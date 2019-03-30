package com.dsquare.sabithaIndrareddy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;


import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.HomeActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by D square on 15-12-2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter
{
    private Context mContext;
    private List<String> mListDataHeader;
    private List<String> childListDataHeader;

    // child data in format of header title, child title
    private HashMap<String, List<String>> mListDataChild;
    ExpandableListView expandList;

    public ExpandableListAdapter(Context context,
                                 List<String> listDataHeader,
                                 HashMap<String,
                                         List<String>> listChildData
                                 //        ,ExpandableListView mView
    )
    {
        this.mContext = context;
        this.mListDataHeader = listDataHeader;
        this.mListDataChild = listChildData;
        //this.expandList = mView;
    }
    @Override
    public int getGroupCount()
    {
        int i = mListDataHeader.size();
        //Log.d("GROUPCOUNT", String.valueOf(i));
        return i;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(mListDataChild.get(mListDataHeader.get(groupPosition))!=null)
        {
            return this.mListDataChild.get(
                    this.mListDataHeader.get(groupPosition)).size();
        }

        return 0;


    }



    @Override
    public Object getGroup(int groupPosition) {
        return this.mListDataHeader.get(groupPosition);

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.mListDataChild.get(
                this.mListDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = infalInflater.inflate(R.layout.listheader, null);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.submenu);
        ImageView headerIcon = (ImageView) convertView.findViewById(R.id.iconimage);
        headerIcon.setVisibility(View.GONE);
        ImageView headerIcon1 = (ImageView) convertView.findViewById(R.id.image);
        headerIcon1.setImageResource(HomeActivity.icon1[groupPosition]);
//        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        Log.d("token",mListDataHeader.get(groupPosition));
        //lblListHeader.setText(headerTitle.getIconName());
        if(mListDataChild.get(mListDataHeader.get(groupPosition))!=null)
        {
            if(mListDataChild.get(mListDataHeader.get(groupPosition)).size()!=0)
            {
                headerIcon.setVisibility(View.VISIBLE);
                Log.d("token1",mListDataHeader.get(groupPosition));
               // Log.d("token1",mListDataChild.get(mListDataHeader.get(groupPosition)).size()+"");
                headerIcon.setImageResource(HomeActivity.icon[groupPosition]);
            }
        }
        else
        {
            headerIcon.setVisibility(View.GONE);
        }


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_submenu, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.submenu);

        txtListChild.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }
}
