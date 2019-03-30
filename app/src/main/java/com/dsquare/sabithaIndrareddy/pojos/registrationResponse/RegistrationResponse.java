package com.dsquare.sabithaIndrareddy.pojos.registrationResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D square on 15-05-2018.
 */

public class RegistrationResponse
{
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("user_mobile")
    @Expose
    private String userMobile;
    @SerializedName("mobile_verified_otp")
    @Expose
    private String mobileVerifiedOtp;
    @SerializedName("mobile_verified_status")
    @Expose
    private Integer mobileVerifiedStatus;

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

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getMobileVerifiedOtp() {
        return mobileVerifiedOtp;
    }

    public void setMobileVerifiedOtp(String mobileVerifiedOtp) {
        this.mobileVerifiedOtp = mobileVerifiedOtp;
    }

    public Integer getMobileVerifiedStatus() {
        return mobileVerifiedStatus;
    }

    public void setMobileVerifiedStatus(Integer mobileVerifiedStatus) {
        this.mobileVerifiedStatus = mobileVerifiedStatus;
    }

}
