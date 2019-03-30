package com.dsquare.sabithaIndrareddy.pojos.questionsResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D square on 08-05-2018.
 */

public class QuestionResponse
{
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<QuestionsListResponse> result = null;

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

    public List<QuestionsListResponse> getResult() {
        return result;
    }

    public void setResult(List<QuestionsListResponse> result) {
        this.result = result;
    }
}
