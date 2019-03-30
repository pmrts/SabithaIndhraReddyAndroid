package com.dsquare.sabithaIndrareddy.pojos.EventsListResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D square on 14-05-2018.
 */

public class Result
{
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("event_image")
    @Expose
    private String eventImage;
    @SerializedName("event_name")
    @Expose
    private String eventName;
    @SerializedName("event_address")
    @Expose
    private String eventAddress;

    public String getEventDate()
    {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @SerializedName("event_time")
    @Expose
    private String eventDate;
    @SerializedName("event_date")
    @Expose
    private String eventTime;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
