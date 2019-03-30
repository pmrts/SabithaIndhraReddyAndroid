package com.dsquare.sabithaIndrareddy.pojos.feedslist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D square on 02-05-2018.
 */

public class FeedData
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    @SerializedName("base_url")
    @Expose
    private String base_url;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    @SerializedName("total_feeds_count")
    @Expose
    private Integer totalFeedsCount;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Integer getTotalFeedsCount() {
        return totalFeedsCount;
    }

    public void setTotalFeedsCount(Integer totalFeedsCount) {
        this.totalFeedsCount = totalFeedsCount;
    }

}
