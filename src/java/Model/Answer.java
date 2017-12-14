/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private static final String TABLE_NAME = "answer";

    public Answer() {
    }


    public Answer(String id, String userId, String answer, String questionId , String surveyId) {
        this.id = id;
        this.userId = userId;
        this.answer = answer;
        this.questionId = questionId;
        this.surveyId = surveyId;

    }
    
    public ArrayList<Answer> getAnswers(HashMap<String, String> attribbutes){
        ArrayList<Answer> answers = new ArrayList<>();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ResultSet rs = databaseConnection.select(TABLE_NAME, attribbutes);
        
        try {
            while(rs.next())
            {
                answers.add(new Answer(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Answer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
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
    
    public static int getAnonymosUsers(HashMap<String, String> attribbutes){
        ArrayList<Answer> answers = new Answer().getAnswers(attribbutes);
        int userCounter = 0;
        for(int i = 0; i < answers.size(); i++){
            if(answers.get(i).getUserId() == null){
                userCounter++;
            }
        }
        return userCounter;
    }
    
    public static int getKnownUsers(HashMap<String, String> attribbutes){
        ArrayList<Answer> answers = new Answer().getAnswers(attribbutes);
        int userCounter = 0;
        for(int i = 0; i < answers.size(); i++){
            if(answers.get(i).getUserId() != null){
                userCounter++;
            }
        }
        return userCounter;
    }
}
