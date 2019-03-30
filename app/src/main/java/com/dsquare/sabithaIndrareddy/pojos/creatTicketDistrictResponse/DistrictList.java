package com.dsquare.sabithaIndrareddy.pojos.creatTicketDistrictResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D square on 22-05-2018.
 */

public class DistrictList
{
    public String getGrievance_id() {
        return grievance_id;
    }

    public void setGrievance_id(String grievance_id) {
        this.grievance_id = grievance_id;
    }

    public String getComplaint_title() {
        return complaint_title;
    }

    public void setComplaint_title(String complaint_title) {
        this.complaint_title = complaint_title;
    }

    @SerializedName("grievance_id")
    @Expose
    private String grievance_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("complaint_title")
    @Expose
    private String complaint_title;


}
