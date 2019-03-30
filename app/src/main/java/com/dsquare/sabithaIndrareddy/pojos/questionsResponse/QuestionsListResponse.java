package com.dsquare.sabithaIndrareddy.pojos.questionsResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D square on 14-05-2018.
 */

public class QuestionsListResponse
{
    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("question_name")
    @Expose
    private String questionName;
    @SerializedName("options")
    @Expose
    private List<OptionsResponse> options = null;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public List<OptionsResponse> getOptions() {
        return options;
    }

    public void setOptions(List<OptionsResponse> options) {
        this.options = options;
    }

}
