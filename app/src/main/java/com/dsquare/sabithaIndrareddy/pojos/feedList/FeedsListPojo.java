package com.dsquare.sabithaIndrareddy.pojos.feedList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class FeedsListPojo
{
    @SerializedName("data")
    @Expose
    private List<FeedsData> data = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public String getImage_base_path()
    {
        return image_base_path;
    }

    public void setImage_base_path(String image_base_path) {
        this.image_base_path = image_base_path;
    }

    @SerializedName("image_base_path")
    @Expose
    private String image_base_path;

    private final static long serialVersionUID = -4815242821389117027L;

    public List<FeedsData> getData() {
        return data;
    }

    public void setData(List<FeedsData> data)
    {
        this.data = data;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
}
