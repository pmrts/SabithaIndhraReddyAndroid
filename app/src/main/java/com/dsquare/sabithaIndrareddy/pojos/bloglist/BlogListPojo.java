package com.dsquare.sabithaIndrareddy.pojos.bloglist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BlogListPojo
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    private String image1;
    @Expose
    private String description;
    @SerializedName("blog_date")
    @Expose
    private String blogDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("fb_share_link")
    @Expose
    private String fbShareLink;
    @SerializedName("gp_share_link")
    @Expose
    private String gpShareLink;
    @SerializedName("twitter_share_link")
    @Expose
    private String twitterShareLink;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(String blogDate) {
        this.blogDate = blogDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFbShareLink() {
        return fbShareLink;
    }

    public void setFbShareLink(String fbShareLink) {
        this.fbShareLink = fbShareLink;
    }

    public String getGpShareLink() {
        return gpShareLink;
    }

    public void setGpShareLink(String gpShareLink) {
        this.gpShareLink = gpShareLink;
    }

    public String getTwitterShareLink() {
        return twitterShareLink;
    }

    public void setTwitterShareLink(String twitterShareLink) {
        this.twitterShareLink = twitterShareLink;
    }
}
