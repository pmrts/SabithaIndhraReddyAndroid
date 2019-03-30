package com.dsquare.sabithaIndrareddy.pojos.questionsResponse;

/**
 * Created by NFCUSER on 19-05-2018.
 */

public class SubmitQuestionResponse
{
    private String questionId;
    private String optionId;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    private String remarks;
}
