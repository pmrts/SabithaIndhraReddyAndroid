package com.dsquare.sabithaIndrareddy.pojos.feedslist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D square on 02-05-2018.
 */

public class Result
{
    @SerializedName("feed_id")
    @Expose
    private String feedId;
    @SerializedName("feed_type")
    @Expose
    private String feedType;
    @SerializedName("feed_videolink")
    @Expose
    private String feedVideolink;
    @SerializedName("feed_images")
    @Expose
    private List<ImagesList> feedImages = null;
    @SerializedName("feed_name")
    @Expose
    private String feedName;
    @SerializedName("feed_desc")
    @Expose
    private String feedDesc;
    @SerializedName("feed_sharelink")
    @Expose
    private String feedSharelink;
    @SerializedName("feed_date")
    @Expose
    private String feedDate;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getFeedVideolink() {
        return feedVideolink;
    }

    public void setFeedVideolink(String feedVideolink) {
        this.feedVideolink = feedVideolink;
    }

    public List<ImagesList> getFeedImages() {
        return feedImages;
    }

    public void setFeedImages(List<ImagesList> feedImages) {
        this.feedImages = feedImages;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public String getFeedDesc() {
        return feedDesc;
    }

    public void setFeedDesc(String feedDesc) {
        this.feedDesc = feedDesc;
    }

    public String getFeedSharelink() {
        return feedSharelink;
    }

    public void setFeedSharelink(String feedSharelink) {
        this.feedSharelink = feedSharelink;
    }

    public String getFeedDate() {
        return feedDate;
    }

    public void setFeedDate(String feedDate) {
        this.feedDate = feedDate;
    }

}
