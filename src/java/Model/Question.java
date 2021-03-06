/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Bassyouni
 */



public class Question {
    
    public static final String MCQ = "MCQ";
    public static final String FREEANSWER = "free answer";
    public static final String CHECKBOXES = "check boxes";
    
   private int id;
   private int surveyId;
   private String type;
   private String question;
   private String givenAnswers;
   private boolean isSuspended;
   public ArrayList<Answer> answers;

    public Question() {
        surveyId = -99;
    }

    public Question(int id, int survey, String type, String question, String givenAnswers, boolean isSuspended) {
        this.id = id;
        this.surveyId = survey;
        this.type = type;
        this.question = question;
        this.givenAnswers = givenAnswers;
        this.isSuspended = isSuspended;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSurveyiD() {
        return surveyId;
    }

    public void setSurveyiD(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(String givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    public boolean isIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(boolean isSuspended) {
        this.isSuspended = isSuspended;
    }
    
    public HashMap<String, String> getAttributes(){
        HashMap<String, String> attributes = new HashMap<>();
        if(this.surveyId != -99)
            attributes.put("survey", String.valueOf(this.surveyId));
        if(this.type != null)
            attributes.put("type", this.type);
        if(this.question != null)
            attributes.put("question", this.question);
        if(this.givenAnswers != null)
            attributes.put("given_answers", this.givenAnswers);
        
        return attributes;
    }
}
