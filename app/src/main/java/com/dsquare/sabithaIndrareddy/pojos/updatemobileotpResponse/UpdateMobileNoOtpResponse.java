package com.dsquare.sabithaIndrareddy.pojos.updatemobileotpResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D square on 01-10-2018.
 */

public class UpdateMobileNoOtpResponse
{
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("mobile_verified_otp")
    @Expose
    private String mobileVerifiedOtp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobileVerifiedOtp() {
        return mobileVerifiedOtp;
    }

    public void setMobileVerifiedOtp(String mobileVerifiedOtp) {
        this.mobileVerifiedOtp = mobileVerifiedOtp;
    }
}
