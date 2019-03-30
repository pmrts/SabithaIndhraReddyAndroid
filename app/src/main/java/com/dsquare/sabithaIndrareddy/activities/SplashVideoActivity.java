package com.dsquare.sabithaIndrareddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.squareup.picasso.Picasso;

/**
 * Created by D square on 05-09-2018.
 */

public class SplashVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
{
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private YouTubePlayerView youTubeView;
    private ImageView playButton, youtubeImg;
    private CommonFunction com;
    private String code;
    private TextView skip;
    private RelativeLayout relativeLayoutOverYouTubeThumbnailView, relete;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.splash_video_activity);


        skip = (TextView) findViewById(R.id.skip);
        youtubeImg = (ImageView) findViewById(R.id.youtube_view);
        playButton = (ImageView) findViewById(R.id.btnYoutube_player);
        relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) findViewById(R.id.relativeLayout_over_youtube_thumbnail);

        Picasso
                .with(this)
                .load("http://img.youtube.com/vi/qJMeJahP5Is/hqdefault.jpg")
                .placeholder(R.drawable.img_placeholder)
                .into(youtubeImg);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(SplashVideoActivity.this ,VideoActivity.class);
               startActivity(intent);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashVideoActivity.this , LoginMobileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        com = new CommonFunction(this);
     //   getVideo();
        // Initializing video player with developer key

    }

    private void getVideo()
    {
        youTubeView.initialize(com.DEVELOPER_KEY, this);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo("qJMeJahP5Is");

            // Hiding player controls
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(com.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider()
    {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }
    public boolean hasPrevious ()
    {
        return true;
    }
}
