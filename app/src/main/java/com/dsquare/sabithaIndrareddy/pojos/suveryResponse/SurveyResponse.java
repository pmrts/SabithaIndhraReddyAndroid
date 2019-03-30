package com.dsquare.sabithaIndrareddy.pojos.suveryResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D square on 14-05-2018.
 */

public class SurveyResponse
{
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<SurveyListResponse> result = null;
    @SerializedName("total_surveys_count")
    @Expose
    private Integer totalSurveysCount;

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

    public List<SurveyListResponse> getResult() {
        return result;
    }

    public void setResult(List<SurveyListResponse> result) {
        this.result = result;
    }

    public Integer getTotalSurveysCount() {
        return totalSurveysCount;
    }

    public void setTotalSurveysCount(Integer totalSurveysCount) {
        this.totalSurveysCount = totalSurveysCount;
    }
}
