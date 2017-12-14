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
public class Reports {
    private static final String TABLE_NAME = "reports";
    
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
    
    public static ArrayList<Reports> getAllReports(){
        ArrayList<Reports> reports = new ArrayList<>();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ResultSet rs = databaseConnection.select(TABLE_NAME);
        Reports report;
        try {
            while(rs.next()){
                report = new Reports();
                report.setComment(rs.getString("comment"));
                report.setId(rs.getString("id"));
                report.setSurveyId(rs.getString("survey"));
                report.setUserId(rs.getString("user"));
                reports.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return reports;
    }
    
}
