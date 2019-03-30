package com.dsquare.sabithaIndrareddy.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.fragments.AllFragment;
import com.dsquare.sabithaIndrareddy.fragments.NewsFragment;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.feedslist.FeedData;
import com.dsquare.sabithaIndrareddy.pojos.feedslist.ImagesList;
import com.dsquare.sabithaIndrareddy.pojos.feedslist.Result;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D square on 25-04-2018.
 */

public class FeedDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title, description, date, feedsTitle;
    private Button detail;
    private Toolbar toolbar;
    private ImageView feed_img, backbutton, playButton, youtubeImg, share;
    private int position;
    private CommonFunction com;
    private List<Result> feedDataList = new ArrayList<>();
    private RelativeLayout relativeLayoutOverYouTubeThumbnailView, relete;
    private int maxId=1;
    private ViewPager image;
    ImageView youTubeThumbnailView;
    CustomPagerAdapter mCustomPagerAdapter;
    WebView webView;

    int currentPage = 0;
    int NUM_PAGES = 0;
    Timer timer;
    final long DELAY_MS = 0;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    private FrameLayout frameLayout;
    ImageView rightArrowButton,leftArrowButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initializeViews();
    }

    private void initializeViews() {
        title = (TextView) findViewById(R.id.title);
        feedsTitle = (TextView) findViewById(R.id.feeds_title);
        description = (TextView) findViewById(R.id.description);
        date = (TextView) findViewById(R.id.date);
        feed_img = (ImageView) findViewById(R.id.feed_image);
        youtubeImg = (ImageView) findViewById(R.id.youtube_view);
        backbutton = (ImageView) findViewById(R.id.back);
        share = (ImageView) findViewById(R.id.blog_share);
        image = (ViewPager) findViewById(R.id.feeds_image);
        frameLayout = (FrameLayout) findViewById(R.id.frame);
        frameLayout = (FrameLayout) findViewById(R.id.frame);
        webView = (WebView) findViewById(R.id.textContent);
        share.setOnClickListener(this);
        playButton = (ImageView) findViewById(R.id.btnYoutube_player);
        youTubeThumbnailView = (ImageView) findViewById(R.id.youtube_view);
        relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) findViewById(R.id.relativeLayout_over_youtube_thumbnail);
        relete = (RelativeLayout) findViewById(R.id.relative);

        com = new CommonFunction(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        position = intent.getIntExtra("pos", 1);

        backbutton = (ImageView) findViewById(R.id.back);
        backbutton.setOnClickListener(this);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = YouTubeStandalonePlayer.createVideoIntent(FeedDetailsActivity.this, com.DEVELOPER_KEY, com.feedDataList.getFeedVideolink().replace("&feature=youtu.be","").replace("https://www.youtube.com/watch?v=",""), 1, true, false);
                startActivity(intent);
            }
        });

  //      getFeeds();
        relativeLayoutOverYouTubeThumbnailView.setVisibility(View.GONE);
        title.setText(com.feedDataList.getFeedName());
        title.setTextColor(Color.parseColor("#000080"));
        date.setText(com.feedDataList.getFeedDate());

        webView = (WebView) findViewById(R.id.textContent);

        try {

            String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";
            String myData = com.feedDataList.getFeedDesc();

            webView.loadData(String.format(htmlText, myData), "text/html", "utf-8");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(com.feedDataList.getFeedType().equals("1")) {
            feedsTitle.setText("Video");
            relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            feed_img.setVisibility(View.GONE);
            relete.setVisibility(View.GONE);
            youtubeImg.setVisibility(View.VISIBLE);
            Picasso
                    .with(this)
                    .load("http://img.youtube.com/vi/"+com.feedDataList.getFeedVideolink().replace("&feature=youtu.be","").replace("https://www.youtube.com/watch?v=","")+"/hqdefault.jpg")
                    .placeholder(R.drawable.img_placeholder)
                    .into(youtubeImg);
        }
        else if(com.feedDataList.getFeedType().equals("2"))
        {
//            feed_img.setVisibility(View.VISIBLE);
//            youtubeImg.setVisibility(View.GONE);
//            Picasso
//                    .with(this)
//                    .load(String.valueOf(com.feedDataList.getFeedImages().get(0).getImage_name()))
//                    .placeholder(R.drawable.img_placeholder)
//                    .into(feed_img);
           // Log.d("sizee1",com.feedDataList.getType()+"");
            //source.setVisibility(View.VISIBLE);
            date.setVisibility(View.VISIBLE);
            image.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.VISIBLE);
            youTubeThumbnailView.setVisibility(View.GONE);
            relativeLayoutOverYouTubeThumbnailView.setVisibility(View.GONE);
            timer = new Timer();

            if(com.feedDataList.getFeedImages()!=null)
            {
                mCustomPagerAdapter = new CustomPagerAdapter(this ,com.feedDataList.getFeedImages());
                image.setAdapter(mCustomPagerAdapter);
                NUM_PAGES =com.feedDataList.getFeedImages().size();
                final Handler handler = new Handler();
                final Runnable Update = new Runnable()
                {
                    public void run() {
               /* if (currentPage == NUM_PAGES-1) {
                    currentPage = 0;
                }*/

                        if(com.feedDataList.getFeedImages()!=null)
                        {
                            if (currentPage == NUM_PAGES) {
                                currentPage = 0;
                            }
                            image.setCurrentItem(currentPage++, true);
                            // image.setCurrentItem((image.getCurrentItem()+1)%com.feedsDataList.getFeedImages().size());
                        }

                /*int tab = viewPager.getCurrentItem()%images.length;
                tab++;
                viewPager.setCurrentItem(tab);*/
//                        image.setCurrentItem(currentPage++, true);
                    }
                };


                timer .schedule(new TimerTask()
                { // task to be scheduled

                    @Override
                    public void run()

                    {
                        handler.post(Update);
                    }
                }, DELAY_MS, PERIOD_MS);

                leftArrowButton = (ImageView)findViewById(R.id.left_nav);
                leftArrowButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //  viewPager.setCurrentItem(currentPage--);
                        int tab = image.getCurrentItem();
                        if (tab > 0) {
                            tab--;
                            image.setCurrentItem(tab);
                        } else if (tab == 0) {
                            image.setCurrentItem(tab);
                        }
                    }
                });
                rightArrowButton = (ImageView)findViewById(R.id.right_nav);
                rightArrowButton.setOnClickListener(

                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // viewFlipper.showNext();
                                // viewPager.setCurrentItem(currentPage++);
                                int tab = image.getCurrentItem();
                                tab++;
                                image.setCurrentItem(tab);
                            }
                        });

            }

            image.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int pos)
                {
                    Log.d("sizeee",pos+"");
                    if(com.feedDataList.getFeedImages()!=null)
                    {
                        if(com.feedDataList.getFeedImages().size()==1)
                        {
                            leftArrowButton.setVisibility(View.GONE);
                            rightArrowButton.setVisibility(View.GONE);
                        }
                        else if(pos == 0)
                        {
                            leftArrowButton.setVisibility(View.GONE);
                            rightArrowButton.setVisibility(View.VISIBLE);
                        }
                        else if(com.feedDataList.getFeedImages().size()==pos+1)
                        {
                            leftArrowButton.setVisibility(View.VISIBLE);
                            rightArrowButton.setVisibility(View.GONE);
                        }
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


        }
        }


    /*invite friends*/
    private void inviteFriends(String invitationMessage) {
        List<Intent> targetShareIntents = new ArrayList<Intent>();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, invitationMessage);
        sendIntent.setType("text/plain");
        PackageManager pm = getPackageManager();
        Resources resources = getResources();
        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
        List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
        for (int i = 0; i < resInfo.size(); i++) {
            // Extract the label, append it, and repackage it in a LabeledIntent
            ResolveInfo ri = resInfo.get(i);
            String packageName = ri.activityInfo.packageName;
            Log.d("Package Name", packageName);

            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, invitationMessage);
            intent.setPackage(packageName);
            targetShareIntents.add(intent);

        }
        if (!targetShareIntents.isEmpty()) {
            System.out.println("Have Intent");
            Intent chooserIntent = Intent.createChooser(targetShareIntents.remove(0), "Choose app to invite friends");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toArray(new Parcelable[]{}));
            startActivity(chooserIntent);
        }

    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.blog_share:
                inviteFriends(com.feedDataList.getFeedSharelink());
                break;
        }

    }
    private class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;
        List<ImagesList> feedImages = new ArrayList<>();

        CustomPagerAdapter(Context context, List<ImagesList> feedImages) {
            mContext = context;
            this.feedImages = feedImages;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return feedImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            Log.d("token",feedImages.get(position).toString());
            Picasso.with(mContext)
                    .load(feedImages.get(position).getImage_name())
                    .placeholder(R.drawable.img_placeholder)
                    .into(imageView);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}
