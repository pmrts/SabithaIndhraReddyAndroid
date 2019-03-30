package com.dsquare.sabithaIndrareddy.pojos.publicationtypeResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D square on 31-05-2018.
 */

public class Result
{

    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("type_name")
    @Expose
    private String typeName;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }



    @SerializedName("publication_id")
    @Expose
    private String publicationId;

    public String getTypeimage() {
        return typeimage;
    }

    public void setTypeimage(String typeimage) {
        this.typeimage = typeimage;
    }

    @SerializedName("type_image")
    @Expose
    private String typeimage;

    public String getTypeimage1() {
        return typeimage1;
    }

    public void setTypeimage1(String typeimage1) {
        this.typeimage1 = typeimage1;
    }

    @SerializedName("image_name")
    @Expose
    private String typeimage1;
}
