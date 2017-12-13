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
public class Reports {
    private String id;
    private String userId;
    private String surveyId;
    private String comment;

    public Reports() {
    }

    public Reports(String id, String userId, String surveyId, String comment) {
        this.id = id;
        this.userId = userId;
        this.surveyId = surveyId;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public HashMap<String, String> getAttributes(){
        
        HashMap<String, String> attributes = new HashMap<>();
        
        if(userId != null)
            attributes.put("user", userId);
        if(surveyId != null)
            attributes.put("survey", surveyId);
        if(comment != null)
            attributes.put("comment", comment);
        
        return attributes;
    }
    
}
