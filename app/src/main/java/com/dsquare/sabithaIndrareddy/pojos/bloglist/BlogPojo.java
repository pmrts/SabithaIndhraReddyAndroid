package com.dsquare.sabithaIndrareddy.pojos.bloglist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D square on 02-05-2018.
 */

public class BlogPojo
{
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<BlogListPojo> data = null;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<BlogListPojo> getData() {
        return data;
    }

    public void setData(List<BlogListPojo> data) {
        this.data = data;
    }
}
