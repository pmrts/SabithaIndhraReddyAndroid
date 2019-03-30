package com.dsquare.sabithaIndrareddy.pojos.EventsListResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D square on 14-05-2018.
 */

public class EventResponse
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    @SerializedName("total_events_count")
    @Expose
    private Integer totalEventsCount;

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

    public Integer getTotalEventsCount() {
        return totalEventsCount;
    }

    public void setTotalEventsCount(Integer totalEventsCount) {
        this.totalEventsCount = totalEventsCount;
    }
}
