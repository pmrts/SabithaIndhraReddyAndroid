package com.dsquare.sabithaIndrareddy.pojos.createticketStateResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D square on 21-09-2018.
 */

public class Example
{
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private List<StateList> message = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<StateList> getMessage() {
        return message;
    }

    public void setMessage(List<StateList> message) {
        this.message = message;
    }
}
