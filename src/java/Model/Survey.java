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
public class Survey {
    
    private int id;
    private int ownerId;
    private String name;
    private boolean suspended;
    public ArrayList<Question> questions;
    
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    private static final String surveyTableName = "surveys";
    private static final String questionsTableName = "question";

    public Survey(){
        questions = new ArrayList<>();
    }
    public Survey(int id, int owner, String Name) {
        this.id = id;
        this.ownerId = owner;
        this.name = Name;
        questions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }
    
    public static Survey getSurvey(HashMap<String, String> selectAttributeMap){
        ResultSet rs = databaseConnection.select(surveyTableName, selectAttributeMap);
        Survey dummySurvey = null;
        try {
            while(rs.next()){
                dummySurvey = new Survey();
                dummySurvey.setId(rs.getInt("id"));
                dummySurvey.setOwnerId(rs.getInt("owner"));
                dummySurvey.setName(rs.getString("name"));
                dummySurvey.setSuspended(rs.getBoolean("isSuspended"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Survey.class.getName()).log(Level.SEVERE, null, ex);
            return dummySurvey;
        }
        return dummySurvey;
    }
    public static Survey getSurvey(int id, HashMap<String, String> selectAttributeMap){
        ResultSet rs = databaseConnection.select(surveyTableName, selectAttributeMap);
        Survey dummySurvey = null;
        try {
            while(rs.next()){
                dummySurvey = new Survey();
                dummySurvey.setId(rs.getInt("id"));
                dummySurvey.setOwnerId(rs.getInt("owner"));
                dummySurvey.setName(rs.getString("name"));
                dummySurvey.setSuspended(rs.getBoolean("isSuspended"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Survey.class.getName()).log(Level.SEVERE, null, ex);
            return dummySurvey;
        }
        return dummySurvey;
    }
    
    public static ArrayList<Survey> getAllSurveys(){
        ArrayList<Survey> surveys = new ArrayList<>();
        ResultSet rs = databaseConnection.select(surveyTableName);
        Survey dummySurvey;
        try {
            while(rs.next()){
                dummySurvey = new Survey();
                dummySurvey.setId(rs.getInt("id"));
                dummySurvey.setOwnerId(rs.getInt("owner"));
                dummySurvey.setName(rs.getString("name"));
                dummySurvey.setSuspended(rs.getBoolean("isSuspended"));
                surveys.add(dummySurvey);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Survey.class.getName()).log(Level.SEVERE, null, ex);
            return surveys;
        }
        
        return surveys;
    }

    @Override
    public String toString() {
        return String.format("SurveyName: %s, OwnerId: %d", this.name, this.ownerId);
                
    }
    
    public boolean getAllQuestions()
    {
        HashMap<String,String> selectAttributeMap = new HashMap<>();
        selectAttributeMap.put("survey", Integer.toString(this.id));
        ResultSet rs = databaseConnection.select(questionsTableName, selectAttributeMap);
        
        try {
            while(rs.next()){
                Question dummyQuestion = new Question();
                dummyQuestion.setId(rs.getInt("id"));
                dummyQuestion.setSurveyiD(rs.getInt("survey"));
                dummyQuestion.setType(rs.getString("type"));
                dummyQuestion.setQuestion(rs.getString("question"));
                dummyQuestion.setGivenAnswers(rs.getString("given_answers"));
                this.questions.add(dummyQuestion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Survey.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        System.out.println(this.questions);
        return true;
    }
            
    public int getSurveyCount()
    {
        ResultSet rs = databaseConnection.getSurveyCount(Integer.toString(this.id));
        int count = -1;
        if(rs != null)
            {
            try {
                while(rs.next())
                {
                    count = rs.getInt("user_counter");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Survey.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        return count;
    }
    
    public int getNumberOfReports()
    {
        HashMap<String,String> attributs = new HashMap<>();
        attributs.put("survey", String.valueOf(id));
        String reportTableName = "reports";
        ResultSet rs = databaseConnection.select(reportTableName, attributs);
        int count = 0;
        if(rs != null)
            {
            try {
                while(rs.next())
                {
                    count += 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Survey.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        return count;
    }
    
    
}
