/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;

/**
 *
 * @author Bassyouni
 */
public class Answer {
    private String id;
    private String userId;
    private String answer;
    private String questionId;
    private String surveyId;

    public Answer() {
    }


    public Answer(String id, String userId, String answer, String questionId , String surveyId) {
        this.id = id;
        this.userId = userId;
        this.answer = answer;
        this.questionId = questionId;
        this.surveyId = surveyId;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUseriD() {
        return userId;
    }

    public void setUser(String userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public HashMap<String, String> getAttributes(){
        //`is_administrator`, `name`, `password`, `email`, `is_suspended`
        HashMap<String, String> attributes = new HashMap<>();
        
        if(userId != null)
            attributes.put("user_id", userId);
        if(surveyId != null)
            attributes.put("survey_id", surveyId);
        if(questionId != null)
            attributes.put("question_id", questionId);
        if(answer != null)
            attributes.put("answer", answer);
        
        return attributes;
    }
    
    
}
