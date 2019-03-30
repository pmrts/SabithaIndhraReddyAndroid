package com.dsquare.sabithaIndrareddy.pojos.suveryResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D square on 14-05-2018.
 */

public class SurveyListResponse
{
    @SerializedName("survey_id")
    @Expose
    private String surveyId;
    @SerializedName("survey_unique_display_id")
    @Expose
    private String surveyUniqueDisplayId;
    @SerializedName("survey_main_question")
    @Expose
    private String surveyMainQuestion;
    @SerializedName("survey_date")
    @Expose
    private String surveyDate;

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyUniqueDisplayId() {
        return surveyUniqueDisplayId;
    }

    public void setSurveyUniqueDisplayId(String surveyUniqueDisplayId) {
        this.surveyUniqueDisplayId = surveyUniqueDisplayId;
    }

    public String getSurveyMainQuestion() {
        return surveyMainQuestion;
    }

    public void setSurveyMainQuestion(String surveyMainQuestion) {
        this.surveyMainQuestion = surveyMainQuestion;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }
}
