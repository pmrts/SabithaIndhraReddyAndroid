package com.dsquare.sabithaIndrareddy.pojos.congress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class CongressPojo
{
    public List<CongressResponse> getList() {
        return list;
    }

    public void setList(List<CongressResponse> list) {
        this.list = list;
    }

    @SerializedName("data")
    @Expose
    private List<CongressResponse> list = null;

}
