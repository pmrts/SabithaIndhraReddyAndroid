package com.dsquare.sabithaIndrareddy.pojos.volunteerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D square on 14-05-2018.
 */

public class Result
{
    @SerializedName("skill_id")
    @Expose
    private String skillId;
    @SerializedName("skill_name")
    @Expose
    private String skillName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
