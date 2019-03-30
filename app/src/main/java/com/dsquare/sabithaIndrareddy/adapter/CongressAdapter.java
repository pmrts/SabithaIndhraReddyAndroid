package com.dsquare.sabithaIndrareddy.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.pojos.congress.CongressListResponse;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;
import com.dsquare.sabithaIndrareddy.utils.TouchImageView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CongressAdapter extends RecyclerView.Adapter<CongressAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<CongressListResponse> jouneryListPojos;
    private CommonFunction com;
    private CommonSharePrefrences comshare;

    public CongressAdapter(Context context, List<CongressListResponse> jouneryListPojos)
    {
        this.context = context;
        this.jouneryListPojos = jouneryListPojos;
        com = new CommonFunction(context);
        comshare =CommonSharePrefrences.getInstance(context);
        comshare.setUserLanguage("0");
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = (LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_congress, parent, false));
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.playButton.setOnClickListener(this);
        viewHolder.playButton.setTag(viewHolder);
        viewHolder.feeds_image.setOnClickListener(this);
        viewHolder.feeds_image.setTag(viewHolder);
        viewHolder.readImage.setOnClickListener(this);
        viewHolder.readImage.setTag(viewHolder);
        viewHolder.text2.setOnClickListener(this);
        viewHolder.text2.setTag(viewHolder);
        viewHolder.text3.setOnClickListener(this);
        viewHolder.text3.setTag(viewHolder);
        return viewHolder;
    }

    public void refresh(List<CongressListResponse> jouneryListPojos)
    {
        this.jouneryListPojos = jouneryListPojos;
        notifyDataSetChanged();
    }


    @Override
    public long getItemId(int position)
    {
        return position;
    }


    private void dataChanged(final ViewHolder holder , int position)
    {
        final CongressListResponse pojo = jouneryListPojos.get(position);

        if(pojo.getStatus()==null)
        {
            holder.text2.setMaxLines(5);
            holder.readImage.setBackgroundResource(R.drawable.ic_move_icon);
            holder.readmore.setText("Read More...");
        }
        else
        {
            holder.text2.setMaxLines(Integer.MAX_VALUE);
            holder.readmore.setText("Read Less...");
            holder.readImage.setBackgroundResource(R.drawable.ic_less);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CongressListResponse pojo = jouneryListPojos.get(position);
        if (pojo.getYear() != null)
        {
            holder.text.setVisibility(View.VISIBLE);
            if(comshare.getUserLanguage().equals("0"))
            {
                holder.text.setText(pojo.getYear());
            }
           else
            {
                holder.text.setText(pojo.getYear1());
            }
        } else
            {
            holder.text.setVisibility(View.GONE);
           }

        holder.title.setVisibility(View.VISIBLE);
        if(comshare.getUserLanguage().equals("0")) {
            holder.title.setText(pojo.getHeadline());
            holder.text2.setText(pojo.getMatter());
            holder.text2.setVisibility(View.VISIBLE);
            holder.text3.setVisibility(View.GONE);
        }
        else
        {
            holder.title.setText(pojo.getHeadline1());
            holder.text3.setText(pojo.getMatter1());
            holder.text2.setVisibility(View.GONE);
            holder.text3.setVisibility(View.VISIBLE);

        }

        if(pojo.getStatus()==null)
        {
            holder.text2.setMaxLines(5);
            holder.text3.setMaxLines(5);
            holder.readImage.setBackgroundResource(R.drawable.ic_move_icon);
          //  makeTextViewResizable(holder.text2, 4, " (...)", true);
            holder.readmore.setText("Read More...");

         //   holder.text2.setText(holder.text2.getLayout().getText().subSequence(0, holder.text2.getLayout().getLineEnd( holder.text2.getLayout().getLineCount() - 1)) + " " + "(...)");
        }
        else
        {
            holder.text2.setMaxLines(Integer.MAX_VALUE);
            holder.text3.setMaxLines(Integer.MAX_VALUE);
            holder.readmore.setText("Read Less...");
//            makeTextViewResizable(holder.text2, 5, "", true);
            holder.readImage.setBackgroundResource(R.drawable.ic_less_arrow_icon);
        }


        if(pojo.getMatter()!=null)
        {
            if(pojo.getMatter().length()<175)
            {
                holder.readImage.setVisibility(View.INVISIBLE);

//                makeTextViewResizable(holder.text2, 5, "", false);
            }
            else
            {
                holder.readImage.setVisibility(View.VISIBLE);
               // makeTextViewResizable(holder.text2, 4, " (...)", true);
            }
        }
        else
        {
            holder.readImage.setVisibility(View.INVISIBLE);
        }




        if(pojo.getFeedtype()!=null)
        {
            if(pojo.getFeedtype().equals("1"))
            {
                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.GONE);
                holder.feeds_image1.setVisibility(View.GONE);

                holder.feeds_image.setVisibility(View.VISIBLE);

                Log.d("tokenn",pojo.getURL());

                if(!pojo.getURL().equals(""))
                {
                    Picasso
                            .with(context)
                            .load(pojo.getURL())
                            .placeholder(R.drawable.img_placeholder)
                            .resize(150,150)
                            .into(holder.feeds_image);
                }
                else
                {

                    holder.feeds_image.setVisibility(View.GONE);
                }

            }
            else
            {
                if(!pojo.getURL().equals(""))
                {
                    holder.feeds_image.setVisibility(View.GONE);
                    holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
                    holder.feeds_image1.setVisibility(View.VISIBLE);

                    try {
                        Picasso
                                .with(context)
                                .load("http://img.youtube.com/vi/"+jouneryListPojos.get(position).getURL()+"/hqdefault.jpg")
                                .resize(150,150)
                                .placeholder(R.drawable.img_placeholder)
                                .into(holder.feeds_image1);
                    } catch(IllegalArgumentException ex) {
                        Log.wtf("Glide-tag", String.valueOf(holder.feeds_image1.getTag()));
                    }


                }
                else
                {

                    holder.feeds_image1.setVisibility(View.GONE);
                }


            }
        }


    }

    @Override
    public int getItemCount() {
        return jouneryListPojos.size();
    }

    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder)view.getTag();
        int position = holder.getAdapterPosition();

        switch (view.getId())
        {
            case R.id.btnYoutube_player:
                Intent intent1 = YouTubeStandalonePlayer.createVideoIntent((Activity) context, com.DEVELOPER_KEY, jouneryListPojos.get(position).getURL(),1,true,false);
                context.startActivity(intent1);
                break;
            case R.id.image:
                showImage(jouneryListPojos.get(position).getURL());
                break;
            case R.id.read_image:
                if(jouneryListPojos.get(position).getStatus()==null)
                {

                    for(int i = 0 ; i < jouneryListPojos.size() ; i++)
                    {
                        jouneryListPojos.get(i).setStatus(null);
//                        dataChanged(holder,i);
                    }

                    jouneryListPojos.get(position).setStatus("1");
                }
                else
                {
                    jouneryListPojos.get(position).setStatus(null);
                }
                notifyDataSetChanged();
                break;
            case R.id.des:
                if(jouneryListPojos.get(position).getStatus()==null)
                {
                    for(int i = 0 ; i < jouneryListPojos.size() ; i++)
                    {
                        jouneryListPojos.get(i).setStatus(null);
//                        dataChanged(holder,i);
                    }

                    jouneryListPojos.get(position).setStatus("1");
                }
                else
                {
                    jouneryListPojos.get(position).setStatus(null);
                }
                notifyDataSetChanged();
                break;
            case R.id.des1:
                if(jouneryListPojos.get(position).getStatus()==null)
                {
                    for(int i = 0 ; i < jouneryListPojos.size() ; i++)
                    {
                        jouneryListPojos.get(i).setStatus(null);
//                        dataChanged(holder,i);
                    }

                    jouneryListPojos.get(position).setStatus("1");
                }
                else
                {
                    jouneryListPojos.get(position).setStatus(null);
                }
                notifyDataSetChanged();
                break;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        private TextView text, text2,text3,title,readmore;
        private ImageView feeds_image,readImage,feeds_image1;
        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        protected ImageView playButton;

        public ViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            text = (TextView) itemView.findViewById(R.id.name);
            text2 = (TextView) itemView.findViewById(R.id.des);
            text3 = (TextView) itemView.findViewById(R.id.des1);
            readmore = (TextView) itemView.findViewById(R.id.read);
            readImage = (ImageView) itemView.findViewById(R.id.read_image);
            feeds_image = (ImageView) itemView.findViewById(R.id.image);
            feeds_image1 = (ImageView) itemView.findViewById(R.id.image1);
            playButton=(ImageView)itemView.findViewById(R.id.btnYoutube_player);
            relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
        }
    }

    public void showImage(String imageUri) {
        Dialog builder = new Dialog(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(R.layout.adapter_congress_imageview);


        me.iwf.photopicker.widget.TouchImageView imageView = (me.iwf.photopicker.widget.TouchImageView) builder.findViewById(R.id.image);


        Picasso
                .with(context)
                .load(imageUri)
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);

        builder.setCancelable(true);
        builder.show();
    }
    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                String text;
                int lineEndIndex;
                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    lineEndIndex = tv.getLayout().getLineEnd(0);
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                }else {
                    lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                }
                tv.setText(text);
//                tv.setMovementMethod(LinkMovementMethod.getInstance());

            }
        });

    }

}