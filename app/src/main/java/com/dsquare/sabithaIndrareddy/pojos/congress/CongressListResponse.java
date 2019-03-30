package com.dsquare.sabithaIndrareddy.pojos.congress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CongressListResponse
{
    @SerializedName("feedtype")
    @Expose
    private String feedtype;

    public String getHeadline1() {
        return headline1;
    }

    public void setHeadline1(String headline1) {
        this.headline1 = headline1;
    }

    public String getMatter1() {
        return matter1;
    }

    public void setMatter1(String matter1) {
        this.matter1 = matter1;
    }

    public String getYear1() {
        return year1;
    }

    public void setYear1(String year1) {
        this.year1 = year1;
    }

    @SerializedName("Headline")
    @Expose
    private String headline;
    @SerializedName("Headline1")
    @Expose
    private String headline1;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("Matter")
    @Expose
    private String matter;
    @SerializedName("Matter1")
    @Expose
    private String matter1;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("Year1")
    @Expose
    private String year1;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public String getFeedtype() {
        return feedtype;
    }

    public void setFeedtype(String feedtype) {
        this.feedtype = feedtype;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
