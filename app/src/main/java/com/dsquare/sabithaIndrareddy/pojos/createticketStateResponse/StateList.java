package com.dsquare.sabithaIndrareddy.pojos.createticketStateResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D square on 22-05-2018.
 */

public class StateList
{
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrievance_type() {
        return grievance_type;
    }

    public void setGrievance_type(String grievance_type) {
        this.grievance_type = grievance_type;
    }

    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("grievance_type")
    @Expose
    private String grievance_type;


}

