package com.dsquare.sabithaIndrareddy.pojos.questionsResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D square on 08-05-2018.
 */

public class OptionsResponse
{

    @SerializedName("option_id")
    @Expose
    private String optionId;
    @SerializedName("option_name")
    @Expose
    private String optionName;
    @SerializedName("option_value")
    @Expose
    private String optionValue;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

}
