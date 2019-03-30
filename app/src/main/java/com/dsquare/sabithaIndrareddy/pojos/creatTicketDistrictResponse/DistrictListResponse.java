package com.dsquare.sabithaIndrareddy.pojos.creatTicketDistrictResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D square on 21-09-2018.
 */

public class DistrictListResponse
{
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("complaintsList")
    @Expose
    private List<DistrictList> complaintsList = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<DistrictList> getComplaintsList() {
        return complaintsList;
    }

    public void setComplaintsList(List<DistrictList> complaintsList) {
        this.complaintsList = complaintsList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
