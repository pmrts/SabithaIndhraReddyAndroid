package com.dsquare.sabithaIndrareddy.pojos.congress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by NFCUSER on 28-12-2017.
 */

public class CongressResponse
{
    @SerializedName("MainYear")
    @Expose
    private String mainYear;
    @SerializedName("List")
    @Expose
    private List<CongressListResponse> list = null;

    public String getMainYear() {
        return mainYear;
    }

    public void setMainYear(String mainYear) {
        this.mainYear = mainYear;
    }

    public List<CongressListResponse> getList() {
        return list;
    }

    public void setList(List<CongressListResponse> list) {
        this.list = list;
    }
}
